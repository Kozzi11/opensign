/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard


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
*/

/* $Id: JCETools.java,v 1.4 2012/09/27 11:03:44 pakj Exp $ */

package org.openoces.opensign.certificate.support.suncrypto;

import org.openoces.opensign.certificate.x509.KeyUsage;
import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.utils.IOUtils;

import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * This class represents the interface to high-level crypto related functionality
 *
 * @author Jens Bo Friis  <jbf@it-practice.dk>
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 * @author Preben Valeur  <prv@tdc.dk>
 */
public class JCETools {
	public static final int digitalSignature = 0; //OID 2.5.29.15
	public static final int nonRepudiation = 1; //OID 2.5.29.15
	public static final int keyEncipherment = 2; //OID 2.5.29.15
	public static final int dataEncipherment = 3; //OID 2.5.29.15
	public static final int keyAgreement = 4; //OID 2.5.29.15
	public static final int keyCertSign = 5; //OID 2.5.29.15
	public static final int cRLSign = 6; //OID 2.5.29.15

	public static final String IPADDRESS = "ipaddress";
	public static final String SIGNTEXT = "signtext";
	public static final String TIMESTAMP = "signingtime";
	public static final String CPR_CVR = "cpr-cvr";
	public static final String PID = "pid";
	public static final String SUBJECTID = "subject-id";
	public static final String EMAIL = "email";

	public static final String ACTION = "action";
	public static final String ACTION_SIGN = "sign";
	public static final String ACTION_LOGON = "logon";

	public static final String SIGNTATURE_ID = "OCESOpenSign";

	private static final String KEYSTORE_TYPE = "PKCS12";

    protected JCETools() {}

	/**
	 * Compare two byte arrays. Return true if they are the same
	 * @param b1
	 * @param b2
	 * @return boolean
	 */
	public static boolean compare(byte[] b1, byte[] b2) {
		if (b1.length != b2.length)
			return false;
		for (int i = 0; i < b1.length; i++)
			if (b1[i] != b2[i])
				return false;
		return true;
	}

	public static KeyStore getKeyStore(File keystoreFile, char[] password) throws KeyStoreException, NoSuchAlgorithmException, NoSuchProviderException, IOException, CertificateException {
		FileInputStream fin = new FileInputStream(keystoreFile);
		KeyStore store = KeyStore.getInstance(KEYSTORE_TYPE);
		try {
			store.load(fin, password);
		} finally {
            IOUtils.close(fin);
		}
		return store;
	}

	public static PrivateKey getPrivateKey(KeyStore store, String alias, char[] password) throws UnrecoverableKeyException, NoSuchAlgorithmException, NoSuchProviderException, KeyStoreException {
		return (PrivateKey) store.getKey(alias, password);
	}

	public static String[] getAliases(KeyStore store) throws KeyStoreException {
		Enumeration enumeration = store.aliases();
		List<String> list = new ArrayList<String>();
		while (enumeration.hasMoreElements()) {
			list.add(enumeration.nextElement().toString());
		}
		return list.toArray(new String[list.size()]);
	}

	/**
	 * Return the private key alias (there can be only one...)
	 * Return null, if none exist
	 * @param store
	 * @return String
	 * @throws java.security.KeyStoreException
	 */
	public static String getKeyAlias(KeyStore store) throws KeyStoreException {
		String[] aliases = getAliases(store);
		for (int i = 0; i < aliases.length; i++) {
			if (store.isKeyEntry(aliases[i]))
				return aliases[i];
		}
		return null;
	}

	public static byte[] sha1(byte[] bytes) throws NoSuchProviderException, NoSuchAlgorithmException {
        return hash(bytes, "SHA1");
	}

    public static byte[] sha256(byte[] bytes) throws NoSuchProviderException, NoSuchAlgorithmException {
        return hash(bytes, "SHA-256");
    }

    private static byte[] hash(byte[] bytes, String algorithm) throws NoSuchProviderException, NoSuchAlgorithmException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        try {
            MessageDigest hashSum = MessageDigest.getInstance(algorithm);
            byte[] buffer = new byte[1024];
            int c;
            while((c = inputStream.read(buffer, 0, buffer.length)) != -1) {
                hashSum.update(buffer, 0, c);
            }
            return hashSum.digest();

        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                //do nothing
            }
        }
    }


    /**
     * Sign the signtext and return the signature. RSA with SHA1 is forced
     * @param signText
     * @param privateKey
     * @param signatureAlg 
     * @return byte[]
     * @throws InvalidKeyException
     * @throws SignatureException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    public static byte[] sign(byte[] signText, PrivateKey privateKey, String signatureAlg) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature sig = Signature.getInstance(signatureAlg);
        return sign(sig, signText, privateKey);
    }
	/**
	 * Sign the signtext and return the signature. RSA with SHA1 is forced
	 * @param sig
	 * @param signText
	 * @param privateKey
	 * @return byte[]
	 * @throws java.security.InvalidKeyException
	 * @throws java.security.SignatureException
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.security.NoSuchProviderException
	 */
	public static byte[] sign(Signature sig, byte[] signText, PrivateKey privateKey) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		sig.initSign(privateKey);
		sig.update(signText);
		return  sig.sign();
	}

	/**
	 * Verify signature
	 * @param signature
	 * @param signText
	 * @param key
	 * @return boolean
	 * @throws java.security.InvalidKeyException
	 * @throws java.security.SignatureException
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.security.NoSuchProviderException
	 */
	public static boolean verify(byte[] signature, byte[] signText, PublicKey key) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		Signature sig = Signature.getInstance("SHA1withRSA");
		sig.initVerify(key);
		sig.update(signText);
		return sig.verify(signature);
	}

	/**
	 * Get Digest from signtext, coded as BASE64
	 * @param signText
	 * @return String
	 * @throws java.security.NoSuchProviderException
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.io.UnsupportedEncodingException
	 */
	public static String getDigestValue(String signText) throws NoSuchProviderException, NoSuchAlgorithmException, UnsupportedEncodingException {
		return new String(Base64.encode(sha1(signText.getBytes("UTF8"))));
	}

	/**
	 * return boolean true if certificate contains the "digitalsignature" in keyusage attribute
	 * @param cert
	 * @return
	 */
	public static boolean canSign(X509Certificate cert) {
        if (cert.getKeyUsage() == null)
            return true;
        if (cert.getKeyUsage()[digitalSignature] || cert.getKeyUsage()[nonRepudiation])
            return true;
        return false;
    }

    public static KeyUsage getIntendedKeyUsage(X509Certificate c) {
        boolean ku[];
        if ((ku = c.getKeyUsage()) == null ) {
            return null;
        }
        return new KeyUsage(ku[JCETools.digitalSignature],
                            ku[JCETools.nonRepudiation],
                            ku[JCETools.keyEncipherment],
                            ku[JCETools.dataEncipherment],
                            ku[JCETools.keyAgreement],
                            ku[JCETools.keyCertSign],
                            ku[JCETools.cRLSign]);
    }


	public static String getUsage(X509Certificate cert) {
		if (cert.getKeyUsage() == null)
			return "no key usage defined for certificate";

		StringBuffer sb = new StringBuffer();
		if (cert.getKeyUsage()[JCETools.digitalSignature])
			sb.append(" digitalSignature ");
		if (cert.getKeyUsage()[JCETools.cRLSign])
			sb.append(" cRLSign ");
		if (cert.getKeyUsage()[JCETools.dataEncipherment])
			sb.append(" dataEncipherment ");
		if (cert.getKeyUsage()[JCETools.keyAgreement])
			sb.append(" keyAgreement ");
		if (cert.getKeyUsage()[JCETools.keyCertSign])
			sb.append(" keyCertSign ");
		if (cert.getKeyUsage()[JCETools.keyEncipherment])
			sb.append(" keyEncipherment ");
		if (cert.getKeyUsage()[JCETools.nonRepudiation])
			sb.append(" nonRepudiation ");
		return sb.toString().trim();
	}

	public static Certificate[] getSigningCertificateChain(KeyStore store) throws KeyStoreException {
		String[] aliases = JCETools.getAliases(store);
		Certificate[] chain = null;
		for (int i = 0; i < aliases.length; i++) {
			Certificate[] certs = store.getCertificateChain(aliases[i]);
			if (chain == null || (certs != null && chain.length < certs.length))
				chain = certs;
		}
		return chain;
	}
}