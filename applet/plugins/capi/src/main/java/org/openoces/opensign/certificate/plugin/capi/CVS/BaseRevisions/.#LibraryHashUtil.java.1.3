/*
	Copyright 2010 DanID A/S

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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LibraryHashUtil {

    private static final String folder="../../../jni/microsoftcryptoapi";
    private static final String lib= "MicrosoftCryptoApi_0_6.dll";
    private static final String x64lib= "MicrosoftCryptoApi_x64_0_6.dll";
    private static final String libHashVariableName ="LIBRARY_WIN32_DIGESTVALUE";
    private static final String x64libHashVariableName ="LIBRARY_WINx64_DIGESTVALUE";

    protected LibraryHashUtil() {}

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        byte[] hash = calculateHash(folder+File.separatorChar+lib);
        writeOutHashForCutAndPaste(libHashVariableName, hash);

        hash = calculateHash(folder+File.separatorChar+x64lib);
        writeOutHashForCutAndPaste(x64libHashVariableName, hash);
    }

    private static void writeOutHashForCutAndPaste(String libHashVariableName, byte[] hash) {
        StringBuilder sb = new StringBuilder();
        sb.append("    private static final byte[] ");
        sb.append(libHashVariableName);
        sb.append(" = {");
        for( int i = 0; i < hash.length; ++i){
            sb.append("(byte)");
            appendByte(sb, hash[i]);
            if( i != hash.length -1 )
                sb.append(", ");
        }
        sb.append("};");
        System.out.println(sb.toString());
    }

    private static void appendByte(StringBuilder sb, byte b) {
        sb.append("0x");
        int val = b;
        sb.append(getCharForNibble((val&0xf0)>>4));
        sb.append(getCharForNibble(val&0x0f));
    }

    private static final char[] hexChars = new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    private static char getCharForNibble(int i) {
        return hexChars[i];
    }

    private static byte[] calculateHash(String file) throws NoSuchAlgorithmException, IOException {

        FileInputStream ios = new FileInputStream(file);
        MessageDigest sha = MessageDigest.getInstance("SHA-1");

        byte[] data = new byte[256];
        int readBytes;

        while ((readBytes = ios.read(data)) != -1) {
            sha.update(data, 0, readBytes);
        }
        ios.close();
        return sha.digest();

    }
}
