package org.openoces.opensign.certificate.support.suncrypto;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openoces.opensign.certificate.CertificateInfo;
import org.openoces.opensign.crypto.SSLResponse;
import org.openoces.opensign.util.test.CertificateFileReader;
import org.openoces.opensign.util.test.FileReader;
import org.openoces.opensign.util.test.HexUtil;
import org.openoces.opensign.util.test.KeyStoreReader;
import org.openoces.opensign.utils.Base64;

import java.net.URL;
import java.security.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class SunCryptoSupportTest {
    private static final String TEST_MODULUS = "00:be:cb:d1:ee:9f:45:3e:6d:55:14:7e:c4:0c:8e:3a:ca:1a:7f:35:5f:a8:0d:e8:f9:" +
            "b3:57:27:f5:1f:36:99:22:21:5c:45:b2:c8:a8:3b:d7:98:b9:50:c9:6c:d1:91:a1:24:9f:b6:df:7a:99:ad:4e:09:" +
            "e8:16:e2:f7:df:e7:e2:c1:ee:9f:76:6c:36:70:84:05:93:65:2b:84:d0:f7:39:56:12:bd:8f:4e:42:8c:5e:86:2c:" +
            "b0:f3:e9:32:30:e9:dc:36:c4:1f:2c:c7:8d:76:f3:c0:b4:79:00:93:69:f9:c1:e7:d7:4c:a4:1c:aa:6d:13:7b:fe:4c:8d:07:bc:ef";
    private static final String TEST_EXPONENT = "01:00:01";
    private static final String PROD_MODULUS = "00:eb:c7:d6:24:55:13:f5:08:65:9f:00:73:e8:82:59:94:fb:be:a0:1c:82:8c:2b:c1:4d:10:bf:ea:cf:72:25:1e:71:66:2c:cc:56:c4:81:fe:f3:52:05:84:bd:67:5d:a6:ea:07:0e:bb:c1:03:64:37:e2:8f:a8:fe:85:9c:aa:03:0a:ef:7e:ab:45:d8:30:cd:27:b2:53:67:d2:04:b5:80:91:e5:cc:f5:39:43:6e:ef:23:c3:cc:d9:cf:29:4c:80:c7:28:e4:be:ab:72:a0:e9:7d:27:ea:64:c7:35:21:4c:ed:e0:27:84:bf:49:4f:38:c8:c7:e8:37:6f:c9:1e:a7:25:95:08:09:99:65:4b:5f:fb:83:15:be:df:6b:b5:09:65:0e:f9:53:fa:80:d8:ce:1f:1f:68:3e:d8:5c:21:f7:01:2b:60:00:ac:2c:4f:44:b5:ff:06:a3:7f:5b:5c:7a:77:9c:c1:ae:fb:6c:32:c9:73:7b:75:91:07:2a:eb:d5:d4:fd:e8:90:57:f2:b0:ab:fb:8f:42:d4:98:98:12:c6:0c:57:b4:7c:4d:cf:78:c2:2e:75:df:10:57:55:10:28:db:37:cd:96:65:85:52:fc:0b:76:85:9a:6a:5f:26:11:d6:24:a4:8e:ed:16:fa:20:98:74:29:87:65:fd:f7:df";
    private static final String PROD_EXPONENT = "01:00:01";


    private SunCryptoSupport cryptoSupport;

    @Before
    public void setup() {
        this.cryptoSupport = new SunCryptoSupport();
        this.cryptoSupport.setHttpsClientFactory(new FakeHttpsClientFactory());
    }

    @Test
    public void signSHA256withRSA() throws Exception {
        sign("SHA256withRSA");
    }

    @Test
    public void signSHA1withRSA() throws Exception {
        sign("SHA1withRSA");
    }

    @Test
    public void sha1() throws Exception {
        byte[] value = new byte[]{0x44, 0x65, 0x74, 0x74, 0x65, 0x20, 0x65, 0x72, 0x20, 0x65, 0x6e, 0x20, 0x74, 0x65, 0x73, 0x74};

        byte[] result = cryptoSupport.sha1(value);
        Assert.assertNotNull(result);

        String hexResult = HexUtil.getHexString(result);
        Assert.assertEquals("5a27363f823ecf20a7722f445a35aa77bd5ea236", hexResult);
        //sha1: 5a27363f823ecf20a7722f445a35aa77bd5ea236 calculated using http://www.fileformat.info/tool/hash.htm
    }

    @Test
    public void sha256() throws Exception {
        byte[] value = new byte[]{0x44, 0x65, 0x74, 0x74, 0x65, 0x20, 0x65, 0x72, 0x20, 0x65, 0x6e, 0x20, 0x74, 0x65, 0x73, 0x74};

        byte[] result = cryptoSupport.sha256(value);
        Assert.assertNotNull(result);

        String hexResult = HexUtil.getHexString(result);
        Assert.assertEquals("3757d63c07c165a3bee2c1e8890e7c3e7986f95a51acdb2f8724c9efc361eefc", hexResult);
        //sha256: 3757d63c07c165a3bee2c1e8890e7c3e7986f95a51acdb2f8724c9efc361eefc calculated using http://www.fileformat.info/tool/hash.htm
    }

    @Test
    public void getPrivateKey() throws Exception {
        KeyStore keyStore = KeyStoreReader.readKeyStore("/x509/keystores/opensign.jks", "Test1234");
        PrivateKey privateKey = cryptoSupport.getPrivateKey(keyStore, "Test1234".toCharArray());
        Assert.assertNotNull(privateKey);
    }

    @Test
    public void getCertInfoFromKeyStore() throws Exception {
        KeyStore keyStore = KeyStoreReader.readKeyStore("/x509/keystores/opensign.jks", "Test1234");
        CertificateInfo certificateInfo = cryptoSupport.getCertInfo(keyStore);
        Assert.assertNotNull(certificateInfo);
        Assert.assertEquals("CN=OpenSign, OU=openoces.org, O=openoces.org, L=Aarhus, ST=Denmark, C=DK", certificateInfo.getSubjectDN().getName());
    }

    @Test
    public void getCertInfoFromFile() throws Exception {
        String fileAsString = FileReader.readFile("/x509/certificates/personal-systemtest-not-revoked-0.pem");
        String b64String = new String(Base64.encode(fileAsString.getBytes("UTF-8")), "UTF-8");
        CertificateInfo certificateInfo = cryptoSupport.getCertInfo(b64String);
        Assert.assertNotNull(certificateInfo);
        Assert.assertEquals("SERIALNUMBER=PID:9208-2002-2-397343901380 + CN=Testperson 2802751705 Testsen, O=Ingen organisatorisk tilknytning, C=DK", certificateInfo.getSubjectDN().getName());
    }

    @Test
    public void getKeyStoreFromOces() throws Exception {
        String pkcs12 = CertificateFileReader.getPkcs12FromOcesFile("/oces1/opensign.html");
        KeyStore keyStore = cryptoSupport.getKeyStore(pkcs12, "Test1234".toCharArray());
        Assert.assertNotNull(keyStore);
    }

    @Test
    public void getCertificateChain() throws Exception {
        String pkcs12 = CertificateFileReader.getPkcs12FromOcesFile("/oces1/opensign.html");
        KeyStore keyStore = cryptoSupport.getKeyStore(pkcs12, "Test1234".toCharArray());
        byte[][] chain = cryptoSupport.getCertificateChain(keyStore);
        Assert.assertNotNull(chain);
        Assert.assertEquals(2, chain.length);
    }

    @Test
    @Ignore  //because not able to get cd card test data
    public void verify() throws Exception {
        String signText = "Dette er en test";

        String pkcs12 = CertificateFileReader.getPkcs12FromOcesFile("/oces1/opensign.html");
        KeyStore keyStore = cryptoSupport.getKeyStore(pkcs12, "Test1234".toCharArray());

        PrivateKey privateKey = (PrivateKey) keyStore.getKey("opensign", "Test1234".toCharArray());
        byte[] signature = cryptoSupport.sign(signText.getBytes(), privateKey, "SHA1withRSA");

        boolean result = cryptoSupport.verify(TEST_MODULUS, TEST_EXPONENT, signText.getBytes(), signature);
        Assert.assertTrue(result);
    }

    @Test
    public void httpsPost() throws Exception {
        URL url = new URL("https://openoces.org");

        Map<String, String> params = new HashMap<String, String>();
        params.put("key1", "value1");
        params.put("key2", "value2");

        SSLResponse response = cryptoSupport.httpsPost(url, params);
        Assert.assertNotNull(response);

        String result = response.toString();

        Assert.assertEquals("Success\n", result);
    }


    private void sign(String algorithm) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        String signText = "Dette er en test";
        KeyStore keyStore = KeyStoreReader.readKeyStore("/x509/keystores/opensign.jks", "Test1234");
        PrivateKey privateKey = (PrivateKey) keyStore.getKey("opensign", "Test1234".toCharArray());
        byte[] signature = cryptoSupport.sign(signText.getBytes(), privateKey, algorithm);
        Assert.assertNotNull(signature);
    }


}
