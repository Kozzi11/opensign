package org.openoces.opensign.certificate.plugin.capi;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class FakeMicrosoftCapiFactory implements MicrosoftCapiFactory {

    @Override
    public MicrosoftCapi createMicrosoftCapi() {
        return new FakeMicrosoftCapi();
    }
}
