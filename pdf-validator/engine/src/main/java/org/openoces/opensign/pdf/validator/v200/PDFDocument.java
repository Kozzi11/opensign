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
/**
 * Class for handling the PDF document
 * 
 */
package org.openoces.opensign.pdf.validator.v200;

import org.openoces.opensign.pdf.validator.ErrorHandler;
import org.openoces.opensign.pdf.validator.PDFException;

import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * 
 * @author S�ren Dines Jensen Miracle A/S
 * 
 */

public class PDFDocument {

	/**
	 * List of PDF objects in the document
	 */
	private List<PDFObject> PDFobjects ;
	/**
	 * List of PDF stream objects in the document
	 */
	private List<PDFstreamObject> psoLst;  
	/**
	 * PDF trailer
	 * */
	private List<PDFTrailer> trailers;

    private List<Boolean> foundxRef;

    private List<String> listOfTypes;
	/**
	 * PDF version
	 * */
	private String pdfVersion;

	/**
	 * set of keywords that are created in this pdf. The words are allowed
	 * 
	 * */
	private HashSet<String> createdTags = new HashSet<String>();

	/**
	 * Set of invalid keywords found. The list can be reduced later as the parser checks
	 * to see if a tag has been valid later in the process
	 * */
	private HashSet<String> invalidTags = new HashSet<String>();

    /**
     * List of valid fonts
     */
    private HashSet<String> fontList = new HashSet<String>();

    /**
     * List of used fonts
     */
    private HashSet<String> usedFontList = new HashSet<String>();

    /**
	 * local string for keeping the last token 
	 * */
	private String s1;

	/**
	 * counter to save if we are inside a string
	 * */
	private int stringCounter = 0;

	/**
	 * Last keyword met
	 */
	private String lastToken;

	/**
	 * Indicates if cursor is inside BT/ET block
	 */
	private boolean insideTextBlock;

	/**
	 * Indicates if cursor is inside BI/EI block
	 */
	private boolean insideImageBlock;

	/**
	 * Indicates if cursor is inside q/Q block
	 */
	private boolean insideImageStackBlock;


	/***
	 * current page
	 */
	private int currentPage;


	public PDFDocument() {
		super();
		PDFobjects = new ArrayList<PDFObject>();
		this.psoLst = new ArrayList<PDFstreamObject>();
		this.trailers = new ArrayList<PDFTrailer>(); 
		createdTags = new HashSet<String>();
		invalidTags = new HashSet<String>();
        this.foundxRef = new ArrayList<Boolean>();
        listOfTypes = new ArrayList<String>();
        fontList = new HashSet<String>(Arrays.asList(
                new String[]{"Times-Roman","Times-Bold",
                        "Times-Italic","Times-BoldItalic",
                        "Helvetica","Helvetica-Bold","Helvetica-Oblique",
                        "Helvetica-BoldOblique","Courier",
                        "Courier-Bold","Courier-Oblique",
                        "Courier-BoldOblique","Symbol",
                        "ZapfDingbats",
                        "CourierNew","CourierNew,Italic","CourierNew,Bold","CourierNew,BoldItalic",
                        "Arial", "Arial,Italic","Arial,Bold","Arial,BoldItalic",
                        "TimesNewRoman", "TimesNewRoman,Italic","TimesNewRoman,Bold","TimesNewRoman,BoldItalic"
                }));



        usedFontList= new HashSet<String>();
    }

    public HashSet<String> getFontList() {
        return fontList;
    }

    public HashSet<String> getUsedFontList() {
        return usedFontList;
    }



    public List<PDFObject> getPDFobjects() {
		return PDFobjects;
	}

	public void setPDFobjects(List<PDFObject> PDFobjects) {
		this.PDFobjects = PDFobjects;
	}

	public List<PDFTrailer> getTrailers() {
		return trailers;
	}

	public String getPdfVersion() {
		if (pdfVersion==null) return "";
		return pdfVersion;
	}

	public void setPdfVersion(String pdfVersion) {
		this.pdfVersion = pdfVersion;
	}

	public HashSet<String> getInvalidTags() {
		return invalidTags;
	}

	public void setInvalidTags(HashSet<String> invalidTags) {
		this.invalidTags = invalidTags;
	}

	public HashSet<String> getCreatedTags() {
		return createdTags;
	}

	public void setCreatedTags(HashSet<String> createdTags) {
		this.createdTags = createdTags;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public boolean isInsideTextBlock() {
		return insideTextBlock;
	}

	public void setInsideTextBlock(boolean insideTextBlock) {
		this.insideTextBlock = insideTextBlock;
	}

    public List<Boolean> getFoundxRef() {
        return foundxRef;
    }

    public void setFoundxRef(List<Boolean>foundxRef) {
        this.foundxRef = foundxRef;
    }

    public void setFoundxRefOnPage(Boolean found) {
        if (foundxRef.size() < currentPage) {
            for(int i=foundxRef.size(); i < currentPage;i++) {
                foundxRef.add(false);
            }
        }
        foundxRef.set(currentPage-1,found);

    }
    /**
	 * Method for setting the right type for some of the PDFobjects
	 */
	public void syncData(ErrorHandler eh,boolean fastFail) throws Exception {

		/**
		 * Setting the info reference from the trailer
		 */
		for (PDFTrailer trailer: trailers) {

            String encryptStr = Util.ExtractData(trailer.getObjectData(),"/Encrypt",null);

            if (encryptStr != null && encryptStr.length() > 1 ) {
                if (fastFail)  {
                    eh.addError("Encrypt is not supported", "Encrypted documents are not supported");
                    throw new PDFException("Encrypt is not supported", "Encrypted documents are not supported");
                }
                else {
                    eh.addError("Encrypt is not supported", "Encrypted documents are not supported");
                }

            }

            String rootStr = Util.ExtractData(trailer.getObjectData(),"/Root",null);
            if (rootStr != null) {
                trailer.setRootStr(rootStr);
            }
            String sizeStr = Util.ExtractData(trailer.getObjectData(),"/Size",null);
            if (sizeStr != null) {
                trailer.setSizeStr(sizeStr);
            }

            String infoStr = Util.ExtractData(trailer.getObjectData(),"/Info",null);
			if (infoStr != null && infoStr.length() > 0) {

				if (Util.containsWhiteSpace(infoStr)) {
					// typeStr contains a list or an array reference to Info
					PDFObject indirectRefObject =  getPDFObjectElement(Util.getValue(infoStr),trailer.getGenerationsNumber(),trailer.getPageNo());
					if (indirectRefObject != null) {
						indirectRefObject.setObjectType("/Info");

					}
				}
			}
		}
		/***
		 * Setting the type
		 */
		for (PDFObject po : PDFobjects) {

            Boolean isFontRequired;
            isFontRequired = false;
            String fontNameStr = Util.ExtractData(po.getObjectData(),"/FontName",null);
            if (fontNameStr.startsWith("/"))
                fontNameStr = fontNameStr.substring(1);
            if ((fontNameStr != null && fontNameStr.length() > 1)) {
                // check if font is standard. if not it should be embedded
                if (!fontList.contains(fontNameStr) && !(fontList.contains(Util.cleanFontName(fontNameStr))))   {

                    isFontRequired = true;
                }
            }



            String fontFileStr = Util.ExtractData(po.getObjectData(),"/FontFile3",null);
            if (fontFileStr == null || fontFileStr.length() < 1) fontFileStr = Util.ExtractData(po.getObjectData(),"/FontFile",null);
            if (fontFileStr == null || fontFileStr.length() < 1) fontFileStr = Util.ExtractData(po.getObjectData(),"/FontFile1",null);
            if (fontFileStr == null || fontFileStr.length() < 1) fontFileStr = Util.ExtractData(po.getObjectData(),"/FontFile2",null);

            if (fontFileStr != null && fontFileStr.length() > 0) {
                Pattern pRef = Pattern.compile("(\\d+)(\\s+)(\\d+)(\\s+)R.*", Pattern.DOTALL);
                Matcher mRef = pRef.matcher(fontFileStr);

                if (mRef.find() == true) {

                    //System.out.println("is file included? " + fontFileStr);
                    PDFObject indirectRefObject =  getPDFObjectElement(Util.getValue(fontFileStr),po.getGenerationsNumber(),po.getPageNo());

                    if (indirectRefObject == null) {
                        /*Embedded font not found in file*/

                        if (fastFail)  {
                            eh.addError("Embedded font not found in file", "Font file is missing. Reference " + indirectRefObject);
                            throw new PDFException("Embedded font not found in file", "Embedded font not found in file. Reference"  + indirectRefObject);
                        }
                        else {
                            eh.addError("Embedded font not found in file", "Font file is missing. Reference"  + indirectRefObject);
                        }


                    }
                }

            }
            else if(isFontRequired == true) {
                if (fastFail)  {
                    eh.addError("Font not found in file ", "Font file is missing. Reference " + fontNameStr);
                    throw new PDFException("Font not found in file", "Font not found in file. Reference"  + fontNameStr);
                }
                else {
                    eh.addError("Font not found in file", "Font file is missing. Reference"  + fontNameStr);
                }

            }


            String nameStr = Util.ExtractData(po.getObjectData(),"/Name",null);
            if (nameStr != null && nameStr.length() > 0) {
                if (!createdTags.contains(nameStr)) createdTags.add(nameStr);
            }


			String typeStr = Util.ExtractData(po.getObjectData(),"/Type",null);

            if (typeStr.length() == 0) {
                typeStr = Util.ExtractData(po.getObjectData(),"/Subtype",null);
            }

			if (typeStr != null && typeStr.length() > 0) {
				if (Util.containsWhiteSpace(typeStr)) {
					// typeStr contains a list or an array reference to Type
					PDFObject indirectRefObject =  getPDFObjectElement(Util.getValue(typeStr),po.getGenerationsNumber(),po.getPageNo());
					if (indirectRefObject != null) {
						po.setObjectType(indirectRefObject.getData());

					}
				}
				else {
					po.setObjectType(typeStr);

				}
			}

			/**
			 * Setting the length and filters of the stream objects
			 */
			PDFstreamObject pso = getElement(po.getObjectName(),po.getGenerationsNumber(),po.getPageNo());
			if (pso != null) {

				String lengthStr = Util.ExtractData(po.getObjectData(),"/Length",null);

				if (lengthStr != null && lengthStr.length() > 0) {
					if (Util.containsWhiteSpace(lengthStr)) {
						// lengthstr contains a list or an array reference to Length
						PDFObject indirectRefObject =  getPDFObjectElement(Util.getValue(lengthStr),po.getGenerationsNumber(),po.getPageNo());
						if (indirectRefObject != null) {
							int len=-1;
							try{
								len = Integer.parseInt(indirectRefObject.getData()) ;
							}catch (NumberFormatException nfe) {
								len = -1;
							}
							pso.setStreamLength(len);
						}
					}
					else {
						int len=-1;
						try{
							len = Integer.parseInt(lengthStr) ;
						}catch (NumberFormatException nfe) {
							len = -1;
						}

						pso.setStreamLength(len);

					}
				}
				/*filter*/
				String filterStr = Util.ExtractData(po.getObjectData(),"/Filter",null);
                if (filterStr != null && filterStr.length() > 0) {
					if (Util.containsWhiteSpace(filterStr)) {
						/*reference or more than one filter*/
						PDFObject indirectRefObject =  getPDFObjectElement(Util.getValue(filterStr),po.getGenerationsNumber(),po.getPageNo());
						if (indirectRefObject != null) {

							StringTokenizer st = new StringTokenizer( indirectRefObject.getData(),",");
							while (st.hasMoreTokens()) {

								pso.addFilter(st.nextToken());
							}

						}
						else {
							StringTokenizer st = new StringTokenizer( filterStr,",");
							while (st.hasMoreTokens()) {

								pso.addFilter(st.nextToken());
							}
						}
					}
					else {
						StringTokenizer st = new StringTokenizer( filterStr,",");
						while (st.hasMoreTokens()) {

							pso.addFilter(st.nextToken());
						}
					}
				}
			}
		}
    }


	private PDFObject getPDFObjectElement(String name, String generation,int page) {
		for (PDFObject po : PDFobjects ) {
			if (po.getObjectName().equals(name) && po.getGenerationsNumber().equals(generation) && po.getPageNo() == page) 
				return po;
		}
		return null;
	}

	public List<PDFstreamObject> getPDFStreamObjects() {
		return psoLst;

	}

	public PDFstreamObject getElement(String ObjectName, String generation,int page) {
		for (PDFstreamObject pso : psoLst ) {

			if (pso.getStreamName().equals(ObjectName) && pso.getStreamGeneration().equals(generation) && pso.getPageNumber() == page) 
				return pso;
		}
		return null;
	}


	/**
	 * Validates the stream for correct length
	 * */
	public void validateStreams(ErrorHandler eh,boolean fastFail) throws PDFException {

        HashSet<String> errorsFound = new HashSet<String>();

		for (PDFstreamObject pso : psoLst ) {
			try {
				pso.cleanUpStream(pso.getStreamLength());
			}
			catch (IndexOutOfBoundsException e) {
			}
            if (pso.getStreamLength() == 0) break;

//			if ( pso.getStreamLength() != pso.getStreamBuffer().size() && Math.abs(pso.getStreamLength() - pso.getStreamBuffer().size()) > 1) {
//				eh.addWarning("Stream " + pso.getStreamName() + "-" + pso.getStreamGeneration() + " on revision " + (pso.getPageNumber() - 1 ) + " did not have a correct length. Number of bytes in stream was " + pso.getStreamBuffer().size() + " and stream length was set to " + pso.getStreamLength() );
//			}
			PDFObject po = getPDFObjectElement(pso.getStreamName(),pso.getStreamGeneration(),pso.getPageNumber());
			String subType= Util.ExtractData(po.getObjectData(),"/Subtype",null);
			if (subType != null && subType.length() > 0) {
				if (Util.containsWhiteSpace(subType)) {
					// typeStr contains a list or an array reference to Type
					PDFObject indirectRefObject =  getPDFObjectElement(Util.getValue(subType),po.getGenerationsNumber(),po.getPageNo());
					if (indirectRefObject != null) {
						subType= indirectRefObject.getData();

					}
				}
			}

			if (po.getObjectType().equals("Image") || Constants.KNOWNBINARYTYPE.contains(subType )) {
				continue;
			}

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			for (Byte b : pso.getStreamBuffer()) {
				baos.write(b);
			}

			byte[] bytes = baos.toByteArray();
            try {
			    baos.close();
            }
            catch (Exception e) {}
			baos = null;
			boolean isValid = true;
            String errorTxt = "";

			boolean isBinary = false;
			for (int filteridx=0;filteridx<pso.getFilters().size();filteridx++) {
				//System.out.println("PSO: " + pso.getStreamName() + " Filter " + pso.getFilters().get(filteridx));
				if (pso.getFilters().get(filteridx).equals("/FlateDecode") || pso.getFilters().get(filteridx).equals("/Fl") ) {

					try {
						//bytes = Util.FlateDecode(bytes, false);
                        bytes = Util.decodeFlate (bytes,pso.getStreamLength());
					} catch (Exception e) {

                        eh.addError(e.getMessage());
                        bytes = null;
						//throw new Exception(e.getMessage());
					}
					if (bytes == null) { //  || bytes.length == 0  ) {

                        errorTxt = "invalid compression";
                        //eh.addError("Stream could not be parsed", "Stream " + pso.getStreamName() + " " + pso.getStreamGeneration());
                        bytes = null;
                        isValid= false;

					}

                }
				else if(pso.getFilters().get(filteridx).equals("/ASCII85Decode") || pso.getFilters().get(filteridx).equals("/A85") ) {
					try {
						bytes = ASCII85Decode.decode(bytes);
					} catch (Exception e) {
                        eh.addError(e.getMessage());
                        bytes = null;

					}
					if (bytes == null ) {
                        errorTxt = "Stream could not be parsed, invalid compression";
                        //eh.addError("Stream could not be parsed", "Stream " + pso.getStreamName() + " " + pso.getStreamGeneration());
                        isValid= false;
					}


				}
				else if(pso.getFilters().get(filteridx).equals("/ASCIIHexDecode") || pso.getFilters().get(filteridx).equals("/AHx") ) {
					try {
                        bytes = ASCIIHexDecode.decode(bytes);
                    }
                    catch (Exception e) {
                        eh.addError(e.getMessage());
                        bytes = null;
                    }
                    if (bytes == null ) {
                        errorTxt = "Stream could not be parsed, invalid compression";
                        //eh.addError("Stream could not be parsed", "Stream " + pso.getStreamName() + " " + pso.getStreamGeneration());
                        isValid= false;
                    }

				}
                else if(pso.getFilters().get(filteridx).equals("/RunLength") || pso.getFilters().get(filteridx).equals("/RL") ) {
                    try {
                        bytes = RunlengthDecode.decodeRunLength(bytes);
                    }  catch (Exception e) {
                        eh.addError(e.getMessage());
                        bytes = null;
                    }
                    if (bytes == null ) {
                        errorTxt = "Stream could not be parsed, invalid compression";
                        //eh.addError("Stream could not be parsed", "Stream " + pso.getStreamName() + " " + pso.getStreamGeneration());
                        isValid= false;
                    }
                }
                /*
				else if(pso.getFilters().get(filteridx).equals("/DCTDecode") || pso.getFilters().get(filteridx).equals("/DCT") ) {
					//used with images no need to unzip these any futher
					isBinary = true;
				}
				else if(pso.getFilters().get(filteridx).equals("/JPXDecode") ) {
					//used with images no need to unzip these any futher
					isBinary = true;
				}
				else if(pso.getFilters().get(filteridx).equals("/CCITTFaxDecode")|| pso.getFilters().get(filteridx).equals("/CCF") ) {
					//used with images no need to unzip these any futher
					isBinary = true;
				} */
				else {
                    if (pso.getFilters().get(filteridx).trim().length()>1)  {
                        isValid = false;
                        errorTxt = "Unknown filter";
                        break;
                    }
				}
                if (!errorTxt.equals(""))  {
                    errorsFound.add(errorTxt);
                    errorTxt = "";
                }
			}


        	//System.out.println("looking at" + pso.getStreamName() + ".." + pso.getFilters() + isBinary + ".." + isValid);
			//System.out.println("filters was..." + bytes.length );

			StringBuilder pp = new StringBuilder();

			if (isValid && !isBinary) {

				boolean commentFound = false;
				for (byte b : bytes) {

					if (b==10 || b == 13) {
						commentFound = false;
					}
					if (b ==37) commentFound = true; //37 = % 
					if (!commentFound && ((b < 32 && b!=10 && b!=13)  || b>127)) {
						isBinary = true;
							break;
					}
					pp.append((char)b );

				}
				if (isBinary== false) {

					if (po != null) {
						po.addObjectData(bytes);

					}
				}
			}

        }
        if (errorsFound.size() > 0) {
            if (fastFail)  {
                eh.addError("Could not parse streams",errorsFound.toString());
                //eh.addError("Unknown filter found", "Filter found was not on whitelist");
                throw new PDFException("Could not parse streams",errorsFound.toString());
            }
            else {
                eh.addError("Could not parse streams",errorsFound.toString());
            }

            //throw new PDFException("Could not parse streams",errorsFound.toString());
        }


    }

    /**
     * Validates the Trailers.
     * */
    public void validateTrailers(boolean fastFail) throws PDFException {

        HashSet<String> savedInvalidTags = invalidTags;
        invalidTags = new HashSet<String>();

        for (PDFTrailer t: getTrailers()) {
            StringBuilder txt = new StringBuilder();
            char slash = 0;
            stringCounter = 0;
            lastToken = "";
            insideTextBlock = false;
            insideImageStackBlock = false;
            insideImageBlock = false;

            listOfTypes = new ArrayList<String>();
            char previousChar = 0;
            for (int i=0; i<t.getObjectData().size(); i++) {

                byte byteRead = t.getObjectData().get(i);

                char c = (char)byteRead;


                if ( Constants.ALLOWEDTYPES.contains(slash + txt.toString())) {

                    StringBuilder isRefTxt = new StringBuilder();
                    for (int jj=i;jj<t.getObjectData().size();jj++) {

                        byte c2 = t.getObjectData().get(jj);
                        if (Util.isSpecialChar((char)c2))
                            break;
                        if (! (c== 'R' || (c >=0 && c <=9) || Util.isWhiteSpace(c)))
                            break;
                        isRefTxt.append((char)c2);

                    }
                    if (isRefTxt != null && isRefTxt.length() > 0) {
                        PDFObject pdfref = parsePDFReference(isRefTxt);

                        if (pdfref !=null)    {
                            pdfref.setObjectType(slash + txt.toString());
                        }
                    }

                }


                //check if inside text block
                if (c == 'T' && i > 1 ) {
                    if (t.getObjectData().get(i - 1) == 'B') {
                        if ((i > 2 && Util.isWhiteSpaceOrSpecial((char) (byte) t.getObjectData().get(i - 2))) || i < 3) {
                            if (t.getObjectData().size() > (i + 1)  &&  Util.isWhiteSpaceOrSpecial((char) (byte) t.getObjectData().get(i + 1))) {
                                insideTextBlock = true;
                                //System.out.println("Inside block" + po.getObjectName());
                            }

                        }
                    }
                    else if (t.getObjectData().get(i - 1) == 'E') {
                        if ((i > 2 && Util.isWhiteSpaceOrSpecial((char) (byte) t.getObjectData().get(i - 2))) || i < 3) {

                            if ((t.getObjectData().size() > (i + 1)  &&  Util.isWhiteSpaceOrSpecial((char)(byte)t.getObjectData().get(i + 1))) || t.getObjectData().size() == i + 1) {
                                insideTextBlock = false;
                                //System.out.println("outside block" + po.getObjectName());
                            }

                        }
                    }
                }
                //check if inside imageblock
                if (c == 'I' && i > 1 ) {
                    if (t.getObjectData().get(i - 1) == 'B') {
                        if ((i > 2 && Util.isWhiteSpaceOrSpecial((char) (byte) t.getObjectData().get(i - 2))) || i < 3) {
                            if (t.getObjectData().size() > (i + 1)  &&  Util.isWhiteSpaceOrSpecial((char) (byte) t.getObjectData().get(i + 1))) {
                                insideImageBlock = true;
                                //System.out.println("Inside image block" + po.getObjectName());
                            }

                        }
                    }
                    else if (t.getObjectData().get(i - 1) == 'E') {
                        if ((i > 2 && Util.isWhiteSpaceOrSpecial((char) (byte) t.getObjectData().get(i - 2))) || i < 3) {
                            if ((t.getObjectData().size() > (i + 1)  &&  Util.isWhiteSpaceOrSpecial((char)(byte)t.getObjectData().get(i + 1))) || t.getObjectData().size() == i + 1) {
                                insideImageBlock = false;
                                //System.out.println("outside image block" + po.getObjectName());
                            }

                        }
                    }
                }
                // check if inside imagestack block
                if (c == 'q'  ) {

                    if (!((i > 1) && t.getObjectData().get(i - 1) == 47) &&
                            (i > 1 && Util.isWhiteSpaceOrSpecial((char) (byte) t.getObjectData().get(i - 1)) &&
                                    (t.getObjectData().size() > (i + 1)  &&  Util.isWhiteSpaceOrSpecial((char) (byte) t.getObjectData().get(i + 1))))) {
                        insideImageStackBlock = true;
                        //System.out.println("inside imagestack block" + po.getObjectName());
                    }

                }
                if (c == 'Q'  ) {
                    if (!((i > 1) && t.getObjectData().get(i - 1) == 47) && (i > 1 && Util.isWhiteSpaceOrSpecial((char) (byte) t.getObjectData().get(i - 1)) &&
                            (t.getObjectData().size() > (i + 1)  &&  Util.isWhiteSpaceOrSpecial((char) (byte) t.getObjectData().get(i + 1))))) {
                        insideImageStackBlock = false;
                        //System.out.println("outside imagestack block" + po.getObjectName());
                    }

                }

                char charUpper = Character.toUpperCase(c);

                if ((charUpper >= 'A' && charUpper <= 'Z') || (charUpper >='0' && charUpper <= '9') || (charUpper == '+') || (charUpper == '_') || (charUpper == '-')|| (charUpper == '.') || (charUpper == '$') || (charUpper == ':')) {
                    txt.append(c);
                }
                else if ( slash == '/' &&   c == '#' ) {
                    //the next chars are in hex
                    int d1 =  t.getObjectData().get(++i);
                    if (d1 != -1 ) {
                        int d2 =   t.getObjectData().get(++i);
                        if ((d2 != -1) && (
                                (
                                        ((char)d1 >= '0' && (char)d1<= '9' ) ||
                                                (Character.toUpperCase((char)d1) >= 'A' && Character.toUpperCase((char)d1) <='F')
                                ) && (
                                        ((char)d2 >= '0' && (char)d2<= '9' ) ||
                                                (Character.toUpperCase((char)d2) >= 'A' && Character.toUpperCase((char)d2) <='F')
                                ))) {

                            int decimal = Integer.parseInt(Integer.toHexString(Integer.parseInt((char)d1 + "", 16)) + "" + Integer.toHexString(Integer.parseInt((char)d2 + "", 16)) + "", 16);
                            txt.append((char)decimal);

                        }
                        else {
                            if (stringCounter > 0) {
                                txt.append(c);
                            }
                        }
                    }
                    else {
                        if (stringCounter > 0) {
                            txt.append(c);
                        }
                    }

                }
                else {

                    if (c =='(') stringCounter++;
                    if (c == '<' && previousChar == '<') {
                        //
                        listOfTypes.add("");
                    }
                    if (c == '>' && previousChar == '>'){
                        if (listOfTypes.size() > 0)
                            listOfTypes.remove(listOfTypes.size()-1);
                    }
                    if (insideImageStackBlock == false && insideImageBlock == false && insideTextBlock == false && stringCounter==0 && updateData(txt,slash,byteRead,t,i,fastFail) == true ) {
                        txt = new StringBuilder("");
                    }

                    if (stringCounter > 0) {
                        txt.append(c);

                    }

                    if (c ==')') {
                        stringCounter--;
                    }


                    if (c == '/') {

                        slash = '/';
                    }
                    else
                        slash = 0;
                }
                previousChar = c;
            }
            //**/
            if (txt.length() > 0) updateData(txt,slash,0,t,t.getObjectData().size(),fastFail);
        } /*loops through PDFObjects*/


        //loop through invalidtags to see if they have been validated later in the pdf
        HashSet<String> tmp = new HashSet<String>();
        for (String kword: invalidTags) {
            if (!createdTags.contains(kword)) {
                tmp.add(kword);
            }
        }
        invalidTags = tmp;

        tmp = null;
           /*
        if (invalidTags.size() > 0) {

            System.out.println("Inv" + invalidTags);
            System.out.println("Sav" + savedInvalidTags);
            invalidTags.addAll(savedInvalidTags);
            System.out.println("Inv" + invalidTags);
            System.out.println("Sav" + savedInvalidTags);
            throw new PDFException("Document does not comply to whitelist." , savedInvalidTags + " does not exists on whitelist");
        }
             */

    }

        /**
       * Validates the PDF objects.
       * */
	public void validateObjects(boolean fastFail) throws PDFException {


		for (int validateLoop=0;validateLoop<1;validateLoop++) {

            for (PDFObject po :PDFobjects) {
                StringBuilder txt = new StringBuilder();
				char slash = 0;
				stringCounter = 0;
				lastToken = "";
				insideTextBlock = false;
                insideImageStackBlock = false;
				insideImageBlock = false;

                listOfTypes = new ArrayList<String>();
                char previousChar = 0;
				for (int i=0; i<po.getObjectData().size(); i++) {

					byte byteRead = po.getObjectData().get(i);

					char c = (char)byteRead;


                    if ( Constants.ALLOWEDTYPES.contains(slash + txt.toString())) {

                        StringBuilder isRefTxt = new StringBuilder();
                        for (int jj=i;jj<po.getObjectData().size();jj++) {

                            byte c2 = po.getObjectData().get(jj);
                            if (Util.isSpecialChar((char)c2))
                                break;
                            if (! (c== 'R' || (c >=0 && c <=9) || Util.isWhiteSpace(c)))
                                break;
                            isRefTxt.append((char)c2);

                        }
                        if (isRefTxt != null && isRefTxt.length() > 0) {
                            PDFObject pdfref = parsePDFReference(isRefTxt);

                            if (pdfref !=null)    {
                                pdfref.setObjectType(slash + txt.toString());
                            }
                        }

                    }


					//check if inside text block
                    if (c == 'T' && i > 1 ) {
						if (po.getObjectData().get(i - 1) == 'B') {
							if ((i > 2 && Util.isWhiteSpaceOrSpecial2((char) (byte) po.getObjectData().get(i - 2))) || i < 3) {
								if (po.getObjectData().size() > (i + 1)  &&  Util.isWhiteSpaceOrSpecial((char) (byte) po.getObjectData().get(i + 1))) {
									insideTextBlock = true;
                                    txt = new StringBuilder("");
									//System.out.println("Inside block" + po.getObjectName() + txt);
								}

							}
						}
						else if (po.getObjectData().get(i - 1) == 'E') {
							if ((i > 2 && Util.isWhiteSpaceOrSpecial2((char) (byte) po.getObjectData().get(i - 2))) || i < 3) {

								if ((po.getObjectData().size() > (i + 1)  &&  Util.isWhiteSpaceOrSpecial((char)(byte)po.getObjectData().get(i + 1))) || po.getObjectData().size() == i + 1) {
									insideTextBlock = false;
                                    txt = new StringBuilder("");
                                    //System.out.println("outside block" + po.getObjectName() + txt);
								}

							}
						}
					}
					//check if inside imageblock
					if (c == 'I' && i > 1 ) {
						if (po.getObjectData().get(i - 1) == 'B') {
							if ((i > 2 && Util.isWhiteSpaceOrSpecial2((char) (byte) po.getObjectData().get(i - 2))) || i < 3) {
								if (po.getObjectData().size() > (i + 1)  &&  Util.isWhiteSpaceOrSpecial((char) (byte) po.getObjectData().get(i + 1))) {
									insideImageBlock = true;
                                    txt = new StringBuilder("");
                                    //System.out.println("Inside image block" + po.getObjectName() + txt);
								}

							}
						}
						else if (po.getObjectData().get(i - 1) == 'E') {
							if ((i > 2 && Util.isWhiteSpaceOrSpecial2((char) (byte) po.getObjectData().get(i - 2))) || i < 3) {
								if ((po.getObjectData().size() > (i + 1)  &&  Util.isWhiteSpaceOrSpecial((char)(byte)po.getObjectData().get(i + 1))) || po.getObjectData().size() == i + 1) {
									insideImageBlock = false;
                                    txt = new StringBuilder("");
                                    //System.out.println("outside image block" + po.getObjectName() + txt);
								}

							}
						}
					}
					// check if inside imagestack block
					if (c == 'q'  ) {

						if (!((i > 1) && po.getObjectData().get(i - 1) == 47) &&
                          (i > 1 && Util.isWhiteSpaceOrSpecial2((char) (byte) po.getObjectData().get(i - 1)) &&
						 (po.getObjectData().size() > (i + 1)  &&  Util.isWhiteSpaceOrSpecial((char) (byte) po.getObjectData().get(i + 1))))) {
							insideImageStackBlock = true;
                            txt = new StringBuilder("");
                            //System.out.println("inside imagestack block" + po.getObjectName() + txt);
						}
						
					}
					if (c == 'Q'  ) {
                        if (!((i > 1) && po.getObjectData().get(i - 1) == 47) && (i > 1 && Util.isWhiteSpaceOrSpecial2((char) (byte) po.getObjectData().get(i - 1)) &&
								 (po.getObjectData().size() > (i + 1)  &&  Util.isWhiteSpaceOrSpecial((char) (byte) po.getObjectData().get(i + 1))))) {
									insideImageStackBlock = false;
                                    txt = new StringBuilder("");
                                    //System.out.println("outside imagestack block" + po.getObjectName() + txt);
								}
								
							}
					
					char charUpper = Character.toUpperCase(c);

                    if(insideImageBlock ||  insideImageStackBlock || insideTextBlock ) continue;

					if ((charUpper >= 'A' && charUpper <= 'Z') || (charUpper >='0' && charUpper <= '9') || (charUpper == '+') || (charUpper == '_') || (charUpper == '-')|| (charUpper == '.') || (charUpper == '$') || (charUpper == ':')) {
						txt.append(c);
					}	
					else if ( slash == '/' &&   c == '#' ) {
						//the next chars are in hex
						int d1 =  po.getObjectData().get(++i);
						if (d1 != -1 ) {
							int d2 =   po.getObjectData().get(++i);
							if ((d2 != -1) && (
									(
											((char)d1 >= '0' && (char)d1<= '9' ) || 
											(Character.toUpperCase((char)d1) >= 'A' && Character.toUpperCase((char)d1) <='F')
											) && (
													((char)d2 >= '0' && (char)d2<= '9' ) || 
													(Character.toUpperCase((char)d2) >= 'A' && Character.toUpperCase((char)d2) <='F')
													))) {

								int decimal = Integer.parseInt(Integer.toHexString(Integer.parseInt((char)d1 + "", 16)) + "" + Integer.toHexString(Integer.parseInt((char)d2 + "", 16)) + "", 16);
								txt.append((char)decimal);

							}
							else {
								if (stringCounter > 0) {
									txt.append(c);
								}
							}
						}
						else {
							if (stringCounter > 0) {
								txt.append(c);
							}
						}

					}
					else {

						if (c =='(') stringCounter++;
                        if (c == '<' && previousChar == '<') {
                            //
                            listOfTypes.add("");
                        }
                        if (c == '>' && previousChar == '>'){
                            if (listOfTypes.size() > 0)
                                listOfTypes.remove(listOfTypes.size()-1);
                        }
						if (insideImageStackBlock == false && insideImageBlock == false && insideTextBlock == false && stringCounter==0 && updateData(txt,slash,byteRead,po,i,fastFail) == true ) {
							txt = new StringBuilder("");
                        }

						if (stringCounter > 0) {
							txt.append(c);

						}

						if (c ==')') {
							stringCounter--;
						}


						if (c == '/') {

							slash = '/';
						}
						else
							slash = 0;
					}
                    previousChar = c;
				}
                //**/
                if (txt.length() > 0) updateData(txt,slash,0,po,po.getObjectData().size(),fastFail);
            } /*loops through PDFObjects*/
		} //validateLoop


		//loop through invalidtags to see if they have been validated later in the pdf
		HashSet<String> tmp = new HashSet<String>(); 
		for (String kword: invalidTags) {
			if (!createdTags.contains(kword)) {
				tmp.add(kword);
			}
		}
		invalidTags = tmp;
		tmp = null;
		if (invalidTags.size() > 0) throw new PDFException("Document does not comply to whitelist." , invalidTags + " does not exists on whitelist");

	}


	/**
	 * Loops through the line and checks for validation
	 * 
	 * @param txt current line
	 * @param slash set to / if working on keyword
	 * @param c current char
	 * @param po current PDFobject
	 * @param readcounter current location in byte stream
	 * @param fastFail should fail at first exception or report all exceptions at the end
	 * @return true if line was parsed and should be cleared and false if line should not be cleared 
	 * @throws Exception if fastfail is set and an error is found
	 */
	private boolean updateData(StringBuilder txt,char slash,int c, PDFObject po, int readcounter, boolean fastFail) throws PDFException {

        Boolean addToInvalidList;
        addToInvalidList = false;
		if (txt != null && txt.length() > 0 ) {

			if (slash == '/') {

                if (lastToken.equals("Type")  && listOfTypes.size() > 0) {
                    listOfTypes.set(listOfTypes.size() -1, slash + txt.toString());
                }
                else if (Constants.ALLOWEDTYPES.contains(lastToken) && listOfTypes.size() > 0 ) {
                    listOfTypes.set(listOfTypes.size() -1, lastToken);
                }

                if (lastToken.equals("FontName")) {
                    fontList.add(txt.toString());
                    lastToken = "";

                }
                if (lastToken.equals("BaseFont")) {
                    usedFontList.add(txt.toString());
                    lastToken = "";
                }


                if ((slash + txt.toString()).equals("/Type")) {lastToken = "Type";}


               /* if ((slash + txt.toString()).equals("/FontName")) {lastToken = "FontName";}
                if ((slash + txt.toString()).equals("/BaseFont")) {lastToken = "BaseFont";}
                 */

                //System.out.println(po.getObjectType() + ".." + po.getObjectName() + "Last token:" + lastToken + ".->" + txt.toString() + " for " + po.getObjectType() + "-" + ".." + Constants.ALLOWEDTYPES.contains(slash + txt.toString()) + ".." + po.getDictionaryType() + ".." ); //+ listOfTypes.size() + ".." + listOfTypes.get(listOfTypes.size()-1));

                if ((Constants.WHITELIST.contains(slash + txt.toString()) == false && (createdTags.contains(slash + txt.toString()) == false) ) || Constants.ALLOWEDTYPES.contains(lastToken) || (listOfTypes.size() > 0 && Constants.ALLOWEDTYPES.contains(listOfTypes.get(listOfTypes.size() - 1)))) {
                    if (( Constants.ALLOWEDTYPES.contains(lastToken) || Constants.ALLOWEDTYPES.contains(po.getObjectType()) ) || (listOfTypes.size() > 0 && Constants.ALLOWEDTYPES.contains(listOfTypes.get(listOfTypes.size() - 1))) ){
                    //    System.out.println("lastToken" + lastToken + ".." + po.getObjectType() + ".." + listOfTypes);

						if (!createdTags.contains(slash + txt.toString()) && Constants.WHITELIST.contains(slash + txt.toString()) == false) {
							createdTags.add(slash + txt.toString());
                            //System.out.println("Creating.." + txt.toString() + "lastToken:" + lastToken);
							return true;
						}


					}
					else {
                       // System.out.println("Added.." + txt.toString() );
						addToInvalidList = true;
					}
				}
				// check if txt is pointing to a ref if so do not update lasttoken
				boolean isRef=false;
				StringBuilder isRefTxt = new StringBuilder();
				int insideStringCounter = 0;
				int insideDictionaryCounter = 0;
				for(int idx=readcounter + 1; idx < po.getObjectData().size();idx++) {
					

						byte ch = po.getObjectData().get(idx);
						if (ch == '/' || ch == '<' || ch == '>')
							break;

						if ((char)ch == 'R' && ( idx - 1 ) > readcounter && po.getObjectData().get(idx - 1) == ' '&& insideDictionaryCounter>-1) {

							PDFObject pdfref = parsePDFReference(isRefTxt.append((char)ch)); 
							if (pdfref != null) {

                                if (addToInvalidList == true && /*((Constants.ALLOWEDTYPES.contains(pdfref.getObjectType()) ||*/  (listOfTypes.size() > 0 &&  ((Constants.ALLOWEDTYPES.contains(listOfTypes.get(listOfTypes.size() - 1)) ) || listOfTypes.get(listOfTypes.size() - 1).equals(""))))    {
                                    //System.out.println(po.getObjectType() + "is now valid" + txt + ".." + pdfref.getObjectType() + ".." + listOfTypes.size() +" " +  listOfTypes);
                                    if (Constants.ALLOWEDTYPES.contains(pdfref.getObjectType()) )
                                      addToInvalidList = false; //else is openAction in page valid
                                }
								isRef = true;
								break;
							}

							else {
								isRefTxt = new StringBuilder();
							}
						}
						else	
							isRefTxt.append((char)ch);
					
	
				}
				if (isRef==false )
					lastToken = slash + txt.toString();
				
			}
            if ((slash + txt.toString()).equals("/Type")) {lastToken = "Type";}
            if ((slash + txt.toString()).equals("/FontName")) {lastToken = "FontName";}
            if ((slash + txt.toString()).equals("/BaseFont")) {lastToken = "BaseFont";}

        }

        if (addToInvalidList  ) {
                //System.out.println("Adding to list" + txt.toString());
                //System.out.println(po.getObjectName() + "-" + stringCounter + "NOOOT Allowed type " + slash + txt.toString() + " found " + po.getObjectType() + " for " + po.getObjectName() + "-" + po.getDictionaryType() + "-" +  lastToken + ".." + /* listOfTypes.get(listOfTypes.size() - 1) + */ ".." + listOfTypes.size());

            //System.out.println("data:" + Util.byteToString(po.getObjectData()));
            invalidTags.add(slash + txt.toString());
        }
		s1 = (char)c + "";
		if (!txt.toString().equals("obj") && s1.equals("<")) return false;
		return true;
	}

	
	
	/**
	 * Parsing a line and returns the PDFObject that matches the name
	 *
	 * @param stringBuilder the line that should be checked for reference
	 * @return PDFObject that matches and null if not found
	 */

	private PDFObject parsePDFReference(StringBuilder stringBuilder) {
		Pattern pRef = Pattern.compile("(\\d+)(\\s+)(\\d+)(\\s+)R.*", Pattern.DOTALL);
		Matcher mRef = pRef.matcher(stringBuilder);

		if (mRef.find() == true) {

			String s1 = mRef.group(1);
			String s2 = mRef.group(3);

			for (PDFObject p : PDFobjects) {
				if (p.getObjectName().equals(s1) && p.getGenerationsNumber().equals(s2)) {
					return p;
				}

			}
		}	
		return null;
	}

    public boolean canFindObject(String typeStr) {
        for (PDFObject p : PDFobjects) {
            if (p.getObjectType().equals(typeStr)) return true;

        }
        return false;
    }

}