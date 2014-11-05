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

/* $Id: AttachmentListPanel.java,v 1.4 2012/09/27 11:03:49 pakj Exp $ */

package org.openoces.opensign.client.applet.attach.dialogs;

import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.attach.AttachmentImpl;
import org.openoces.opensign.client.applet.attach.AttachmentListViewDialog;
import org.openoces.opensign.client.applet.attach.resources.AttachmentResourceManager;
import org.openoces.opensign.client.applet.dialogs.GuiUtil;
import org.openoces.opensign.client.applet.dialogs.panel.AbstractActionsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AttachmentListPanel extends AbstractActionsPanel {
    private JLabel titleLabel;
    private JButton okBtn;
    private JScrollPane scrollPane;

    public AttachmentListPanel(CallBackHandler callbackHandler, final AttachmentListViewDialog dialog) {
        super(callbackHandler);

        titleLabel = componentFactory.createHeaderLabel(AttachmentResourceManager.getString("ATTACHMENTS"));

        okBtn = componentFactory.createNormalButton("DLG_PASSWORD_BUTTON_OK", true);
        okBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.hide();
            }
        });

        closeButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.hide();
            }
        };

        AttachmentImpl[] attachments = dialog.getAttachments();

        JPanel attachmentsPanel = componentFactory.createPanel(new GridBagLayout());

        int y = 0;
        for (AttachmentImpl a : attachments) {
            a.setDialogOwner(dialog);

            addComponent(attachmentsPanel, 0, y, componentFactory.createTextLabel(a.getTitle()));
            addComponent(attachmentsPanel, 1, y, componentFactory.createTextLabel(Long.toString(a.getSize()) + " bytes"));

            JButton saveButton = componentFactory.createNormalTextButton(AttachmentResourceManager.getString("SAVE"), false);
            saveButton.addActionListener(a);
            saveButton.setActionCommand("SAVE");
            addComponent(attachmentsPanel, 2, y, saveButton);

            JButton viewButton = componentFactory.createNormalTextButton(AttachmentResourceManager.getString("VIEW"), false);
            viewButton.setEnabled(a.isViewable());
            viewButton.addActionListener(a);
            viewButton.setActionCommand("VIEW");
            addComponent(attachmentsPanel, 3, y, viewButton);

            JLabel statusIndicator = componentFactory.createTextLabel("");
            if (a.isHasSeen()) {
                statusIndicator.setText(AttachmentResourceManager.getString("FETCHED"));
            } else {
                if (a.isOptional()) {
                    statusIndicator.setText(AttachmentResourceManager.getString("NOT_FETCHED_IS_OPTIONAL"));
                } else {
                    statusIndicator.setText(AttachmentResourceManager.getString("NOT_FETCHED_IS_MANDATORY"));
                }
            }
            new SeenObserver(a, statusIndicator);

            addComponent(attachmentsPanel, 4, y, statusIndicator);

            y++;
        }

        JPanel panel = componentFactory.createPanel(new BorderLayout());
        panel.add(attachmentsPanel, BorderLayout.NORTH);

        // add a ScrollPane:
        scrollPane = componentFactory.createScrollPane(panel);
    }

    private void addComponent(JPanel panel, int x, int y, Component component) {
        GridBagConstraints gbc = GuiUtil.getDefaultConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(2, 2, 2, 2);

        panel.add(component, gbc);
    }

    @Override
    protected JComponent[] getActionButtons() {
        return new JComponent[]{okBtn};
    }

    @Override
    protected JComponent getContentPanel() {

        JPanel panel = componentFactory.createPanel(new BorderLayout());
        panel.add(this.titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel filler = componentFactory.createPanel(new GridBagLayout());
        GuiUtil.addFillerLine(filler, 0);

        panel.add(filler, BorderLayout.SOUTH);

        return panel;
    }

    @Override
    public JComponent getFocusComponent() {
        return okBtn;
    }

    @Override
    protected boolean addCloseButton() {
        return true;
    }
}