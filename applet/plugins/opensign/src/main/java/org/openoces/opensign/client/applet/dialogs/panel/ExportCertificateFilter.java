package org.openoces.opensign.client.applet.dialogs.panel;

import org.openoces.opensign.certificate.CertificateHandler;

public class ExportCertificateFilter implements CertificateFilter {

    @Override
    public boolean filter(CertificateHandler certificateHandler) {
        return certificateHandler.isExportable();
    }
}
