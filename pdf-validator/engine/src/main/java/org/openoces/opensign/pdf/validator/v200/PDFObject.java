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

import java.util.ArrayList;
import java.util.List;

public class PDFObject {

	/***
	 * Name of object
	 */
	private String objectName;
	
	/***
	 * Generation
	 */
	private String generationsNumber;
	
	/***
	 * Object data
	 */
	private List<Byte> objectData;
	
	/***
	 * Data as a string
	 */
	private StringBuffer sb;
	
	/***
	 * Object type
	 */
	private String objectType;
	
	/***
	 * Dictionary type
	 */
	private String dictionaryType;
	
	/***
	 * "Page" number
	 */
	private int pageNo;
	
	public PDFObject() {
		super();
		objectData = new ArrayList<Byte>();
		sb = new StringBuffer();
		objectType = "";
		pageNo = 0;
	}

	public String getGenerationsNumber() {
		return generationsNumber;
	}

	public void setGenerationsNumber(String generationsNumber) {
		this.generationsNumber = generationsNumber;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public List<Byte> getObjectData() {
		return objectData;
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/***
	 * Adds a byte to object data unless char is newline and previous char was \ 
	 * @param addObjectData
	 */
	public void addObjectData(byte addObjectData) {
		
		//do not add char if char is \ + newline
		if (addObjectData==13 && (this.objectData.size() > 1) &&  this.objectData.get(this.objectData.size() - 1)==92 ) {
			this.objectData.remove(this.objectData.size() - 1);
			sb.deleteCharAt(sb.length() - 1);
		}
		else {
			this.objectData.add(addObjectData);
			sb.append((char)addObjectData);
		}
	}
			
	public String getDictionaryType() {
		return dictionaryType;
	}

	public void setDictionaryType(String dictionaryType) {
		this.dictionaryType = dictionaryType;
	}

	@Override
	public String toString() {
		return "PDFObject [objectName=" + objectName + ", generationsNumber="
				+ generationsNumber + ", sb=" + sb + ", objectType="
				+ objectType + ", dictionaryType=" + dictionaryType
				+ ", pageNo=" + pageNo + "]";
	}

	/**
	 * extract data
	 * @return data
	 */
	public String getData() {
		StringBuilder rtn=new StringBuilder("");
		for (int i=0; i<objectData.size();i++) {
			byte b = (byte)objectData.get(i);
			if ((Util.isWhiteSpace((char)b) || Util.isSpecialChar((char)b))) ;

			else
				rtn.append((char)b);
		}

		return rtn.toString();
	}


	public void addObjectData(byte[] bytes) {
		for (int i=0; i< bytes.length; i++) {
			addObjectData(bytes[i]);
		}

	}

}
