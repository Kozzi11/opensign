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

public class ASCIIHexDecode {

	private byte[] buf;
	private int location; 

	private ASCIIHexDecode(byte[] buf) {
		this.buf = buf;
		location = 0;
	}

	private int readHexDigit() throws Exception {

		if (buf.length == location ) return -1;
        while(buf.length > location) {
			int i = (int) buf[location++];
			if (!Util.isWhiteSpace((char) i)) {
				if (i >= '0' && i <= '9') {
					i -= '0';
				} else if (i >= 'a' && i <= 'f') {
					i -= 'a' - 10;
				} else if (i >= 'A' && i <= 'F') {
					i -= 'A' - 10;
				} else if (i == '>') {
					i = -1;
				} else {

					throw new Exception("Invalid character " + i + " in stream");
				}

				return i;
			}
		}

		// end of stream reached
		throw new Exception("Missing data in stream");
	}

	/**
	 * decode the array
	 * @return the decoded bytes
	 */
	private byte[] decode() throws Exception {

		// allocate the output buffer
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		while (true) {
			int first = readHexDigit();
			int second = readHexDigit();

			if (first == -1) {
				break;
			} else if (second == -1) {
				baos.write((byte) (first << 4));
				break;
			} else {
				baos.write((byte) ((first << 4) + second));
			}
		}

		return baos.toByteArray();
	}

	public static byte[] decode(byte[] buf) throws Exception {
		ASCIIHexDecode me = new ASCIIHexDecode(buf);
		return me.decode();
	}
}