/*
	Copyright 2011 Paw F. Kjeldgaard
    Copyright 2011 Daniel Andersen

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
*/

/* $Id: SignScreen.java,v 1.4 2012/09/27 11:03:46 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs;

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.dialogs.panel.AbstractPanel;

/**
 * User: pakj
 * Date: 03-02-11
 * Time: 13:04
 */
public class SignScreen extends AbstractScreen {
    private static SignPanelFactory signPanelFactory = new DefaultSignPanelFactory();

    private AbstractPanel panel;

    public SignScreen(CallBackHandler callBackHandler, java.util.List<CertificateHandler> certificates, boolean supportBrowsingForCertificate) {
        super(callBackHandler, certificates, supportBrowsingForCertificate);
    }

    @Override
    protected AbstractPanel getPanel() {
        return panel;
    }

    @Override
    protected void initialize() {
        if(panel == null) panel = signPanelFactory.createSignPanel(callBackHandler, certificates, supportBrowsingForCertificate);
    }

    public static void setSignPanelFactory(SignPanelFactory signPanelFactory) {
        SignScreen.signPanelFactory = signPanelFactory;
    }
}