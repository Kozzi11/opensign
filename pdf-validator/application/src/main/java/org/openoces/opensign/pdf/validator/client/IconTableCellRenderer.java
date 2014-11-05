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

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @author: Michael Martinsen <mma@openminds.dk>
 */
class IconTableCellRenderer extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column
        );
        label.setText("");
        label.setIcon(null);
        if (value instanceof String) {
            // value is of type String when a file is added (and no validation has been performed)
            if (isSelected) {
                setBackground((Color) UIManager.get("Table.selectionBackground"));
            } else {
                setBackground((Color) UIManager.get("Table.background"));
            }
        } else if (value instanceof ValidationStatus) {
            ValidationStatus validationStatus = (ValidationStatus) value;
            label.setIcon(validationStatus.getStatusIcon());
            if (validationStatus.isPassed()) {
                label.setBackground(Color.GREEN);
            } else {
                label.setBackground(Color.RED);
            }
        }

        label.setHorizontalAlignment(SwingConstants.CENTER);

        return label;
    }

}
