package org.openoces.opensign.client.applet.dialogs.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.certificate.x509.KeyStoreHandler;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.CertificateView;
import org.openoces.opensign.client.applet.dialogs.DlgInformation;
import org.openoces.opensign.client.applet.dialogs.FileDialogStrategy;
import org.openoces.opensign.client.applet.dialogs.GuiUtil;
import org.openoces.opensign.client.applet.dialogs.SwingFileDialogStrategy;
import org.openoces.opensign.client.applet.resources.ResourceManager;

public class BrowseForCertificateActionListener
        implements ActionListener
{
    private CallBackHandler callBackHandler;
    private CertificateView certificateView;
    private JButton actionButton;
    private JComboBox comboBox;

    public BrowseForCertificateActionListener(CallBackHandler callBackHandler, CertificateView certificateView, JButton actionButton, JComboBox comboBox)
    {
        this.callBackHandler = callBackHandler;
        this.certificateView = certificateView;
        this.actionButton = actionButton;
        this.comboBox = comboBox;
    }

    public void actionPerformed(ActionEvent e)
    {
        final KeyStoreHandler[] keyStoreHandlers = callBackHandler.getKeyStoreHandlers();
        FileDialogStrategy dialogStrategy = new SwingFileDialogStrategy(callBackHandler.getContentPane(), actionButton);
        File certFile = dialogStrategy.getLoadFile(ResourceManager.getString("FIND_CERTIFICATE"), new FilenameFilter() {
            public boolean accept(File dir, String n) {
                for (KeyStoreHandler keyStore : keyStoreHandlers) {
                    FilenameFilter filter = keyStore.getFilenameFilter();
                    if ((filter != null) && (filter.accept(dir, n))) {
                        return true;
                    }
                }
                return false;
            }
        });
        if (certFile == null) {
            GuiUtil.requestFocus(actionButton);
            return;
        }

        CertificateHandler cert = null;
        for (KeyStoreHandler handler : keyStoreHandlers) {
            cert = handler.getCertificateFromFile(certFile);
            if (cert != null)
            {
                break;
            }
        }
        if (cert != null)
        {
            certificateView.addCertificate(cert, comboBox);
            GuiUtil.requestFocus(actionButton);
        } else if (!fileIsDriver(certFile))
        {
            DlgInformation dlgInformation = new DlgInformation(callBackHandler, actionButton, ResourceManager.getString("DLG_ERROR_HEADER"), ResourceManager.getString("DLG_ERROR_TEXT_GENERAL_LOAD_FROM_FILE_FAILURE"));
            dlgInformation.show();
        }
    }

    private boolean fileIsDriver(File file) {
        if ((file != null) && (file.exists())) {
            String nameLower = file.getName().toLowerCase();
            return (nameLower.endsWith(".dll")) || (nameLower.endsWith(".so")) || (nameLower.endsWith(".jnilib")) || (nameLower.endsWith(".dylib"));
        }
        return false;
    }
}