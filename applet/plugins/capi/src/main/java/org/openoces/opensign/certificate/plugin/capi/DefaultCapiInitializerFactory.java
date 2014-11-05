package org.openoces.opensign.certificate.plugin.capi;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class DefaultCapiInitializerFactory implements CapiInitializerFactory {
    private boolean sunMsCapiInstalled;

    public DefaultCapiInitializerFactory() {
        sunMsCapiInstalled = false; //TODO: Disabled for now ProviderUtil.getProvider("SunMSCAPI") != null;
    }

    @Override
    public CapiInitializer createCapiInitializer() {
        return sunMsCapiInstalled ? new NullCapiInitializer() : new JniCapiInitializer();
    }
}
