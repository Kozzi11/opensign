/*
	Copyright 2011 Paw F. Kjeldgaard
	Copyright 2011 Daniel Andersen
	Copyright 2012 Anders M. Hansen

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

/* $Id: AbstractScreen.java,v 1.5 2013/03/05 11:24:17 anmha Exp $ */

package org.openoces.opensign.client.applet.dialogs;

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.GuiCallback;
import org.openoces.opensign.client.applet.dialogs.panel.AbstractPanel;

import javax.swing.*;
import java.awt.*;

/**
 * User: pakj
 * Date: 03-02-11
 * Time: 13:06
 */
public abstract class AbstractScreen implements Screen {
    final protected CallBackHandler callBackHandler;
    final protected java.util.List<CertificateHandler> certificates;
    final protected boolean supportBrowsingForCertificate;

    protected AbstractScreen(CallBackHandler callBackHandler, java.util.List<CertificateHandler> certificates, boolean supportBrowsingForCertificate) {
        this.callBackHandler = callBackHandler;
        this.certificates = certificates;
        this.supportBrowsingForCertificate = supportBrowsingForCertificate;
    }

    protected abstract AbstractPanel getPanel();

    protected abstract void initialize();

    public void show() {
        initialize();

		final AbstractPanel panel = getPanel();

		GuiUtil.doInGui(new GuiCallback() {
			public void doInGui() {
				Container appletContainer = callBackHandler.getContentPane();
                appletContainer.removeAll();
				appletContainer.setLayout( null );
				appletContainer.add(panel);
				panel.setSize(appletContainer.getWidth(), appletContainer.getHeight());
				panel.setLocation(0,0);
				panel.showPanel();

				appletContainer.validate();
				appletContainer.repaint();

				if( appletContainer.getParent() != null ) {
					appletContainer.getParent().validate();
					appletContainer.getParent().repaint();
				}
			}
		});


    }

    @Override
    public JComponent getFocusComponent() {
        AbstractPanel panel = getPanel();
        return panel != null ? panel.getFocusComponent() : null;
    }
    
    protected void finalize() throws Throwable {
    	try{
    		if(certificates != null) {
    			certificates.clear();
    		}
    	} finally {
    		super.finalize();
    	}
    }
}