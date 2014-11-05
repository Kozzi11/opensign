/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard
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

/* $Id: Sign.java,v 1.4 2012/09/27 11:03:52 pakj Exp $ */

package org.openoces.opensign.client.applet;

/**
 * This class implements the sign applet
 *
 * @author Jens Bo Friis  <jbf@it-practice.dk>
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 * @author Mads Jensen <mjn@trifork.com>
 * @author Jeppe Burchhardt <Jeppe.Burchhardt@Cryptomathic.com>
 * @author Ole Friis Østergaard <ofo@trifork.com>
 */

import org.openoces.opensign.xml.nanoxml.XMLException;
import org.openoces.opensign.client.applet.interfaces.PollingApplet;
import org.openoces.opensign.client.applet.resources.ResourceManager;
import org.openoces.opensign.client.applet.dialogs.SignScreen;
import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.utils.Version;

import java.io.UnsupportedEncodingException;

public class Sign extends AbstractApplet implements PollingApplet {
    // RM polling code
    private String outputData = "";
    private String appletState = "waiting";

    public Sign() {
        super(SignScreen.class);
        this.setName(ResourceManager.getString("TITLE_SIGN") + " v." + Version.getVersion());
    }

    public String getAppletInfo() {
        return "OpenSign";
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

    protected ParamReader createParamReader() {
        // find out if we have xml input in "signtext" and set paramreader if so

        try {
            String b64signText = getParameter("signtext");
            String explicitInputStyle = getParameter("inputstyle");

            if (b64signText != null) {
                String signText;
                byte[] ba = Base64.decode(b64signText.getBytes());
                try {
                    signText = new String(ba, "UTF8");
                } catch (UnsupportedEncodingException e) {
                    FileLog.fatal(e.toString(), e);
                    return null;
                }

                if (explicitInputStyle != null) {
                    if ("default".equals(explicitInputStyle)) {
                        return new DefaultParamReader(this);
                    } else if ("virk".equals(explicitInputStyle)) {
                        return new AppletArguments(this);
                    } else if ("ver_1.0".equals(explicitInputStyle)) {
                        return new DefaultParamReader(this, DefaultParamReader.XML_VERSION_1_0);
                    } else {
                        return new DefaultParamReader(this);
                    }
                } else {
                    if (signText.indexOf("<?xml") == 0) {
                        return new AppletArguments(this);// could be named XmlParamReader..
                    } else {
                        return new DefaultParamReader(this);
                    }
                }
            } else {
                return new DefaultParamReader(this);
            }
        } catch (XMLException e) {
            FileLog.error("Could not create applet arguments", e);
            return new DefaultParamReader(this);
        }
    }
}