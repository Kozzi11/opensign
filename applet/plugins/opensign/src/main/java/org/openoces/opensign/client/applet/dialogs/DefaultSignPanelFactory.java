package org.openoces.opensign.client.applet.dialogs;

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.dialogs.panel.AbstractPanel;
import org.openoces.opensign.client.applet.dialogs.panel.SignPanel;

import java.util.List;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class DefaultSignPanelFactory implements SignPanelFactory {

    @Override
    public AbstractPanel createSignPanel(CallBackHandler callbackHandler, List<CertificateHandler> certificates, boolean supportBrowsingForCertificate) {
        return new SignPanel(callbackHandler, certificates, supportBrowsingForCertificate);
    }
}
