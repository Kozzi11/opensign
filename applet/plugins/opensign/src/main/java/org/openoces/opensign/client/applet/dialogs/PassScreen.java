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

/* $Id: PassScreen.java,v 1.4 2012/09/27 11:03:46 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs;

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.client.applet.CallBackHandler;

import javax.swing.*;

/**
 * User: pakj
 * Date: 04-02-11
 * Time: 10:21
 */
public class PassScreen implements Screen {

    public PassScreen(CallBackHandler callBackHandler, java.util.List<CertificateHandler> certificates, boolean supportBrowsingForCertificate) {
    }

    public void show() {

    }

    public void setUserMessage(String message, boolean error) {

    }

    public void setUserMessage(Icon icon) {
    }

    public void setUserMessage(String message, Icon icon, boolean error) {
    }

    public void clearUserMessage(boolean error) {

    }

    @Override
    public JComponent getFocusComponent() {
        return null;
    }
}