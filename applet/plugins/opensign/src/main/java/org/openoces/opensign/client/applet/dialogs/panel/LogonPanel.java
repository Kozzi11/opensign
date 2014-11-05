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

/* $Id: LogonPanel.java,v 1.5 2012/12/19 12:28:02 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs.panel;

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.OcesAppletUtils;
import org.openoces.opensign.client.applet.dialogs.GuiUtil;
import org.openoces.opensign.client.applet.dialogs.listeners.CancelLogonActionListener;
import org.openoces.opensign.client.applet.dialogs.listeners.LogonActionListener;
import org.openoces.opensign.client.applet.resources.ResourceManager;
import org.openoces.opensign.utils.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class LogonPanel extends AbstractCertificateViewPanel {
    private String host;


    public LogonPanel(CallBackHandler callbackHandler, java.util.List<CertificateHandler> certificates, boolean supportBrowsingForCertificate) {
        super(callbackHandler, certificates, supportBrowsingForCertificate, new DefaultCertificateFilter());
        this.host = getHost(callbackHandler);

        bOk.addActionListener(new LogonActionListener(callbackHandler, this, host, bOk));
        bCancel.addActionListener(new CancelLogonActionListener("Logon", callbackHandler));
    }

    @Override
    protected JComponent[] getActionButtons() {
        return new JComponent[]{bOk, bDetails, bCancel};
    }

    @Override
    protected JComponent getContentPanel() {
        JPanel panel = componentFactory.createPanel(new GridBagLayout());

        String header = callbackHandler.getParameter("LOGON_HEADER");
        if(StringUtils.isEmpty(header)) header = ResourceManager.getString("LOGON_HOW_TO2");
        header = header.concat(" ").concat(host);

        JLabel label = componentFactory.createHeaderLabel(header);

        int y = 0;
        y = GuiUtil.addSingleLine(panel, 0, y, label);
        y = GuiUtil.addComboBoxLine(panel, 0, y, chCertList, bFunctions);
        if(lSocialSecurityNumber != null && tfSocialSecurityNumber != null) {
            y = GuiUtil.addSingleLine(panel, 0, y, lSocialSecurityNumber);
            y = GuiUtil.addTextFieldLine(panel, 0, y, tfSocialSecurityNumber);
        }
        if(lOptionalEntry != null && tfOptionalEntry != null) {
            y = GuiUtil.addSingleLine(panel, 0, y, lOptionalEntry);
            GuiUtil.addTextFieldLine(panel, 0, y, tfOptionalEntry);
        }

        return panel;
    }

    @Override
    public JComponent getFocusComponent() {
        return bOk;
    }

    @Override
    protected String getOkButtonTextId() {
        return "OK_LOGON_MODERN";
    }

    @Override
    protected String getBrowseButtonTextId() {
        return "FUNCTIONS_MODERN_LOGON";
    }

    private String getHost(CallBackHandler callbackHandler) {
        String host = callbackHandler.getParamReader().getParameter("logonto");
        if (host == null || host.length() == 0) {
            URL documentBase = callbackHandler.getDocumentBase();
            host = OcesAppletUtils.getDocBase(documentBase);
        }

        try {
            URL hostUrl = new URL(host);
            host = hostUrl.getHost();
        } catch (MalformedURLException e) {
            // stick with the old text
        }
        return host;
    }


}