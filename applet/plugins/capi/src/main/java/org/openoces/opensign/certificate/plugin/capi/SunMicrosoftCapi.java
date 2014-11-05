package org.openoces.opensign.certificate.plugin.capi;

import org.openoces.opensign.utils.FileLog;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class SunMicrosoftCapi implements MicrosoftCapi {
    private CertificateFactory certificateFactory = null;
    private KeyStore keyStore = null;

    public SunMicrosoftCapi() {
        try {
            this.certificateFactory = CertificateFactory.getInstance("X.509");
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void hello() {
    }

    @Override
    public byte[][][] getCertificatesInSystemStore(String storeName) {
        try {
            KeyStore ks = getKeyStore();
            if (ks != null) {
                Enumeration<String> aliases = ks.aliases();
                List<X509Certificate> certificates = new ArrayList<X509Certificate>();
                while (aliases.hasMoreElements()) {
                    String alias = aliases.nextElement();
                    Certificate certificate = ks.getCertificate(alias);
                    X509Certificate x509Certificate = createCertificate(certificate.getEncoded());
                    if (x509Certificate != null) {
                        certificates.add(x509Certificate);
                    }
                }

                if (!certificates.isEmpty()) {
                    byte[][][] rs = new byte[certificates.size()][1][];
                    for (int i = 0; i < certificates.size(); i++) {
                        rs[i][0] = certificates.get(i).getEncoded();
                    }
                    return rs;
                }
            }
            return new byte[0][0][];
        } catch (Exception e) {
            FileLog.warn("Problems reading certificates from Sun MS CAPI Provider: " + e.getMessage());
            return new byte[0][0][];
        }
    }

    @Override
    public byte[] signMessage(byte[] toBeSigned, byte[] certificate, String algorithm) {
        return signMessage(toBeSigned, certificate, algorithm, null);
    }

    public byte[] signMessage(byte[] toBeSigned, byte[] certificate, String algorithm, String password) {
        try {
            X509Certificate x509Certificate = createCertificate(certificate);
            KeyStore ks = getKeyStore();
            String alias = ks.getCertificateAlias(x509Certificate);
            PrivateKey privateKey = (PrivateKey) ks.getKey(alias, password != null ? password.toCharArray() : null);

            Provider p = getProvider(ks);
            Signature signature = Signature.getInstance(convertCapiAlgorithm(algorithm), p);
            signature.initSign(privateKey);

            signature.update(toBeSigned);
            return signature.sign();
        } catch (KeyStoreException e) {
            FileLog.error(e.getMessage(), e);
            return null;
        } catch (NoSuchAlgorithmException e) {
            FileLog.error(e.getMessage(), e);
            return null;
        } catch (UnrecoverableKeyException e) {
            FileLog.error(e.getMessage(), e);
            return null;
        } catch (InvalidKeyException e) {
            FileLog.error(e.getMessage(), e);
            return null;
        } catch (SignatureException e) {
            FileLog.error(e.getMessage(), e);
            return null;
        }
    }


    @Override
    public int getCertificateVersion(byte[] certificate) {
        X509Certificate x509Certificate = createCertificate(certificate);
        return x509Certificate.getVersion();
    }

    @Override
    public byte[] digest(byte[] data, String algorithm) {
        return new byte[0];
    }

    @Override
    public int getLastErrorCode() {
        return 0;
    }

    @Override
    public int getMajorVersion() {
        return 0;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public int getKeyUsage(byte[] certificate) {
        X509Certificate x509Certificate = createCertificate(certificate);
        boolean[] keyUsage = x509Certificate.getKeyUsage();
        int value = 0;
        if (keyUsage != null) {
            for (int i = 0; i < keyUsage.length; i++) {
                if (keyUsage[i]) {
                    value = value + (1 ^ i);
                }
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

    @Override
    public String getIssuerDnString(byte[] certificate) {
        X509Certificate x509Certificate = createCertificate(certificate);
        Principal issuerDn = x509Certificate.getIssuerDN();
        return issuerDn.toString();
    }

    protected KeyStore getKeyStore() {
        try {
            if (keyStore == null) {
                keyStore = KeyStore.getInstance("Windows-MY");
                keyStore.load(null, null);
            }
            return keyStore;
        } catch (Exception e) {
            FileLog.warn("Could not get instance of Sun MS CAPI provider: " + e.getMessage());
            return null;
        }
    }

    protected void setKeyStore(KeyStore keyStore) {
        this.keyStore = keyStore;
    }

    private X509Certificate createCertificate(byte[] certificate) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(certificate);
            return (X509Certificate) certificateFactory.generateCertificate(inputStream);
        } catch (CertificateException e) {
            return null;
        }
    }

    protected Provider getProvider(KeyStore keyStore) {
        return keyStore.getProvider();
    }

    private String convertCapiAlgorithm(String algorithm) {
        if ("sha1".equals(algorithm)) {
            return "SHA1withRSA";
        } else if ("sha256".equals(algorithm)) {
            return "SHA256withRSA";
        } else {
            return algorithm;
        }
    }
}
