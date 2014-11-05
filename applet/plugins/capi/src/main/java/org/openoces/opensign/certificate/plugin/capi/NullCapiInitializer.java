package org.openoces.opensign.certificate.plugin.capi;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class NullCapiInitializer implements CapiInitializer {

    @Override
    public boolean initialize(String baseUrl) {
        return true;
    }

    @Override
    public void cleanup() {
    }

}
