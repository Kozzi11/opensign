package org.openoces.opensign.certificate.plugin.capi;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public interface CertificateHandlerFactory {

    MicrosoftWindowsCertificateHandler createCertificateHandler(byte[][] r);
}
