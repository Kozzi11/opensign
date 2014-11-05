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

/* $Id: StringMatchCertificateFilter.java,v 1.2 2012/02/28 08:21:10 pakj Exp $ */

package org.openoces.opensign.certificate.filter;

/**
 * This class represents an abstract string matching filter
 *
 * @author Preben Valeur  <prv@tdc.dk>
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 */

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.utils.FileLog;

import java.util.StringTokenizer;
import java.io.UnsupportedEncodingException;

public abstract class StringMatchCertificateFilter extends CertificateFilter {
    protected String [] matchStrings;
    private static boolean PLAIN = false;  // for debugging purposes
    private void parseParameter(String b64filter){
        byte[] ba = Base64.decode(b64filter.getBytes());
        try {
            String plainFilter = new String(ba, "UTF8");
            if (PLAIN){
                plainFilter = b64filter; // don't b64 decode
            }
            StringTokenizer tok = new StringTokenizer(plainFilter, "|");
            matchStrings = new String[tok.countTokens()];
            int tokNo = 0;
            while (tok.hasMoreTokens()){
                String token = tok.nextToken();
                matchStrings[tokNo++] = token;
            }
        } catch (UnsupportedEncodingException e) {
            FileLog.error(e.getMessage(), e);
        }
    }
    public boolean accept(CertificateHandler certificate) {
        try {
            String dn = getCertificateStringToMatch(certificate);
            for (String matchString : matchStrings) {
                if (compare(dn, matchString)) {
                    return true;
                }
            }
        } catch (Exception e) {
            FileLog.error(e.getMessage(), e);
            return false;
        }
        /* return true in case we match against the empty list of substrings */
        return matchStrings.length == 0;
    }

    public void setParameter(String parameter){
        parseParameter(parameter);
    }

    /**
     *
     * @param certificate
     * @return String from certificate to match against
     * @throws Exception
     */
    public abstract String getCertificateStringToMatch(CertificateHandler certificate) throws Exception;

    /**
     * Compare the to-be-matched string from the certificate to the pattern
     * @param tbm The to-be-matched string from the certificate
     * @param pattern The pattern to be matched
     * @return if the to-be-matched string matches the pattern
     */
    public abstract boolean compare(String tbm, String pattern);
}
