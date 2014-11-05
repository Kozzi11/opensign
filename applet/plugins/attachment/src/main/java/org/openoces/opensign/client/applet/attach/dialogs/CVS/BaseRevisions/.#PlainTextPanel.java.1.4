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

/* $Id: PlainTextPanel.java,v 1.4 2012/09/27 11:03:49 pakj Exp $ */

package org.openoces.opensign.client.applet.attach.dialogs;

import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.GuiCallback;
import org.openoces.opensign.client.applet.dialogs.GuiUtil;
import org.openoces.opensign.client.applet.dialogs.OpenSignDialog;
import org.openoces.opensign.client.applet.dialogs.components.SignTextDisplay;
import org.openoces.opensign.client.applet.dialogs.components.SigntextValidationException;
import org.openoces.opensign.client.applet.dialogs.components.TextMimeType;
import org.openoces.opensign.utils.FileLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.UnsupportedEncodingException;

public class PlainTextPanel extends AbstractViewPanel {

    public PlainTextPanel(CallBackHandler callbackHandler, OpenSignDialog dialog, String title, byte[] data) {
        super(callbackHandler, dialog, title, data);
    }

    @Override
    protected JComponent getContentPanel() {
        JPanel signPanel = componentFactory.createPanel(new BorderLayout());
        signPanel.add(title, BorderLayout.NORTH);

        try {
            // todo: find out what happens here
            String content = new String(data, "UTF8");
            if (content.charAt(0) == 0xFEFF) {
                content = content.substring(1);
            }
            final SignTextDisplay textArea = componentFactory.createSignTextDisplay(TextMimeType.PLAIN, content, null);

            JScrollPane sp = componentFactory.createScrollPane(textArea);
            sp.setName("SignScrollPane");
            sp.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    GuiUtil.doInGui(new GuiCallback() {
                        public void doInGui() {
                            textArea.grabFocus();
                            textArea.setCaretPosition(0);
                        }
                    });
                }
            });
            signPanel.add(sp, BorderLayout.CENTER);

        } catch (UnsupportedEncodingException e) {
            FileLog.warn("UTF8 not supported", e);
            JLabel message = componentFactory.createTextLabel("UTF8 not supported");
            signPanel.add(message, BorderLayout.CENTER);
        } catch (SigntextValidationException e) {

        }
        return signPanel;
    }

}