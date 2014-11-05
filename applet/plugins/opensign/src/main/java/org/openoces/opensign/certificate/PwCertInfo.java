/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard
    Copyright 2010 Paw F. Kjeldgaard
	Copyright 2013 Anders M. Hansen

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

/* $Id: PwCertInfo.java,v 1.5 2013/03/05 11:24:17 anmha Exp $ */

package org.openoces.opensign.certificate;

import org.openoces.opensign.certificate.x509.KeyUsage;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.OS;
import org.openoces.opensign.client.applet.dialogs.DlgPassword;
import org.openoces.opensign.client.applet.dialogs.InputValidator;
import org.openoces.opensign.client.applet.dialogs.PasswordEnteredListener;
import org.openoces.opensign.client.applet.resources.ResourceManager;
import org.openoces.opensign.crypto.RsaWithSha256Handler;
import org.openoces.opensign.crypto.SignatureAlgorithmFactory;
import org.openoces.opensign.crypto.SignatureAlgorithmHandler;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.util.Date;

/**
 * This class represents ..
 *
 * @author Preben Valeur  		<prv@tdc.dk>
 * @author Paw F. Kjeldgaard 	<pakj@danid.dk>
 * @author Anders M. Hansen 	<consult@ajstemp.dk>
 */
public abstract class PwCertInfo extends AbstractCertificateHandler implements CertificateInfo, DecryptingCertificateHandler {
    protected File file;
    protected CallBackHandler handler;

    public PwCertInfo(File file) {
        this.file = file;
    }

    public void setCallBackHandler(CallBackHandler handler) {
        this.handler = handler;
    }

    protected CertificateInfo getCertInfo(char[] password) throws IOException {
        try {
            if (password == null) return null;
            KeyStore keyStoreInstance = getKeyStore(password);
            return keyStoreInstance != null ? OS.getCryptoSupport().getCertInfo(keyStoreInstance) : null;
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    protected abstract KeyStore getKeyStore(char[] password) throws IOException;

    public Principal getSubjectDN() throws IOException {
        return certInfo.getSubjectDN();
    }

    public Principal getIssuerDN() throws IOException {
        return certInfo.getIssuerDN();
    }


    public BigInteger getSerialNumber() throws IOException {
        return certInfo.getSerialNumber();
    }

    public Date getNotBefore() throws IOException {
        return certInfo.getNotBefore();
    }

    public Date getNotAfter() throws IOException {
        return certInfo.getNotAfter();
    }

    public int getVersion() throws IOException {
        return certInfo.getVersion();
    }


    public byte[] sign(byte[] toBeSigned, SignatureAlgorithmFactory signatureAlg) throws GeneralSecurityException, IOException {
        if (canSign()) {
            PrivateKey pk = OS.getCryptoSupport().getPrivateKey(getKeyStore(this.password), this.password);
            return OS.getCryptoSupport().sign(toBeSigned, pk, signatureAlg.getHandler(certInfo).getSignatureAlgorithm());
        } else
            throw new SignatureException("certificate not signable");
    }

    public byte[] digest(byte[] toBeHashed, SignatureAlgorithmHandler signatureAlgorithmHandler) throws GeneralSecurityException {
        if (signatureAlgorithmHandler instanceof RsaWithSha256Handler)
            return OS.getCryptoSupport().sha256(toBeHashed);
        else
            return OS.getCryptoSupport().sha1(toBeHashed);
    }


    public byte[] getCertificate() throws IOException {
        return certInfo.getCertificate();
    }

    public byte[][] getCertificateChain() throws IOException {
        try {
            return OS.getCryptoSupport().getCertificateChain(getKeyStore(this.password));
        } catch (KeyStoreException e) {
            throw new IOException(e.getMessage(), e);
        } catch (CertificateEncodingException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    public String getKeyUsage() throws IOException {
        return certInfo.getKeyUsage();
    }


    public boolean canSign() throws IOException {
        return certInfo.canSign();
    }

    public void promptForPassword(CallBackHandler callBackHandler, JComponent actionButton, PasswordEnteredListener listener) {
        DlgPassword dlg = new DlgPassword(callBackHandler, actionButton, ResourceManager.getString("DLG_PASSWORD_HEADER_PREFIX") + " " + file, getInputValidator());
        dlg.addPasswordEnteredListener(this);
        dlg.addPasswordEnteredListener(listener);
        dlg.show();
    }

    public KeyUsage getIntendedKeyUsage() throws IOException {
        return certInfo.getIntendedKeyUsage();
    }

    /**
     * @return File
     */
    public File getFile() {
        return file;
    }

    /*
    * Decrypting stuff
    */
    private boolean canDecrypt(char[] pw) {
        return true;
    }

    public PrivateKey getPrivateKey() throws Exception {
        if (canDecrypt(password)) {
            return OS.getCryptoSupport().getPrivateKey(getKeyStore(password), password);
        } else {
            return null;
        }
    }

    // if above returns null: support this one
    public byte[] decrypt(byte[] encrypted) throws Exception {
        return null;
    }

    protected abstract InputValidator getInputValidator();

    public void close() {
    	file = null;
    	handler = null;
    }
}