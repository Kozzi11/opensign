/*
	Copyright 2011 Paw F. Kjeldgaard
	Copyright 2012 Anders M. Hansen

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
    
 * @author Anders M. Hansen <consult@ajstemp.dk>
*/

/* $Id: LogonActionListener.java,v 1.5 2012/12/13 11:39:59 anmha Exp $ */

package org.openoces.opensign.client.applet.dialogs.listeners;

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.CertificateView;
import org.openoces.opensign.client.applet.JavascriptRunner;
import org.openoces.opensign.client.applet.ParamReader;
import org.openoces.opensign.client.applet.dialogs.DefaultProgressUI;
import org.openoces.opensign.client.applet.dialogs.DlgInformation;
import org.openoces.opensign.client.applet.dialogs.PasswordEnteredListener;
import org.openoces.opensign.client.applet.dialogs.ProgressUI;
import org.openoces.opensign.client.applet.resources.ResourceManager;
import org.openoces.opensign.crypto.SignatureAlgorithmFactory;
import org.openoces.opensign.exceptions.InputDataError;
import org.openoces.opensign.exceptions.UserCancel;
import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.xml.xmldsig.SignatureGenerator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogonActionListener implements ActionListener, PasswordEnteredListener {
    private CallBackHandler callBackHandler;
    private CertificateView certificateView;
    private String hostName;
    private ProgressUI progressUI;
    private JButton actionButton;

    public LogonActionListener(CallBackHandler callBackHandler, CertificateView certificateView, String host, JButton actionButton) {
        this.callBackHandler = callBackHandler;
        this.certificateView = certificateView;
        this.hostName = host;
        this.actionButton = actionButton;
    }

    public void actionPerformed(ActionEvent e) {
        CertificateHandler currentCertificate = certificateView.getSelectedCertificate();
        if (currentCertificate != null) {
        	certificateView.stopCertificatePollerService(); //User has chosen a certificate - no reason to keep polling.
            progressUI = new DefaultProgressUI(callBackHandler.getDefaultGlassPane());
            progressUI.show((JApplet)callBackHandler);
            currentCertificate.promptForPassword(callBackHandler, actionButton, this);
        }
    }

    public void validPasswordEntered(char[] password) {
        try {
            CertificateHandler certificate = certificateView.getSelectedCertificate();
            if (!certificate.isInfoAvailable()) {
                throw new IOException("Could not read certificate info.");
            }

            ParamReader paramReader = callBackHandler.getParamReader();

            Map<Object, Object> invisibleProps = callBackHandler.getSignProperties();
            addLayoutSettings(invisibleProps);
            addCertificateProperties(invisibleProps, certificate);

            String signtext = "OpenLogon " + SimpleDateFormat.getInstance().format(new Date(System.currentTimeMillis()));

            invisibleProps.put(SignatureGenerator.SIGNTEXT, signtext);
            Map<Object, Object> visibleProps = new HashMap<Object, Object>();

            String socialSecurityNumber = certificateView.getSsn();
            if (!isEmpty(socialSecurityNumber)) {
                visibleProps.put(SignatureGenerator.SOCIALSECURITYNUMBER, socialSecurityNumber);
            }
            String optionalEntry = certificateView.getOptionalEntry();
            if (!isEmpty(optionalEntry)) {
                visibleProps.put(SignatureGenerator.OPTIONALFIELD2, optionalEntry);
            }

            visibleProps.put(SignatureGenerator.HOSTNAME, hostName);
            visibleProps.put(SignatureGenerator.LOGONTO, hostName);

            SignatureGenerator sigGen = paramReader.createSignatureGenerator();
            SignatureAlgorithmFactory signatureAlgorithmFactory = callBackHandler.getSignatureAlgorithmFactory(password);
            String signature = sigGen.logon(certificate, visibleProps, invisibleProps, signatureAlgorithmFactory);
            handleOk("onLogonOK", signature);
        } catch (UserCancel e) {
            // do nothing
        } catch (InputDataError ex) {
            // Tell the user but let the user try again
            userMessage(ex.getMessage());
        } catch (IOException ex) {
            FileLog.error("could not create logon signature, wrong password");
            handleError("onLogonError", "wrong password");
        } catch (Exception ex) {
            FileLog.error("could not create logon signature", ex);
            handleError("onLogonError", ex.getMessage());
        } finally {
            progressUI.hide((JApplet)callBackHandler);
        }
    }

    public void invalidPasswordEntered(String message) {
        progressUI.hide((JApplet)callBackHandler);
        userMessage(message);
    }

    public void cancelled() {
        progressUI.hide((JApplet)callBackHandler);
    }

    protected void addLayoutSettings(Map<Object, Object> p) {
        /* add properties concerning the applet layout.
           if the visible signed text is somehow unreadable we may be able to detect
           it using these values
        */
        JApplet applet = (JApplet) callBackHandler;
        p.put("openoces_opensign_layout_color_background", applet.getBackground().getRed() + "," + applet.getBackground().getGreen() + "," + applet.getBackground().getBlue());
        try {
            p.put("openoces_opensign_layout_size_width", "" + applet.getWidth());
            p.put("openoces_opensign_layout_size_height", "" + applet.getHeight());
        } catch (java.lang.NoSuchMethodError e) {
            FileLog.debug("Applet dimensions not available");
        }
    }

    private void addCertificateProperties(Map<Object, Object> p, CertificateHandler currentCertificate) {
        Properties extraProps = currentCertificate.getExtraCertificateProperties();
        if (extraProps != null) {
            for (Enumeration e = extraProps.keys(); e.hasMoreElements(); ) {
                Object key = e.nextElement();
                p.put(key, extraProps.get(key));
            }
        }
    }

    // end RM polling code
    // general handling of result of signing/logging on
    private void handleOk(String javaScriptFunction, String signature) {
        try {
            String base64signature = new String(Base64.encode(signature.getBytes()), "UTF8");
            callBackHandler.setOutputData(base64signature);
            reportDataSigned();
            JavascriptRunner javascriptRunner = callBackHandler.getJavascriptRunner();
            javascriptRunner.callFunction(javaScriptFunction, new String[]{base64signature});
        } catch (UnsupportedEncodingException e) {
            FileLog.warn(e.getMessage(), e);
        }
    }

    private void handleError(String javaScriptFunction, String msg) {
        JavascriptRunner javascriptRunner = callBackHandler.getJavascriptRunner();
        javascriptRunner.callFunction(javaScriptFunction, new String[]{msg});
        reportError("could not create signature");
    }

    private void userMessage(String msg) {
        DlgInformation dlgInformation = new DlgInformation(callBackHandler, actionButton, ResourceManager.getString("DLG_ERROR_HEADER"), msg);
        dlgInformation.show();
    }

    /**
     * Reports an error and clears the output data.
     */
    private void reportError(String errorDescription) {
        callBackHandler.setOutputData(errorDescription);
        callBackHandler.setAppletState("error");
    }

    private void reportDataSigned() {
        callBackHandler.setAppletState("datasigned");
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }
}