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
*/

/* $Id: CryptoSupport.java,v 1.2 2012/02/28 08:21:48 pakj Exp $ */

package org.openoces.opensign.crypto;

import org.openoces.opensign.certificate.CertificateInfo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.util.Map;

/**
 * This interface defines all cryptographic method sigantures.
 *
 * @author Jens Bo Friis  <jbf@it-practice.dk>
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 * @author Mads Jensen <mjn@trifork.com>
 * @author Jeppe Burchhardt <Jeppe.Burchhardt@Cryptomathic.com>
 * @author Ole Friis Østergaard <ofo@trifork.com>
 *
 */

public interface CryptoSupport {
    public byte[] sign(byte[] signText, PrivateKey privateKey, String signatureAlg);
    public CertificateInfo getCertInfo(String b64clientCert);
    public CertificateInfo getCertInfo(KeyStore keyStore);
    public boolean verify(String modulus, String publicExp, byte [] signedValue, byte [] signatureToVerify);
    public byte[] sha1(byte[] bytes) throws GeneralSecurityException;
    public byte[] sha256(byte[] bytes) throws GeneralSecurityException;
    public SSLResponse httpsPost(URL url, Map<String, String> params);
    // pkcs12 support...
    public KeyStore getKeyStore(String pkcs12string, char [] pw) throws KeyStoreException, NoSuchAlgorithmException, IOException, CertificateException;
    public KeyStore getKeyStore(File file, char [] pw) throws KeyStoreException, NoSuchAlgorithmException, NoSuchProviderException, IOException, CertificateException;
    public PrivateKey getPrivateKey(KeyStore keyStore, char [] pw) throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, NoSuchProviderException;
    public byte [][] getCertificateChain(KeyStore keyStore) throws KeyStoreException, CertificateEncodingException;
}
