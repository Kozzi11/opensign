/*
	Copyright 2011 Paw F. Kjeldgaard

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

/* $Id: OpensignComboboxUI.java,v 1.5 2012/09/27 11:03:44 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs.components;

import org.openoces.opensign.client.applet.dialogs.GuiUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalComboBoxButton;
import javax.swing.plaf.metal.MetalComboBoxUI;
import java.awt.*;

public class OpensignComboboxUI extends MetalComboBoxUI {

    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
        // Do not paint the ocean background - it does not fit with NemID design
    }

    /**
     * Paints the currently selected item.
     */
    public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus) {
        // Do not paint the ocean surroundings - it does not fit with NemID
        // design
        ListCellRenderer renderer = comboBox.getRenderer();
        Component c;

        if (hasFocus && !isPopupVisible(comboBox)) {
            c = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, true, false);
        } else {
            c = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, false, false);
            c.setBackground(UIManager.getColor("ComboBox.background"));
        }
        c.setFont(comboBox.getFont());
        if (hasFocus && !isPopupVisible(comboBox)) {
            c.setForeground(listBox.getSelectionForeground());
            c.setBackground(listBox.getSelectionBackground());
        } else {
            if (comboBox.isEnabled()) {
                c.setForeground(comboBox.getForeground());
                c.setBackground(comboBox.getBackground());
            } else {
                c.setForeground(UIManager.getColor("ComboBox.disabledForeground") == null ? Color.darkGray : UIManager
                        .getColor("ComboBox.disabledForeground"));
                c.setBackground(UIManager.getColor("ComboBox.disabledBackground") == null ? Color.white : UIManager
                        .getColor("ComboBox.disabledBackground"));
            }
        }

        // Fix for 4238829: should lay out the JPanel.
        boolean shouldValidate = false;
        if (c instanceof JPanel) {
            shouldValidate = true;
        }

        currentValuePane.paintComponent(g, c, comboBox, bounds.x, bounds.y, bounds.width, bounds.height, shouldValidate);

        arrowButton.setBackground(c.getBackground());
    }

    public static ComponentUI createUI(JComponent c) {
        return new OpensignComboboxUI();
    }

    /**
     * Returns the area that is reserved for drawing the currently selected
     * item.
     */
    protected Rectangle rectangleForCurrentValue() {
        int width = comboBox.getWidth();
        int height = comboBox.getHeight();
        Insets insets = getInsets();
        int buttonSize = height - (insets.top + insets.bottom);
        if (arrowButton != null) {
            buttonSize = 0; // arrowButton.getWidth();
        }
        return new Rectangle(insets.left, insets.top, width - (insets.left + insets.right + buttonSize), height
                - (insets.top + insets.bottom));
    }

    protected JButton createArrowButton() {
        JButton button = new MetalComboBoxButton(comboBox, new ImageIcon(GuiUtil.createImage("combobutton.jpg")),
                true, currentValuePane, listBox) {

            public void paintComponent(Graphics g) {

                Insets insets = getInsets();

                int width = getWidth() - (insets.left + insets.right);
                int height = getHeight() - (insets.top + insets.bottom);

                if (height <= 0 || width <= 0) {
                    return;
                }

                int top = insets.top;
                int bottom = top + (height - 1);
                int left = insets.left;
                int right = left + (width - 1);

                int iconWidth = 0;
                int iconLeft = right;

                // Paint the icon
                iconWidth = comboIcon.getIconWidth();
                int iconHeight = comboIcon.getIconHeight();
                int iconTop = 0;

                iconLeft = (left + (width - 1)) - iconWidth;
                iconTop = (top + ((bottom - top) / 2)) - (iconHeight / 2);

                if (!comboBox.isEnabled()) {
                    g.setColor(UIManager.getColor("ComboBox.disabledBackground"));
                } else {
                    g.setColor(getBackground());
                }

                g.fillRect(0, 0, getWidth(), getHeight());
                comboIcon.paintIcon(this, g, iconLeft, iconTop);
            }
        };
        button.setRolloverEnabled(false);

        button.setBorder(new EmptyBorder(1, 3, 0, 3));
        button.setMargin(new Insets(2, 2, 2, 2));


        button.setOpaque(true);
        return button;
    }


}