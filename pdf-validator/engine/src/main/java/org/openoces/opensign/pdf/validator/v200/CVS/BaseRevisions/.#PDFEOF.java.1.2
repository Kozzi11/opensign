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

public class PDFEOF {

	/***
	 * Simple method to handle the EOF and current page
	 */
	private String token = "";
	private int countEOFs = 0;
	private int cntCharsAfterLastEOF=0;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getCountEOFs() {
		return countEOFs;
	}

	public void setCountEOFs(int countEOFs) {
		this.countEOFs = countEOFs;
	}

	public float getCntCharsAfterLastEOF() {
		return cntCharsAfterLastEOF;
	}

	public void setCntCharsAfterLastEOF(int cntCharsAfterLastEOF) {
		this.cntCharsAfterLastEOF = cntCharsAfterLastEOF;
	}

	/***
	 * Parses the chars and indicate if EOF
	 * @param b
	 */
	public void parse(int b) {
		if (countEOFs > 0)
			cntCharsAfterLastEOF += 1;
		if (token == "" && (char)b == '%') {
			token += (char)b;
			return;
		}
		else if( token.equals("%") && (char)b == '%') {
			token += (char)b;
			return;
		}

		else if( token.equals("%%") && (char)b == 'E') {
			token += (char)b;
			return;
		}      
		else if( token.equals("%%E") && (char)b == 'O') {
			token += (char)b;
			return;
		}      
		else if( token.equals("%%EO") && (char)b == 'F') {
			token += (char)b;
			return;
		}

		else if( token.equals("%%EOF") && ((char)b == '\n' || (char)b == '\r' || (char)b == ' ' || (char)b == '\t')) {
			countEOFs += 1;
			cntCharsAfterLastEOF = 0;
			if ((char)b == '\n') {
				token = "";
			}
			else
				token += (char)b;
			return;
		}
		else if (token.equals("%%EOF\r")){
			if ((char)b == '\n'){
				cntCharsAfterLastEOF = 0;
				token = "";
			}
		}
		else
			token = "";
	}
}
