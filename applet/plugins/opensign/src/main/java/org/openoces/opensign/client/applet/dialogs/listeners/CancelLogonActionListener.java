/*
	Copyright 2011 Paw F. Kjeldgaard

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

/* $Id: CancelLogonActionListener.java,v 1.4 2012/09/27 11:03:44 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs.listeners;

import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.JavascriptRunner;
import org.openoces.opensign.utils.FileLog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelLogonActionListener implements ActionListener {
    private String type;
    private CallBackHandler callBackHandler;

    public CancelLogonActionListener(String type, CallBackHandler callBackHandler) {
        this.type = type;
        this.callBackHandler = callBackHandler;
    }

    public final void actionPerformed(ActionEvent e) {
        handleCancel();
    }

    private void handleCancel(){
        JavascriptRunner javascriptRunner = callBackHandler.getJavascriptRunner();
        javascriptRunner.callFunction("on" + type + "Cancel", null);
        reportCancel();
    }

    /**
     * Reports that the signing process has been canceled and clears any old output data.
     */
    private void reportCancel() {
        callBackHandler.setOutputData("");
        callBackHandler.setAppletState(type.toLowerCase() + "canceled");
    }
}