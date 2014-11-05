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

/* $Id: AksResponse.java,v 1.2 2012/02/28 08:21:58 pakj Exp $ */

package org.openoces.opensign.certificate.plugin.cdcard.util;

/**
 * This class represents the response from the AKS server
 *
 * @author Preben Valeur  <prv@tdc.dk>
 */

import java.util.StringTokenizer;

public class AksResponse {
//    0: alt i orden, her kommer adgangskoden
//    1: pin-kode i orden men det er f�rste gang, her kommer aftaleteksten der skal godkendes f�r aktivering
//    2: pin-kode er i orden men certifikatet er blevet sp�rret (eller?)
//    3: ikke-eksisterende n�gle-id eller forkert pin-kode
    public static final int OK = 0;
    public static final int FIRST_TIME = 1;
    public static final int REVOKED = 2;
    public static final int FAIL = 3;
    public static final int TOO_MANY = 4;
    public static final int SYSTEM_ERROR = 5;
    public static final int ERROR_CPR_MISMATCH = 6;
    public static final int ERROR_PIN_EXPIRED = 7;
    public static final int PIN_OK_FIRST_TIME_FORCE_CHANGE = 8;    
    public static final int ERROR_CARD_CLOSED = 9;    
    public static final int INVALID_NEW_PIN = 10;    
    public static final int PIN_OK_REACTIVATE_FORCE_CHANGE = 11;
    public static final int PIN_OK_REACTIVATE_FORCE_CHANGE_POCES = 12;
    public static final int FIRST_TIME_POCES = 13;
    private int status;
    private String msg; //
    private String extraInfo; //
//    status=<int>\n
//    content=<aftaletekst>|<adgangskode>
    private static final String STATUS_PROP = "status=";
    private static final String CONTENT_PROP = "content=";
    private static final String EXTRA_CONTENT_PROP = "extrainfo=";

    AksResponse(String responseData) throws Exception {
        // parse it
        StringTokenizer strTok = new StringTokenizer(responseData, "\n");
        if (!strTok.hasMoreElements()) {
            throw new Exception("Unexpected format for aks response - no elements");
        }
        String statusLine = strTok.nextToken();
        if (!(statusLine.indexOf(STATUS_PROP) == 0)) {
            throw new Exception("Unexpected format for aks response - invalid status line:"+statusLine);
        }
        try {
            String statusString = statusLine.substring(STATUS_PROP.length()).trim();
            status = Integer.parseInt(statusString);
        } catch (NumberFormatException e) {
            throw new Exception("Unexpected format for aks response - invalid status line number:" + e.getMessage(), e);
        }
        if (!strTok.hasMoreElements()) {
            throw new Exception("Unexpected format for aks response - no content line");
        }
        String contentLine = strTok.nextToken();
        if (!(contentLine.indexOf(CONTENT_PROP) == 0)) {
            throw new Exception("Unexpected format for aks response - invalid content line:"+contentLine);
        }
        // catenate all the text until extracontent
        StringBuffer msgBuf = new StringBuffer();
        msgBuf.append(contentLine.substring(CONTENT_PROP.length()));
        for (; strTok.hasMoreTokens();) {
            contentLine = strTok.nextToken();
            if (contentLine.indexOf(EXTRA_CONTENT_PROP) == 0) {
                extraInfo = contentLine.substring(EXTRA_CONTENT_PROP.length());
                break;
            }
            msgBuf.append("\n").append(contentLine);
        }
        msg = msgBuf.toString();
    }

    public final int getStatus() {
        return status;
    }

    public final String getMsg() {
        return msg;
    }

    public final String getExtraInfo() {
        return extraInfo;
    }

    public final String toString() {
        return "AksResponse: \n\tstatus: " + status + "\n\tmsg:" + msg + "\n\textraInfo:" + extraInfo;
    }
}
