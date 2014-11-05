package org.openoces.opensign.certificate.plugin.capi;

import junit.framework.Assert;
import org.junit.Test;
import org.openoces.opensign.util.test.KeyStoreReader;

import java.security.KeyStore;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class KeyStoreReaderTest {

    @Test
    public void readKeyStore() throws Exception {
        KeyStore keyStore = KeyStoreReader.readKeyStore("/x509/keystores/opensign.jks", "Test1234");
        Assert.assertNotNull(keyStore);
    }
}
