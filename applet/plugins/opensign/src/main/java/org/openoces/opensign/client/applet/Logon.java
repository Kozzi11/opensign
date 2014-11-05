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

/* $Id: Logon.java,v 1.5 2012/12/19 12:28:03 pakj Exp $ */

package org.openoces.opensign.client.applet;

/**
 * This class implements the Logon applet
 *
 * @author Jens Bo Friis  <jbf@it-practice.dk>
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 */

import org.openoces.opensign.client.applet.dialogs.LogonScreen;
import org.openoces.opensign.client.applet.interfaces.PollingApplet;
import org.openoces.opensign.client.applet.resources.ResourceManager;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.utils.Version;
import org.openoces.opensign.xml.nanoxml.XMLException;

public class Logon extends AbstractApplet implements PollingApplet {
    // RM polling code
    private String outputData = "";
    private String appletState = "waiting";

	public Logon() {
        super(LogonScreen.class);
        this.setName(ResourceManager.getString("TITLE_LOGON") + " v." + Version.getVersion());
    }

    public String getAppletInfo() {
        return "OpenLogon";
    }

    protected ParamReader createParamReader() {
        // find out if we have xml input in "signtext" and set paramreader if so

        try {
            String explicitInputStyle = getParameter("inputstyle");
            String virkLogon = getParameter("VIRK_LOGON");

            if (explicitInputStyle != null) {
                if ("default".equals(explicitInputStyle)) {
                    return new DefaultParamReader(this);
                } else if ("virk".equals(explicitInputStyle)) {
                    return new AppletArguments(this);
                } else {
                    return new DefaultParamReader(this);
                }
            } else {
                if (virkLogon != null) {
                    return new AppletArguments(this);// could be named XmlParamReader..
                } else {
                    return new DefaultParamReader(this);
                }
            }
        } catch (XMLException e) {
            FileLog.error("Could not create applet arguments", e);
            return new DefaultParamReader(this);
        }
    }

    public String getAppletState() {
        return appletState;
    }

    public String getOutputData() {
        return outputData;
    }

    public void setAppletState(String state) {
        appletState = state;
    }

    public void setOutputData(String data) {
        outputData = data;
    }

}