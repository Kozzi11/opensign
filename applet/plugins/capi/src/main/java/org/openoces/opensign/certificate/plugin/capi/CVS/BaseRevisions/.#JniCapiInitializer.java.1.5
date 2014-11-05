/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard
    Copyright 2010 Paw F. Kjeldgaard
    Copyright 2013 Anders M. Hansen


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

 * @author Mads Jensen <mjn@trifork.com>
 * @author Jeppe Burchhardt <Jeppe.Burchhardt@Cryptomathic.com>
 * @author Ole Friis Østergaard <ofo@trifork.com>
 * @author Anders M. Hansen <consult@ajstemp.dk>
*/

package org.openoces.opensign.certificate.plugin.capi;

import org.openoces.opensign.client.applet.OS;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.utils.HexDump;
import org.openoces.opensign.utils.IOUtils;
import org.openoces.opensign.wrappers.microsoftcryptoapi.MicrosoftCryptoApi;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

class JniCapiInitializer implements CapiInitializer {
    private String baseUrl;
    private boolean checkLibLoaded = false;

    @Override
    public boolean initialize(String baseUrl) {
        this.baseUrl = baseUrl;
        if (!OS.isOSWindows()) {
            // only Windows is supported
            return false;
        }

        boolean ok = install(OS_WIN32);
        if (!ok) {
            FileLog.error("Unable to install Microsoft cryptoapi wrapper library");
        }
        return ok;
    }
    // taken from Installer


    private static final int OS_WIN32 = 0;
    private static final int OS_LINUX = 1;

    private final static String OCES_INSTALL_DIRECTORY = ".oces";
    private final static String OPENSIGN_INSTALL_DIRECTORY = "opensign";
    private final static String LIBRARY_INSTALL_DIRECTORY = "lib";

    private static final String LIBRARYNAME_WIN32 = "MicrosoftCryptoApi_0_6.dll";
    private static final String LIBRARYNAME_WINx64 = "MicrosoftCryptoApi_x64_0_6.dll";

    /* the digests below can be calculated using org.openoces.opensign.certificate.plugin.capi.LibraryHashUtil.main()*/
    private static final byte[] LIBRARY_WIN32_DIGESTVALUE = {(byte)0x8F, (byte)0x63, (byte)0xDB, (byte)0x0B, (byte)0xEA, (byte)0xDF, (byte)0x0A, (byte)0x08, (byte)0xCA, (byte)0xBD, (byte)0x29, (byte)0x66, (byte)0x0F, (byte)0x7A, (byte)0xB5, (byte)0x68, (byte)0x13, (byte)0x22, (byte)0x61, (byte)0xF4};
    private static final byte[] LIBRARY_WINx64_DIGESTVALUE = {(byte)0x55, (byte)0x63, (byte)0x34, (byte)0xD7, (byte)0x52, (byte)0x66, (byte)0x31, (byte)0x49, (byte)0x95, (byte)0xAA, (byte)0x33, (byte)0xCF, (byte)0x65, (byte)0x2C, (byte)0xCE, (byte)0x3A, (byte)0xE3, (byte)0x68, (byte)0x73, (byte)0x09};
  
    // Create runtime dir.
    private void createRuntimeDir(File localDirectory) {
        Random random = new Random();
        String tempDirName = "runtime-" + (int) (random.nextDouble() * 10000000);
        runtimeDirectory = new File(localDirectory, tempDirName);

        runtimeDirectory.mkdirs();
    }

    @Override
    public void cleanup() {
        // delete what can be deleted - maybe not the dll - but try:
        if (runtimeDirectoryDll != null) {
            if (runtimeDirectoryDll.delete() && runtimeDirectory.delete()) {
                FileLog.debug("deleted dll and directory");
            } else {
                FileLog.debug("Couldn't clean up - could try on some of the other files/dirs");
                String[] otherDirs = new File(runtimeDirectory.getParent()).list(new FilenameFilter() {
                    public boolean accept(File f, String s) {
                        return true;
                    }
                });
                for (String dirName : otherDirs) {
                    File dir = new File(runtimeDirectory.getParent(), dirName);
                    if (!dir.isDirectory()) continue;
                    if (new File(dir, getLibraryName()).delete() && dir.delete()) {
                        FileLog.debug("succes in deleting dir " + dir);
                    } else {
                        FileLog.debug("failed in deleting dir " + dir);
                    }
                }
            }
            runtimeDirectoryDll = null;
        }
    }

    private File runtimeDirectory;
    private File runtimeDirectoryDll;

    private File getRuntimeDirectory() {
        return runtimeDirectory;
    }

    /**
     * Creates a copy of the file that can be used runtime and returns the path to the file.
     *
     * @param installedFile
     * @return
     */
    private File createRuntimeCopy(File installedFile) {
        File src = installedFile;

        int index = installedFile.toString().lastIndexOf('.');
        if (index == -1)
            throw new IllegalArgumentException("");

        File dest = new File(getRuntimeDirectory(), installedFile.getName());

        if (!src.exists())
            throw new IllegalStateException("This method requires that the library '" + installedFile.toString() + "' is installed. ");

        FileInputStream is = null;
        try {
            try {
                is = new FileInputStream(src);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            saveToFile(is, dest);
        } finally {
            IOUtils.close(is);
        }

        return dest;
    }

    void saveToFile(InputStream data, File file) {
        if (data == null)
            throw new IllegalArgumentException("Missing input stream.");

        OutputStream os;
        try {
            os = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int bufsize = 10000;
        byte[] buf = new byte[bufsize];
        int count;
        try {
            while ((count = data.read(buf)) > 0) {
                os.write(buf, 0, count);
            }
        } catch (IOException e1) {
            FileLog.debug(e1.getMessage(), e1);
        } finally {
            IOUtils.close(data);
            IOUtils.close(os);
        }
    }

    public boolean install(int os) {
        String relativePath;

        relativePath = OCES_INSTALL_DIRECTORY + File.separator + OPENSIGN_INSTALL_DIRECTORY +
                File.separator + LIBRARY_INSTALL_DIRECTORY;

        File installDirectory = new File(OS.getCacheHome() + File.separator + relativePath);

        if (!installDirectory.exists()) {
            FileLog.warn("directories for the microsoft cryptoapi wrapper dll do not exist");
            if (!installDirectory.mkdirs()) {
                FileLog.warn("Unable to create directories for microsoft cryptoapi wrapper dll");
                return false;
            }
        } else {
            FileLog.warn("directories for the microsoft cryptoapi wrapper dll do exist");
        }

        return install(os, installDirectory);
    }

    public boolean install(int os, File location) {

        File f = getLibraryFile(os, location);

        if (f == null) {
            FileLog.warn("Unable to generate filename for the microsoft cryptoapi wrapper dll");
            return false;
        }

        if (!f.exists()) {
            if (!copyLibrary(os, f)) {
                FileLog.warn("Unable to copy library");
                return false;
            }
        }

        if (!verifyLibrary(f)) {
            FileLog.warn("Library verification failed. Library may have been tampered with. Installing fresh library.");

            /* try installing the library again */
            if (!copyLibrary(os, f)) {
                FileLog.warn("Unable to copy library");
                return false;
            }

            if (!verifyLibrary(f)) {
                deleteFile(f);
                FileLog.warn("Second library verification failed. It may have been tampered with. Bailing out.");
                return false;
            }
        }

        if (!loadLibrary(os, f)) {
            FileLog.warn("Unable to load microsoft cryptoapi wrapper dll");
            return false;
        }

        MicrosoftCryptoApi mscapi = new MicrosoftCryptoApi();
        byte[][][] rs = mscapi.getCertificatesInSystemStore("My");

        FileLog.info("Returned " + rs.length + " certificates from the microsoft cryptoapi");
        FileLog.info("Microsoft cryptoapi wrapper dll version: " + mscapi.getMajorVersion() + "." + mscapi.getMinorVersion());

        return true;
    }

    private boolean loadLibrary(int os, File f) {
        if (os == OS_WIN32) {
            return loadLibraryOnWindowsPlatform(f);
        } else if (os == OS_LINUX) {
            FileLog.info("Microsoft cryptoapi wrapper dll can not be loaded on the Linux platform");
            return false;
        } else {
            FileLog.info("Microsoft cryptoapi wrapper dll can not be loaded on an unknown platform");
            return false;
        }
    }

    private File getLibraryFile(int os, File location) {
        if (os == OS_WIN32) {
            return new File(location, getLibraryName());
        } else {
            return null;
        }
    }

    private boolean copyLibrary(int os, File f) {
        return os == OS_WIN32 && copyLibraryOnWindowsPlatform(f);
    }

    private boolean loadLibraryOnWindowsPlatform(File f) {
        try {
            if (!isLoaded()) {
                System.load(f.toString());
            }
/* FIXME: How can we test whether the native library is working?
      One test can be calling some trivial native method
*/
        } catch (UnsatisfiedLinkError e) {
            // copy it somewhere and try again!
            createRuntimeDir(new File(f.getParent()));
            runtimeDirectoryDll = createRuntimeCopy(f);
            System.load(runtimeDirectoryDll.toString());
        }
        return true;
    }

    private boolean verifyLibrary(File f) {

        FileInputStream ios = null;
        MessageDigest sha;

        try {
            byte[] data;
            byte[] hash;
            int readBytes;

            data = new byte[256];

            sha = MessageDigest.getInstance("SHA-1");
            ios = new FileInputStream(f);

            while ((readBytes = ios.read(data)) != -1) {
                sha.update(data, 0, readBytes);
            }
            hash = sha.digest();

            byte[] libraryDigestValue = getLibraryDigestValue();
            if (hash.length != libraryDigestValue.length) {
                FileLog.warn("Calculated and stored digest have different lengths");
                return false;
            }

            for (int i = 0; i < hash.length; i++) {
                byte b = hash[i];
                byte c = libraryDigestValue[i];
                if (b != c) {
                    FileLog.warn("Calculated and stored digest do not match at position " + i);
                    FileLog.warn("Calculated hash:\n" + HexDump.xdump(hash));
                    FileLog.warn("Stored hash:\n" + HexDump.xdump(libraryDigestValue));
                    return false;
                }
            }
            return true;

        } catch (FileNotFoundException e) {
            FileLog.warn("Library to be verified not found");
        } catch (IOException e) {
            FileLog.warn("IO error occured while verifying library integrity");
        } catch (NoSuchAlgorithmException e) {
            FileLog.warn("Missing algorithm while verifying library integrity");
        } finally {
            IOUtils.close(ios);
        }
        return false;
    }

    private boolean copyLibraryOnWindowsPlatform(File f) {
        URLConnection urlCon;
        InputStream fis;

        try {
            urlCon = new URL(baseUrl + "/" + getLibraryName()).openConnection();
            urlCon.connect();
            fis = urlCon.getInputStream();
        } catch (IOException e) {
            FileLog.warn("failed to open url connection");
            return false;
        }

        FileOutputStream fos = null;
        try {


/* FIXME: We should restrict the permissions set on the file
*/
            fos = new FileOutputStream(f);
            int ch;

            while ((ch = fis.read()) != -1) {
                fos.write(ch);
            }

        } catch (IOException e) {
            FileLog.error(e.getMessage(), e);
            return false;
        } finally {
            IOUtils.close(fis);
            IOUtils.close(fos);
        }
        return true;
    }

    private boolean isLoaded() {
        boolean isLoaded = false;
        try {
            MicrosoftCryptoApi capi = new MicrosoftCryptoApi();
            capi.getMajorVersion();
            isLoaded = true;
        } catch (UnsatisfiedLinkError e) {
        	if(checkLibLoaded) {
        		FileLog.error("mscapi wrapper dll has not already been loaded", e);
        	} else {
        		FileLog.debug("mscapi wrapper dll loaded check", e);
        		checkLibLoaded=true;
        	}
        } catch (Exception e) {
            FileLog.warn("Unexpected exception occured while checking if the mscapi wrapper is loaded: " + e.toString());
        }

        return isLoaded;
    }

    private String getLibraryName() {
        return OS.is64BitJre() ? LIBRARYNAME_WINx64 : LIBRARYNAME_WIN32;
    }

    private byte[] getLibraryDigestValue() {
        return OS.is64BitJre() ? LIBRARY_WINx64_DIGESTVALUE : LIBRARY_WIN32_DIGESTVALUE;
    }

    private void deleteFile(File file) {
        try {
            if (!file.delete()) FileLog.warn("Not able to delete " + file.getPath());
        } catch (Exception e) {
            FileLog.warn(e.getMessage(), e);
        }
    }
}
