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

import org.openoces.opensign.pdf.validator.PDFException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	/***
	 * Variables to indicate where parser is
	 */
	private boolean insideStream = false;
	private boolean insideObject = false;
	private boolean insideHeader = false;
	private boolean insideTrailer = false;
	private boolean insideXref = false;

    private boolean hasSeenTrailer = false;
    private boolean hasSeenXrefStart = false;
    private boolean hasSeenXref = false;
	/***
	 * Simple method to parse line
	 * @param c current char
	 * @param pdf current PDF document
	 * @param line current line
	 * @return true if just exits an object otherwise false
	 */
	public boolean parseLine(char c,PDFDocument pdf,StringBuilder line) throws PDFException {
		
		boolean wasInsideObject = false;
		
		String s1;
		String s2;
		Pattern pObj = Pattern.compile("(\\d+)(\\s+)(\\d+)(\\s+)obj.*", Pattern.DOTALL);
		Matcher mObj = pObj.matcher(line);

		Pattern pEndObj = Pattern.compile("^endobj|\\bendobj\\b", Pattern.DOTALL);
		Matcher mEndObj = pEndObj.matcher(line);

		Pattern pStream = Pattern.compile("(^(?!endstream))(.*stream$)", Pattern.DOTALL);
		//Pattern pStream = Pattern.compile("stream.*", Pattern.DOTALL);
		Matcher mStream = pStream.matcher(line);
		
		Pattern pTrailer = Pattern.compile("^trailer.*", Pattern.DOTALL);
		Matcher mTrailer = pTrailer.matcher(line);

		Pattern pXref = Pattern.compile("(?!startxref)(xref$)", Pattern.DOTALL);
		Matcher mXref = pXref.matcher(line);
		
		Pattern pEndTrailer = Pattern.compile("^startxref$", Pattern.DOTALL);
		Matcher mEndTrailer = pEndTrailer.matcher(line);

		Pattern pEndXref = Pattern.compile("^%%EOF", Pattern.DOTALL);
		Matcher mEndXref = pEndXref.matcher(line);
		
		if (mObj.find() == true) {
			setInsideObject(true);

			s1 = mObj.group(1);
			s2 = mObj.group(3);

			PDFObject po = new PDFObject();
			po.setObjectName(s1);
			po.setGenerationsNumber(s2);
			po.setPageNo(pdf.getCurrentPage());

			pdf.getPDFobjects().add(po);
		}
		if (mEndObj.find() == true ) {
	        if (insideObject == false) {

                throw new PDFException("Object is corrupt.","Found endobject outside object ");
            }
            setInsideObject(false);
			wasInsideObject = true;
		} 
		if (mStream.find() == true ) {
			if (mStream.group().indexOf("stream") > 0) {
				PDFObject po = pdf.getPDFobjects().get(pdf.getPDFobjects().size() - 1);
				po.addObjectData(line.toString().substring(0,mStream.group().indexOf("stream") - 1).getBytes());
			}
			setInsideStream(true);
		}
		if (mTrailer.find() == true ) {
			setInsideTrailer(true);
            hasSeenTrailer = true;
		}
		if (mXref.find() == true ) {
			setInsideXref(true);
            hasSeenXref = true;
			
		}
		if (mEndTrailer.find() == true ) {
 //           if (insideTrailer == false && !pdf.canFindObject("/XRef")) {

   //             throw new PDFException("Trailer is corrupt.","Found endtrailer but no beginning");
   //         }

            hasSeenXrefStart = true;

            setInsideTrailer(false);
			wasInsideObject = true;
		}
		if (mEndXref.find() == true ) {
            if (insideXref == false) {
                throw new PDFException("XReference table is corrupt.","Found end of cross-reference table but no beginning");
            }
			setInsideXref(false);
			wasInsideObject = true;
		}

		if (isInsideObject() && !isInsideStream()) {
			
			int startFrom = 0;
			if (mObj.find(0) == true &&  mObj.group().length() > 0 && mObj.end(4) > 0 ) {
				startFrom = mObj.end(4) + 3;
				
			}
			PDFObject po = pdf.getPDFobjects().get(pdf.getPDFobjects().size() - 1);
	
			if (po.getObjectData().size() > 0) {
				po.addObjectData((byte)c);
			}
			if (startFrom < line.toString().length())
				po.addObjectData(line.toString().substring(startFrom).getBytes());
		}
		if (insideTrailer) {
			if (pdf.getCurrentPage() > pdf.getTrailers().size()) {
				PDFTrailer trailer = new PDFTrailer();
				trailer.setGenerationsNumber("0");
				trailer.setPageNo(pdf.getCurrentPage());
				pdf.getTrailers().add(trailer);
			}
			pdf.getTrailers().get(pdf.getCurrentPage() - 1).addObjectData(line.toString().getBytes());
			
		}
		return wasInsideObject;
	}


	public boolean isInsideStream() {
		return insideStream;
	}


	public void setInsideStream(boolean insideStream) {
		this.insideStream = insideStream;
	}


	public boolean isInsideObject() {
		return insideObject;
	}


	public void setInsideObject(boolean insideObject) {
		this.insideObject = insideObject;
	}


	public boolean isInsideHeader() {
		return insideHeader;
	}


	public void setInsideHeader(boolean insideHeader) {
		this.insideHeader = insideHeader;
	}


	public boolean isInsideTrailer() {
		return insideTrailer;
	}


	public void setInsideTrailer(boolean insideTrailer) {
		this.insideTrailer = insideTrailer;
	}

	public boolean isInsideXref() {
		return insideXref;
	}

	public void setInsideXref(boolean insideXref) {
		this.insideXref = insideXref;
	}


	public boolean foundXref() {
		return (!isInsideHeader() && !isInsideObject() && !isInsideStream() && !isInsideTrailer());

	}

    public boolean isAllClosed() {
        return (!isInsideHeader() && !isInsideObject() && !isInsideStream() && !isInsideTrailer() && !isInsideXref() );

    }
    public boolean isAllFound(PDFDocument pdf) {
        if (pdf.canFindObject("/XRef"))
            hasSeenTrailer=true;
        return (hasSeenTrailer && hasSeenXref && hasSeenXrefStart);

    }

}
