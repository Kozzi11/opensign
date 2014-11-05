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

/* $Id: MicrosoftWindowsCertificateHandler.java,v 1.6 2013/01/23 08:46:27 anmha Exp $ */

package org.openoces.opensign.certificate.plugin.capi;

/**
 * This class represents the interface to a certificate accessed through the Microsoft cryptoapi
 *
 * @author Carsten Raskgaard   <carsten@raskgaard.dk>
 * @author Preben Valeur  <prv@tdc.dk>
 * @author Kim Rasmussen  <kr@it-practice.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 * @author Mads Jensen <mjn@trifork.com>
 * @author Jeppe Burchhardt <Jeppe.Burchhardt@Cryptomathic.com>
 * @author Ole Friis Østergaard <ofo@trifork.com>
 * @author Anders M. Hansen <consult@ajstemp.dk>
 */
import org.openoces.opensign.certificate.AbstractCertificateHandler;
import org.openoces.opensign.certificate.CertificateInfo;
import org.openoces.opensign.certificate.NotExportableException;
import org.openoces.opensign.certificate.StringPrincipal;
import org.openoces.opensign.certificate.x509.KeyUsage;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.OS;
import org.openoces.opensign.client.applet.dialogs.PasswordEnteredListener;
import org.openoces.opensign.crypto.RsaWithSha256Handler;
import org.openoces.opensign.crypto.SignatureAlgorithmFactory;
import org.openoces.opensign.crypto.SignatureAlgorithmHandler;
import org.openoces.opensign.exceptions.UserCancel;

import javax.swing.*;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.security.SignatureException;
import java.util.Date;
import java.util.Properties;

public class MicrosoftWindowsCertificateHandler extends AbstractCertificateHandler {

    private byte[][] binaryCertificate;
    private MicrosoftCapi capi;
    private Principal subjectPrincipal;
    private Principal issuerPrincipal;
    private BigInteger serialNumber;
    private KeyUsage keyUsage;
    private Date cachedNotBefore;
    private Date cachedNotAfter;
    private int cachedVersion = -1;

    public MicrosoftWindowsCertificateHandler(byte[][] binaryCertificate, MicrosoftCapi microsoftCapi) {
        this.binaryCertificate = binaryCertificate.clone();
        this.capi = microsoftCapi;
        subjectPrincipal = null;
        keyUsage = null;
    }

    public final void promptForPassword(CallBackHandler callBackHandler, JComponent oldFocusComponent, PasswordEnteredListener listener) {
        listener.validPasswordEntered(password);
    }

    public final boolean isInfoAvailable() {
        return true;
    }

    protected final CertificateInfo getCertInfo(char[] password) throws IOException {
        return this;
    }

    public final String getUserFriendlyName() {
        // remove anything but CN= to shorten name as much as possible. Detailed info can be viewed by selecting details
        int s0 = getSubjectDN().getName().toLowerCase().indexOf("cn=") + 3;
        int s1 = getSubjectDN().getName().toLowerCase().indexOf(",", s0);
        int s2 = getSubjectDN().getName().toLowerCase().indexOf("+", s0);
        if (s1 == -1)
            s1 = getSubjectDN().getName().length();
        if (s2 != -1)
            s1 = s2;
        return getSubjectDN().getName().substring(s0, s1).trim();
    }

    public final Principal getSubjectDN() {
        if (subjectPrincipal == null) {
            subjectPrincipal = new StringPrincipal(capi.getSubjectDnString(binaryCertificate[0]));
        }
        return subjectPrincipal;
    }

    public final Principal getIssuerDN() {
        if (issuerPrincipal == null) {
            issuerPrincipal = new StringPrincipal(capi.getIssuerDnString(binaryCertificate[0]));
        }
        return issuerPrincipal;
    }

    public final BigInteger getSerialNumber() {
    	if (serialNumber == null) {
    		serialNumber = capi.getSerialNumberBigInteger(binaryCertificate[0]);
    	}
        return serialNumber;
    }

    public final Date getNotBefore() {
        if (cachedNotBefore == null)
            cachedNotBefore = capi.getNotBeforeDate(binaryCertificate[0]);
        return new Date(cachedNotBefore.getTime());
    }

    public final Date getNotAfter() {
        if (cachedNotAfter == null)
            cachedNotAfter = capi.getNotAfterDate(binaryCertificate[0]);
        return new Date(cachedNotAfter.getTime());
    }

    public final int getVersion() {
        if (cachedVersion == -1)
            cachedVersion = capi.getCertificateVersion(binaryCertificate[0]);
        return cachedVersion;
    }


    public final byte[] sign(byte[] toBeSigned, SignatureAlgorithmFactory signatureAlg) throws GeneralSecurityException, UserCancel {
        byte[] data = capi.signMessage(toBeSigned, binaryCertificate[0], signatureAlg.getHandler(this).getCapiAlgorithm());

        if (data == null) {
            int capiError = getLastErrorCode();
            if (capiError == 11) throw new UserCancel();
            // todo: check errornumber for various conditions and react properly - telling the user when needed etc
            // note that different CSP's can return different codes etc.
            // for now: expect the user to have cancelled

            throw new SignatureException("Sign message failed. CAPI failed with error code: " + capiError);
        }

        return data;
    }

    private int getLastErrorCode() {
        return capi.getLastErrorCode();
    }


    public final byte[] digest(byte[] toBeHashed, SignatureAlgorithmHandler signatureAlgorithmHandler) throws GeneralSecurityException {
        byte[] data;
        if (signatureAlgorithmHandler instanceof RsaWithSha256Handler) {
            data = OS.getCryptoSupport().sha256(toBeHashed);
        } else {
            data = OS.getCryptoSupport().sha1(toBeHashed);
        }
        return data;
    }

    public final byte[] getCertificate() {
        return binaryCertificate != null ? binaryCertificate[0].clone() : null;
    }

    public final byte[][] getCertificateChain() {
        return binaryCertificate.clone();
    }

    public final String getKeyUsage() {
        return "not implemented yet";
    }

    public final boolean canSign() {
        KeyUsage ku = getIntendedKeyUsage();
        return ku == null || ku.includesDigitalSignature() || ku.includesNonRepudiation();

    }

    public final KeyUsage getIntendedKeyUsage() {
        if (keyUsage == null) {
            keyUsage = getIntendedKeyUsage(getCertificate());
        }
        return keyUsage;
    }

    private KeyUsage getIntendedKeyUsage(byte[] certificate) {

        int i = capi.getKeyUsage(certificate);

        return new KeyUsage((i & 1) != 0,

                (i & 2) != 0,

                (i & 4) != 0,

                (i & 8) != 0,

                (i & 16) != 0,

                (i & 32) != 0,

                (i & 64) != 0);
    }

    public final String getStoreName() {
        return "Microsoft Windows";
    }

    // if you want to pass extra info about a certificate (like path to cd-card...)
    public final Properties getExtraCertificateProperties() {
        return null;
    }

    @Override
    public boolean isExportable() {
        return false;
    }

    @Override
    public byte[] getPkcs12() throws NotExportableException, IOException {
        throw new NotExportableException();
    }
}