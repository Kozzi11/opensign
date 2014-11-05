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

import org.openoces.opensign.pdf.validator.ErrorHandler;
import org.openoces.opensign.pdf.validator.PDFException;
import org.openoces.opensign.pdf.validator.PDFValidator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PDFValidatorImpl implements PDFValidator {
    private PDFDocument pdfDoc;
    private Parser parser;
    private ErrorHandler errorHandler;
    private PDFEOF pdfeof;


    public PDFValidatorImpl() {
        super();
    }

    public String getVersion() {
        return "200";
    }


    public PDFDocument getPdfDoc() {
        return pdfDoc;
    }

    public void setPdfDoc(PDFDocument pdfDoc) {
        this.pdfDoc = pdfDoc;
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    @Override
    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public PDFEOF getPdfeof() {
        return pdfeof;
    }

    public void setPdfeof(PDFEOF pdfeof) {
        this.pdfeof = pdfeof;
    }

    /***
     * Simple tester for one file
     * @param args
     */
    public static void main(String[] args)  {


        PDFValidatorImpl tester = new PDFValidatorImpl();

        String [] testFiles = {
                "C:\\pdf\\example\\example.pdf"
        };

        ArrayList<String> exceptionlst = new ArrayList<String>();

        for (String sFile: testFiles) {
            try {
                tester.readPDF(Util.getBytesFromFile(new File(sFile)), false);
                System.out.println("No errors");
            }
            catch (java.lang.Exception e) {
                exceptionlst.add("Result for file " + sFile + "\r\n" + e.getLocalizedMessage());

                System.out.println(tester.getErrorHandler().getAllErrors());

            }
        }

    }

    /***
     * Parses the byte array
     * @param bytesFromFile containing the file contents as a byte array
     * @param fastFail should parser cast exception on first error or add them to a list
     * @return PDFversion if file is a pdf otherwise ""
     * @throws Exception if fastfail is set
     */
    @Override
    public void readPDF(byte[] bytesFromFile, boolean fastFail) throws Exception {

        try {
            pdfDoc = new PDFDocument();
            parser = new Parser();
            pdfeof = new PDFEOF();
            errorHandler = new ErrorHandler(fastFail);

            /***
             * check for correct header
             */
            int headerInBytes = -1;

            if (bytesFromFile.length < 1) {
                errorHandler.addError("PDF was empty","File length was < 1");
                throw new Exception();
            }

            headerInBytes = getHeader(pdfDoc,bytesFromFile);

            if (headerInBytes < 0 ) {
                errorHandler.addError("PDF did not start with valid PDF-tag", "Valid PDF file contains %PDF");
                throw new Exception();
            }

            StringBuilder line = new StringBuilder();

            char c = 0;
            for (int i=headerInBytes; i<bytesFromFile.length; i++) {
                c = (char)bytesFromFile[i];
                /*loops through bytearray until eof*/

                if (Util.isEOL(c) && !parser.isInsideStream()) {

                    if (line != null && line.length() > 0) {
                        if (line.toString().indexOf("%") > -1 && line.toString().indexOf("%%EOF") == -1  ) {
                            /*comment*/
                            line = new StringBuilder(line.substring(0,line.toString().indexOf("%") ));
                        }
                    }

                    if ( line.length() > 0) {
                        boolean wasInsideSomeThing = false;
                        try {
                            wasInsideSomeThing = parser.parseLine(c,pdfDoc,line);
                        } catch (PDFException e) {
                            if (fastFail == true ) {
                                throw e;
                            }
                            else
                                errorHandler.addError(e.getShortDescription(),e.getLongDescription());
                        }
                        if (parser.foundXref() == true)
                            pdfDoc.setFoundxRefOnPage(true);

                        line = new StringBuilder();
                    }
                }
                else if (parser.isInsideStream()) {

                    PDFObject po = pdfDoc.getPDFobjects().get(pdfDoc.getPDFobjects().size() -1);
                    PDFstreamObject pso = new PDFstreamObject(po.getObjectName(), po.getGenerationsNumber(),po.getPageNo());

                    try {
                        if (bytesFromFile[i - 1] == 13 && bytesFromFile[i] == 10) {
                            pso.addToStreamBuffer(bytesFromFile[i - 1 ]);
                        }
                        pso.addToStreamBuffer(bytesFromFile[i++]);
                        while (!Util.endOfStream(Arrays.copyOfRange(bytesFromFile, i, i + 9))) {

                            if (bytesFromFile.length < i + 1) {
                                errorHandler.addError("Reached EOF","Did not expect End-of-file here");
                                break;
                            }

                            pso.addToStreamBuffer(bytesFromFile[i++]);
                        }
                        i += 8;
                        parser.setInsideStream(false);
                        pdfDoc.getPDFStreamObjects().add(pso);
                    }
                    catch (ArrayIndexOutOfBoundsException e ) {
                        errorHandler.addError("Reached EOF","Did not expect End-of-file here");
                    }
                }
                else {
                    line.append(c);
                }


                    pdfeof.parse(c);



                /**
                 * setting page number
                 * */
                pdfDoc.setCurrentPage(pdfeof.getCountEOFs() + 1);




            }
            //*validates if line is not empty*/
            if ( line.length() > 0) {
                boolean wasInsideSomeThing = false;
                try {
                    wasInsideSomeThing = parser.parseLine(c,pdfDoc,line);
                } catch (PDFException e) {
                    if (fastFail == true ) {
                        throw e;
                    }
                    else
                        errorHandler.addError(e.getShortDescription(),e.getLongDescription());
                }

            }



            //check the last EOF if last line does not contain a valid whitespace
            if ( pdfeof.getToken().length() > 0) {

                if (pdfeof.getToken().equals("%%EOF")) {
                    pdfeof.setCountEOFs(pdfeof.getCountEOFs() + 1);
                    pdfeof.setCntCharsAfterLastEOF(0);
                    pdfeof.setToken("");
                }
            }

            // validates EOF
            if (pdfeof == null || pdfeof.getCountEOFs() < 1) {
                if (fastFail)  {
                    errorHandler.addError("PDF document does not contain an ending", "EOF is missing");
                    throw new Exception("PDF document does not contain an ending");
                }
                else {
                    errorHandler.addError("PDF document does not contain an ending", "EOF is missing");
                }
            }


            /***
             * Setting the types for PDF objects
             */

            pdfDoc.syncData(errorHandler,fastFail);


            if (!parser.isAllClosed()) {

                if (fastFail)  {
                    errorHandler.addError("PDF document contains elements that are not closed", "Header, objects, stream, trailer and crossref must be closed for a valid document ");
                    throw new Exception("PDF document contains elements that are not closed");
                }
                else {
                    errorHandler.addError("PDF document contains elements that are not closed", "Header, objects, stream, trailer and crossref must be closed for a valid document ");
                }

            }

            if (!parser.isAllFound( pdfDoc)) {
                if (fastFail)  {
                    errorHandler.addError("PDF document does not have all elements required", "Trailer and crosstable is required");
                    throw new Exception("PDF document does not have all elements required");
                }
                else {
                    errorHandler.addError("PDF document does not have all elements required", "Trailer and crosstable is required");
                }

            }


            /***
             * Validates streams
             */
            try {
                pdfDoc.validateStreams(errorHandler,fastFail);
            }
            catch (PDFException e) {
                if (fastFail)  {
                    errorHandler.addError(e.getLocalizedMessage());
                    throw new Exception(errorHandler.getAllErrors());
                }
                else {
                    errorHandler.addError(e.getShortDescription(),e.getLongDescription());
                }
            }

            /*validateTrailers*/
            try {
                pdfDoc.validateTrailers(fastFail);
            }
            catch (PDFException e) {
                if (fastFail)  {
                    errorHandler.addError(e.getLocalizedMessage());
                    throw new Exception(errorHandler.getAllErrors());
                }
                else {
                    errorHandler.addError(e.getShortDescription(),e.getLongDescription());
                }
            }

            /***
             * Validates objects
             */
            try {
                pdfDoc.validateObjects(fastFail);
            }
            catch (PDFException e) {
                if (fastFail)  {
                    errorHandler.addError(e.getLocalizedMessage());
                    throw new Exception(errorHandler.getAllErrors());
                }
                else {
                    errorHandler.addError(e.getShortDescription(),e.getLongDescription());
                }
            }
            //check for valid trailer
            if ((pdfDoc.getTrailers() == null || pdfDoc.getTrailers().size() < 1) && !pdfDoc.canFindObject("/XRef")) {
                if (fastFail)  {
                    errorHandler.addError("PDF document does not contain a valid trailer", "Trailer is missing in file");
                    throw new Exception("PDF document does not contain a valid trailer");
                }
                else {
                    errorHandler.addError("PDF document does not contain a valid trailer", "Trailer is missing in file");
                }

            }
            else {
                boolean validTrailers = true;
                //for (PDFTrailer trailer: pdfDoc.getTrailers()) {
                PDFTrailer trailer;
                for (int i=0; i <pdfDoc.getTrailers().size();i++ )   {
                    trailer = pdfDoc.getTrailers().get(i);

                    if ((trailer.getRootStr() == null || trailer.getRootStr().length() < 1) && i==0) {
                        validTrailers = false;
                    }
                    if ((trailer.getSizeStr() == null || trailer.getSizeStr().length() < 1)){

                        validTrailers = false;
                    }
                }
                if (validTrailers == false) {
                    if (fastFail)  {
                        errorHandler.addError("PDF document missing data in trailer","Root or size is missing");
                        throw new Exception("PDF document missing data in trailer");
                    }
                    else {
                        errorHandler.addError("PDF document missing data in trailer","Root or size is missing");
                    }
                }
            }



            /*validates fonts*/
            /*building a list of valid fonts*/
            StringBuffer invalidFonts = new StringBuffer();
            for (String fnt: pdfDoc.getUsedFontList()) {
                if (!pdfDoc.getFontList().contains(fnt) && !pdfDoc.getFontList().contains(Util.cleanFontName(fnt))) {
                    if (!isValidFontSubSet(fnt)  ) {
                        if (invalidFonts.length() > 0)
                            invalidFonts.append(",");

                        invalidFonts.append(fnt);
                    }

                }

            }


            if (invalidFonts.length() > 0) {
                if (fastFail)  {
                    errorHandler.addError("Nonstandard font found", "Nonstandard font found but file did not include the font " + invalidFonts);
                    throw new PDFException("Nonstandard font found", "Nonstandard font found but file did not include the font " + invalidFonts);
                }
                else {
                    errorHandler.addError("Nonstandard font found", "Nonstandard font found but file did not include the font " + invalidFonts);
                }
            }




            /*check if Cross-reference exists*/
            for (int i=1; i<pdfDoc.getFoundxRef().size();i++) {
                if (pdfDoc.getFoundxRef().get(i) == false) {

                    if (fastFail)  {
                        errorHandler.addError("PDF document missing crossreference table","Missing on page " + i + " in trailer");
                        throw new Exception("PDF document missing crossreference table");
                    }
                    else {
                        errorHandler.addError("PDF document missing crossreference table","Missing on page " + i + " in trailer");
                    }

                }

            }

        }
        catch (OutOfMemoryError oome) {

            errorHandler.addError("Out of memory","Document could not be parsed due to its size");
        }

        if (errorHandler.errorsFound()) {
            throw new Exception();
        }

    }

    @Override
    public void readPDF(File pdfFile, boolean fastFail) throws Exception {
        if (pdfFile == null) {
            errorHandler.addError("Unable to access file. File is null" );
            throw new Exception("Unable to access file. File is null");
        }

        byte[] fileContents = new byte[0];
        try {
            fileContents =  Util.getBytesFromFile(pdfFile);
            readPDF(fileContents, fastFail);
        }
        catch(Exception e) {
            if(getErrorHandler() == null)   {
                errorHandler = new ErrorHandler(fastFail);
                errorHandler.addError("Unable to access file. File is unreadable" );
            }
            if (errorHandler.errorsFound()) {
                throw new Exception();
            }

        }
    }

    /***
     * Check for valid header
     * @param pdfArray
     * @return number of bytes in header
     */
    private int getHeader(PDFDocument pdf,byte[] pdfArray) {

        StringBuffer hdr = new StringBuffer("");
        int headerBytes=-1;

        int size = Math.min(1024, pdfArray.length);
        for (int i=0;i < size; i++) {
            if (((pdfArray[i] == 10 || pdfArray[i] == 13) ) &&  hdr.toString().indexOf("%PDF-") > -1) {
                headerBytes = i;
                break;
            }
            if ((i + 1 ) > pdfArray.length) {
                headerBytes = i;
                break;
            }


            hdr.append((char)(pdfArray[i]));
        }

        if (hdr.toString().indexOf("%PDF-") > -1) {
            pdf.setPdfVersion(hdr.substring(hdr.toString().indexOf("%PDF-") ));
            return headerBytes - 1;
        }
        else {
            return -1;
        }

    }

    private boolean isValidFontSubSet(String fontName) {
        if (fontName.length() < 6) {
            return false;
        }

        int idx = fontName.indexOf("+");
        if (idx != 6) { return false; }

        for(int i=0; i<6;i++) {
            if (fontName.charAt(i)<'A' && fontName.charAt(i)>'Z') { return false; }
        }

        return pdfDoc.getFontList().contains(fontName.substring(7));
    }

}

