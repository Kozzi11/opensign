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

/* $Id: SerialNoCertificateFilter.java,v 1.2 2012/02/28 08:21:10 pakj Exp $ */

package org.openoces.opensign.certificate.filter;

/**
 * This class represents a serial number certificate filter
 *
 * @author Preben Valeur  <prv@tdc.dk>
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 */

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.exceptions.UserCancel;
import org.openoces.opensign.exceptions.InputDataError;
import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.utils.FileLog;

import java.math.BigInteger;
import java.io.UnsupportedEncodingException;

public class SerialNoCertificateFilter extends CertificateFilter {

    BigInteger serialNoToMatch = null;

    public boolean accept(CertificateHandler cert) throws UserCancel, InputDataError {
        try {
            return cert.getSerialNumber().equals(serialNoToMatch);
        } catch (Exception e) {
            FileLog.error("An exception occurred: " + e, e);
        }
        FileLog.info("An error occurred while filtering on serial numbers. Certificate not excluded.");
        return true;
    }

    public void setParameter(String parameter) {
        byte[] ba = Base64.decode(parameter.getBytes());
        try {
            String s = new String(ba, "UTF8");
            if (s.startsWith("0x")) {
                serialNoToMatch = new BigInteger(s.substring(2), 16);
            } else {
                serialNoToMatch = new BigInteger(s);
            }
        } catch (UnsupportedEncodingException e) {
            FileLog.error("An exception occurred: " + e, e);
        }
    }
}
