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
*/

/* $Id: SunTruststoreFile.java,v 1.2 2012/02/28 08:21:03 pakj Exp $ */

package org.openoces.opensign.certificate.support.suncrypto;

import org.openoces.opensign.client.applet.OS;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.utils.HexDump;
import org.openoces.opensign.utils.IOUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class represents the ssl trust store used for ssl communications
 *
 * @author Preben Valeur  <prv@tdc.dk>
 * @author Daniel Andersen  <dani_ande@yahoo.dk>
 */
public final class SunTruststoreFile {

    private static String OCES_INSTALL_DIRECTORY = ".oces";
    private static String OPENSIGN_INSTALL_DIRECTORY = "opensign";
    private static String TRUSTSTORE_FILENAME = "truststore.jks";
    private static final String TRUSTSTORE_PASSWORD = "default";

    private static final byte[] DIGESTVALUE = {(byte) 0x31, (byte) 0xdf, (byte) 0xd9, (byte) 0x7a, (byte) 0xec, (byte) 0xd0, (byte) 0x6d, (byte) 0x9c,
            (byte) 0xcb, (byte) 0x23, (byte) 0xd3, (byte) 0x13, (byte) 0x41, (byte) 0xbb, (byte) 0x38, (byte) 0x54,
            (byte) 0x47, (byte) 0xd3, (byte) 0x8c, (byte) 0x4a};
    private String baseUrl;

    public SunTruststoreFile(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private File getInstallDirectory() {
        String relativePath = OCES_INSTALL_DIRECTORY + File.separator + OPENSIGN_INSTALL_DIRECTORY;
        String cacheDir = OS.getCacheHome();
        return new File(cacheDir + File.separator + relativePath);
    }

    private File getAbsoluteFile() {
        return new File(getInstallDirectory(), TRUSTSTORE_FILENAME);
    }

    public boolean isValid() {
        FileInputStream ios = null;
        MessageDigest sha;

        try {
            byte[] data;
            byte[] hash;
            int readBytes;

            data = new byte[256];

            sha = MessageDigest.getInstance("SHA-1");
            ios = new FileInputStream(getAbsoluteFile());

            while ((readBytes = ios.read(data)) != -1) {
                sha.update(data, 0, readBytes);
            }
            ios.close();
            hash = sha.digest();

            if (hash.length != DIGESTVALUE.length) {
                FileLog.warn("Calculated and stored digest have different lengths");
                return false;
            }

            for (int i = 0; i < hash.length; i++) {
                byte b = hash[i];
                byte c = DIGESTVALUE[i];
                if (b != c) {
                    FileLog.warn("Calculated and stored digest do not match at position " + i);
                    FileLog.warn("Calculated hash:\n" + HexDump.xdump(hash));
                    FileLog.warn("Stored hash:\n" + HexDump.xdump(DIGESTVALUE));
                    return false;
                }
            }
            return true;

        } catch (FileNotFoundException e) {
            FileLog.warn("Truststore to be verified not found");
        } catch (IOException e) {
            FileLog.warn("IO error occured while verifying truststore integrity");
        } catch (NoSuchAlgorithmException e) {
            FileLog.warn("Missing algorithm while verifying truststore integrity");
        } catch (NullPointerException e) {
            FileLog.warn("Got a null pointer exception");
        }
        return false;
    }

    public boolean download() {
        InputStream fis = null;
        FileOutputStream fos = null;
        try {
            try {
                URLConnection urlCon = new URL(baseUrl + "/" + TRUSTSTORE_FILENAME).openConnection();
                urlCon.connect();
                fis = urlCon.getInputStream();

            } catch (IOException e) {
                FileLog.warn("failed to open url connection");
                return false;
            }

            try {

                File f = this.getAbsoluteFile();
                /* FIXME: check that the directory exists? */

                fos = new FileOutputStream(f);
                int ch;

                while ((ch = fis.read()) != -1) {
                    fos.write(ch);
                }

            } catch (IOException e) {
                FileLog.error("An IO exception occured");
                return false;
            }
            return true;
        } finally {
            IOUtils.close(fis);
            IOUtils.close(fos);
        }
    }

    public void useAsTruststore() {
        FileLog.debug("using truststore password: " + TRUSTSTORE_PASSWORD);
        FileLog.debug("using truststore located at: " + this.getAbsoluteFile().toString());
        System.setProperty("javax.net.ssl.trustStorePassword", TRUSTSTORE_PASSWORD);
        System.setProperty("javax.net.ssl.trustStore", this.getAbsoluteFile().toString());
    }

    public InputStream getTrustStoreInputStream() {
        try {
            return new FileInputStream(getAbsoluteFile());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTrustStorePassword() {
        return TRUSTSTORE_PASSWORD;
    }
}
