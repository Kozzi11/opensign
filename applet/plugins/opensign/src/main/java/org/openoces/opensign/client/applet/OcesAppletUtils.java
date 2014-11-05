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

/* $Id: OcesAppletUtils.java,v 1.5 2013/01/18 10:10:18 anmha Exp $ */

package org.openoces.opensign.client.applet;

/**
 * This class contains code for handling logon through
 * a simple URLConnection to the server instead of logging
 * on through JS (LiveConnect)
 *
 * @author Jesper Nielsen  <?@it-practice.dk>
 */

import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.utils.StringUtils;

import javax.swing.*;
import java.net.URL;

/**
 * This class contains code for handling logon through
 * a simple URLConnection to the server instead of logging
 * on through JS (LiveConnect)
 *
 * @version $Revision: 1.5 $
 *          <p/>
 *          <pre>
 *                                                                                                             OCES certificate logon and digital signature
 *                                                                                                             Copyright(c) 2002,2003, IT Practice A/S
 *
 *                                                                                                             web page: http://it-practice.dk
 *
 *                                                                                                             This is open source software, placed under the terms of the
 *                                                                                                             GNU LESSER GENERAL PUBLIC LICENSE.
 *                                                                                                             Please see: http://www.gnu.org/licenses/lgpl.txt or
 *                                                                                                             opensign.license for details.
 *
 *                                                                                                             Permission to use, copy, modify, and distribute this software
 *                                                                                                             for any purpose and without fee is hereby granted, provided
 *                                                                                                             that the above copyright notices appear in all copies and that
 *                                                                                                             both the copyright notice and this permission notice appear in
 *                                                                                                             supporting documentation.
 *                                                                                                             </pre>
 */
public class OcesAppletUtils {

    protected OcesAppletUtils() {
    }

    static void printInitInformation(JApplet applet) {
        FileLog.debug(
                "opensign.doappletrequestonmac: "
                        + applet.getParameter("opensign.doappletrequestonmac"));
        FileLog.debug(
                "opensign.doappletrequest: "
                        + applet.getParameter("opensign.doappletrequest"));
        FileLog.debug(
                "opensign.verifieruri: "
                        + applet.getParameter("opensign.verifieruri"));
        FileLog.debug(
                "opensign.alerturi: " + applet.getParameter("opensign.alerturi"));
        FileLog.debug(
                "opensign.erroruri: " + applet.getParameter("opensign.erroruri"));
        FileLog.debug(
                "opensign.canceluri: " + applet.getParameter("opensign.canceluri"));
        FileLog.debug(
                "opensign.verifiedokuri: "
                        + applet.getParameter("opensign.verifiedokuri"));
        FileLog.debug(
                "opensign.verifiederroruri: "
                        + applet.getParameter("opensign.verifiederroruri"));
        FileLog.debug(
                "opensign.cookiecount: "
                        + applet.getParameter("opensign.cookiecount"));
        FileLog.debug(
                "opensign.formdata.count: "
                        + applet.getParameter("opensign.formdata.count"));
    }


    public static String getDocBase(URL documentBase) {
        String protocol = "http".equalsIgnoreCase(documentBase.getProtocol()) || "https".equalsIgnoreCase(documentBase.getProtocol()) ? documentBase.getProtocol() : "http";
        String host = !StringUtils.isEmpty(documentBase.getHost()) ? documentBase.getHost() : "localhost";
        int port = documentBase.getPort() != -1 ? documentBase.getPort() : ("https".equalsIgnoreCase(protocol) ? 443 : 80);

        StringBuilder builder = new StringBuilder();
        builder.append(protocol)
                .append("://")
                .append(host);

        if(!(("http".equalsIgnoreCase(protocol) && port == 80) || ("https".equalsIgnoreCase(protocol) && port == 443))) {
            builder.append(":").append(port);
        }

        String url = builder.toString();

        FileLog.debug("DocBase = " + url);

        return url;
    }
}