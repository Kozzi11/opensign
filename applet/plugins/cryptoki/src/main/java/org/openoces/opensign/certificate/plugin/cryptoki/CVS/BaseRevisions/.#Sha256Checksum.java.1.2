/*
    Copyright 2011 Mads Jensen
    Copyright 2011 Jeppe Burchhardt
    Copyright 2011 Ole Friis Østergaard
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
	
 * @author Mads Jensen <mjn@trifork.com>
 * @author Jeppe Burchhardt <Jeppe.Burchhardt@Cryptomathic.com>
 * @author Ole Friis Østergaard <ofo@trifork.com>
 * @author Daniel Andersen <daand@nets.eu>
*/

package org.openoces.opensign.certificate.plugin.cryptoki;

import org.openoces.opensign.utils.IOUtils;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256Checksum {
    private byte[] value;
    private MessageDigest sha;

    public Sha256Checksum(String filename) throws NoSuchAlgorithmException, IOException {
        sha = MessageDigest.getInstance("SHA-256");
        File file = new File(filename);
        InputStream in = new FileInputStream(file);
        try {
            byte[] data = new byte[1024];
            int count;
            while ((count = in.read(data)) > 0) {
                update(data, 0, count);
            }
        } finally {
            IOUtils.close(in);
        }
    }

    private void update(byte[] data,int offset,int count) {
        sha.update(data, offset, count);
    }

    public final byte[] getShaValue() {
        if (value == null) {
            digest();
        }
        return value.clone();
    }

    private void digest() {
        value = sha.digest();
    }

    public final boolean matches(String checksum) {
        return convertToHex().equals(checksum.toLowerCase());
    }

    private String convertToHex() {
        StringBuffer buf = new StringBuffer();
        byte[] data = getShaValue();
        for (int i = 0; i < data.length; i++) {
            int b = data[i];
            int halfbyte = (b >>> 4) & 0x0F;
            int twoHalfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = b & 0x0F;
            } while (twoHalfs++ < 1);
        }
        return buf.toString();
    }

    public final String toString(){
        return convertToHex();
    }
}
