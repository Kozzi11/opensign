/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard


    This file is part of OpenSign.

    OpenSign is free software; you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation; either version 2.1 of the License, or
    (at your option) any later version.

    OpenSign is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with OpenOcesAPI; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA


    Note to developers:
    If you add code to this file, please take a minute to add an additional
    copyright statement above and an additional
    @author statement below.

 * @author Preben Valeur <prv@tdc.dk>
 * @author Jens Bo Friis <jbf@it-practice.dk>
 * @author Mads Jensen <mjn@trifork.com>
 * @author Jeppe Burchhardt <Jeppe.Burchhardt@Cryptomathic.com>
 * @author Ole Friis Østergaard <ofo@trifork.com>
 */


package org.openoces.opensign.client.applet.bootstrap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

final class ChecksummingClassLoader extends SecureClassLoader {

    private Map<String, Class> classes = new HashMap<String, Class>();
    private PermissionCollection extraPerms; // Extra permissions set for this item
    private PermissionCollection totalPerms; // sum of super.getPermissions() and extraPerm cached for performance.
    // it takes some extra time it looks like - AND it generates 404's and is apparently not needed anymore:
    // BUT it is not necessary with the new classloader architecture without URLClassLoader as parent (we belive).


    private ClassLoader parent;

    private ProtectionDomain pd = null;

    private boolean initialized = false;

    ChecksummingClassLoader(DownloadItem item, ClassLoader parent, ProtectionDomain pd) throws IOException {
        super(parent);

        this.parent = parent;
        this.pd = pd;

        if (!verifyAndLoad(item)) {
            throw new IOException("could not load plugins");
        }
        initialized = true;
    }

    /**
     * Checks if there is a zip-file in cache of the right name with the correct
     * checkSum If not: gets it from the url and puts it into cache. Open it and
     * scans through to check checksum. If not ok: deletes it and fail,
     */
    private boolean verifyAndLoad(DownloadItem item) throws IOException {
        boolean loaded = false;
        Map<String, byte[]> cache = new HashMap<String, byte[]>();

        if (item.cachedExists()) {
            loaded = loadPlugin(item.getAsStream(), item, cache);
        }
        if (!loaded) {
            item.download();
            loaded = loadPlugin(item.getAsStream(), item, cache);
        }
        if (!loaded) {
            item.download();
            loaded = loadPlugin(item.getAsStream(), item, cache);
        }
        if (!loaded) {
            FileLog.error("Failed to load plugin " + item.getAlias());
            return false;
        }

        cachedEntries = cache;
        return true;
    }

    private boolean loadPlugin(InputStream fis, DownloadItem item, Map<String, byte[]> cache) throws IOException {
        Sha1ChecksummedInputStream checksummedStream;
        try {
            checksummedStream = new Sha1ChecksummedInputStream(fis, item.getChecksum());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


        byte[] ba = new byte[16 * 1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int c;
        while ((c = checksummedStream.read(ba, 0, 16 * 1024)) > -1) {
            baos.write(ba, 0, c);
        }

        byte[] bytes = baos.toByteArray();
        baos.close();

        try {
            if (!Configuration.doChecksumPlugins())
                FileLog.info("ChecksummingClassLoader: checksumming of plugins disabled. This is dangerous !!!");
            if (!Configuration.doChecksumPlugins() || checksummedStream.hasChecksumValid()) {


                ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(bytes));
                ZipEntry entry;

                while ((entry = zis.getNextEntry()) != null) {
                    if (entry.isDirectory()) {
                        continue;
                    }
                    int count;
                    baos = new ByteArrayOutputStream();
                    while ((count = zis.read(ba)) != -1) {
                        baos.write(ba, 0, count);
                    }

                    byte[] data = baos.toByteArray();
                    baos.close();

                    String name = entry.getName();
                    cache.put(name, data);

                }
                zis.close();

                return true;
            } else {
                FileLog.error("Invalid checksum occurred " + item.getAlias());
                return false;
            }
        } catch (Exception e) {
            FileLog.error("An exception occured while verifying the checksum", e);
        }
        return false;
    }

    private Map<String, byte[]> cachedEntries = new HashMap<String, byte[]>();

    /**
     * load verified classbytes
     */
    public final byte[] loadClassBytes(String className) {
        if (!initialized)
            throw new SecurityException("not initialized");
        className = className.replace('.', '/') + ".class";
        return cachedEntries.get(className);
    }

    public final InputStream getResourceAsStream(String name) {
        if (!initialized)
            throw new SecurityException("not initialized");
        byte[] ba = loadResource(name);
        if (ba == null)
            return null;
        return new ByteArrayInputStream(ba);
    }

    public final URL getResource(String name) {
        if (!initialized)
            throw new SecurityException("not initialized");
        return null;
    }

    private byte[] loadResource(String name) {
        byte[] bytes = cachedEntries.get(name);
        if (bytes == null && name.startsWith("/")) {
            name = name.substring(1);
            bytes = cachedEntries.get(name);
        }
        return bytes;
    }

    final public Class loadClass(String className) throws ClassNotFoundException {
        if (!initialized)
            throw new SecurityException("not initialized");
        return (loadClass(className, true));
    }

    // ---------- Abstract Implementation ---------------------
    final public synchronized Class loadClass(String className, boolean resolveIt) throws ClassNotFoundException {
        if (!initialized)
            throw new SecurityException("not initialized");

        // ----- Check our local cache of classes
        Class result = classes.get(className);
        if (result != null) {
            return result;
        }

        result = findLoadedClass(className);
        // check is result classloader is "dk.pbs" or "dk.danid" and loaded by "this"
        if (result != null) {
            return result;
        }
        // always check to see if the class exists in the ZIP class loader.
        if (className.startsWith("org.openoces")
                || className.startsWith("iaik.pkcs.pkcs11")
                || className.startsWith("org.jpedal")
                || className.startsWith("com.idrsolutions.pdf")
                || className.startsWith("com.l2fprod.common")) {
            byte[] classBytes = loadClassBytes(className);

//			// ----- Define it (parse the class file)
//			// check parent classloader (the applet/application loader)
            if (parent != null && classBytes == null) {
                try {
                    return parent.loadClass(className);
                } catch (ClassNotFoundException e) {
                    throw e;
                }
            }
            if (classBytes == null)
                throw new ClassFormatError("could not load class " + className);

            result = defineClass(className, classBytes, 0, classBytes.length, pd);

            if (result == null) {
                throw new ClassFormatError("could not define class " + className);
            }

            // ----- Resolve if necessary
            if (resolveIt)
                resolveClass(result);

            // Done
            classes.put(className, result);
            return result;

        } else {
            try {
                result = super.findSystemClass(className);

                return result;
            } catch (ClassNotFoundException e) {
                throw new ClassFormatError("could not load class " + className);
            }
        }
    }

    protected PermissionCollection getPermissions(CodeSource codesource) {
        if (totalPerms == null) {
            totalPerms = super.getPermissions(codesource);
            if (extraPerms != null) {
                Enumeration permEnum = extraPerms.elements();
                while (permEnum.hasMoreElements()) {
                    totalPerms.add((Permission) permEnum.nextElement());
                }
            }
            extraPerms = null; // not needed anymore
        }
        return totalPerms;
    }

    public final Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
