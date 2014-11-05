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

/* $Id: CertificateDetailsActionListener.java,v 1.7 2012/12/19 10:05:52 anmha Exp $ */

package org.openoces.opensign.client.applet.dialogs.listeners;

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.CertificateView;
import org.openoces.opensign.client.applet.dialogs.DlgCertificateDetails;
import org.openoces.opensign.client.applet.dialogs.PasswordEnteredListener;
import org.openoces.opensign.client.applet.resources.ResourceManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CertificateDetailsActionListener implements ActionListener, PasswordEnteredListener {
    private CallBackHandler callBackHandler;
    private CertificateView certificateView;
    private JButton actionButton;

    public CertificateDetailsActionListener(CallBackHandler callBackHandler, CertificateView certificateView, JButton actionButton) {
        this.callBackHandler = callBackHandler;
        this.certificateView = certificateView;
        this.actionButton = actionButton;
    }

    public void actionPerformed(ActionEvent e) {
        CertificateHandler currentCertificate = certificateView.getSelectedCertificate();
        if (currentCertificate != null) {
            currentCertificate.promptForPassword(callBackHandler, actionButton, this);
        }
    }

    public void validPasswordEntered(char[] password) {
        CertificateHandler certificate = certificateView.getSelectedCertificate();
        
        String cryptokiStoreName = ResourceManager.getString("DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE_CRYPTOKI");
        if (certificate.getStoreName() != null && certificate.getStoreName().equalsIgnoreCase(cryptokiStoreName) && password == null) {
            return;
        }
        
        if (certificate.isInfoAvailable()) {
            DlgCertificateDetails dlgDetails = new DlgCertificateDetails(callBackHandler, actionButton, ResourceManager.getString("CERTIFICATE_DETAILS"), certificate);
            dlgDetails.show();
        }
    }

    public void invalidPasswordEntered(String message) {
    }

    public void cancelled() {
    }
}