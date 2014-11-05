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

/* $Id: Sha1Checksum.java,v 1.4 2012/09/27 11:03:51 pakj Exp $ */

package org.openoces.opensign.client.applet.bootstrap;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This interface class represents a sha1 checksum
 *
 * @author Carsten Rasgaard <carsten@raskgaard.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 */
class Sha1Checksum implements Checksum {
	private byte[] value;
	private MessageDigest sha;

	Sha1Checksum(byte[] value) {
		this.value = (byte[])value.clone();

	}

	Sha1Checksum(File f) throws FileNotFoundException {
		InputStream is = null;
		try {
			is = new FileInputStream(f);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	public void update(byte[] data, int offset, int count) {
		sha.update(data, offset, count);
	}

	public void digest() {
		value = sha.digest();
	}

	Sha1Checksum() throws NoSuchAlgorithmException {
		sha = MessageDigest.getInstance("SHA-1");
	}

	byte[] getSha1Value() {
		if (value == null) {
			digest();
		}
		return value.clone();
	}

	public boolean matches(Checksum checksum) throws Exception {
		byte[] valueExt;

		if (!(checksum instanceof Sha1Checksum)) {
			System.out.println("Tried matching incompatible checksums");
			return false;
		}

		valueExt = ((Sha1Checksum) checksum).getSha1Value();

		if (value == null) {
			System.out.println("Checksum is null");
			return false;
		}

		if (valueExt == null) {
			System.out.println("External checksum is null");
			return false;
		}

		if (valueExt.length != value.length) {
			System.out.println("Calculated and stored digest have different lengths");
			return false;
		}

		if (value.length != 20) {
			System.out.println("Stored digest has invalid length");
			return false;
		}

		for (int i = 0; i < value.length; i++) {
			byte b = value[i];
			byte bExt = valueExt[i];
			if (b != bExt) {
				System.out.println("Calculated and stored digest do not match at position "
								+ i);
				System.out.println("Internal value:\n" + HexDump.xdump(value));
				System.out.println("External value:\n" + HexDump.xdump(valueExt));
				return false;
			}
		}
		System.out.println("Sha1 checksum matching was successful");
		return true;
	}

	private static final String legalFileNameChars = "abcdefghijklmnopqrstuvwxyzABCEDFGHIJKLMNOPQRSTUVWXYZ0123456789";

	/**
	 * Create a string which is sufficiently unique to avoid accidental clashes
	 * and can be used as a directoryname
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		byte[] sha1value = this.getSha1Value();
		for (int i = 0; i < sha1value.length; i++) {
			char c = legalFileNameChars.charAt(((char) sha1value[i])
					% legalFileNameChars.length());
			buf.append(c);
		}
		return buf.toString();
	}
}