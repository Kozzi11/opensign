/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard
    Copyright 2006 Paw F. Kjeldgaard
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
*/

/* $Id: DlgConfirmInformation.java,v 1.4 2013/03/13 09:31:18 anmha Exp $ */

package org.openoces.opensign.client.applet.dialogs;

/**
 * This class represent a general dialog box displaying information
 *
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 * @author Anders M. Hansen <consult@ajstemp.dk>
 */

import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.dialogs.panel.AbstractPanel;
import org.openoces.opensign.client.applet.dialogs.panel.ConfirmInformationPanel;

import javax.swing.*;

public class DlgConfirmInformation extends AbstractDialog {
    private ConfirmInformationPanel panel;
    private String title;
    private String text;

    public DlgConfirmInformation(CallBackHandler callBackHandler, final JComponent oldFocusComponent, String title, String text, int width, int height) {
        super(callBackHandler, oldFocusComponent, width, height);
        this.title = title;
        this.text = text;
    }

    public DlgConfirmInformation(CallBackHandler callBackHandler, final JComponent oldFocusComponent, String title, String text) {
        this(callBackHandler, oldFocusComponent, title, text, calcWidth(callBackHandler, text), 100);
    }

    public DlgConfirmInformation(AbstractDialog parent, final JComponent oldFocusComponent, String title, String text) {
        super(parent, oldFocusComponent, calcWidth(parent.getCallBackHandler(), text), 100);
        this.title = title;
        this.text = text;
    }

    @Override
    protected void initialize() {
        if(panel == null) panel = new ConfirmInformationPanel(callBackHandler, this, title, text);
    }

    @Override
    protected AbstractPanel getPanel() {
        return panel;
    }


}
