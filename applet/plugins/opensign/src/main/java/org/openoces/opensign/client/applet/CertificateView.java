package org.openoces.opensign.client.applet;

import java.io.File;
import javax.swing.JComboBox;
import org.openoces.opensign.certificate.CertificateHandler;

public abstract interface CertificateView
{
    public abstract void addCertificate(CertificateHandler paramCertificateHandler);

    public abstract void addCertificate(CertificateHandler paramCertificateHandler, JComboBox paramJComboBox);

    public abstract void addCertificate(File paramFile, JComboBox paramJComboBox);

    public abstract CertificateHandler getSelectedCertificate();

    public abstract String getSsn();

    public abstract String getOptionalEntry();

    public abstract void stopCertificatePollerService();
}