package org.openoces.opensign.client.applet.dialogs.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.CertificateView;
import org.openoces.opensign.client.applet.dialogs.DlgInformation;
import org.openoces.opensign.client.applet.dialogs.FileDialogStrategy;
import org.openoces.opensign.client.applet.dialogs.GuiUtil;
import org.openoces.opensign.client.applet.dialogs.SwingFileDialogStrategy;
import org.openoces.opensign.client.applet.resources.ResourceManager;

public class BrowserForPublicKeyActionListener
        implements ActionListener
{
    private CallBackHandler callBackHandler;
    private CertificateView certificateView;
    private JButton actionButton;
    private JComboBox comboBox;

    public BrowserForPublicKeyActionListener(CallBackHandler callBackHandler, CertificateView certificateView, JButton actionButton, JComboBox comboBox)
    {
        this.callBackHandler = callBackHandler;
        this.certificateView = certificateView;
        this.actionButton = actionButton;
        this.comboBox = comboBox;
    }

    public void actionPerformed(ActionEvent e)
    {
        FileDialogStrategy dialogStrategy = new SwingFileDialogStrategy(callBackHandler.getContentPane(), actionButton);
        File certFile = dialogStrategy.getLoadFile(ResourceManager.getString("FIND_CERTIFICATE"), new FilenameFilter() {
            public boolean accept(File dir, String n) {
                return n.toLowerCase().endsWith(".pem");
            }
        });
        if (certFile != null)
        {
            certificateView.addCertificate(certFile, comboBox);
            GuiUtil.requestFocus(actionButton);
        }
        else {
            DlgInformation dlgInformation = new DlgInformation(callBackHandler, actionButton, ResourceManager.getString("DLG_ERROR_HEADER"), ResourceManager.getString("DLG_ERROR_TEXT_GENERAL_LOAD_FROM_FILE_FAILURE"));
            dlgInformation.show();
        }
    }
}