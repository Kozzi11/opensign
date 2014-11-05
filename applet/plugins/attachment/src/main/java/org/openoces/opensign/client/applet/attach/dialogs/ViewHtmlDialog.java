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

/* $Id: ViewHtmlDialog.java,v 1.4 2012/09/27 11:03:49 pakj Exp $ */

package org.openoces.opensign.client.applet.attach.dialogs;

import org.openoces.opensign.client.applet.dialogs.AbstractDialog;
import org.openoces.opensign.client.applet.dialogs.panel.AbstractPanel;
import org.openoces.opensign.utils.ArrayUtil;

import javax.swing.*;

public class ViewHtmlDialog extends AbstractDialog {
    private HtmlPanel panel;

    private String title;
    private byte[] data;

    public ViewHtmlDialog(AbstractDialog parent, final JComponent oldFocusComponent, String title, byte[] data) {
        super(parent, oldFocusComponent, 500, 300);
        this.title = title;
        this.data = ArrayUtil.copyOf(data);
    }

    @Override
    protected void initialize() {
        if(panel == null) panel = new HtmlPanel(callBackHandler, this, title, data);
    }

    @Override
    protected AbstractPanel getPanel() {
        return panel;
    }
}