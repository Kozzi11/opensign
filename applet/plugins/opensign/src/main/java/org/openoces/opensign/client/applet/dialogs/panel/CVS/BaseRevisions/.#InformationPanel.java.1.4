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

/* $Id: InformationPanel.java,v 1.4 2012/09/27 11:03:48 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs.panel;

import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.dialogs.DlgInformation;
import org.openoces.opensign.client.applet.dialogs.GuiUtil;
import org.openoces.opensign.client.applet.dialogs.OpenSignDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformationPanel extends AbstractPanel {
    private JLabel title;
    private JLabel lInformation;

    public InformationPanel(CallBackHandler callbackHandler, final DlgInformation dialog, String title, String text) {
        super(callbackHandler);

        this.title = componentFactory.createHeaderLabel(title);

        lInformation = componentFactory.createTextLabel(text);

        closeButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                okClicked(dialog);
            }
        };
    }

    @Override
    protected JComponent getActionPanel() {
        return null;
    }

    @Override
    protected JComponent getContentPanel() {
        JPanel panel = componentFactory.createPanel(new GridBagLayout());

        int y = 0;
        y = GuiUtil.addSingleLine(panel, 0, y, title);
        y = GuiUtil.addSingleLine(panel, 0, y, lInformation);
        GuiUtil.addFillerLine(panel, y);

        return panel;
    }

    @Override
    public JComponent getFocusComponent() {
        return closeButton;
    }


    @Override
    protected boolean addCloseButton() {
        return true;
    }

    protected void okClicked(OpenSignDialog dialog) {
        dialog.hide();
    }
}