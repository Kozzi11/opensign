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

import org.openoces.opensign.pdf.validator.PDFException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Michael Martinsen <mma@openminds.dk>
 */
public class PDFSingleFileValidationResult {
    private boolean passed = false;

    private List<PDFException> errors;

    public PDFSingleFileValidationResult(boolean passed) {
        this.passed = passed;
        errors = new ArrayList<PDFException>();
    }

    public boolean hasPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public List<PDFException> getErrors() {
        return errors;
    }

    public void setErrors(List<PDFException> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PDFSingleFileValidationResult");
        sb.append("{passed=").append(passed);
        sb.append(", errors=").append(errors);
        sb.append('}');
        return sb.toString();
    }
}
