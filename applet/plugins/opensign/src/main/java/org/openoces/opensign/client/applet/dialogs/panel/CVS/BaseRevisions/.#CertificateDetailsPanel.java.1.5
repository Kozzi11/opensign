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

/* $Id: CertificateDetailsPanel.java,v 1.5 2012/09/27 11:03:48 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs.panel;

/**
 * This class represent the panel of a dialog displaying certificate details
 *
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 */

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.certificate.x509.KeyUsage;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.dialogs.DlgCertificateDetails;
import org.openoces.opensign.client.applet.dialogs.GuiUtil;
import org.openoces.opensign.client.applet.resources.ResourceManager;
import org.openoces.opensign.utils.FileLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CertificateDetailsPanel extends AbstractPanel {
    private JLabel title;

    private JLabel lCertificateSubjectDN;
    private JLabel lCertificateSubjectDNValue;
    private JLabel lCertificateStore;
    private JLabel lCertificateStoreValue;
    private JLabel lCertificateIssuerDN;
    private JLabel lCertificateIssuerDNValue;
    private JLabel lCertificateSerialNumber;
    private JLabel lCertificateSerialNumberValue;
    private JLabel lCertificateNotBefore;
    private JLabel lCertificateNotBeforeValue;
    private JLabel lCertificateNotAfter;
    private JLabel lCertificateNotAfterValue;
    private JLabel lCertificateVersion;
    private JLabel lCertificateVersionValue;
    private JLabel lCertificateKeyUsage;
    private JLabel lCertificateKeyUsageValue;
    public CertificateDetailsPanel(CallBackHandler callBackHandler, final DlgCertificateDetails dialog, String title, CertificateHandler certificate) {
        super(callBackHandler);

        this.title = componentFactory.createHeaderLabel(title);

        closeButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.hide();
            }
        };

        lCertificateSubjectDN = componentFactory.createTextLabel("");
        lCertificateSubjectDNValue = componentFactory.createTextLabel("");
        lCertificateStore = componentFactory.createTextLabel("");
        lCertificateStoreValue = componentFactory.createTextLabel("");
        lCertificateIssuerDN = componentFactory.createTextLabel("");
        lCertificateIssuerDNValue = componentFactory.createTextLabel("");
        lCertificateSerialNumber = componentFactory.createTextLabel("");
        lCertificateSerialNumberValue = componentFactory.createTextLabel("");
        lCertificateNotBefore = componentFactory.createTextLabel("");
        lCertificateNotBeforeValue = componentFactory.createTextLabel("");
        lCertificateNotAfter = componentFactory.createTextLabel("");
        lCertificateNotAfterValue = componentFactory.createTextLabel("");
        lCertificateVersion = componentFactory.createTextLabel("");
        lCertificateVersionValue = componentFactory.createTextLabel("");
        lCertificateKeyUsage = componentFactory.createTextLabel("");
        lCertificateKeyUsageValue = componentFactory.createTextLabel("");

        addCertificateDetails(certificate);
    }


    /**
     * get certificate information and update gui
     */
    private void addCertificateDetails(CertificateHandler certificate) {
        try {
            lCertificateSubjectDN.setText(ResourceManager.getString("DLG_CERTIFICATEDETAILS_LABEL_SUBJECTDN") + ":");
            lCertificateSubjectDNValue.setText(certificate.getSubjectDN().getName());

            lCertificateStore.setText(ResourceManager.getString("DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE") + ":");
            String storeName = certificate.getStoreName();
            lCertificateStoreValue.setText(storeName);

            lCertificateIssuerDN.setText(ResourceManager.getString("DLG_CERTIFICATEDETAILS_LABEL_ISSUERDN") + ":");
            String issuerString = certificate.getIssuerDN() == null ? null : certificate.getIssuerDN().getName();

            lCertificateIssuerDNValue.setText(issuerString);

            lCertificateSerialNumber.setText(ResourceManager.getString("DLG_CERTIFICATEDETAILS_LABEL_SERIALNUMBER") + ":");

            String serialNumberString = certificate.getSerialNumber() == null ? null : certificate.getSerialNumber().toString(16).toUpperCase();
            lCertificateSerialNumberValue.setText(serialNumberString);

            lCertificateNotBefore.setText(ResourceManager.getString("DLG_CERTIFICATEDETAILS_LABEL_DATE_ISSUANCE") + ":");

            String notBeforeString = certificate.getNotBefore() == null ? null : java.text.SimpleDateFormat.getInstance().format(certificate.getNotBefore());
            lCertificateNotBeforeValue.setText(notBeforeString);

            String notAfterString = certificate.getNotAfter() == null ? null : java.text.SimpleDateFormat.getInstance().format(certificate.getNotAfter());

            lCertificateNotAfter.setText(ResourceManager.getString("DLG_CERTIFICATEDETAILS_LABEL_DATE_EXPIRY") + ":");
            lCertificateNotAfterValue.setText(notAfterString);

            lCertificateVersion.setText(ResourceManager.getString("DLG_CERTIFICATEDETAILS_LABEL_VERSION") + ":");
            lCertificateVersionValue.setText(Integer.toString(certificate.getVersion()));

            lCertificateKeyUsage.setText(ResourceManager.getString("DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE") + ":");

            KeyUsage keyusage = certificate.getIntendedKeyUsage();
            String keyUsageValue = "";

            if (keyusage == null) {
                keyUsageValue = ResourceManager.getString("DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_NOT_AVAILABLE");
            } else {
                if (keyusage.includesDigitalSignature())
                    keyUsageValue += (keyUsageValue.length() == 0 ? "" : ", ") + ResourceManager.getString("DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_DIGITAL_SIGNATURE");
                if (keyusage.includesNonRepudiation())
                    keyUsageValue += (keyUsageValue.length() == 0 ? "" : ", ") + ResourceManager.getString("DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_NON_REPUDIATION");
                if (keyusage.includesKeyEncipherment())
                    keyUsageValue += (keyUsageValue.length() == 0 ? "" : ", ") + ResourceManager.getString("DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_KEY_ENCIPHERMENT");
                if (keyusage.includesDataEncipherment())
                    keyUsageValue += (keyUsageValue.length() == 0 ? "" : ", ") + ResourceManager.getString("DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_DATA_ENCIPHERMENT");
                if (keyusage.includesKeyAgreement())
                    keyUsageValue += (keyUsageValue.length() == 0 ? "" : ", ") + ResourceManager.getString("DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_KEY_AGREEMENT");
                if (keyusage.includesKeyCertSign())
                    keyUsageValue += (keyUsageValue.length() == 0 ? "" : ", ") + ResourceManager.getString("DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_KEY_CERT_SIGN");
                if (keyusage.includesKeyCRLSign())
                    keyUsageValue += (keyUsageValue.length() == 0 ? "" : ", ") + ResourceManager.getString("DLG_CERTIFICATEDETAILS_LABEL_KEYUSAGE_CRL_SIGN");
            }

            lCertificateKeyUsageValue.setText(keyUsageValue);
        } catch (IOException e) {
            FileLog.error(e.getMessage(), e);
        }
    }

    private void createtLabelForPanel(JPanel panel) {
        JLabel certDetailsLabel = componentFactory.createTextLabel("");
        certDetailsLabel.setText(title.getText() + lCertificateSubjectDN.getText() + lCertificateSubjectDNValue.getText() + lCertificateStore.getText()
                + lCertificateStoreValue.getText() + lCertificateIssuerDN.getText() + lCertificateIssuerDNValue.getText() + lCertificateSerialNumber.getText()
                + lCertificateSerialNumberValue.getText() + lCertificateNotBefore.getText() + lCertificateNotBeforeValue.getText() + lCertificateNotAfter.getText()
                + lCertificateNotAfterValue.getText() + lCertificateVersion.getText() + lCertificateVersionValue.getText() + lCertificateKeyUsage.getText()
                + lCertificateKeyUsageValue.getText());
        certDetailsLabel.setFocusable(true);
        certDetailsLabel.setLabelFor(panel);
    }

    @Override
    protected JComponent getActionPanel() {
        return null;
    }

    @Override
    protected JComponent getContentPanel() {
        JPanel detailsPanel = componentFactory.createPanel(new GridBagLayout());

        int y = 0;
        y = GuiUtil.addDetailsLine(detailsPanel, 0, y, lCertificateSubjectDN, lCertificateSubjectDNValue);
        y = GuiUtil.addDetailsLine(detailsPanel, 0, y, lCertificateStore, lCertificateStoreValue);
        y = GuiUtil.addDetailsLine(detailsPanel, 0, y, lCertificateIssuerDN, lCertificateIssuerDNValue);
        y = GuiUtil.addDetailsLine(detailsPanel, 0, y, lCertificateSerialNumber, lCertificateSerialNumberValue);
        y = GuiUtil.addDetailsLine(detailsPanel, 0, y, lCertificateNotBefore, lCertificateNotBeforeValue);
        y = GuiUtil.addDetailsLine(detailsPanel, 0, y, lCertificateNotAfter, lCertificateNotAfterValue);
        y = GuiUtil.addDetailsLine(detailsPanel, 0, y, lCertificateVersion, lCertificateVersionValue);
        y = GuiUtil.addDetailsLine(detailsPanel, 0, y, lCertificateKeyUsage, lCertificateKeyUsageValue);
        GuiUtil.addFillerLine(detailsPanel, y);

        JScrollPane scrollPane = componentFactory.createScrollPane(detailsPanel);

        JPanel panel = componentFactory.createPanel(new BorderLayout());
        panel.add(title, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        createtLabelForPanel(panel);

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
}