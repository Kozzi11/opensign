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

/* $Id: ConfirmInformationPanel.java,v 1.3 2012/09/26 09:32:19 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs.panel;

import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.dialogs.AbstractDialog;
import org.openoces.opensign.client.applet.dialogs.DlgConfirmInformation;
import org.openoces.opensign.client.applet.dialogs.GuiUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmInformationPanel extends AbstractActionsPanel {
    private JLabel title;
    private JLabel lInformation;
    protected JButton bOk;

    public ConfirmInformationPanel(CallBackHandler callbackHandler, final DlgConfirmInformation dialog, String title, String text) {
        super(callbackHandler);

        this.title = componentFactory.createHeaderLabel(title);

        lInformation = componentFactory.createTextLabel(text);

        bOk = componentFactory.createNormalButton("DLG_INFO_BUTTON_OK", true);

        closeButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                okClicked(dialog);
            }
        };

        bOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                okClicked(dialog);
            }
        });
    }

    private void createLabelForPanel(JPanel panel) {
        JLabel confirmInfoLabel = componentFactory.createTextLabel("");
        confirmInfoLabel.setText(title.getText() + lInformation.getText());
        confirmInfoLabel.setFocusable(true);
        confirmInfoLabel.setLabelFor(panel);
    }

    @Override
    protected JComponent[] getActionButtons() {
        return new JComponent[]{bOk};
    }

    @Override
    protected JComponent getContentPanel() {
        JPanel panel = componentFactory.createPanel(new GridBagLayout());
        int y = 0;
        y = GuiUtil.addSingleLine(panel, 0, y, title);
        y = GuiUtil.addSingleLine(panel, 0, y, lInformation);
        y = GuiUtil.addSingleButton(panel, 1, y, bOk);
        GuiUtil.addFillerLine(panel, y);
        createLabelForPanel(panel);

        return panel;
    }

    @Override
    public JComponent getFocusComponent() {
        return bOk;
    }


    @Override
    protected boolean addCloseButton() {
        return true;
    }

    protected void okClicked(AbstractDialog dialog) {
        dialog.hide();
    }
}
