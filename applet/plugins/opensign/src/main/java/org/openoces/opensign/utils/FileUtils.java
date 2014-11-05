package org.openoces.opensign.utils;

/*
Copyright 2006 Paw F. Kjeldgaard


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
*/

/* $Id: FileUtils.java,v 1.2 2012/02/28 08:21:57 pakj Exp $ */

import java.io.File;

/**
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 */

public class FileUtils {

    protected FileUtils() {}
	
	public static String[] getFolderAndFileName(File file) {
		String absolutePath = file.getAbsolutePath();
		String fileSeperator = File.separator;
		int startIndex = absolutePath.lastIndexOf(fileSeperator);
		if(startIndex != -1) {
			String dir = absolutePath.substring(0, startIndex);
			String name = absolutePath.substring(startIndex + fileSeperator.length());
			return new String[]{dir, name};
		} else {
			return new String[]{absolutePath, ""};
		}
	}
	
	public static String getFileName(File file) {
		String absolutePath = file.getAbsolutePath();
		return getFileName(absolutePath);
	}
	
	public static String getFileName(String absolutePath) {
		String fileSeperator = File.separator;
		int startIndex = absolutePath.lastIndexOf(fileSeperator);
		if(startIndex != -1) {
			return absolutePath.substring(startIndex + fileSeperator.length());
		} else {
			return absolutePath;
		}
	}

}
