/*
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

package org.openoces.opensign.pdf.validator.client;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is a validation result holding the validation result for any number of PDF files.
 *
 * @author: Michael Martinsen <mma@openminds.dk>
 */
public class PDFFilesValidationResult {

    private Map<File, PDFSingleFileValidationResult> filesValidated;

    public PDFFilesValidationResult() {
        filesValidated = new HashMap<File, PDFSingleFileValidationResult>();
    }

    public void addSingleValidationResult(File fileValidated, PDFSingleFileValidationResult validationResult) {
        if (fileValidated == null) {
            throw new IllegalArgumentException("File is null.");
        }
        if (validationResult == null) {
            throw new IllegalArgumentException("ValidationResult is null.");
        }

        filesValidated.put(fileValidated, validationResult);
    }

    /**
     * The overall validation is only successful if all single files have been
     * successfully validated.
     * @return true if overall successful, otherwise false
     */
    public boolean hasPassed() {
        for (File validatedFile : filesValidated.keySet()) {
            PDFSingleFileValidationResult singleFileValidationResult = filesValidated.get(validatedFile);
            if ( ! singleFileValidationResult.hasPassed()) {
                return false;
            }
        }

        return true;
    }

    public PDFSingleFileValidationResult getSingleValidationResult(File fileToGetValidationResultFor) {
        if (filesValidated.containsKey(fileToGetValidationResultFor)) {
            return filesValidated.get(fileToGetValidationResultFor);
        } else {
            throw new IllegalStateException("Expected file " + fileToGetValidationResultFor + " is not present in files map!");
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PDFFilesValidationResult");
        sb.append("{filesValidated=").append(filesValidated);
        sb.append('}');
        return sb.toString();
    }
}
