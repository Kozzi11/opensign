/*
	Copyright 2011 Daniel Andersen
	Copyright 2012 Anders M. Hansen
	

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

 * @author Daniel Andersen <daand@nets.eu>
 * @author Anders M. Hansen <consult@ajstemp.dk>
*/

package org.openoces.opensign.certificate.plugin.cryptoki;

import org.openoces.opensign.client.applet.OS;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.utils.IOUtils;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class NativeLibraryInstaller {
    private static enum Bits {BITS_32, BITS_64}
    private static Bits platformBits = Bits.BITS_32;

    private static HashMap<String, String> libraryName = new HashMap<String, String>();
    private static HashMap<String, String> librarySha256 = new HashMap<String, String>();

    static {
        libraryName.put("win32", "pkcs11wrapper32.dll");
        librarySha256.put("win32", "e969107e96fb707dc6ffa1c9160180afdae7530175fa28843352659019976c3c");

        libraryName.put("win64", "pkcs11wrapper64.dll");
        librarySha256.put("win64", "06c3ad7ead5ccae73a5be0569917dee715861a3525bde5a783a4015588c6152f");

        libraryName.put("linux_x86", "pkcs11wrapper32.so");
        librarySha256.put("linux_x86", "f2e17dd76d75a8940245cdfe5ec4be0397a38ccdcb6b6b8d144ee51649fda083");

        libraryName.put("linux", "pkcs11wrapper64.so");
        librarySha256.put("linux", "16d62491f9e9a76d301315f6bbf0e0c3d731ca1f06d275aee25ff956fdf81cd3");

        libraryName.put("macosx_universal", "pkcs11wrapper.jnilib");
        librarySha256.put("macosx_universal", "1d95895d3cd1ef420220469a9c61867a32637a12bd88100d3d30c7c150f93dd9");

        libraryName.put("solaris_sparc", "pkcs11wrapper.so");
        librarySha256.put("solaris_sparc", "7c5c713d6724d84ec02074fdc89ecfc0a6d90138c1841a51c0fe2c9d3a1e087a");

        libraryName.put("solaris_sparcv9", "pkcs11wrapper.so");
        librarySha256.put("solaris_sparcv9", "1d9420b0ee8ee10bc6e6411e732fa64697fc15c017ee4a2a35742d2cf28bcb77");
    }

    public static void installLibrary() {
        String platform = getPlatformName();
        String libraryFilename = libraryName.get(platform);
        String destinationDirectory = getDestinationDirectory();

        InputStream fileInputStream = null;
        BufferedInputStream bufferedFileInputStream = null;
        BufferedOutputStream bufferedFileOutputStream = null;

                
        try {
            File destinationFile = new File(destinationDirectory, libraryFilename);
            if (!destinationFile.exists()) {
                createDestinationDirectory();
                fileInputStream = NativeLibraryInstaller.class.getClassLoader().getResourceAsStream("resources/" + platform + "/" + libraryFilename);

                bufferedFileInputStream = new BufferedInputStream(fileInputStream);
                bufferedFileOutputStream = new BufferedOutputStream(new FileOutputStream(destinationFile));

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = bufferedFileInputStream.read(buffer, 0, buffer.length)) != -1) {
                    bufferedFileOutputStream.write(buffer, 0, bytesRead);
                }
                bufferedFileOutputStream.flush();
            }
        } catch (Exception e) {
            FileLog.warn("Could not install native library - ignoring... [" + e.getMessage() + "]");
        } finally {
            IOUtils.close(bufferedFileInputStream);
            IOUtils.close(bufferedFileOutputStream);
            IOUtils.close(fileInputStream);
        }
    }

    public static void ensureCorrectChecksum(String filename) throws IOException, NoSuchAlgorithmException {
        Sha256Checksum calculatedChecksum = new Sha256Checksum(filename);
        String correctChecksum = librarySha256.get(getPlatformName());
        if (!calculatedChecksum.matches(correctChecksum)) {
            throw new RuntimeException("Native library checksum mismatch. Should be " + correctChecksum + " but was " + calculatedChecksum);
        }
    }

    public static String getPathToLibrary() {
        String platform = getPlatformName();
        String libraryFilename = libraryName.get(platform);
        String destinationDirectory = getDestinationDirectory();
        return destinationDirectory + System.getProperty("file.separator") + libraryFilename;
    }

    // There is no way of finding out the architecture of the underlying OS - the "os.arch"-property just returns the JRE version
    // So, unfortunately we need to make trial and error
    public static void set64BitPlatform() {
        platformBits = Bits.BITS_64;
    }

    public static void detectPlatform() {
        platformBits = OS.is64BitJre() ? Bits.BITS_64 : Bits.BITS_32;
    }

    private static String getPlatformName() {
        if (OS.isOSWindows()) {
            if (platformBits == Bits.BITS_64) {
                return "win64";
            } else {
                return "win32";
            }
        } else if (OS.isOSLinux()) {
            if (platformBits == Bits.BITS_64) {
                return "linux";
            } else {
                return "linux_x86";
            }
        } else if (OS.isOSMac()) {
            return "macosx_universal";
        } else { // TODO! Add Solaris
            return null;
        }
    }

    private static void createDestinationDirectory() {
        File file = new File(getDestinationDirectory());
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private static String getDestinationDirectory() {
        return OS.getUserHome() + File.separator + ".oces" + File.separator + "opensign" + File.separator + "lib";
    }
}