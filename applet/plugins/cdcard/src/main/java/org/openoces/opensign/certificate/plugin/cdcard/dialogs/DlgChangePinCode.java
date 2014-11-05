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

/* $Id: DlgChangePinCode.java,v 1.4 2012/09/27 11:03:46 pakj Exp $ */

package org.openoces.opensign.certificate.plugin.cdcard.dialogs;

/**
 * This class represents the dialog displayed when the cdcard pin code is being changed.
 *
 * @author Preben Valeur  <prv@tdc.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 */

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.certificate.plugin.cdcard.util.AksResponse;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.dialogs.AbstractDialog;
import org.openoces.opensign.client.applet.dialogs.panel.AbstractPanel;

import javax.swing.*;

public class DlgChangePinCode extends AbstractDialog {
    private ChangePinCodePanel changePinCodePanel;

    private int state;
    private CertificateHandler certificate;
    private String title;
    private String oldPin;
    private String nid;
    private String service;
    private String conditions;
    private String lraInfo;
    private String cpr;

    /**
     * Used when reactivating cd card.
     * There are no conditions then.
     *
     * @param certificate
     * @param title
     * @param oldPin
     * @param nid
     * @param service
     * @param lraInfo
     * @param cpr         CPR of the user we are changing pin on behalf of
     */
    public DlgChangePinCode(CallBackHandler parent, JComponent oldFocusComponent, int state, CertificateHandler certificate, String title, String oldPin, String nid, String service, String lraInfo, String cpr) {
        this(parent, oldFocusComponent, state, certificate, title, oldPin, nid, service, null, lraInfo, cpr);
    }

    /**
     * Used for first time activation
     *
     * @param certificate
     * @param title
     * @param oldPin
     * @param nid
     * @param service
     * @param conditions
     * @param lraInfo
     * @param cpr         CPR of the user we are changing pin on behalf of
     */
    public DlgChangePinCode(CallBackHandler parent, JComponent oldFocusComponent, int state, CertificateHandler certificate, String title, String oldPin, String nid, String service, String conditions, String lraInfo, String cpr) {
        super(parent, oldFocusComponent, 400, 300);
        this.state = state;
        this.certificate = certificate;
        this.title = title;
        this.oldPin = oldPin;
        this.nid = nid;
        this.service = service;
        this.conditions = conditions;
        this.lraInfo = lraInfo;
        this.cpr = cpr;
    }

    @Override
    protected final void initialize() {
        if(changePinCodePanel == null) changePinCodePanel = new ChangePinCodePanel(callBackHandler, this, oldPin, cpr, nid, service, conditions, lraInfo, certificate, title, state);
    }

    @Override
    protected final AbstractPanel getPanel() {
        return changePinCodePanel;
    }

    public final boolean isCancelled() {
        return changePinCodePanel.isCancelled();
    }

    public final AksResponse getAksResponse() {
        return changePinCodePanel.getAksResponse();
    }


}