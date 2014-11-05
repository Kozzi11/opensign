/**
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
 @author sdj sdj@miracleas.dk
 */
package org.openoces.opensign.pdf.validator.v200;

import java.io.ByteArrayOutputStream;

/**
 * @author S�ren Dines Jensen Miracle A/S
 *
 */
public class ASCII85Decode {

	private byte[] buf;
	private int location; 

	/**
	 * initialize the decoder with byte buffer in ASCII85 format
	 */
	private ASCII85Decode(byte[] buf) {
		this.buf = buf;
		location = 0;
	}

	/**
	 * get the next character from the input.
	 * @return the next character, or -1 if at end of stream
	 */
	private int nextChar() {
		// skip whitespace
		// returns next character, or -1 if end of stream
		while(buf.length > 0) {
			char c = (char) buf[location++];

			if (!Util.isWhiteSpace(c)) {
				return c;
			}
		}

		// EOF reached
		return -1;
	}

	
	/**
	 * decode the next five ASCII85 characters into up to four decoded
	 * bytes.  Return false when finished, or true otherwise.
	 *
	 * @param baos the ByteArrayOutputStream to write output to, set to the
	 *        correct position
	 * @return false when finished, or true otherwise.
	 */
	private boolean decode5(ByteArrayOutputStream baos)	throws Exception
			{
		// stream ends in ~>
		int[] five = new int[5];
		int i;
		for (i = 0; i < 5; i++) {
			five[i] = nextChar();
			if (five[i] == '~') {
				
				if (nextChar() == '>') {
					break;
				} else {
					throw new Exception("Bad character in ASCII85Decode: not ~>");
				}
			} else if (five[i] >= '!' && five[i] <= 'u') {
				five[i] -= '!';
			} else if (five[i] == 'z') {
				if (i == 0) {
					five[i] = 0;
					i = 4;
				} else {
					throw new Exception("Inappropriate 'z' in ASCII85Decode");
				}
			} else {
				//throw new Exception("Bad character in ASCII85Decode: "+five[i]+" ("+(char)five[i]+")");
			}
		}
		if (i > 0) {
			i -= 1;
		}

		int value =
				five[0] * 85 * 85 * 85 * 85 +
				five[1] * 85 * 85 * 85 +
				five[2] * 85 * 85 +
				five[3] * 85 +
				five[4];

		for (int j = 0; j < i; j++) {
			int shift = 8 * (3 - j);
			baos.write((byte) ((value >> shift) & 0xff));
		}

		return (i == 4);
	}

	/**
	 * decode the bytes
	 * @return the decoded bytes
	 */
	private byte[] decode() throws Exception {

		// allocate the output buffer
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// decode the bytes 
		while (decode5(baos)) {}

		return baos.toByteArray();
	}

	/**
	 * decode an array of bytes in ASCII85 format.
	 * <p>
	 * In ASCII85 format, every 5 characters represents 4 decoded
	 * bytes in base 85.  The entire stream can contain whitespace,
	 * and ends in the characters '~&gt;'.
	 *
	 * @param buf the encoded ASCII85 characters in a byte buffer
	 * @return the decoded bytes
	 */
	public static byte[] decode(byte[] buf)throws Exception
	{
		ASCII85Decode me = new ASCII85Decode(buf);
		return me.decode();
	}

}