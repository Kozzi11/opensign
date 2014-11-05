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
*/

package org.openoces.opensign.client.applet.attach;

import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.utils.HexDump;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256Checksum implements org.openoces.opensign.client.applet.attach.Checksum {
    private byte[] value;
    private MessageDigest sha;

    public Sha256Checksum(byte[] value) {
        this.value = (byte[])value.clone();

    }

    public void update(byte[] data,int offset,int count) {
        sha.update(data, offset, count);
    }

    public void digest() {
        value = sha.digest();
    }

    public Sha256Checksum() throws NoSuchAlgorithmException {
        sha = MessageDigest.getInstance("SHA-256");
    }

    public byte[] getShaValue() {
        if ( value == null ) {
            digest();
        }
        return (byte[])value.clone();
    }

    public boolean matches(Checksum checksum) throws Exception {
        byte[] valueExt;

        if (!(checksum instanceof Sha256Checksum)) {
            FileLog.warn("Tried matching incompatible checksums");
            return false;
        }

        valueExt = ((Sha256Checksum) checksum).getShaValue();

        if (value == null) {
            FileLog.warn("Checksum is null");
            return false;
        }

        if (valueExt == null) {
            FileLog.warn("External checksum is null");
            return false;
        }

        if (valueExt.length != value.length) {
            FileLog.warn("Calculated and stored digest have different lengths");
            return false;
        }

        FileLog.warn("Length are" + value.length);
        if (value.length != 32) {
            FileLog.warn("Stored digest has invalid length");
            return false;
        }

        for (int i = 0; i < value.length; i++) {
            byte b = value[i];
            byte bExt = valueExt[i];
            if (b != bExt) {
                FileLog.warn("Calculated and stored digest do not match at position " + i);
                FileLog.warn("Internal value:\n" + HexDump.xdump(value));
                FileLog.warn("External value:\n" + HexDump.xdump(valueExt));
                return false;
            }
        }
        return true;
    }

    private static final String legalFileNameChars="abcdefghijklmnopqrstuvwxyzABCEDFGHIJKLMNOPQRSTUVWXYZ0123456789";
	/**
	 * Create a string which is sufficiently unique to avoid accidental clashes and can be used as a directoryname
	 */
    public String toString(){
    	StringBuffer buf = new StringBuffer();
    	byte [] shavalue = this.getShaValue();
    	for (int i=0; i<shavalue.length;i++){
    		char c = legalFileNameChars.charAt(((char)shavalue[i])%legalFileNameChars.length());
    		buf.append(c);
    	}
    	return buf.toString();
    }
}
