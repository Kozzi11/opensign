/*
	Copyright 2011 Paw F. Kjeldgaard
	Copyright 2011 Daniel Andersen
	Copyright 2013 Anders M. Hansen

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

 * @author Daniel Andersen <daand@nets.eu>
 * @author Anders M. Hansen <consult@ajstemp.dk>
*/

/* $Id: LogonScreen.java,v 1.5 2013/03/05 11:24:17 anmha Exp $ */

package org.openoces.opensign.client.applet.dialogs;

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.dialogs.panel.AbstractPanel;
import org.openoces.opensign.client.applet.dialogs.panel.LogonPanel;

/**
 * User: pakj
 * Date: 03-02-11
 * Time: 13:00
 */
public class LogonScreen extends AbstractScreen {
    private LogonPanel panel;

    public LogonScreen(CallBackHandler callBackHandler, java.util.List<CertificateHandler> certificates, boolean supportBrowsingForCertificate) {
        super(callBackHandler, certificates, supportBrowsingForCertificate);
    }

    @Override
    protected AbstractPanel getPanel() {
        return panel;
    }

    @Override
    protected void initialize() {
        if(panel == null) panel = new LogonPanel(callBackHandler, certificates, supportBrowsingForCertificate);
    }

    protected void finalize() throws Throwable {
    	try{
    		panel = null;
    	} finally {
    		super.finalize();
    	}
    }
}