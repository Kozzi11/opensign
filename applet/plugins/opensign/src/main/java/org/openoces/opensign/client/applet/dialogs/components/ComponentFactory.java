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

/* $Id: ComponentFactory.java,v 1.4 2012/09/27 11:03:44 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs.components;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public interface ComponentFactory {

    JButton createImageButton(String toolTipTextId, String imageFile);

    JButton createLinkImageButton(String textId, String toolTipTextId, String imageFile);

    JButton createLinkButton(String textId);

    JButton createNormalButton(String textId, boolean boldText);

    JButton createNormalTextButton(String text, boolean boldText);

    JComboBox createComboBox(ComboBoxModel model);

    JPanel createPanel();

    JLabel createHeaderLabel(String text);

    JLabel createTextLabel(String text);

    JLabel createLabel(String textId);

    JPasswordField createPasswordField();

    JScrollPane createScrollPane(Component component);

    JTextField createTextField();

    JTextField createTextField(int columns);

    JPanel createPanel(LayoutManager layoutManager);

    SignTextDisplay createSignTextDisplay(TextMimeType signTextFormat, String signText, String stylesheet) throws SigntextValidationException;

    JToolBar createToolBar();

    JTable createTable(TableModel model);
}