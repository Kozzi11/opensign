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

/* $Id: ConditionsPanel.java,v 1.4 2012/09/27 11:03:46 pakj Exp $ */

package org.openoces.opensign.certificate.plugin.cdcard.dialogs;

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.certificate.plugin.cdcard.util.CprChecker;
import org.openoces.opensign.certificate.plugin.cdcard.util.PrintUtil;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.dialogs.GuiUtil;
import org.openoces.opensign.client.applet.dialogs.OpenSignDialog;
import org.openoces.opensign.client.applet.dialogs.panel.AbstractActionsPanel;
import org.openoces.opensign.utils.FileLog;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ConditionsPanel extends AbstractActionsPanel {
    private boolean cancelled;

    private String fullConditions;
    private JTextField cprField;
    private JLabel msgLabel;
    private String cprNumber;
    private static String ERROR_CPR = "Fejl i CPR nummer - pr�v igen";
    private static String blanks = "                                     ".substring(0, ERROR_CPR.length());
    private int state = ConditionsHeaderPanel.ACTIVATE;

    private JLabel title;
    private JTextField ta;
    private JLabel cprLabel;

    private JButton bCancel;
    private JButton bOk;
    private JButton bPrint;

    public ConditionsPanel(CallBackHandler callbackHandler, final OpenSignDialog dialog, CertificateHandler certificate, String title, String conditions, int state) {
        super(callbackHandler);

        this.title = componentFactory.createHeaderLabel(title);

        this.state = state;

        this.msgLabel = componentFactory.createTextLabel(blanks);

        try {
            fullConditions = ConditionsHeaderPanel.prependHeader(state, certificate, conditions);
        } catch (Exception e) {
            FileLog.error("Exception when adding cert info " + e.getMessage());
        }

        ta = componentFactory.createTextField();
        ta.setText(fullConditions);
        ta.setEditable(false);

        cprField = componentFactory.createTextField();
        Document document = cprField.getDocument();
        document.addDocumentListener(
                new DocumentListener() {
                    public void insertUpdate(DocumentEvent e) {
                        cprNumber = cprField.getText();
                    }

                    public void removeUpdate(DocumentEvent e) {
                        cprNumber = cprField.getText();
                    }

                    public void changedUpdate(DocumentEvent e) {
                        cprNumber = cprField.getText();
                    }
                }
        );

        cprField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                msgLabel.setText(blanks);
            }

            public void focusLost(FocusEvent e) {
                cprNumber = cprField.getText();
                // validate
                if (!CprChecker.valid(cprNumber)) {
                    msgLabel.setForeground(Color.red);
                    msgLabel.setText(ERROR_CPR);
                }
            }
        });

        cprLabel = componentFactory.createTextLabel("CPR:");

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

        bPrint = componentFactory.createNormalButton("DLG_PRINT_CONDITIONS", false);

        bPrint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                printClicked(dialog);
            }
        });
    }


    @Override
    protected final JComponent[] getActionButtons() {
        return new JComponent[]{bOk, bPrint, bCancel};
    }

    @Override
    protected final JComponent getContentPanel() {
        JPanel panel = componentFactory.createPanel(new GridBagLayout());

        JLabel stateLabel = componentFactory.createTextLabel(ConditionsHeaderPanel.getOkPrompt(state));

        int y = 0;
        y = GuiUtil.addSingleLine(panel, 0, y, title);
        y = GuiUtil.addSingleLine(panel, 0, y, stateLabel);
        y = GuiUtil.addSingleLine(panel, 0, y, cprLabel);
        y = GuiUtil.addTextFieldLine(panel, 0, y, cprField);
        y = GuiUtil.addTextFieldLine(panel, 0, y, ta);
        y = GuiUtil.addSingleLine(panel, 0, y, msgLabel);
        GuiUtil.addFillerLine(panel, y);

        return panel;
    }

    @Override
    public final JComponent getFocusComponent() {
        return bOk;
    }

    @Override
    protected final boolean addCloseButton() {
        return true;
    }

    public final boolean isCancelled() {
        return cancelled;
    }

    public final String getCprNumber() {
        return cprNumber;
    }

    protected final void okClicked(OpenSignDialog dialog) {
        if (!CprChecker.valid(cprNumber)) {
            msgLabel.setForeground(Color.red);
            msgLabel.setText(ERROR_CPR);
            return;
        }
        cancelled = false;
        dialog.hide();
    }

    private void cancelClicked(OpenSignDialog dialog) {
        cancelled = true;
        dialog.hide();
    }

    private void printClicked(OpenSignDialog dialog) {
        PrintUtil.print(dialog, "Aftalevilkår", fullConditions);
    }
}