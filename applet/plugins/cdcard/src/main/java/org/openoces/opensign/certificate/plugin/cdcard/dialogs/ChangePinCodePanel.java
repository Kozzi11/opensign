/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard


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

/* $Id: ChangePinCodePanel.java,v 1.5 2012/10/10 11:12:11 pakj Exp $ */

package org.openoces.opensign.certificate.plugin.cdcard.dialogs;

/**
 * This class represents an special panel used when the cdcard pincode is being changed
 *
 * @author Preben Valeur  <prv@tdc.dk>
 */

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.certificate.plugin.cdcard.util.AksClient;
import org.openoces.opensign.certificate.plugin.cdcard.util.AksResponse;
import org.openoces.opensign.certificate.plugin.cdcard.util.PrintUtil;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.dialogs.GuiUtil;
import org.openoces.opensign.client.applet.dialogs.OpenSignDialog;
import org.openoces.opensign.client.applet.dialogs.components.SignTextDisplay;
import org.openoces.opensign.client.applet.dialogs.components.TextMimeType;
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
import java.util.ArrayList;

public class ChangePinCodePanel extends AbstractActionsPanel {
    private static final long serialVersionUID = -5018125497301099401L;

    private boolean cancelled;
    private SignTextDisplay fullConditions;

    private JLabel title;


    // end interface
    private String oldPin = "";
    private String newPin;
    private String newPinCopy;
    private String nid;
    private String service;
    private String cpr;
    private AksResponse aksResponse;

    private JLabel resultText;
    private boolean displayEventsInResultText = true;

    private JLabel promptLabel;
    private java.util.List<JButton> actionButtons;

    private JLabel pinCodeLabel1;
    private JLabel pinCodeLabel2;

    private JComponent pinCodeField;
    private JComponent pinCodeCopyField;


    /**
     * Relevant params:
     */
    ChangePinCodePanel(CallBackHandler parent, final OpenSignDialog owner, String oldPin, String cpr, String nid, String service, String conditions, String lraInfo, CertificateHandler certificate, String title, int state) {
        super(parent);
        debug("ImportPanel <cons>(" + oldPin + ")");
        this.oldPin = oldPin;
        this.nid = nid;
        this.service = service;
        this.cpr = cpr;

        this.title = componentFactory.createHeaderLabel(title);


        promptLabel = componentFactory.createTextLabel(ConditionsHeaderPanel.getOkPrompt(state));

        actionButtons = new ArrayList<JButton>();

        JButton okButton = componentFactory.createNormalTextButton("OK", true);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isOk()) {
                    owner.hide();
                }
            }
        });
        actionButtons.add(okButton);

        if (conditions != null && conditions.trim().length() > 0 && conditions.indexOf("null") != 0) {
            // first the conditions:
            try {
                String con = ConditionsHeaderPanel.prependHeader(state, certificate, conditions, lraInfo);
                fullConditions = componentFactory.createSignTextDisplay(TextMimeType.PLAIN, con, null);
                JButton bPrint = componentFactory.createNormalTextButton("Udskriv...", false);
                bPrint.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        printClicked(owner);
                    }
                });
                actionButtons.add(bPrint);
            } catch (Exception e) {
                FileLog.error("Exception when adding cert info " + e.getMessage(), e);
            }
        }

        JButton cancelButton = componentFactory.createNormalTextButton("Annuller", false);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelClicked(owner);
            }
        });
        actionButtons.add(cancelButton);

        closeButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelClicked(owner);
            }
        };


        pinCodeLabel1 = componentFactory.createTextLabel("Opret PIN-kode");

        pinCodeField = createPinCodeField();

        pinCodeLabel2 = componentFactory.createTextLabel("Gentag PIN-kode");

        pinCodeCopyField = createPinCodeCopyField();

        resultText = componentFactory.createTextLabel(" ");

        GuiUtil.addEnterKeyListener(pinCodeCopyField, okButton);
    }

    @Override
    protected final JComponent[] getActionButtons() {
        return actionButtons.toArray(new JButton[actionButtons.size()]);
    }

    @Override
    protected final JComponent getContentPanel() {
        JPanel panel = componentFactory.createPanel(new BorderLayout());
        panel.add(title, BorderLayout.NORTH);

        JPanel pincodePanel = componentFactory.createPanel(new GridBagLayout());

        int y = 0;
        y = GuiUtil.addSingleLine(pincodePanel, 0, y, promptLabel);
        y = GuiUtil.addSingleLine(pincodePanel, 0, y, pinCodeLabel1);
        y = GuiUtil.addSingleLine(pincodePanel, 0, y, pinCodeField);
        y = GuiUtil.addSingleLine(pincodePanel, 0, y, pinCodeLabel2);
        y = GuiUtil.addSingleLine(pincodePanel, 0, y, pinCodeCopyField);
        y = GuiUtil.addSingleLine(pincodePanel, 0, y, resultText);
        GuiUtil.addFillerLine(pincodePanel, y);

        if(fullConditions != null) {
            JScrollPane scrollPane = componentFactory.createScrollPane(fullConditions);
            panel.add(scrollPane, BorderLayout.CENTER);
            panel.add(pincodePanel, BorderLayout.SOUTH);
        } else {
            panel.add(pincodePanel, BorderLayout.CENTER);
        }


        JPanel textAreaPanel = componentFactory.createPanel();
        textAreaPanel.add(fullConditions);
        panel.add(textAreaPanel, BorderLayout.CENTER);

        JPanel okPromptPanel = componentFactory.createPanel();
        okPromptPanel.add(promptLabel);

        JPanel southPanel = componentFactory.createPanel(new BorderLayout());
        southPanel.add(okPromptPanel, BorderLayout.NORTH);

        panel.add(southPanel, BorderLayout.SOUTH);

        JPanel p = componentFactory.createPanel(new GridLayout(1, 0));
        p.add(pinCodeLabel1);
        p.add(pinCodeField);

        p.add(pinCodeLabel2);
        p.add(pinCodeCopyField);

        panel.add(p, BorderLayout.CENTER);

        panel.add(resultText, BorderLayout.SOUTH);

        return panel;
    }

    @Override
    public final JComponent getFocusComponent() {
        return pinCodeField;
    }

    final boolean isOk() {
        return validateInput();
    }

    final AksResponse getAksResponse() {
        return aksResponse;
    }

    private void setResultText(String txt, boolean overwriteMsg) {
        if (txt == null) {
            txt = "";
        }
        if (displayEventsInResultText) {
            if (!overwriteMsg) {
                String oldText = resultText.getText();
                if (oldText == null || oldText.trim().length() == 0) {
                    resultText.setText(txt);
                }
            } else {
                resultText.setText(txt);
            }
        }
    }

    private void setResultText(String txt) {
        setResultText(txt, true);
    }

    private JComponent createPinCodeField() {
        final JPasswordField pinCodeField = componentFactory.createPasswordField();
        pinCodeField.setEchoChar('*');
        pinCodeField.setColumns(AksClient.PIN_CODE_LENGTH);
        Document document = pinCodeField.getDocument();
        document.addDocumentListener(
                new DocumentListener() {
                    public void insertUpdate(DocumentEvent e) {
                        newPin = new String(pinCodeField.getPassword());
                        displayEventsInResultText = true;
                        setResultText(getPinCodeStatusText(newPin, newPinCopy));
                    }

                    public void removeUpdate(DocumentEvent e) {
                        newPin = new String(pinCodeField.getPassword());
                        displayEventsInResultText = true;
                        setResultText(getPinCodeStatusText(newPin, newPinCopy));
                    }

                    public void changedUpdate(DocumentEvent e) {
                        newPin = new String(pinCodeField.getPassword());
                        displayEventsInResultText = true;
                        setResultText(getPinCodeStatusText(newPin, newPinCopy));
                    }
                }
        );
        pinCodeField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                pinCodeField.selectAll();
                setResultText(getPinCodeStatusText(newPin, newPinCopy), false);
            }

            public void focusLost(FocusEvent e) {
//                setResultText("");
            }
        });
        return pinCodeField;
    }

    private JComponent createPinCodeCopyField() {
        final JPasswordField pinCodeCopyField = componentFactory.createPasswordField();
        pinCodeCopyField.setEchoChar('*');
        pinCodeCopyField.setColumns(AksClient.PIN_CODE_LENGTH);
        Document document = pinCodeCopyField.getDocument();
        document.addDocumentListener(
                new DocumentListener() {

                    public void insertUpdate(DocumentEvent e) {
                        newPinCopy = new String(pinCodeCopyField.getPassword());
                        displayEventsInResultText = true;
                        setResultText(getPinCodeStatusText(newPin, newPinCopy));
                    }

                    public void removeUpdate(DocumentEvent e) {
                        newPinCopy = new String(pinCodeCopyField.getPassword());
                        displayEventsInResultText = true;
                        setResultText(getPinCodeStatusText(newPin, newPinCopy));
                    }

                    public void changedUpdate(DocumentEvent e) {
                        newPinCopy = new String(pinCodeCopyField.getPassword());
                        displayEventsInResultText = true;
                        setResultText(getPinCodeStatusText(newPin, newPinCopy));
                    }
                }
        );
        pinCodeCopyField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                pinCodeCopyField.selectAll();
                setResultText(getPinCodeStatusText(newPin, newPinCopy));
            }

            public void focusLost(FocusEvent e) {
                //            setResultText("");
            }
        });
        return pinCodeCopyField;
    }

    private String getPinCodeStatusText(String p, String p2) {
        if (p == null) {
            p = "";
        }
        if (p2 == null) {
            p2 = "";
        }

        boolean pinCodeIsValid = true;
        StringBuffer b = new StringBuffer("Opret PIN-kode til beskyttelse af Digital Signatur.\n");
        if (p.length() != AksClient.PIN_CODE_LENGTH) {
            b.append("- PIN-koden skal indeholde " + AksClient.PIN_CODE_LENGTH + " cifre\n");
            pinCodeIsValid = false;
        }

        if (!allDigits(p)) {
            b.append("- PIN-koden skal være et " + AksClient.PIN_CODE_LENGTH + "-cifret tal\n");
            pinCodeIsValid = false;
        }

        if (p.equals("") || !p.equals(p2)) {
            b.append("- De to adgangskoder skal være ens\n");
            pinCodeIsValid = false;
        }

        if (p.equals(oldPin)) {
            b.append("- Den nye PIN-kode skal være forskellig fra den gamle.\n");
            pinCodeIsValid = false;
        }

        if (pinCodeIsValid) {
            return "Du har oprettet og gentaget en gyldig PIN-kode.";
        }
        return b.toString();

    }

    private static boolean allDigits(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    private static boolean pinCodeValid(String pinCode) {

        if (pinCode == null) {
            return false;
        }
        // pinCode skal være PIN_CODE_LENGTH tegn
        return pinCode.length() == AksClient.PIN_CODE_LENGTH && allDigits(pinCode);
    }

    private boolean validateInput() {
        displayEventsInResultText = true;
//            setResultText("");

        if (newPin == null || newPin.equals("")) {
            setResultText("Indtast PIN-kode");
            return false;
        }

        if (!pinCodeValid(newPin)) {
            setResultText("Du skal indtaste en " + AksClient.PIN_CODE_LENGTH + "-cifret PIN-kode.");
            return false;
        }
        if (newPinCopy == null) {
            setResultText("Gentag PIN-koden.");
            return false;
        }
        if (!newPin.equals(newPinCopy)) {
            setResultText("De to PIN-koder skal være ens.");
            return false;
        }
        // ok, now check with AKS server!
        AksClient client = AksClient.getInstance();
        try {
            aksResponse = client.getPasswordAndChangePin(nid, oldPin, newPin, cpr, service);

            if (aksResponse.getStatus() != AksResponse.OK) {
                setResultText(aksResponse.getMsg());
                return false;
            }
        } catch (Exception e) {
            setResultText("Der skete en fejl (" + e.getMessage() + ")");
            return false;
        }
        return true;
    }


    private void debug(String message) {
        FileLog.debug(message);
    }

    public final boolean isCancelled() {
        return cancelled;
    }

    private void cancelClicked(OpenSignDialog dialog) {
        cancelled = true;
        dialog.hide();
    }

    private void printClicked(OpenSignDialog dialog) {
        PrintUtil.print(dialog, "Aftalevilkår", fullConditions.getText());
    }
}