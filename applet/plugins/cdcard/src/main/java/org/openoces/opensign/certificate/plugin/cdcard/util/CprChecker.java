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

/* $Id: CprChecker.java,v 1.2 2012/02/28 08:21:58 pakj Exp $ */

package org.openoces.opensign.certificate.plugin.cdcard.util;

/**
 * This class implements a syntax checker for CPR numbers
 *
 * @author Preben Valeur  <prv@tdc.dk>
 */

import org.openoces.opensign.utils.FileLog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.StringTokenizer;

public class CprChecker {

    protected CprChecker() {}

    public static boolean valid(String CPR) {
        StringTokenizer tokenizer = new StringTokenizer(CPR, "-");
        StringBuffer tmp = new StringBuffer();
        while (tokenizer.hasMoreTokens()) {
            tmp.append(tokenizer.nextToken());
        }
        if (tmp.length() != 10) {
            return false;
        }
        for (int i = 0; i < tmp.length(); i++) {
            if (!Character.isDigit(tmp.charAt(i))) {
                return false;
            }
        }
        int year = getBirthYear(tmp.toString());

        int month = (tmp.charAt(2) - '0') * 10 + (tmp.charAt(3) - '0');
        int date = (tmp.charAt(0) - '0') * 10 + (tmp.charAt(1) - '0');

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(month + "/" + date + "/" + year);
        } catch (java.text.ParseException pe) {
            return false;
        }

        return true;

    }

    /**
     *
     */
    public static void main(String[] args) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String date = "18.11.2003";
        df.parse(date);
        String[] cprs = {"1811712945"};

        for (String cpr : cprs) {
            System.out.println("cpr: " + cpr);
//            System.out.println("age: " + CprChecker.getAgeInYears(cpr));
//            System.out.println("age on " + date + " : " + CprChecker.getAgeInYears(cpr, d));
            System.out.println("valid: " + CprChecker.valid(cpr));
        }

    }


    public static int getBirthYear(String CPR) {
        int lbn = CPR.charAt(6) - '0';
        int year = Integer.parseInt(CPR.substring(4, 6));

        if (lbn < 4) {
            return year + 1900;
        }
        if (lbn == 4 && year <= 36) {
            return year + 2000;
        }
        if (lbn == 4 && year >= 37) {
            return year + 1900;
        }
        if (lbn == 5 && year <= 36) {
            return year + 2000;
        }
        if (lbn == 5 && year >= 58) {
            return year + 1800;
        }
        if (lbn == 6 && year <= 36) {
            return year + 2000;
        }
        if (lbn == 6 && year >= 58) {
            return year + 1800;
        }
        if (lbn == 7 && year <= 36) {
            return year + 2000;
        }
        if (lbn == 7 && year >= 58) {
            return year + 1800;
        }
        if (lbn == 8 && year <= 36) {
            return year + 2000;
        }
        if (lbn == 8 && year >= 58) {
            return year + 1800;
        }
        if (lbn == 9 && year <= 36) {
            return year + 2000;
        }
        if (lbn == 9 && year >= 37) {
            return year + 1900;
        }
        // Handle unused intervals for lbn == 5, 6, 7, 8
        return -1;
    }
}
