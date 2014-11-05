/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard
    Copyright 2009 Jacob Poulsgaard Tjoernholm
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

/* $Id: DlgPassword.java,v 1.4 2012/09/27 11:03:46 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs;

/**
 * This class represent a password dialog
 *
 * @author Jens Bo Friis  <jbf@it-practice.dk>
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 * @author Jacob Poulsgaard Tjoernholm <chopmo@gmail.com>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 */

import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.dialogs.panel.AbstractPanel;
import org.openoces.opensign.client.applet.dialogs.panel.PasswordPanel;
import org.openoces.opensign.client.applet.resources.ResourceManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DlgPassword extends AbstractDialog {
    private PasswordPanel panel;

    private String title;
    private String passwordLabel;
    private InputValidator validator;

     private java.util.List<PasswordEnteredListener> listeners;


	public DlgPassword(CallBackHandler callBackHandler, final JComponent oldFocusComponent, String title, InputValidator validator) {
		this(callBackHandler, oldFocusComponent, title, ResourceManager.getString("DLG_PASSWORD_LABEL_PASSWORD"), validator);
	}

    public DlgPassword(CallBackHandler callBackHandler, final JComponent oldFocusComponent, String title, String passwordLabel, InputValidator validator) {
		super(callBackHandler, oldFocusComponent, 400, 140);

        this.validator = validator;
        this.title = title;
        this.passwordLabel = passwordLabel;

        this.listeners = new ArrayList<PasswordEnteredListener>();
  	}

    public void addPasswordEnteredListener(PasswordEnteredListener listener) {
        listeners.add(listener);
    }

    public List<PasswordEnteredListener> getListeners() {
        return listeners;
    }

    public void clear() {
        listeners.clear();
    }

    @Override
    protected void initialize() {
        if(panel == null) panel = new PasswordPanel(callBackHandler, this, title, passwordLabel, validator);
    }

    @Override
    protected AbstractPanel getPanel() {
        return panel;
    }
}