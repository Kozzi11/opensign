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
 * Utility class 
 */
package org.openoces.opensign.pdf.validator.v200;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class Util {

	/***
	 * Returns if c is a whitespace character
	 * @param c char to be checked
	 * @return true if whitespace and false if not
	 */
	public static boolean isWhiteSpace(char c) {

		if (c == ' ' || c== '\t' || c == '\n' || c == '\r' || c==0 || c== '\f')
			return true;
		return false;
	}
	
	/***
	 * Returns if c is end-of-line char
	 * @param c char to be checked
	 * @return true if eol and false if not
	 */
	public static boolean isEOL(char c) {

		if ( c == 10 || c == 13 )
			return true;
		return false;
	}
	
	
	/***
	 * Checks if string contains whitespaces
	 * @param str string to be checked
	 * @return true if string contains whitespace and false if not
	 */
	public static boolean containsWhiteSpace(String str) {

		for (int i=0; i<str.length(); i++) 
		{
			if (isWhiteSpace(str.charAt(i))) return true;
		}
		return false;
	}

    public static boolean isWhiteSpaceOrSpecial(char c) {
        return isSpecialChar(c) || isWhiteSpace(c);
    }

    public static boolean isWhiteSpaceOrSpecial2(char c) {
        if (c=='/') return false;
        return isSpecialChar(c) || isWhiteSpace(c);
    }

	/***
	 * Checks if char is a special character
	 * @param c char to be checked
	 * @return true if special char and false if not
	 */
	public static boolean isSpecialChar(char c) {
				
		if (c == '(' || c== ')' || c == '<' || c == '>' || c == '[' || c == ']' || c == '{' || c == '}' || c == '/' || c == '%')
			return true;
		return false;
		
	}

	/***
	 * Extracts keyword from object
	 * @param objectData contains the data 
	 * @param keyWord to be found
	 * @param filters extract filters
	 * @return string containing the result 
	 */
	public static String ExtractData( List<Byte> objectData, String keyWord, HashSet<String> filters) {
		byte b=0;
		StringBuilder result = new StringBuilder();
		boolean keywordFound = false;
		StringBuilder tmp = new StringBuilder();
					
		for (int i=0;i < objectData.size(); i++) {
			b = objectData.get(i);
			if (tmp.length() > 0 && ( Util.isWhiteSpace((char)b) ||  Util.isSpecialChar((char)b))) {
				
				if (keywordFound ) {
					if (filters == null) {
						String ref = Util.CheckIfReference(tmp.toString(),i,objectData);
						
						if (ref != null) {
							return ref;
						}
						else return tmp.toString();
					}
					if ( filters.contains(tmp.toString())) {
						if ( result.length() != 0 ) {
							result.append(",");
						}
						result.append(tmp.toString());
					}
					else {
						if (result.length() > 0) {
							return result.toString();
						}
						//else return tmp.toString();
					}

				}
				if (tmp.toString().equals(keyWord)){
					keywordFound = true;

				} 
				
				tmp = new StringBuilder();

			}
			if (!Util.isWhiteSpace((char)b) &&   !Util.isSpecialChar((char)b))
				tmp.append( (char)b);
			
			if ((char)b == '/') {
				tmp = new StringBuilder();
				tmp.append("/");
			}
		}
		if (keywordFound) {
			if (result.toString().length() > 0 )
				return result.toString();
			else
				return tmp.toString();
		}
		return result.toString();
	}
	
	/***
	 * Checks if string is a reference to an object
	 * @param previousValue previous value
	 * @param start start counter in datastream
	 * @param objectData datastream
	 * @return reference or null if not found
	 */
	private static String CheckIfReference(String previousValue, int start, List<Byte> objectData) {
		
		StringBuffer sb = new StringBuffer();
		String p1 = "";
		String p2 = "";
		
		for (int i = start; i < objectData.size();i++ ) {
			byte b = objectData.get(i);
			if (Util.isSpecialChar((char)b) || Util.isWhiteSpace((char)b) ) {
				if (p1.equals("R")) return previousValue + " " + p2 + " " + p1 + " " + sb.toString();
				
				else if (Util.isSpecialChar((char)b)) { 
					if (sb.toString().equals("R")) return previousValue + " " + p1 + " " + sb.toString();
					return null;
				}
				else if (Util.isWhiteSpace((char)b)) {
					
					p2 = p1;
					p1 = sb.toString();
					sb = new StringBuffer();
				}
			}
			else {
				sb.append((char)b);
			}
				
			 
		}
		
		return null;
	}


	/***
	 * Gets value from string until whitespace
	 * @param str string to be parsed
	 * @return string with value
	 */
	public static String getValue(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<str.length(); i++) 
		{
			char c = str.charAt(i);
			char charUpper = Character.toUpperCase(c);
			if ((charUpper >= 'A' && charUpper <= 'Z') || (charUpper >='0' && charUpper <= '9')) {
				sb.append(c);
			}	

			if (isWhiteSpace(str.charAt(i)) && sb.length() > 0) return sb.toString();
		}


		return sb.toString();
	}
	
	/***
	 * Returns the contents of the file in a byte array.
	 * @param file to be read into bytearray
	 * @return bytearray with file contents
	 * @throws IOException if unable to read file or file to large to be processed
	 */
	public static byte[] getBytesFromFile(File file) throws Exception {
        if (file == null || !file.exists()) {
            throw new Exception("File is invalid, file was not found");
        }
        if (!file.canRead()) {
            throw new Exception("File could not be read. Check file permissions");
        }

        InputStream is = new FileInputStream(file);

	    long length = file.length();

	    if (length > Integer.MAX_VALUE) {
	        // File is too large
	    	throw new IOException("File to large");
	    }

	    byte[] bytes = new byte[(int)length];

	    int offset = 0;
	    int numRead;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }

	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }

	    is.close();
	    return bytes;
	}


	/***
	 * EndofStream checker
	 * @param copyOfRange bytearray
	 * @return true if end of stream met
	 */
	public static boolean endOfStream(byte[] copyOfRange) {
	
		byte [] endStream = {'e','n','d','s','t','r','e','a','m'};
		return ( new String(endStream).equals(new String(copyOfRange)));
		
	}


    public static  byte[] decodeFlate(byte[] data,int len) /*throws Exception*/ {

        int bufSize = 512000;
        ByteArrayOutputStream bos=null;
        boolean failed=true;

        /**
         * decompress byte[]
         */
        if (data != null) {

            int numberOfRetry = 5;
            while(failed==true){ //sometimes partly valid so loop to try this
                // create a inflater and initialize it Inflater inf=new Inflater();
                Inflater inf = new Inflater();
                inf.setInput(data);

                int size = data.length;
                bos = new ByteArrayOutputStream(size);

                if (size < bufSize)
                    bufSize = size;

                byte[] buf = new byte[bufSize];
                //int debug = 20;

                numberOfRetry -= 1;
                if (numberOfRetry < 1) { System.out.println("Retry failed"); return null;}
                int count;
                try{
                    while (!inf.finished()) {

                        count = inf.inflate(buf);

                        bos.write(buf, 0, count);

                        if (inf.getRemaining() == 0)
                            break;
                    }
                    failed=false;
                }catch (DataFormatException dfe) { // wrong format
                    return null;

                }catch(Exception ee){
                    failed=true;

                    if(data.length>10){
                        byte[] newData=new byte[data.length-1];
                        System.arraycopy(data,0,newData,0,data.length-1);
                        data=newData;
                    }else
                        failed=false;
                }

                //return bos.toByteArray();
            }
            data = bos.toByteArray();

        }

        return data;

    }

	public static String byteToString(List<Byte> bs) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bs) {
			sb.append((char)b);
		}
		return sb.toString();
	}

    public static HashSet<String> getWhiteList() {
        return Constants.WHITELIST;
    }


    /***
     * Remove Bold, Italic and BoldItalic from fontname
     * @param fontName
     * @return "real" font name
     */
    public static String cleanFontName(String fontName) {

        //fontName = fontName.replaceAll("-BoldItalic","").replaceAll("-Italic","").replaceAll("-Bold","");
        for (int f=0; f<Constants.VALIDFONTPOSTFIX.length;f++) {

            if (fontName.endsWith(Constants.VALIDFONTPOSTFIX[f])) {
                fontName = fontName.replaceAll(Constants.VALIDFONTPOSTFIX[f],"");
               break;
            }
        }

        return fontName;


    }
	
	
}
