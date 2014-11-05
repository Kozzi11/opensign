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

/* $Id: ConditionsHeaderPanel.java,v 1.2 2012/02/28 08:21:39 pakj Exp $ */

package org.openoces.opensign.certificate.plugin.cdcard.dialogs;

/**
 * This class represents the header of the gui used to display the conditions that must be agreed to
 * before a cdcard can be used.
 *
 * @author Preben Valeur  <prv@tdc.dk>
 */


import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.utils.X500Name;

import java.io.IOException;
import java.util.StringTokenizer;


public class ConditionsHeaderPanel {
    public static final int ACTIVATE = 0;
    public static final int LRA_ACTIVATE = 1;
    public static final int LRA_RE_ACTIVATE = 2;
    public static final int POCES_REACTIVATE = 3;
    public static final int POCES_ACTIVATE_CPR = 4;
    public static final int POCES_ACTIVATE_PIN = 5;

    protected ConditionsHeaderPanel() {}

    static String prependHeader(int state, CertificateHandler certificate, String conditions) throws Exception {
        return prependHeader(state, certificate, conditions, null);
    }

    static String prependHeader(int state, CertificateHandler certificate, String conditions, String lraInfo) throws IOException {
        String subjectDN = certificate.getSubjectDN().getName();
        X500Name name = new X500Name(subjectDN);
        StringBuffer buf = new StringBuffer();
        if ( state == POCES_ACTIVATE_PIN ) {
           return  "Du skal nedenfor vælge din egen personlige PIN-kode til CD-kortet, som du skal indtaste, når du benytter kortet.";
        }
        buf.append(getIntro(state));
        buf.append("\n");
        buf.append(getCertContentLine(name,state));
        buf.append("\n");
        buf.append(getOutro(state, lraInfo));
        if (conditions != null && conditions.indexOf("null") != 0) {
            buf.append("\n");
            buf.append(conditions);
        }
        return buf.toString();
    }

    static String getOkPrompt(int state) {
        switch (state) {
            case ACTIVATE:
                return "Du aktiverer din nye digitale signatur ved at indtaste dit CPR nummer herunder og trykke \"Ok\".";
            case LRA_ACTIVATE:
                return "Du aktiverer din nye digitale signatur ved at vælge en PIN-kode herunder og trykke \"Ok\".";
            case LRA_RE_ACTIVATE:
                return "Du genåbner din digitale signatur ved at vælge en ny PIN-kode herunder og trykke \"Ok\".";
            case POCES_REACTIVATE:
                return "Du genåbner din digitale signatur ved at vælge en ny PIN-kode herunder og trykke \"Ok\".";
            case POCES_ACTIVATE_PIN:
                return "Du aktiverer din digitale signatur ved at vælge en ny PIN-kode herunder og trykke \"Ok\".";
            case POCES_ACTIVATE_CPR:
                return "Angiv dit CPR nummer herunder og tryk \"Ok\".";


        }
        return "error - unknown state";
    }

    private static String getCertContentLine(X500Name name, int state) {
        StringBuffer buf = new StringBuffer();
        buf.append("Indhold af signatur: Navn: ");
        buf.append(name.getCN());
        if (state != POCES_ACTIVATE_CPR && state != POCES_ACTIVATE_PIN) {
            buf.append(", Organisation: ");
            buf.append(name.getO());
        }
        return buf.toString();
    }

    private static String getIntro(int state) {
        switch (state) {
            case ACTIVATE:
            case LRA_ACTIVATE:
                return "Du har modtaget en digital signatur på CD kort, og dette er første gang du anvender den.";
            case LRA_RE_ACTIVATE:
                return "Den digitale signatur på CD kort kan nu genåbnes.";
            case POCES_REACTIVATE:
                return "Den digitale signatur på CD-kort kan nu genåbnes.";
            case POCES_ACTIVATE_CPR:
                return "Du har modtaget en digital signatur på CD-kort, og dette er første gang du anvender den.";
            case POCES_ACTIVATE_PIN:
                return "Du har modtaget en digital signatur på CD kort, og dette er første gang du anvender den.";
        }
        return "internal error - unknown state";
    }

    private static String getOutro(int state, String lraInfo) {
        String lraInfoString = null;
        if (lraInfo != null) {
            StringTokenizer strTok = new StringTokenizer(lraInfo, ";");
            if (strTok.countTokens() == 2) {
                StringBuffer buf = new StringBuffer();
                String lraName = strTok.nextToken();
                buf.append(lraName);
                String lraEmail = strTok.nextToken();
                if (lraEmail != null && !lraEmail.trim().equals("null") && lraEmail.trim().length() > 0) {
                    buf.append(" (");
                    buf.append(lraEmail);
                    buf.append(").\n");
                } else {
                    buf.append("\n");
                }
                lraInfoString = buf.toString();
            } else {
                // no lra info for some reason...
//				lraInfoString = "internal error - malformed lra info";
            }
        }
        switch (state) {
            case ACTIVATE:
                return "Hvis indholdet af signaturen ikke er korrekt, skal du trykke \"Annuller\" herunder \n" +
                        "og derefter destruere CD kort og pinkode.";
            case LRA_ACTIVATE: {
                StringBuffer buf = new StringBuffer();
                buf.append("Hvis indholdet af signaturen ikke er korrekt, skal du trykke \"Annuller\" herunder \n");
                buf.append("og derefter destruere CD kortet.\n");
                if (lraInfoString != null) {
                    buf.append("Denne digitale signatur er udstedt af din LRA:\n");
                    buf.append(lraInfoString);
                }
                return buf.toString();
            }
            case LRA_RE_ACTIVATE: {
                StringBuffer buf = new StringBuffer();
                buf.append("Hvis indholdet af signaturen ikke er korrekt, skal du trykke \"Annuller\" herunder \n");
                buf.append("og derefter destruere CD kortet.\n");
                buf.append("Genåbningen af din digitale signatur er foranlediget af din LRA:\n");
                buf.append(lraInfoString);
                return buf.toString();
            }
            case POCES_REACTIVATE: {
                StringBuffer buf = new StringBuffer();
                buf.append("Hvis indholdet af signaturen ikke er korrekt, skal du trykke \"Annuller\" herunder \n");
                buf.append("og derefter destruere CD-kortet.\n");
                return buf.toString();
            }
            case POCES_ACTIVATE_CPR:
                return "Hvis indholdet af signaturen ikke er korrekt, skal du trykke \"Annuller\" herunder \n" +
                        "og derefter destruere CD-kort og pinkode.";


        }
        return "internal error - unknown state";
    }
}
