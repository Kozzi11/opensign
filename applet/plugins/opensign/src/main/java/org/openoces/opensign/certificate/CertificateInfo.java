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

/* $Id: CertificateInfo.java,v 1.2 2012/02/28 08:21:22 pakj Exp $ */

package org.openoces.opensign.certificate;

import org.openoces.opensign.certificate.x509.KeyUsage;

import java.io.IOException;
import java.math.BigInteger;
import java.security.Principal;
import java.util.Date;

/**
 * This class represents a subset of the X509Certificate interface. It represents what you can
 * extract about a certificate (but NO actions)
 *
 * @author Preben Valeur  <prv@tdc.dk>
 */
public interface CertificateInfo {

    public boolean isInfoAvailable();

    public String getUserFriendlyName();

    public Principal getSubjectDN() throws IOException;

    public Principal getIssuerDN() throws IOException;

    public BigInteger getSerialNumber() throws IOException;

    public Date getNotBefore() throws IOException;

    public Date getNotAfter() throws IOException;

    public int getVersion() throws IOException;

    public String getKeyUsage() throws IOException;

    public boolean canSign() throws IOException;

    public KeyUsage getIntendedKeyUsage() throws IOException;

    public byte[] getCertificate() throws IOException;
}
