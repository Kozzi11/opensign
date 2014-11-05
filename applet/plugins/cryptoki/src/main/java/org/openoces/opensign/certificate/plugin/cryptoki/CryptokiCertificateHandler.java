/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard
    Copyright 2011 Daniel Andersen
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
 */

/* $Id: CryptokiCertificateHandler.java,v 1.13 2013/03/05 11:24:18 anmha Exp $ */

package org.openoces.opensign.certificate.plugin.cryptoki;

import iaik.pkcs.pkcs11.TokenException;
import org.openoces.opensign.certificate.AbstractCertificateHandler;
import org.openoces.opensign.certificate.CertificateInfo;
import org.openoces.opensign.certificate.NotExportableException;
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
import org.openoces.opensign.exceptions.UserCancel;
import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.utils.FileLog;

import javax.swing.*;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Properties;

/**
 * This class represents a certificate stored on a crypto-device
 *
 * @author Daniel Andersen <daand@nets.eu>
 * @author Anders M. Hansen <consult@ajstemp.dk>
 */
public class CryptokiCertificateHandler extends AbstractCertificateHandler {
    private final byte[][] binaryCertificate;
    private final X509Certificate userCertificate;
    private final PKCS11Handler pkcs11Handler;
    private boolean canceled = false;

    public CryptokiCertificateHandler(byte[][] binaryCertificate, X509Certificate userCertificate, PKCS11Handler pkcs11Handler) {
        this.userCertificate = userCertificate;
        this.binaryCertificate = binaryCertificate.clone();
        this.pkcs11Handler = pkcs11Handler;
    }

    protected final CertificateInfo getCertInfo(char[] password) throws IOException {
        try {
            if (binaryCertificate == null) {
                return null;
            }
            String base64EncodedCertificate = new String(Base64.encode(binaryCertificate[0]));
            return OS.getCryptoSupport().getCertInfo(base64EncodedCertificate);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    public final byte[] sign(byte[] toBeSigned, SignatureAlgorithmFactory signatureAlg) throws GeneralSecurityException, IOException, UserCancel {
        try {
            String algorithm = signatureAlg.getHandler(this).getSignatureAlgorithm();
            try {
                return pkcs11Handler.sign(toBeSigned, userCertificate, algorithm);
            } finally {
                pkcs11Handler.logout();
            }
        } catch (TokenException e) {
            throw new GeneralSecurityException("Token exception while signing", e);
        }
    }

    public final byte[] getCertificate() throws IOException {
        return binaryCertificate != null ? binaryCertificate[0].clone() : null;
    }

    public final byte[][] getCertificateChain() throws IOException {
        return binaryCertificate != null ? binaryCertificate.clone() : null;
    }

    public final String getStoreName() {
        return ResourceManager.getString("DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE_CRYPTOKI");
    }

    public final Properties getExtraCertificateProperties() {
        return null;
    }

    public final byte[] digest(byte[] toBeHashed, SignatureAlgorithmHandler signatureAlgorithmHandler) throws GeneralSecurityException, IOException {
        try {
            if (!(signatureAlgorithmHandler instanceof RsaWithSha256Handler)) {
                throw new GeneralSecurityException("Signature algorithm not supported (" + signatureAlgorithmHandler.getDigestAlgorithm() + "). Only SHA-256 supported for tokens");
            }
            return pkcs11Handler.digest(toBeHashed, "SHA-256");
        } catch (TokenException e) {
            throw new GeneralSecurityException("Token exception while digesting", e);
        }
    }

    public final void promptForPassword(CallBackHandler callBackHandler, JComponent oldFocusComponent, PasswordEnteredListener listener) {
        if (password != null) {
            listener.validPasswordEntered(password);
            return;
        } else if (pkcs11Handler.isProtectedAuthenticationPath()) {
            listener.validPasswordEntered(null);
            return;
        } else if (!pkcs11Handler.requiresLogin()) {
            listener.validPasswordEntered(null);
            return;
        }
        DlgPassword dlg = new DlgPassword(callBackHandler, oldFocusComponent, ResourceManager.getString("DLG_PASSWORD_HEADER_PREFIX") + " " + ResourceManager.getString("DLG_PASSWORD_HEADER_CRYPTOKI"), new CryptokiKeystoreHandlerPasswordValidator(pkcs11Handler));
        dlg.addPasswordEnteredListener(this);
        dlg.show();
        if(canceled) {
        	listener.cancelled();
        	canceled=false;
        } else {
        	listener.validPasswordEntered(password);
        }
    }

    public final String getUserFriendlyName() {
        return certInfo.getUserFriendlyName();
    }

    public final Principal getSubjectDN() throws IOException {
        return certInfo.getSubjectDN();
    }

    public final Principal getIssuerDN() throws IOException {
        return certInfo.getIssuerDN();
    }

    public final BigInteger getSerialNumber() throws IOException {
        return certInfo.getSerialNumber();
    }

    public final Date getNotBefore() throws IOException {
        return certInfo.getNotBefore();
    }

    public final Date getNotAfter() throws IOException {
        return certInfo.getNotAfter();
    }

    public final int getVersion() throws IOException {
        return certInfo.getVersion();
    }

    public final String getKeyUsage() throws IOException {
        return certInfo.getKeyUsage();
    }

    public final boolean canSign() throws IOException {
        return isInfoAvailable() && certInfo.canSign();
    }

    public final KeyUsage getIntendedKeyUsage() throws IOException {
        return certInfo.getIntendedKeyUsage();
    }

    private final static class CryptokiKeystoreHandlerPasswordValidator implements InputValidator {
        private PKCS11Handler pkcs11Handler;

        public CryptokiKeystoreHandlerPasswordValidator(PKCS11Handler pkcs11Handler) {
            super();
            this.pkcs11Handler = pkcs11Handler;
            //we need to logout from token first in order to validate another certificate
            pkcs11Handler.logout();
        }

        public String getErrorMsg() {
            return ResourceManager.getString("ERR_WRONG_PASSWORD");
        }

        public String getInfo() {
            return ResourceManager.getString("DLG_PASSWORD_HEADER_PREFIX") + " " + ResourceManager.getString("DLG_PASSWORD_HEADER_CRYPTOKI");
        }

        public boolean isValid(char[] input) {
            try {
                pkcs11Handler.login(input);
                return true;
            } catch (TokenException e) {
                return false;
            }
        }
    }

    @Override
    public boolean isExportable() {
        return false;
    }

    @Override
    public byte[] getPkcs12() throws NotExportableException, IOException {
        throw new NotExportableException();
    }
    
    @Override
    public void cancelled() {
    	canceled = true;
    }
    
    protected void close() {
    	if(pkcs11Handler != null) {
    		pkcs11Handler.destroy();
    	}
    }
    
    protected void finalize() {
    	FileLog.debug("CryptokiCertificateHandler Finalized");
    	super.finalize();
    }
}