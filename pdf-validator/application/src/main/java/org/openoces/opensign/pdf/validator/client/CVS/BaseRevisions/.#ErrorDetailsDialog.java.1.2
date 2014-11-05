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
import java.awt.*;
import java.util.List;

/**
 * @author: Michael Martinsen <mma@openminds.dk>
 */
public class ErrorDetailsDialog extends JDialog implements ApplicationLabels {
    public static final Dimension DEFAULT_DIMENSION = new Dimension(750, 500);

    private PdfValidatorApplication pdfValidatorApplication;
    private JTable errorsTable;
    private NonEditableTableModel errorsTableModel;

    public ErrorDetailsDialog(PdfValidatorApplication owner) {
        super(owner, true);
        this.pdfValidatorApplication = owner;
        init(owner);
    }

    private void init(Frame owner) {
        this.setSize(DEFAULT_DIMENSION);
        this.setTitle(pdfValidatorApplication.resolveLabel(LABEL_KEY_ERRORS_DETAILS_TITLE));
        this.setLocationRelativeTo(owner);    //centre on screen
        this.getContentPane().add(getCenter());
    }

    private JPanel getCenter() {
        JPanel panel = new JPanel(new BorderLayout());

        // Force some space around the entire main content
        panel.setBorder(BorderFactory.createLineBorder(panel.getBackground(), 5));

        errorsTableModel = new NonEditableTableModel();
        errorsTableModel.addColumn(pdfValidatorApplication.resolveLabel(LABEL_KEY_ERRORS_DETAILS_COLUMN_1));
        errorsTableModel.addColumn(pdfValidatorApplication.resolveLabel(LABEL_KEY_ERRORS_DETAILS_COLUMN_2));
        errorsTable = new JTable(errorsTableModel);

        errorsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(errorsTable);
        errorsTable.getColumnModel().getColumn(0).setMaxWidth(50);
        errorsTable.getColumnModel().getColumn(0).setCellRenderer(new HorizontallyCenteredTableCellRender());
        errorsTable.getColumnModel().getColumn(1).setCellRenderer(new MultilineTextCellRenderer());

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public void show(List<PDFException> validationExceptions) {
        for (int i = 0; i < validationExceptions.size(); i++) {
            PDFException pdfException = validationExceptions.get(i);
            errorsTableModel.addRow(new Object[] {i++, pdfException.getLongDescription()});
            errorsTableModel.fireTableDataChanged();
        }

        setVisible(true);
    }

}
