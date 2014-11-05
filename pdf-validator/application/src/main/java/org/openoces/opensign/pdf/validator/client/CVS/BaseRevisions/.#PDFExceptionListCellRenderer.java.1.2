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

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;

/**
 * @author: Michael Martinsen <mma@openminds.dk>
 */
public class PDFExceptionListCellRenderer extends DefaultTableCellRenderer implements ApplicationLabels {
    private PdfValidatorApplication pdfValidatorApplication;

    public PDFExceptionListCellRenderer(PdfValidatorApplication pdfValidatorApplication) {
        this.pdfValidatorApplication = pdfValidatorApplication;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.setText(getCellText(value));

        if (isSelected) {
            setBackground((Color) UIManager.get("Table.selectionBackground"));
        } else {
            setBackground((Color) UIManager.get("Table.background"));
        }

        return this;
    }

    private String getCellText(Object value) {
        String cellText = null;

        if (value instanceof java.util.List) {
            List<PDFException> errors = (List<PDFException>) value;
            if (errors.size() > 0) {
                cellText = errors.get(0).getShortDescription();
                cellText += pdfValidatorApplication.resolveLabel(LABEL_KEY_PDF_FILES_LIST_DOUBLE_CLICK_MSG);
            }
        } else {
            cellText = value.toString();
        }

        return cellText;
    }

}
