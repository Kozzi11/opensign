package org.openoces.opensign.certificate.plugin.capi;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public interface CapiInitializer {
    boolean initialize(String baseUrl);

    void cleanup();
}
