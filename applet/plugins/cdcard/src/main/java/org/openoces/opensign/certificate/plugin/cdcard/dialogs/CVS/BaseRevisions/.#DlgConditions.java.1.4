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

/* $Id: DlgConditions.java,v 1.4 2012/09/27 11:03:46 pakj Exp $ */

package org.openoces.opensign.certificate.plugin.cdcard.dialogs;

/**
 * This class represents the dialog displayed when the cdcard conditions are being displayed.
 *
 * @author Preben Valeur  <prv@tdc.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 */

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.dialogs.AbstractDialog;
import org.openoces.opensign.client.applet.dialogs.panel.AbstractPanel;

import javax.swing.*;

public class DlgConditions extends AbstractDialog {
    private ConditionsPanel panel;

    private CertificateHandler certificate;
    private int state = ConditionsHeaderPanel.ACTIVATE;
    private String conditions;
    private String title;

    public DlgConditions(CallBackHandler parent, JComponent oldFocusComponent, CertificateHandler certificate, String title, String conditions, int state) {
        super(parent, oldFocusComponent, 400, 200);
        this.certificate = certificate;
        this.conditions = conditions;
        this.state = state;
        this.title = title;
    }

    public DlgConditions(CallBackHandler parent, JComponent oldFocusComponent, CertificateHandler certificate, String title, String conditions) {
        this(parent, oldFocusComponent, certificate, title, conditions, ConditionsHeaderPanel.ACTIVATE);
    }

    @Override
    protected final void initialize() {
        if(panel == null) panel = new ConditionsPanel(callBackHandler,  this, certificate, title, conditions, state);
    }

    @Override
    protected final AbstractPanel getPanel() {
        return panel;
    }

    public final boolean isCancelled() {
        return panel.isCancelled();
    }

    public final String getCprNumber() {
        return panel.getCprNumber();
    }


}