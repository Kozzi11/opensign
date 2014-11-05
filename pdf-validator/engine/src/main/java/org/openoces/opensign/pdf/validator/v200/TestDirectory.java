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
 * Class for testing all files in complete directory
 * output is written in outputlog.html which is located in the same directory as the pdf-files.  
 */
package org.openoces.opensign.pdf.validator.v200;

import org.openoces.opensign.pdf.validator.PDFException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestDirectory {

	public static void main(String[] args)  {

		/*directory which is to be tested */
         File dictoryName = new File("C:\\pdf\\examples");

		File[] listOfFiles = dictoryName.listFiles();

		File outputFile = new File(dictoryName.getPath() + File.separatorChar + "outputlog.html");
		
		if(outputFile.exists() && !outputFile.canWrite() ) {
			System.out.println("Unable to write log " + dictoryName.getPath() + File.separatorChar + "outputlog.html");
			System.exit(-1);
		}
		
	
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(outputFile));
	
		
		bw.write("<html>");
		bw.write("<body>");
		
		bw.write("<table border=\"0\">");


        String txt;
		for (File f : listOfFiles) {

		    if (!f.isFile())
                continue;

			PDFValidatorImpl tester = new PDFValidatorImpl();
			String[] fext = f.getName().split("\\.");
			if (fext.length < 2 || !(fext[fext.length -1 ]).equalsIgnoreCase("pdf")) {
				txt= "File is not a PDF file" ;
			}
			else if (f.canRead()) {
				txt="Passed";
				
				try {
					tester.readPDF(Util.getBytesFromFile(f), false);

                }
                catch (Exception pe) {
                    StringBuilder txtStr = new StringBuilder();
                    for(PDFException p :  tester.getErrorHandler().getErrorsFound() ) {
                        txtStr.append(p.getShortDescription() + " -> " + p.getLongDescription());
                    }
                    if (txtStr.length() < 1)
                        txt = pe.getMessage();
                    else
                        txt = txtStr.toString();
                }
			}
			else {

                txt = "Unable to read file";
			}

			bw.write("<tr><td><table border=\"1\">");
			if (txt.equals("Passed")) {
				bw.write("<tr bgcolor=\"#00db00\"> "); 
			}
			else { 
				bw.write("<tr bgcolor=\"#ea1d00\">"); 
			}
			
			bw.write("<td>Filename</td>");
			bw.write("<td>" + f.getName() + "</td></tr>");
			bw.write("<tr><td>PDF version</td><td>");
            if (tester.getPdfDoc() != null ) {
			    bw.write(tester.getPdfDoc().getPdfVersion());
            }
            else {
                bw.write("&nbsp;");
            }

			bw.write("</td></tr>");

			if (txt.equals("Passed")) {
				bw.write("<tr><td>Result</td><td>"); 
				bw.write(txt.toString());
				bw.write("</td></tr>");
				
			} else {
				bw.write("<tr><td>Errors</td><td>"); 
				bw.write(txt.toString());
				bw.write("</td></tr>");
			}
			bw.write("</table></td></tr>");
			bw.flush();
		}
		bw.write("</table>");
			
		bw.write("</body>");
		bw.write("</html>");
		bw.flush();
		bw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
