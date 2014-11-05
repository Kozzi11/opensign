/*
    Copyright 2006 Paw F. Kjeldgaard

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

/* $Id: SunCryptoFactory.java,v 1.5 2012/09/27 11:03:45 pakj Exp $ */

package org.openoces.opensign.certificate.support.suncrypto;

import org.openoces.opensign.utils.FileLog;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * @author Paw F. Kjeldgaard  <pakj@danid.dk>
 */
class SunCryptoFactory implements CryptoFactory {

    private CertificateFactory certificateFactory;

    SunCryptoFactory() {
        try {
            this.certificateFactory = CertificateFactory.getInstance("X.509");
        } catch (CertificateException e) {
            FileLog.fatal(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public X509Certificate createCertificate(byte[] bytes) throws CertificateException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        return (X509Certificate) certificateFactory.generateCertificate(inputStream);
    }
}