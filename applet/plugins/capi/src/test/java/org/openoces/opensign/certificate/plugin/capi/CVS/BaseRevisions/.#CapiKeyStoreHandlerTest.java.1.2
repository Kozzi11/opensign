package org.openoces.opensign.certificate.plugin.capi;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openoces.opensign.certificate.CertificateHandler;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class CapiKeyStoreHandlerTest {
    private CapiKeyStoreHandler keyStoreHandler;

    @Before
    public void setup() throws Exception {
        keyStoreHandler = new CapiKeyStoreHandler();
        keyStoreHandler.setCapiInitializerFactory(new SunCapiInitializerFactory());

        MicrosoftCapiFactory microsoftCapiFactory = new FakeMicrosoftCapiFactory();
        keyStoreHandler.setMicrosoftCapiFactory(microsoftCapiFactory);

        keyStoreHandler.setCertificateHandlerFactory(new DefaultCertificateHandlerFactory(microsoftCapiFactory));
    }

    @Test
    public void install() throws Exception {
        Assert.assertTrue(keyStoreHandler.install("https://opensign.certifikat.dk"));
    }

    @Test
    public void refreshKeystore() throws Exception {
        keyStoreHandler.refreshKeystore();
        CertificateHandler[] certificateHandlers = keyStoreHandler.getCertificates();
        Assert.assertNotNull(certificateHandlers);
        Assert.assertEquals(1, certificateHandlers.length);
    }
}
