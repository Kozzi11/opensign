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

/* $Id: FileUtilsTest.java,v 1.2 2012/02/28 08:22:00 pakj Exp $ */

package org.openoces.opensign.utils;

import java.io.File;

/**
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 */

import junit.framework.Assert;
import junit.framework.TestCase;

public class FileUtilsTest extends TestCase {
	private File testFile;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		testFile = new File("text.txt");
		testFile.createNewFile();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		testFile.delete();
	}

	public void testGetFolderAndFileName() throws Exception {
		String[] folderAndFileName = FileUtils.getFolderAndFileName(testFile);
		Assert.assertEquals("text.txt", folderAndFileName[1]);
	}

}
