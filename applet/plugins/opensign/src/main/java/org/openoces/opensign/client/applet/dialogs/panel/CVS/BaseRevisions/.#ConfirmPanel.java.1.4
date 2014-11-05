/*
	Copyright 2011 Paw F. Kjeldgaard
	Copyright 2011 Daniel Andersen

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

 * @author Daniel Andersen <daand@nets.eu>
*/

/* $Id: ConfirmPanel.java,v 1.4 2012/09/27 11:03:48 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs.panel;

import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.dialogs.DlgConfirm;
import org.openoces.opensign.client.applet.dialogs.GuiUtil;
import org.openoces.opensign.client.applet.dialogs.OpenSignDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmPanel extends AbstractActionsPanel {
    protected JButton bCancel;
    protected JButton bOk;
    protected JLabel title;
    protected JLabel[] msgLabels;
    protected boolean cancelled;

    public ConfirmPanel(CallBackHandler callbackHandler, final DlgConfirm dialog, String title, String confirmationMsg) {
        super(callbackHandler);

        this.title = componentFactory.createHeaderLabel(title);

        String[] messages = confirmationMsg.split("\\n");
        msgLabels = new JLabel[messages.length];
        for (int i = 0; i < messages.length; i++)  {
            msgLabels[i] = componentFactory.createTextLabel(messages[i]);
        }

        bCancel = componentFactory.createNormalButton("DLG_PASSWORD_BUTTON_CANCEL", false);
        bOk = componentFactory.createNormalButton("DLG_PASSWORD_BUTTON_OK", true);



        bCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelClicked(dialog);
            }
        });
        bOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                okClicked(dialog);
            }
        });


        closeButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelClicked(dialog);
            }
        };

    }

    @Override
    protected JComponent[] getActionButtons() {
        return new JComponent[]{bOk, bCancel};
    }

    @Override
    protected JComponent getContentPanel() {
        JPanel panel = componentFactory.createPanel(new GridBagLayout());

        int y = 0;
        y = GuiUtil.addSingleLine(panel, 0, y, title);
        for (int i = 0; i < msgLabels.length; i++) {
            y = GuiUtil.addSingleLine(panel, 0, y, msgLabels[i]);
        }
        GuiUtil.addFillerLine(panel, y);

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

    public boolean isCancelled() {
        return cancelled;
    }

    protected void okClicked(OpenSignDialog dialog) {
        cancelled = false;
        dialog.hide();
    }

    private void cancelClicked(OpenSignDialog dialog) {
        cancelled = true;
        dialog.hide();
    }
}