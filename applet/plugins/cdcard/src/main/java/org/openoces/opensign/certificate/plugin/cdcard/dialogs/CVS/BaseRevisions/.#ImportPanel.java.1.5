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

/* $Id: ImportPanel.java,v 1.5 2012/10/10 11:12:11 pakj Exp $ */

package org.openoces.opensign.certificate.plugin.cdcard.dialogs;

/**
 * This class represents the panel used in the dialog displayed when the import dialog
 * is being displayed.
 *
 * @author Preben Valeur  <prv@tdc.dk>
 */

import org.openoces.opensign.certificate.CertificateInfo;
import org.openoces.opensign.certificate.plugin.cdcard.util.AksClient;
import org.openoces.opensign.certificate.plugin.cdcard.util.AksResponse;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.OS;
import org.openoces.opensign.client.applet.SetPollApplet;
import org.openoces.opensign.client.applet.dialogs.GuiUtil;
import org.openoces.opensign.client.applet.dialogs.panel.AbstractActionsPanel;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.utils.X500Name;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.StringTokenizer;

class ImportPanel extends AbstractActionsPanel {
    private String nid = "";
    private String pinCode = "";
    private String alias;
    private String pkcs12password;
    private String passphrase;
    private String passphrase1;

    private JTextField resultText;
    private JPasswordField pinCodeField;
    private JButton cancelButton;
    private JButton okButton;
    private boolean displayEventsInResultText = true;

    private JLabel pinCodeLabel;
    private JLabel aliasLabel;
    private JTextField aliasField;

    private JLabel passphraseLabel;
    private JPasswordField passphraseField;

    private JLabel passphrase1Label;
    private JPasswordField passphrase1Field;

    private JLabel title;

    /**
     * Relevant params:
     */
    ImportPanel(final CallBackHandler callBackHandler, final DlgImport dialog, String title, String serviceParams) {
        super(callBackHandler);

        this.title = componentFactory.createHeaderLabel(title);

        StringTokenizer strtok = new StringTokenizer(serviceParams, ",");
        nid = strtok.nextToken();
        String firstNameOwns = "Min";
        if (strtok.hasMoreTokens()) {
            // we also have a certificate!
            CertificateInfo certInfo = OS.getCryptoSupport().getCertInfo(strtok.nextToken());
            try {
                X500Name name = new X500Name(certInfo.getSubjectDN().getName());
                String cn = name.getCN();
                String firstName = cn.substring(0, cn.indexOf(' '));
                if (firstName.endsWith("s")) {
                    firstNameOwns = firstName + "'";
                } else {
                    firstNameOwns = firstName + "s";
                }
            } catch (IndexOutOfBoundsException e) {
                FileLog.error("failed to get name from cert", e);
            } catch (Exception e) {
                FileLog.error("failed to get name from cert", e);
            }
        }

        closeButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                callbackHandler.setAppletState("CANCELLED");
                dialog.hide();
            }
        };

        if (pinCode == null || !AksClient.isValidPinCode(pinCode)) {
            pinCodeLabel = componentFactory.createTextLabel("PIN kode");

            pinCodeField = componentFactory.createPasswordField();
            pinCodeField.setText(pinCode);

            Document document = pinCodeField.getDocument();
            document.addDocumentListener(
                    new DocumentListener() {
                        public void insertUpdate(DocumentEvent e) {
                            pinCode = new String(pinCodeField.getPassword());
                            pkcs12password = null; // not valid anymore
                        }

                        public void removeUpdate(DocumentEvent e) {
                            pinCode = new String(pinCodeField.getPassword());
                            pkcs12password = null; // not valid anymore
                        }

                        public void changedUpdate(DocumentEvent e) {
                            pinCode = new String(pinCodeField.getPassword());
                            pkcs12password = null; // not valid anymore
                        }
                    }
            );
            pinCodeField.addFocusListener(new FocusListener() {
                private String text = "Indtast den PIN kode, du har modtaget med posten";

                public void focusGained(FocusEvent e) {
                    setResultText(text);
                }

                public void focusLost(FocusEvent e) {
                    pinCode = new String(pinCodeField.getPassword());
                }
            });
        }

        if (alias == null) {
            aliasLabel = componentFactory.createTextLabel("Indtast navn på nøglen");

            aliasField = componentFactory.createTextField();
            alias = firstNameOwns + " digitale signatur";
            aliasField.setText(alias);
            Document document = aliasField.getDocument();
            document.addDocumentListener(
                    new DocumentListener() {
                        public void insertUpdate(DocumentEvent e) {
                            alias = aliasField.getText();
                        }

                        public void removeUpdate(DocumentEvent e) {
                            alias = aliasField.getText();
                        }

                        public void changedUpdate(DocumentEvent e) {
                            alias = aliasField.getText();
                        }
                    }
            );
            aliasField.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent e) {
                    setResultText("Find selv på et navn til din digitale signatur eller benyt det foreslåede. Er I flere, der skal benytte Digital Signatur fra den samme PC, kan det være en fordel at angive jeres fornavne her - f.eks. Lises digitale signatur.");
                }

                public void focusLost(FocusEvent e) {
                }

            });
        }

        passphraseLabel = componentFactory.createTextLabel("Opret en ny adgangskode");
        passphraseField = componentFactory.createPasswordField();
        Document document = passphraseField.getDocument();
        document.addDocumentListener(
                new DocumentListener() {
                    public void insertUpdate(DocumentEvent e) {
                        passphrase = new String(passphraseField.getPassword());
                        displayEventsInResultText = true;
                        setResultText(getAccessCodeStatusText(passphrase, passphrase1));
                    }

                    public void removeUpdate(DocumentEvent e) {
                        passphrase = new String(passphraseField.getPassword());
                        displayEventsInResultText = true;
                        setResultText(getAccessCodeStatusText(passphrase, passphrase1));
                    }

                    public void changedUpdate(DocumentEvent e) {
                        passphrase = new String(passphraseField.getPassword());
                        displayEventsInResultText = true;
                        setResultText(getAccessCodeStatusText(passphrase, passphrase1));
                    }
                }
        );
        passphraseField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                setResultText(getAccessCodeStatusText(passphrase, passphrase1));
            }

            public void focusLost(FocusEvent e) {
            }
        });


        passphrase1Label = componentFactory.createTextLabel("Gentag den nye adgangskode");

        passphrase1Field = componentFactory.createPasswordField();
        document = passphrase1Field.getDocument();
        document.addDocumentListener(
                new DocumentListener() {
                    public void insertUpdate(DocumentEvent e) {
                        passphrase1 = new String(passphrase1Field.getPassword());
                        displayEventsInResultText = true;
                        setResultText(getAccessCodeStatusText(passphrase, passphrase1));
                    }

                    public void removeUpdate(DocumentEvent e) {
                        passphrase1 = new String(passphrase1Field.getPassword());
                        displayEventsInResultText = true;
                        setResultText(getAccessCodeStatusText(passphrase, passphrase1));
                    }

                    public void changedUpdate(DocumentEvent e) {
                        passphrase1 = new String(passphrase1Field.getPassword());
                        displayEventsInResultText = true;
                        setResultText(getAccessCodeStatusText(passphrase, passphrase1));
                    }
                }
        );
        passphrase1Field.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                setResultText(getAccessCodeStatusText(passphrase, passphrase1));
            }

            public void focusLost(FocusEvent e) {
            }
        });

        cancelButton = componentFactory.createNormalTextButton("Annuller", false);
        cancelButton.setActionCommand("CANCEL");

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                callBackHandler.setAppletState("CANCELLED");
                dialog.hide();
            }
        });

        okButton = componentFactory.createNormalTextButton("Indlæs Digital Signatur", true);
        okButton.setActionCommand("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isOk()) {
                    String capiAlias = getCAPIAlias();
                    String capiPassword = getCAPIPassword();
                    String pkcs12password = getPKCS12Password();
                    // and then:
                    dialog.hide();
                    // and call JavaScript:

                    if (callbackHandler instanceof SetPollApplet) {
                        String[] argNames = {"PKCS12PASSWORD", "CAPIALIAS", "CAPIPASSWORD"};
                        String[] args = {pkcs12password, capiAlias, capiPassword};

                        ((SetPollApplet) callbackHandler).setOutputData(argNames, args);
                    } else {
                        StringBuffer output = new StringBuffer();
                        output.append("PKCS12PASSWORD=").append(pkcs12password).append(", CAPIALIAS=").append(capiAlias).append(", CAPIPASSWORD=").append(capiPassword);
                        callBackHandler.setOutputData(output.toString());
                    }
                }
            }
        });


        resultText = componentFactory.createTextField();
        resultText.setAutoscrolls(false);
        resultText.setEditable(false);

        if (nid == null || nid.length() == 0) {
            setResultText("Systemfejl: manglende nøgleidentifikator. Kontakt support.");
        }
    }

    @Override
    protected JComponent[] getActionButtons() {
        return new JComponent[]{okButton, cancelButton};
    }

    @Override
    protected JComponent getContentPanel() {
         JPanel panel = componentFactory.createPanel(new GridBagLayout());

        int y = 0;
        if (pinCode == null || !AksClient.isValidPinCode(pinCode)) {
            y = GuiUtil.addSingleLine(panel, 0, y, pinCodeLabel);
            y = GuiUtil.addSingleLine(panel, 0, y, pinCodeField);
        }
        if (alias == null) {
            y = GuiUtil.addSingleLine(panel, 0, y, aliasLabel);
            y = GuiUtil.addTextFieldLine(panel, 0, y, aliasField);
        }
        y = GuiUtil.addSingleLine(panel, 0, y, passphraseLabel);
        y = GuiUtil.addSingleLine(panel, 0, y, passphraseField);
        y = GuiUtil.addSingleLine(panel, 0, y, passphrase1Label);
        y = GuiUtil.addSingleLine(panel, 0, y, passphrase1Field);


        GuiUtil.addFillerLine(panel, y);

        JPanel contentPanel = componentFactory.createPanel(new BorderLayout());
        contentPanel.add(title, BorderLayout.NORTH);
        contentPanel.add(panel, BorderLayout.CENTER);

        resultText = componentFactory.createTextField();
        resultText.setAutoscrolls(false);
        resultText.setEditable(false);
        resultText.setBackground(this.getBackground());

        contentPanel.add(resultText, BorderLayout.SOUTH);

        return contentPanel;
    }

    @Override
    public JComponent getFocusComponent() {
        return passphraseField;
    }

    // interface
    boolean isOk() {
        return validateInput();
    }

    String getPKCS12Password() {
        return pkcs12password;
    }

    String getCAPIAlias() {
        return alias;
    }

    String getCAPIPassword() {
        return passphrase;
    }


    private void setResultText(String txt) {
        if (displayEventsInResultText) {
            resultText.setText(txt);
        }
    }

    private String getRealPassword(String pin, String nid) throws Exception {
        AksClient client = AksClient.getInstance();
        AksResponse response = client.getPassword(nid, pin, null, "import");
        switch (response.getStatus()) {
            case AksResponse.OK:
                return response.getMsg();
            case AksResponse.FIRST_TIME:
                throw new Exception("Du kan ikke importere før kortet har været brugt mindst een gang");
            case AksResponse.FAIL:
                throw new Exception("Forkert PIN-kode - prøv igen");
            case AksResponse.SYSTEM_ERROR:
                throw new Exception("Der skete en fejl - prøv igen senere");
            case AksResponse.REVOKED:
                throw new Exception("Kortet er blevet spærret");
            case AksResponse.TOO_MANY:
                throw new Exception("Der har været for mange fejlagtige PIN-kode-forsøg");
            default:
                throw new Exception("Uventet fejlsituation (" + response.getStatus() + ")");
        }
    }

    private String getAccessCodeStatusText(String p, String p2) {
        if (p == null) {
            p = "";
        }
        if (p2 == null) {
            p2 = "";
        }

        boolean accessCodeIsValid = true;
        StringBuffer b = new StringBuffer("Opret adgangskode til beskyttelse af Digital Signatur.\n");
        if (p.length() < 8) {
            b.append("- Adgangskoden skal indeholde mindst 8 tegn\n");
            accessCodeIsValid = false;
        }

        if (!containsDigit(p)) {
            b.append("- Adgangskoden skal indeholde mindst eet tal\n");
            accessCodeIsValid = false;
        }

        if (p.equals(p.toUpperCase())) {
            b.append("- Adgangskoden skal indeholde mindst eet lille bogstav\n");
            accessCodeIsValid = false;
        }

        if (p.equals(p.toLowerCase())) {
            b.append("- Adgangskoden skal indeholde mindst eet stort bogstav\n");
            accessCodeIsValid = false;
        }

        if (p.equals("") || !p.equals(p2)) {
            b.append("- De to adgangskoder skal være ens\n");
            accessCodeIsValid = false;
        }

        if (accessCodeIsValid) {
            return "Du har oprettet og gentaget en gyldig adgangskode.";
        }
        return b.toString();

    }

    private boolean containsDigit(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }


    private static boolean passphraseValid(String passphrase) {

        if (passphrase != null) {
            // Passphrase skal v�re mindst 8 tegn
            if (passphrase.length() < 8) {
                return false;
            }
            // Passphrase skal indeholde store bogstaver
            if (passphrase.equals(passphrase.toLowerCase())) {
                return false;
            }
            // Passphrase skal indeholde sm� bogstaver
            if (passphrase.equals(passphrase.toUpperCase())) {
                return false;
            }
            // Passphrase skal indeholde et tal
            for (int i = 0; i < passphrase.toCharArray().length; i++) {
                if (passphrase.toCharArray()[i] >= '0' && passphrase.toCharArray()[i] <= '9') {
                    return true;
                }
            }
        } // end null check
        return false;
    }

    private boolean validateInput() {
        displayEventsInResultText = true;
//            setResultText("");

        if (nid == null || nid.equals("")) {
            setResultText("Indtast referencenummer");
            return false;
        }

        if (pinCode == null || pinCode.equals("")) {
            setResultText("Indtast PIN kode");
            return false;
        }

        if (alias == null || alias.equals("")) {
            setResultText("Indtast alias");
            return false;
        }

        if (passphrase == null) {
            setResultText("Opret adgangskode");
            return false;
        }

        if (!passphraseValid(passphrase)) {
            setResultText("Du skal indtaste et adgangskode med mindst 8 tegn, 1 tal og blandet store/små bogstaver");
            return false;
        }
        if (passphrase1 == null) {
            setResultText("Gentag adgangskode");
            return false;
        }
        if (!passphrase.equals(passphrase1)) {
            setResultText("De to adgangskoder skal være ens.");
            return false;
        }
        // passed local checks, now check with AKS:
        if (!AksClient.isValidPinCode(pinCode)) {
            setResultText("Indtast gyldig PIN kode. PIN koden skal bestå af " + AksClient.PIN_CODE_LENGTH + " cifre.");
            return false;
        }
        if (pkcs12password == null) {
            try {
                pkcs12password = getRealPassword(pinCode, nid);
            } catch (Exception ex) {
                FileLog.info("Didn't get password from AKS: " + ex.getMessage());
                setResultText(ex.getMessage());
                return false;
            }
        }
        return true;
    }
}