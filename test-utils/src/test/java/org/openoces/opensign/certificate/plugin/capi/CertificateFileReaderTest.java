package org.openoces.opensign.certificate.plugin.capi;

import org.junit.Assert;
import org.junit.Test;
import org.openoces.opensign.util.test.CertificateFileReader;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class CertificateFileReaderTest {

    @Test
    public void canParseCertificates() throws IOException, CertificateException {
        List<X509Certificate> certs = CertificateFileReader.parseCertificates("/x509/certificates/personal-systemtest-not-revoked-0.pem");
        Assert.assertEquals(1, certs.size());
    }
}
