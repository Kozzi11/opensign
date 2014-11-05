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

/* $Id: CertificateHandler.java,v 1.5 2012/12/19 12:28:03 pakj Exp $ */

package org.openoces.opensign.certificate;

import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.dialogs.PasswordEnteredListener;
import org.openoces.opensign.crypto.SignatureAlgorithmFactory;
import org.openoces.opensign.crypto.SignatureAlgorithmHandler;
import org.openoces.opensign.exceptions.UserCancel;

import javax.swing.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * This class represents a generic interface to a certificate
 *
 * @author Jens Bo Friis  <jbf@it-practice.dk>
 */
public interface CertificateHandler extends CertificateInfo, Comparable {


    byte[] sign(byte[] toBeSigned, SignatureAlgorithmFactory signatureAlg) throws GeneralSecurityException, IOException, UserCancel;

    /**
     * Return the known chain of der encoded certificates. The user certificate is at index 0
     * @return byte[]
     * @throws Exception On error
     */
    byte[][] getCertificateChain() throws IOException;

    String getStoreName();

    // if you want to pass extra info about a certificate (like path to cd-card...)
    Properties getExtraCertificateProperties();


    byte[] digest(byte[] data, SignatureAlgorithmHandler signatureAlgorithmHandler) throws GeneralSecurityException, IOException;

    void promptForPassword(CallBackHandler callBackHandler, JComponent actionButton, PasswordEnteredListener listener);

    boolean isExportable();

    byte[] getPkcs12() throws NotExportableException, IOException;
}