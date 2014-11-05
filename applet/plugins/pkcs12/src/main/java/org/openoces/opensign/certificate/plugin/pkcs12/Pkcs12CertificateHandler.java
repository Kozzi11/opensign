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
*/

/* $Id: Pkcs12CertificateHandler.java,v 1.6 2013/03/05 11:24:24 anmha Exp $ */

package org.openoces.opensign.certificate.plugin.pkcs12;

/**
 * This class represents the interface to a pkcs12 certificate
 *
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 * @author Preben Valeur  <prv@tdc.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 * @author Mads Jensen <mjn@trifork.com>
 * @author Jeppe Burchhardt <Jeppe.Burchhardt@Cryptomathic.com>
 * @author Ole Friis Østergaard <ofo@trifork.com>
 * @author Anders M. Hansen <consult@ajstemp.dk>
 */

import org.openoces.opensign.certificate.*;
import org.openoces.opensign.certificate.x509.KeyUsage;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.OS;
import org.openoces.opensign.client.applet.dialogs.*;
import org.openoces.opensign.client.applet.resources.ResourceManager;
import org.openoces.opensign.crypto.RsaWithSha256Handler;
import org.openoces.opensign.crypto.SignatureAlgorithmFactory;
import org.openoces.opensign.crypto.SignatureAlgorithmHandler;
import org.openoces.opensign.utils.ArrayUtil;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.utils.FileUtils;

import javax.swing.*;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.Properties;

public final class Pkcs12CertificateHandler extends AbstractCertificateHandler implements CopyableItem, RestoreableItem {
    private byte[][] chain;
    private File file;
    private KeyStore keyStore;
    private Pkcs12KeyStoreHandler keyStoreHandler;
    private KeyUsage keyusage = null;

    /**
     * @param file
     */
    public Pkcs12CertificateHandler(Pkcs12KeyStoreHandler keyStoreHandler, File file) {
        this.file = file;
        this.keyStoreHandler = keyStoreHandler;
    }

    /**
     * @param file
     * @param password
     */
    public Pkcs12CertificateHandler(File file, char[] password) throws IOException {
        this.file = file;
        this.password = password != null ? ArrayUtil.copyOf(password) : null;
        getKeyStore(this.password);
        getCertificateChain();
    }

    /**
     * If create from same filepath we say they are equal (a second one can replace the first one...)
     *
     * @param o
     * @return
     */
    public boolean equals(Object o) {
        if (o instanceof Pkcs12CertificateHandler) {
            Pkcs12CertificateHandler h = (Pkcs12CertificateHandler) o;
            return (this.file.getAbsolutePath().equals(h.file.getAbsolutePath()));
        } else {
            return false;
        }
    }

    /**
     * @return File
     */
    public File getFile() {
        return file;
    }

    protected CertificateInfo getCertInfo(char[] pw) throws IOException {
        return pw != null ? OS.getCryptoSupport().getCertInfo(getKeyStore(pw)) : null;
    }

    /**
     * @return String
     */

    public String getUserFriendlyName() {
        String certificateId = getCertificateID();
        return FileUtils.getFileName(certificateId);
    }

    public String getCertificateID() {
        return getFile().toString();
    }

    private synchronized KeyStore getKeyStore(char[] password) throws IOException {
        try {
            if (keyStore == null) {
                keyStore = OS.getCryptoSupport().getKeyStore(file, password);
            }
            return keyStore;
        } catch (KeyStoreException e) {
            throw new IOException(e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            throw new IOException(e.getMessage(), e);
        } catch (NoSuchProviderException e) {
            throw new IOException(e.getMessage(), e);
        } catch (CertificateException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

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

    public void promptForPassword(CallBackHandler callBackHandler, JComponent oldFocusComponent, PasswordEnteredListener listener) {
        DlgPassword dlgPassword = new DlgPassword(callBackHandler, oldFocusComponent, ResourceManager.getString("DLG_PASSWORD_HEADER_PREFIX") + " " + file, new PkcsPasswordValidator(this));
        dlgPassword.addPasswordEnteredListener(this);
        dlgPassword.addPasswordEnteredListener(listener);
        dlgPassword.show();
    }


    public byte[] sign(byte[] toBeSigned, SignatureAlgorithmFactory signatureAlg) throws GeneralSecurityException, IOException {
        if (canSign(this.password)) {
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

    /**
     *
     */
    public byte[][] getCertificateChain() throws IOException {
        try {
            if (chain == null) {
                chain = OS.getCryptoSupport().getCertificateChain(getKeyStore(this.password));
            }
            return deepCopy(chain);
        } catch (KeyStoreException e) {
            throw new IOException(e.getMessage(), e);
        } catch (CertificateEncodingException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    public void deleteCertificate() {
        File f = this.getFile();
        f.delete();
    }

    public String getKeyUsage() throws IOException {
        return certInfo.getKeyUsage();
    }

    private boolean canSign(char[] pw) {
        try {
            return certInfo.canSign();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean canSign() throws IOException {
        return canSign(password);
    }

    public KeyUsage getIntendedKeyUsage() throws IOException {
        if (keyusage == null) {
            keyusage = certInfo.getIntendedKeyUsage();
        }
        return keyusage;
    }

    // copy the item from the default place to a user selected place
    public void backup(AbstractDialog dialog, JComponent oldFocusComponent) throws IOException {
        FileDialogStrategy strategy = new SwingFileDialogStrategy(dialog, oldFocusComponent);

        File dest = strategy.getSaveFile(
                keyStoreHandler.getLastDirectoryName().toString(),
                getFile().toString(),
                new String[]{"pfx", "p12"},
                ResourceManager.getString("BACKUP_KEYFILE"));

        if (dest == null) {
            return;
        }

        File src = getFile();

        keyStoreHandler.copy(src, dest);
    }


    // delete from the default place
    public void delete(AbstractDialog dialog, JComponent oldFocusComponent) {
        deleteCertificate();
    }

    // copy the item from another place to the default place
    public void restore(CallBackHandler parent, JComponent oldFocusComponent) throws IOException {
        keyStoreHandler.importPKCS12(oldFocusComponent);
    }


    public String getStoreName() {
        return ResourceManager.getString("DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE_PKCS12") + " (" + this.getFile().toString() + ")";
    }

    // if you want to pass extra info about a certificate (like path to cd-card...)
    public Properties getExtraCertificateProperties() {
        return null;
    }

    private byte[][] deepCopy(byte[][] src) {
        int rows = src.length;
        byte[][] newArray = src.clone();
        for (int row = 0; row < rows; row++) {
            newArray[row] = src[row].clone();
        }
        return newArray;
    }

    private final class PkcsPasswordValidator implements InputValidator {
        private Pkcs12CertificateHandler certificateHandler;

        private PkcsPasswordValidator(Pkcs12CertificateHandler certificateHandler) {
            this.certificateHandler = certificateHandler;
        }

        public String getErrorMsg() {
            return ResourceManager.getString("ERR_WRONG_PASSWORD");
        }

        public String getInfo() {
            return ResourceManager.getString("DLG_PASSWORD_HEADER_PREFIX") + " " + file;
        }

        public boolean isValid(char[] input) {
            try {
                return certificateHandler.getCertInfo(input) != null;
            } catch (Exception e) {
                return false;
            }
        }

    }

    @Override
    public boolean isExportable() {
        return true;
    }

    @Override
    public byte[] getPkcs12() throws NotExportableException, IOException {
        InputStream inputStream = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            inputStream = new BufferedInputStream(new FileInputStream(getFile()));
            byte[] buffer = new byte[512];
            int i;
            while ((i = inputStream.read(buffer, 0, buffer.length)) != -1) {
                outputStream.write(buffer, 0, i);
            }
            return outputStream.toByteArray();
        } finally {
            if (inputStream != null) inputStream.close();
        }
    }
    
    public void close() {
    	file = null;
    	chain = null;
    	keyStore = null;
    	keyStoreHandler = null;
    	keyusage = null;
    }
    
    protected final void finalize() {
        try {
        	close();
        	FileLog.debug("Pkcs12CertificateHandler Finalized");
        } finally {
        	super.finalize();
        }
    }
}