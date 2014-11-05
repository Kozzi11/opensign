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

/***
 * Class for handling the errors found while parsing the PDF
 */

package org.openoces.opensign.pdf.validator;

import org.openoces.opensign.pdf.validator.PDFException;

import java.util.ArrayList;

public class ErrorHandler {

	/***
	 * if fastfail is set the parser will throw it right away instead of adding it to list 
	 */
	private boolean fastFail;
	/***
	 * List of errors found if fastfail is not set
	 */
	private ArrayList<PDFException> errorsFoundLst;
	
    public ErrorHandler(boolean fastFail) {
		super();
		this.fastFail = fastFail;
		
		errorsFoundLst = new ArrayList<PDFException>();
	}

    public ArrayList<PDFException> getErrorsFound() {
        return errorsFoundLst;
    }

    public ArrayList<String> getErrorsFoundAsString() {
        ArrayList<String> lst = new ArrayList<String>();
        for (PDFException pe : errorsFoundLst) {
            lst.add(pe.getShortDescription() + " " + pe.getLongDescription());
        }
        return lst;
    }
    /***
	 * 
	 * @param errorMessage contains the error text
	 * @throws PDFException if fastfail is set
	 */
	public void addError(String errorMessage) throws PDFException {
        addError(errorMessage, "");
	}

    /***
     *
     *
     * @param errorMessage contains the error text
     * @param longMessage  contains a longer description of the error, if exists
     * @throws PDFException if fastfail is set
     */
    public void addError(String errorMessage, String longMessage) throws PDFException {

        if (fastFail) {
            throw new PDFException(errorMessage,longMessage);
        }
        else
            errorsFoundLst.add(new PDFException(errorMessage,longMessage));
    }
	/***
	 * Simple method for getting all the errors as a nice readable string
	 * @return errors as a readable string
	 */
	public String getAllErrors() {
		StringBuilder sb = new StringBuilder();
		for (PDFException er: errorsFoundLst) {
			sb.append(er.getShortDescription()).append(" ").append(er.getLongDescription()).append("\r\n");
		}
		return sb.toString();
	}
	
	/***
	 * 
	 * @return if any erros found
	 */
	public boolean errorsFound() {
		return errorsFoundLst.size() > 0;
	}

}
