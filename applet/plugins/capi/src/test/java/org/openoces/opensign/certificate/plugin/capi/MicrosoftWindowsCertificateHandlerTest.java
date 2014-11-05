package org.openoces.opensign.certificate.plugin.capi;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openoces.opensign.certificate.x509.KeyUsage;
import org.openoces.opensign.crypto.OcesSignatureAlgorithm;
import org.openoces.opensign.crypto.SignatureAlgorithmFactory;
import org.openoces.opensign.exceptions.UserCancel;
import org.openoces.opensign.util.test.CertificateFileReader;

import java.math.BigInteger;
import java.security.Principal;
import java.security.SignatureException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.List;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class MicrosoftWindowsCertificateHandlerTest {
    private MicrosoftWindowsCertificateHandler certificateHandler;
    private X509Certificate certificate;

    @Before
    public void setup() throws Exception {
        List<X509Certificate> certs = CertificateFileReader.parseCertificates("/x509/certificates/personal-systemtest-not-revoked-0.pem");
        certificate = certs.get(0);
        byte[][] rs = new byte[1][];
        rs[0] = certificate.getEncoded();
        certificateHandler = new MicrosoftWindowsCertificateHandler(rs, new FakeMicrosoftCapi());
    }

    @Test
    public void getIssuerDN() throws Exception {
        Principal issuerDn = certificateHandler.getIssuerDN();
        Assert.assertNotNull(issuerDn);
        Assert.assertEquals(certificate.getIssuerDN().getName(), issuerDn.getName());
    }

    @Test
    public void getSubjectDN() throws Exception {
        Principal subjectDn = certificateHandler.getSubjectDN();
        Assert.assertNotNull(subjectDn);
        Assert.assertEquals(certificate.getSubjectDN().getName(), subjectDn.getName());
    }

    @Test
    public void getSerialNumber() throws Exception {
        BigInteger serialNumber = certificateHandler.getSerialNumber();
        Assert.assertNotNull(serialNumber);
        Assert.assertEquals(certificate.getSerialNumber(), serialNumber);
    }

    @Test
    public void getNotBefore() throws Exception {
        Date notBefore = certificateHandler.getNotBefore();
        Assert.assertNotNull(notBefore);
        Assert.assertEquals(certificate.getNotBefore(), notBefore);
    }

    @Test
    public void getNotAfter() throws Exception {
        Date notAfter = certificateHandler.getNotAfter();
        Assert.assertNotNull(notAfter);
        Assert.assertEquals(certificate.getNotAfter(), notAfter);
    }

    @Test
    public void getVersion() throws Exception {
        int version = certificateHandler.getVersion();
        Assert.assertEquals(certificate.getVersion(), version);
    }

    @Test
    public void sign() throws Exception {
        String signText = "Dette er en test";
        SignatureAlgorithmFactory factory = new OcesSignatureAlgorithm();
        byte[] signature = certificateHandler.sign(signText.getBytes(), factory);
        Assert.assertEquals(new String("Signatur".getBytes()), new String(signature));
    }

    @Test(expected = SignatureException.class)
    public void signFails() throws Exception {
        SignatureAlgorithmFactory factory = new OcesSignatureAlgorithm();
        certificateHandler.sign(null, factory);
    }

    @Test(expected = UserCancel.class)
    public void signCancel() throws Exception {
        String signText = "Dette er en test";
        SignatureAlgorithmFactory factory = new OcesSignatureAlgorithm();
        FakeMicrosoftCapi.setLastErrorCode(11); // Simulating user cancel
        certificateHandler.sign(signText.getBytes(), factory);
    }


    @Test
    public void getIntendedKeyUsage() throws Exception {
        KeyUsage keyUsage = certificateHandler.getIntendedKeyUsage();
        Assert.assertNotNull(keyUsage);
    }

}
