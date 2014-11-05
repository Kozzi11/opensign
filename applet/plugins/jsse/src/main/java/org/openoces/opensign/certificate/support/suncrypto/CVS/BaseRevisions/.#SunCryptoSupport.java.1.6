/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard
	Copyright 2012 Anders M. Hansen

    This file is part of OpenSign.

    OpenSign is free software; you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation; either version 2.1 of the License, or
    (at your option) any later version.

    OpenSign is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with OpenOcesAPI; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA


    Note to developers:
    If you add code to this file, please take a minute to add an additional
    copyright statement above and an additional
    @author statement below.

 * @author Mads Jensen <mjn@trifork.com>
 * @author Jeppe Burchhardt <Jeppe.Burchhardt@Cryptomathic.com>
 * @author Ole Friis Østergaard <ofo@trifork.com>
 * @author Anders M. Hansen <consult@ajstemp.dk>
*/

/* $Id: SunCryptoSupport.java,v 1.6 2012/12/13 11:39:58 anmha Exp $ */

package org.openoces.opensign.certificate.support.suncrypto;

import org.openoces.opensign.certificate.CertificateInfo;
import org.openoces.opensign.certificate.StringPrincipal;
import org.openoces.opensign.certificate.x509.KeyUsage;
import org.openoces.opensign.crypto.CryptoSupport;
import org.openoces.opensign.crypto.SSLResponse;
import org.openoces.opensign.exceptions.UserCancel;
import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.utils.IOUtils;
import org.openoces.opensign.utils.X500Name;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLEncoder;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * This class represents the interface to high-level crypto functionality on the Sun jre 1.4-1.5
 * platform
 *
 * @author Preben Valeur  <prv@tdc.dk>
 */
public class SunCryptoSupport implements CryptoSupport {
    private static CryptoSupport instance;
    private CryptoFactory cryptoFactory;
    private HttpsClientFactory httpsClientFactory;

    public static CryptoSupport getInstance() {
        if (instance == null) {
            instance = new org.openoces.opensign.certificate.support.suncrypto.SunCryptoSupport();
        }
        return instance;
    }

    // todo: make private - singleton...but for now it is instantiated by BootApplet...
//    private SunCryptoSupport(){}
    public SunCryptoSupport() {
        this.cryptoFactory = new SunCryptoFactory();
        this.httpsClientFactory = new DefaultHttpsClientFactory();
    }

    public void destroy() {
    	instance = null;
    }
    
    public byte[] sign(byte[] signText, PrivateKey privateKey, String signatureAlg) {
        try {
            return JCETools.sign(signText, privateKey, signatureAlg);
        } catch (NoSuchProviderException e) {
            FileLog.error(e.getMessage(), e);
            return null;
        } catch (NoSuchAlgorithmException e) {
            FileLog.error(e.getMessage(), e);
            return null;
        } catch (SignatureException e) {
            FileLog.error(e.getMessage(), e);
            return null;
        } catch (InvalidKeyException e) {
            FileLog.error(e.getMessage(), e);
            return null;
        }
    }

    public PrivateKey getPrivateKey(KeyStore keyStore, char[] password) throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, NoSuchProviderException {
        String alias = JCETools.getKeyAlias(keyStore);
        return JCETools.getPrivateKey(keyStore, alias, password);
    }

    protected final static class SunCertificateInfo implements CertificateInfo {
        private X509Certificate cert;

        SunCertificateInfo(X509Certificate cert) {
            this.cert = cert;
        }

        public char[] promptForPassword(char[] password) throws UserCancel {
            return password;
        }

        public boolean isInfoAvailable() {
            return true;
        }

        public String getUserFriendlyName() {
            X500Name xname = new X500Name(cert.getSubjectDN().getName());
            return xname.getCN();
        }

        public java.security.Principal getSubjectDN() throws IOException {
            return new StringPrincipal(cert.getSubjectDN().getName());
        }

        public Principal getIssuerDN() throws IOException {
            return new StringPrincipal(cert.getIssuerDN().getName());
        }

        public BigInteger getSerialNumber() throws IOException {
            return cert.getSerialNumber();
        }

        public Date getNotBefore() throws IOException {
            return cert.getNotBefore();
        }

        public Date getNotAfter() throws IOException {
            return cert.getNotAfter();
        }

        public int getVersion() throws IOException {
            return cert.getVersion();
        }

        public String getKeyUsage() throws IOException {
            return JCETools.getUsage(cert);
        }

        public boolean canSign() throws IOException {
            return JCETools.canSign(cert);
        }

        public KeyUsage getIntendedKeyUsage() throws IOException {
            return JCETools.getIntendedKeyUsage(cert);
        }

        public byte[] getCertificate() throws IOException {
            try {
                return cert.getEncoded();
            } catch (CertificateEncodingException e) {
                throw new IOException(e.getMessage(), e);
            }
        }
    }

    private static X509Certificate getCertificate(String b64clientCert) throws CertificateException {
        byte[] decoded = Base64.decode(b64clientCert.getBytes());
        ByteArrayInputStream bis = new ByteArrayInputStream(decoded);
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        return (X509Certificate) cf.generateCertificate(bis);
    }

    public byte[][] getCertificateChain(KeyStore keyStore) throws KeyStoreException, CertificateEncodingException {
        Certificate[] certs = JCETools.getSigningCertificateChain(keyStore);
        byte[][] bchain = new byte[certs.length][0];
        for (int i = 0; i < certs.length; i++) {
            bchain[i] = certs[i].getEncoded();
        }
        return bchain;
    }

    public CertificateInfo getCertInfo(KeyStore keyStore) {
        try {
            Certificate[] certChain = JCETools.getSigningCertificateChain(keyStore);
            return certChain != null && certChain.length > 0 ? new org.openoces.opensign.certificate.support.suncrypto.SunCryptoSupport.SunCertificateInfo((X509Certificate) certChain[0]) : null;
        } catch (KeyStoreException e) {
            FileLog.debug(e.getMessage(), e);
            return null;
        } catch (Exception e) {
            FileLog.debug(e.getMessage(), e);
            return null;
        }
    }

    public CertificateInfo getCertInfo(String b64clientCert) {
        try {
            return new org.openoces.opensign.certificate.support.suncrypto.SunCryptoSupport.SunCertificateInfo(getCertificate(b64clientCert));
        } catch (CertificateException e) {
            FileLog.error(e.getMessage(), e);
            return null;
        }
    }

    protected Signature getSignature(String signatureAlg) throws NoSuchAlgorithmException {
        return Signature.getInstance(signatureAlg);
    }

    public boolean verify(String modulus, String publicExp, byte[] signedValue, byte[] signatureToVerify) {
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(new BigInteger(1, hexDecode(modulus)), new BigInteger(1, hexDecode(publicExp)));
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
            Signature s = getSignature("SHA1withRSA");
            s.initVerify(publicKey);
            s.update(signedValue);
            return s.verify(signatureToVerify);
        } catch (InvalidKeySpecException e) {
            FileLog.error("Failed with SUN crypto", e);
            return false;
        } catch (InvalidKeyException e) {
            FileLog.error("Failed with SUN crypto", e);
            return false;
        } catch (NoSuchAlgorithmException e) {
            FileLog.error("Failed with SUN crypto", e);
            return false;
        } catch (SignatureException e) {
            FileLog.error("Failed with SUN crypto", e);
            return false;
        }
    }

    // todo: move to common util-class - also used by IaikTools
    protected byte[] hexDecode(String hexString) {
        StringTokenizer st = new StringTokenizer(hexString, ":");
        byte[] bytes = new byte[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens()) {
            bytes[i] = (byte) Integer.parseInt(st.nextToken(), 16);
            i++;
        }
        return bytes;
    }

    public byte[] sha1(byte[] bytes) throws GeneralSecurityException {
        return JCETools.sha1(bytes);
    }

    public byte[] sha256(byte[] bytes) throws GeneralSecurityException {
        return JCETools.sha256(bytes);
    }


    public SSLResponse httpsPost(URL url, Map<String, String> params) {
        HttpsClient client = createHttpsClient(this, url, params);
        return client.post();
    }

    protected CryptoFactory getCryptoFactory() {
        return cryptoFactory;
    }

    protected byte[] generateContents(Map<String, String> params) {
        try {
            StringBuffer sb = new StringBuffer();
            boolean first = true;

            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (!first) sb.append("&");
                String key = entry.getKey();
                String value = entry.getValue();
                sb.append(URLEncoder.encode(key, "UTF-8"));

                sb.append("=");
                sb.append(URLEncoder.encode(value, "UTF-8"));
                first = false;
            }
            return sb.toString().getBytes();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    // could be in JCETools or here:
    private static final String KEYSTORE_TYPE = "PKCS12";

    public KeyStore getKeyStore(File file, char[] pw) throws KeyStoreException, NoSuchAlgorithmException, NoSuchProviderException, IOException, CertificateException {
        return JCETools.getKeyStore(file, pw);
    }

    public KeyStore getKeyStore(String pkcs12string, char[] password) throws KeyStoreException, NoSuchAlgorithmException, IOException, CertificateException {
        byte[] decoded = Base64.decode(pkcs12string.getBytes("UTF8"));
        ByteArrayInputStream bis = new ByteArrayInputStream(decoded);
        KeyStore store = KeyStore.getInstance(KEYSTORE_TYPE);
        try {
            store.load(bis, password);
        } finally {
            IOUtils.close(bis);
        }
        return store;
    }

    protected HttpsClient createHttpsClient(SunCryptoSupport sunCryptoSupport, URL url, Map<String, String> params) {
        return httpsClientFactory.create(sunCryptoSupport, url, params);
    }

    public void setHttpsClientFactory(HttpsClientFactory httpsClientFactory) {
        this.httpsClientFactory = httpsClientFactory;
    }
}