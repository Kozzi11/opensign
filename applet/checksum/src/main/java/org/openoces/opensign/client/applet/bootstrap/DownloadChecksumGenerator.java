/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard
    Copyright 2011 Daniel Andersen


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

/* $Id: DownloadChecksumGenerator.java,v 1.4 2012/09/27 11:03:51 pakj Exp $ */

package org.openoces.opensign.client.applet.bootstrap;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * This class generates the java class containing the checksums
 *
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 * @author Preben Valeur  <prv@tdc.dk>
 * @author Daniel Andersen <daand@nets.eu>
 */
public class DownloadChecksumGenerator {
    private static String items[] = {"opensign", "capi", "cdcard", "pkcs12", "oces", "jsse", "attachment", "cryptoki", "pdf"};
    private Map<String, Integer> itemSizes = new Hashtable<String, Integer>();

    private DownloadChecksumGenerator(String fname, String pluginDir) throws IOException, NoSuchAlgorithmException {

        File f = new File(fname);
        FileOutputStream os = new FileOutputStream(f);

        os.write(generatePrologue().getBytes());

        for (String item : items) {
            os.write(generateItemMethod(item, pluginDir).getBytes());
        }

        for (String item : items) {
            getSize(item + ".zip", pluginDir);
            os.write(generateItemSizeMethod(item).getBytes());
        }
        os.write(generateCopyArray().getBytes());
        os.write(generateEpilogue().getBytes());
        os.close();
    }

    private String generateItemMethod(String pluginName, String pluginDir) throws NoSuchAlgorithmException, IOException {
        StringBuffer sb = new StringBuffer();

        String variableName = pluginName + "Checksum";
        String methodName = "get" + pluginName + "Checksum";

        sb.append("private static final byte[] ").append(variableName).append(" = ");
        sb.append(getChecksum(pluginName + ".zip", pluginDir));
        sb.append(";\n");
        sb.append("public static byte[] ").append(methodName).append("() { return copyArray(").append(variableName).append("); }\n");

        return sb.toString();
    }

    private int getSize(String filename, java.lang.String path) {
        int size = 0;
        try {
            File f = new File(path + File.separator + filename);
            FileInputStream fis = new FileInputStream(f);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            byte buf[] = new byte[64 * 1024];
            int c;
            while ((c = fis.read(buf, 0, 64 * 1024)) > -1) {
                bos.write(buf, 0, c);
                size += c;
            }
        } catch (IOException e) {
            //do nothing
        }
        String itemName = filename.substring(0, filename.indexOf("."));
        itemSizes.put(itemName, size);
        return size;
    }

    /**
     * Calculates the checksum and as a side-effect also the total data size which are stored in itemSizes
     *
     * @param filename
     * @param path
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    private String getChecksum(String filename, java.lang.String path) throws NoSuchAlgorithmException, IOException {
        StringBuffer sb = new StringBuffer();
        InputStream fis;
        byte[] digest;

        // find the item-name again:
        String itemName = filename.substring(0, filename.indexOf("."));

        sb.append("{");

        try {
            File f = new File(path + File.separator + filename);
            fis = new FileInputStream(f);
            Sha1ChecksummedInputStream checksummedStream = new Sha1ChecksummedInputStream(fis, new Sha1Checksum());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            byte buf[] = new byte[64 * 1024];
            int c;

            while ((c = checksummedStream.read(buf, 0, 64 * 1024)) > -1) {
                bos.write(buf, 0, c);
            }

            ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(bos.toByteArray()));
            ZipEntry entry;

            int totalDataSize = 0;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    continue;
                }
                int count;
                int datasize = (int) entry.getSize();
                byte[] data;
                int pos = 0;
                if (datasize != -1) {
                    data = new byte[datasize];
                    while ((count = zis.read(data, pos, data.length - pos)) != -1) {
                        pos += count;
                        if (pos == datasize) {
                            break;
                        }
                    }
                } else { // datasize == -1
                    while ((count = zis.read(buf, pos, buf.length - pos)) != -1) {
                        pos += count;
                        if (pos == datasize) {
                            break;
                        }
                    }
                }
                totalDataSize += entry.getCompressedSize();
            }
            zis.close();

            itemSizes.put(itemName, totalDataSize);

            digest = checksummedStream.getCalculatedChecksum().getSha1Value();

        } catch (FileNotFoundException e) {
            digest = new byte[20];
            itemSizes.put(itemName, 0);
        }

        for (byte aDigest : digest) {
            sb.append("(byte) 0x").append(HexDump.tohex(aDigest, 2).toLowerCase()).append(",");
        }

        sb.append("}");
        return sb.toString();
    }

    /**
     * To be called after generateItemMethod - when itemSizes are updated
     *
     * @param pluginName
     * @return source code of the method returning the data size of the item
     */
    private String generateItemSizeMethod(String pluginName) {
        StringBuffer sb = new StringBuffer();

        String methodName = "get" + pluginName + "DataSize";
        int itemDateSize = itemSizes.get(pluginName);
        sb.append("public static int ").append(methodName).append("() { return ").append(itemDateSize).append("; }\n");

        return sb.toString();
    }

    private String generatePrologue() {
        StringBuffer sb = new StringBuffer();
        sb.append("package org.openoces.opensign.client.applet.bootstrap;\n\n");
        sb.append("public class DownloadChecksums {\n");
        sb.append("protected DownloadChecksums() {}\n");
        return sb.toString();
    }

    private String generateCopyArray() {
        StringBuffer sb = new StringBuffer();
        sb.append("private static byte [] copyArray(byte [] src){\n");
        sb.append("byte [] dest = new byte[src.length];\n");
        sb.append("System.arraycopy(src, 0, dest, 0, src.length);\n");
        sb.append("return dest;\n");
        sb.append("}\n");
        return sb.toString();
    }

    private String generateEpilogue() {
        return "}";
    }

    public static void main(String[] args) {
        try {
            new DownloadChecksumGenerator(args[0], args[1]);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}