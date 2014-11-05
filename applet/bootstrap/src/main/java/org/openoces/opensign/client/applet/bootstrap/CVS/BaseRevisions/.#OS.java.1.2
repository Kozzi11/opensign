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

/* $Id: OS.java,v 1.2 2012/02/28 08:21:00 pakj Exp $ */

package org.openoces.opensign.client.applet.bootstrap;

import javax.swing.*;

/**
 * This class defines OS specific functionality.
 *
 * @author Preben Valeur  <prv@tdc.dk>
 * @author Carsten Raskgaard  <carsten@tuxit.dk>
 */


class OS {

    protected OS() {
    }

    static String getBrowser(JApplet applet) {
        // String theBrowser = "APPLICATION";
        // we know it is not - so:
        String appletContext = applet.getAppletContext().toString();
        String theBrowser = appletContext;
        if (appletContext.startsWith("sun.applet.AppletViewer")) {
            theBrowser = "APPLETVIEWER";
        } else if (appletContext.startsWith("netscape.applet.")) {
            theBrowser = "NETSCAPE";
        } else if (appletContext.startsWith("com.ms.applet.")) {
            theBrowser = "MICROSOFT";
        } else if (appletContext.startsWith("sunw.hotjava.tags.TagAppletPanel")) {
            theBrowser = "HOTJAVA";
        } else if (appletContext.startsWith("sun.plugin.navig.win32.AppletPlugin")) {
            theBrowser = "NETSCAPEPLUGIN";
        } else if (appletContext.startsWith("sun.plugin.ocx.ActiveXApplet")) {
            theBrowser = "MICROSOFTPLUGIN";
        } else if (appletContext.startsWith("sun.plugin.viewer.context.IExplorerAppletContext")) {
            theBrowser = "MICROSOFTPLUGINJRE1.4";
        } else if (appletContext.startsWith("com.opera.")) {
            theBrowser = "OPERA";
        }
        return theBrowser;
    }

    static boolean isJavaPlugin() {
        return (System.getProperty("javaplugin.version") != null);
    }

    static String getJavaPluginVersion() {
        return (System.getProperty("javaplugin.version"));
    }

    static String getUserHome() {
        return nullChecked(System.getProperty("user.home"));
    }

    private static String nullChecked(String prop) {
        for (int i = 0; i < prop.length(); i++) {
            if (prop.charAt(i) == '\0') {
                throw new RuntimeException("Illegal null char present");
            }
        }
        return prop;
    }
}
