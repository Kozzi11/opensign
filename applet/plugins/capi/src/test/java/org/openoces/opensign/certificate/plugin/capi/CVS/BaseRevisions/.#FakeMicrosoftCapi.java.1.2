package org.openoces.opensign.certificate.plugin.capi;

import org.openoces.opensign.util.test.CertificateFileReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.List;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class FakeMicrosoftCapi implements MicrosoftCapi {
    private CertificateFactory certificateFactory;
    private static int lastErrorCode = 0;

    public FakeMicrosoftCapi() {
        try {
            this.certificateFactory = CertificateFactory.getInstance("X.509");
            lastErrorCode = 0;
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void hello() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public byte[][][] getCertificatesInSystemStore(String storeName) {
        try {
            List<X509Certificate> certs = CertificateFileReader.parseCertificates("/x509/certificates/personal-systemtest-not-revoked-0.pem");
            X509Certificate certificate = certs.get(0);
            byte[][][] rs = new byte[1][1][];

            rs[0][0] = certificate.getEncoded();

            return rs;
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] signMessage(byte[] toBeSigned, byte[] certificate, String algorithm) {
        if (toBeSigned == null || toBeSigned.length == 0 || getLastErrorCode() != 0) return null;
        return "Signatur".getBytes();
    }

    @Override
    public int getCertificateVersion(byte[] certificate) {
        X509Certificate x509Certificate = createCertificate(certificate);
        return x509Certificate.getVersion();
    }

    @Override
    public byte[] digest(byte[] data, String algorithm) {
        return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getLastErrorCode() {
        return lastErrorCode;
    }

    public static void setLastErrorCode(int lastErrorCode) {
        FakeMicrosoftCapi.lastErrorCode = lastErrorCode;
    }

    @Override
    public int getMajorVersion() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getMinorVersion() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getKeyUsage(byte[] certificate) {
        X509Certificate x509Certificate = createCertificate(certificate);
        boolean[] keyUsage = x509Certificate.getKeyUsage();
        int value = 0;
        for (int i = 0; i < keyUsage.length; i++) {
            if (keyUsage[i]) {
                value = value + (1 ^ i);
            }
        }
        return value;
    }

    @Override
    public Date getNotBeforeDate(byte[] certificate) {
        X509Certificate x509Certificate = createCertificate(certificate);
        return x509Certificate.getNotBefore();
    }

    @Override
    public Date getNotAfterDate(byte[] certificate) {
        X509Certificate x509Certificate = createCertificate(certificate);
        return x509Certificate.getNotAfter();
    }

    @Override
    public BigInteger getSerialNumberBigInteger(byte[] certificate) {
        X509Certificate x509Certificate = createCertificate(certificate);
        return x509Certificate.getSerialNumber();
    }

    @Override
    public String getSubjectDnString(byte[] certificate) {
        X509Certificate x509Certificate = createCertificate(certificate);
        Principal subjectDn = x509Certificate.getSubjectDN();
        return subjectDn.toString();
    }

    private X509Certificate createCertificate(byte[] certificate) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(certificate);
            return (X509Certificate) certificateFactory.generateCertificate(inputStream);
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getIssuerDnString(byte[] certificate) {
        X509Certificate x509Certificate = createCertificate(certificate);
        Principal issuerDn = x509Certificate.getIssuerDN();
        return issuerDn.toString();
    }


}
