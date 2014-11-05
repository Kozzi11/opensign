package org.openoces.opensign.certificate.plugin.capi;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openoces.opensign.util.test.KeyStoreReader;

import java.math.BigInteger;
import java.security.KeyStore;
import java.util.Date;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class SunMicrosoftCapiTest {
    private SunMicrosoftCapi sunMicrosoftCapi;
    private boolean runSunSpecificTests = true;

    @Before
    public void setup() {
        KeyStore keyStore = KeyStoreReader.readKeyStore("/x509/keystores/opensign.jks", "Test1234");
        sunMicrosoftCapi = new FakeSunMicrosoftCapi();
        sunMicrosoftCapi.setKeyStore(keyStore);
        if (sunMicrosoftCapi.getProvider(keyStore) == null)
          runSunSpecificTests = false;
    }

    @Test
    public void getCertificatesInSystemStore() throws Exception {
        byte[][][] rs = sunMicrosoftCapi.getCertificatesInSystemStore("My");
        Assert.assertNotNull(rs);
        Assert.assertNotNull(rs[0][0]);
        Assert.assertTrue(rs[0][0].length > 0);
    }

    @Test
    public void signMessageSha1() throws Exception {
      if (runSunSpecificTests) {
        byte[][][] rs = sunMicrosoftCapi.getCertificatesInSystemStore("My");

        String data = "Dette er en test";
        String algorithm = "sha1";

        byte[] signature = sunMicrosoftCapi.signMessage(data.getBytes(), rs[0][0], algorithm, "Test1234");
        Assert.assertNotNull(signature);
      }
    }

    @Test
    public void signMessageSha256() throws Exception {
      if (runSunSpecificTests) {
        byte[][][] rs = sunMicrosoftCapi.getCertificatesInSystemStore("My");

        String data = "Dette er en test";
        String algorithm = "sha256";

        byte[] signature = sunMicrosoftCapi.signMessage(data.getBytes(), rs[0][0], algorithm, "Test1234");
        Assert.assertNotNull(signature);
      }
    }


    @Test
    public void getCertificateVersion() {
        byte[][][] rs = sunMicrosoftCapi.getCertificatesInSystemStore("My");
        int version = sunMicrosoftCapi.getCertificateVersion(rs[0][0]);
        Assert.assertEquals(3, version);
    }


    @Test
    public void getKeyUsage() {
        byte[][][] rs = sunMicrosoftCapi.getCertificatesInSystemStore("My");
        int keyUsage = sunMicrosoftCapi.getKeyUsage(rs[0][0]);
        Assert.assertEquals(0, keyUsage);
    }

    @Test
    public void getNotBeforeDate() {
        byte[][][] rs = sunMicrosoftCapi.getCertificatesInSystemStore("My");
        Date beforeDate = sunMicrosoftCapi.getNotBeforeDate(rs[0][0]);
        Assert.assertEquals(new Date(1334256028000l), beforeDate);
    }

    @Test
    public void getNotAfterDate() {
        byte[][][] rs = sunMicrosoftCapi.getCertificatesInSystemStore("My");
        Date afterDate = sunMicrosoftCapi.getNotAfterDate(rs[0][0]);
        Assert.assertEquals(new Date(1342032028000l), afterDate);
    }

    @Test
    public void getSerialNumberBigInteger() {
        byte[][][] rs = sunMicrosoftCapi.getCertificatesInSystemStore("My");
        BigInteger serialNumber = sunMicrosoftCapi.getSerialNumberBigInteger(rs[0][0]);
        Assert.assertEquals(BigInteger.valueOf(1334256028), serialNumber);
    }

    @Test
    public void getSubjectDnString() {
        byte[][][] rs = sunMicrosoftCapi.getCertificatesInSystemStore("My");
        String dn = sunMicrosoftCapi.getSubjectDnString(rs[0][0]);
        Assert.assertEquals("CN=OpenSign, OU=openoces.org, O=openoces.org, L=Aarhus, ST=Denmark, C=DK", dn);
    }

    @Test
    public void getIssuerDnString() {
        byte[][][] rs = sunMicrosoftCapi.getCertificatesInSystemStore("My");
        String dn = sunMicrosoftCapi.getSubjectDnString(rs[0][0]);
        Assert.assertEquals("CN=OpenSign, OU=openoces.org, O=openoces.org, L=Aarhus, ST=Denmark, C=DK", dn);
    }


}
