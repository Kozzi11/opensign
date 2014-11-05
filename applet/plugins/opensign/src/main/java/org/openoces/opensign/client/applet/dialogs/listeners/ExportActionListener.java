package org.openoces.opensign.client.applet.dialogs.listeners;

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.certificate.NotExportableException;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.CertificateView;
import org.openoces.opensign.client.applet.OS;
import org.openoces.opensign.client.applet.dialogs.*;
import org.openoces.opensign.client.applet.resources.ResourceManager;
import org.openoces.opensign.utils.FileLog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ExportActionListener implements ActionListener, PasswordEnteredListener {
    private CertificateView certificateView;
    private ProgressUI progressUI;
    private CallBackHandler callBackHandler;
    private JButton actionButton;

    public ExportActionListener(CallBackHandler callBackHandler, CertificateView certificateView, JButton actionButton) {
        this.certificateView = certificateView;
        this.callBackHandler = callBackHandler;
        this.actionButton = actionButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CertificateHandler currentCertificate = certificateView.getSelectedCertificate();
        if (currentCertificate != null) {
            certificateView.stopCertificatePollerService(); //User has chosen a certificate - no reason to keep polling.
            progressUI = new DefaultProgressUI(callBackHandler.getDefaultGlassPane());
            progressUI.show((JApplet)callBackHandler);
            currentCertificate.promptForPassword(callBackHandler, actionButton, this);
        }
    }

    @Override
    public void validPasswordEntered(char[] password) {
        try {
            CertificateHandler certificate = certificateView.getSelectedCertificate();

            FileDialogStrategy strategy = new SwingFileDialogStrategy(callBackHandler.getContentPane(), actionButton);

            File dest = strategy.getSaveFile(
                    OS.getUserHome(),
                    getPkcs12FileName(certificate),
                    new String[]{"pfx", "p12"},
                    ResourceManager.getString("BACKUP_KEYFILE"));

            if (dest == null) {
                return;
            }


            storePkcs12(certificate, dest);
        } finally {
            progressUI.hide((JApplet)callBackHandler);
        }
    }

    @Override
    public void invalidPasswordEntered(String message) {
        progressUI.hide((JApplet)callBackHandler);
        userMessage(message);
    }

    @Override
    public void cancelled() {
        progressUI.hide((JApplet)callBackHandler);
    }

    private void userMessage(String msg) {
        DlgInformation dlgInformation = new DlgInformation(callBackHandler, actionButton, ResourceManager.getString("DLG_ERROR_HEADER"), msg);
        dlgInformation.show();
    }

    private void storePkcs12(CertificateHandler certificateHandler, File destination) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(destination, false);
            outputStream.write(certificateHandler.getPkcs12());
        } catch (IOException e) {
            FileLog.error(e.getMessage(), e);
        } catch (NotExportableException e) {
            FileLog.error(e.getMessage(), e);
            userMessage("The certificate is not exportable!!!");
        } finally {
            try {
                if(outputStream != null) outputStream.close();
            } catch (IOException e) {
                //do nothing
            }
        }
    }

    private String getPkcs12FileName(CertificateHandler certificateHandler) {
        String name = certificateHandler.getUserFriendlyName();
        if(name.endsWith(".html")) {
            name = name.substring(0, name.length() - 5);
        }
        return name  + ".p12";
    }

}
