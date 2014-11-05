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

public class PDFstreamObject {

	/***
	 * byte array of stream data
	 */
	private List<Byte> streamBuffer;
	/***
	 * Stream name
	 */
	private String streamName;
	/***
	 * Stream generation
	 */
	private String streamGeneration;
	/***
	 * Data Length
	 */
	private int streamLength;

	/***
	 * List of Filters
	 */
	private List<String> filters = new ArrayList<String>();

	private int pageNumber;
	
	/***
	 * Constructor
	 * @param streamName
	 * @param streamGeneration
	 */
	public PDFstreamObject(String streamName, String streamGeneration, int pageNumber) {
		super();

		this.streamName = streamName;
		this.streamGeneration = streamGeneration;
		this.streamBuffer = new ArrayList<Byte>();
		streamLength = 0;
		this.pageNumber = pageNumber;
		
	}
	
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}



	public int getStreamLength() {
		return streamLength;
	}

	public void setStreamLength(int streamLength) {
		this.streamLength = streamLength;
	}
	
	public String getStreamGeneration() {
		return streamGeneration;
	}

	public void setStreamGeneration(String streamGeneration) {
		this.streamGeneration = streamGeneration;
	}

	public List<Byte> getStreamBuffer() {
		return streamBuffer;
	}

	public void setStreamBuffer(List<Byte> streamBuffer) {
		this.streamBuffer = streamBuffer;
	}

	public void addToStreamBuffer(byte b) {
		streamBuffer.add(b);
	}

	public String getStreamName() {
		return streamName;
	}

	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}

	public List<String> getFilters() {
		return filters;
	}

	public void setFilters(List<String> filters) {
		this.filters = filters;
	}

	public void addFilter(String filter) {
		this.filters.add(filter);
	}

	@Override
	public String toString() {

		return "PDFstreamObject [streamName=" + streamName  
				/*+ ", streamBuffer=" + streamBuffer*/  + ", streamLength="
				+ streamLength + ", filters=" + filters +/* ", streamBufferTxt=" + stream2String() +*/  "]";
	}

	/***
	 * removes the first new line and removes the new line + endstream tag 
	 * Most pdfs do have the length mixed up so this creates only a warning
	 * streams are the data between stream &lt;cr+nl&gt; or &lt;cr&gt; and 
	 * &lt;cr&gt;endstream 
	 */
	public void cleanUpStream(int len) throws IndexOutOfBoundsException {
		
		
		try {
			int removeExtraFromStart = 0;
			int removeExtraFromEnd   = 0;
			if (streamBuffer.size() > 0 && streamBuffer.get(0) == 13 && streamBuffer.get(1) == 10) {
				removeExtraFromStart = 2;
			}
			else if (streamBuffer.get(0) == 10) {
				removeExtraFromStart = 1;
			}
			if ( streamBuffer.size() > 2 && streamBuffer.get(streamBuffer.size() - 2 ) == 13 &&  streamBuffer.get(streamBuffer.size() - 1 ) == 10) {
				removeExtraFromEnd = 2;
			}
			else if ( streamBuffer.get(streamBuffer.size() - 1 ) == 10 ) {
				removeExtraFromEnd = 1;

			}
					
			if (removeExtraFromStart > streamBuffer.size() - removeExtraFromEnd) {
				streamBuffer =  new ArrayList<Byte>();
			}
			else {	
				List<Byte> tmpBuffer = streamBuffer.subList( removeExtraFromStart, streamBuffer.size() - (removeExtraFromEnd));
				streamBuffer = tmpBuffer;
				tmpBuffer = null;
			}
		}
		catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}


}
