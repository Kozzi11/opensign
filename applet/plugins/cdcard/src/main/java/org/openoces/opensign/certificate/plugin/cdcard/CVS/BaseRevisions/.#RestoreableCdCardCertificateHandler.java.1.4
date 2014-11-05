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

/* $Id: RestoreableCdCardCertificateHandler.java,v 1.4 2012/09/27 11:03:48 pakj Exp $ */

package org.openoces.opensign.certificate.plugin.cdcard;

/**
 * This class represents an cdcard that can be restored (ie. imported from the cd into a directory read
 * by OpenSign)
 *
 * @author Preben Valeur  <prv@tdc.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 */

import org.openoces.opensign.certificate.RestoreableItem;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.OS;
import org.openoces.opensign.client.applet.dialogs.DlgInformation;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class RestoreableCdCardCertificateHandler extends CdCardCertificateHandler implements RestoreableItem {

    protected RestoreableCdCardCertificateHandler(CdCardKeyStoreHandler handler, String dirPath) throws Exception {
        super(handler, dirPath);
    }

    // copy the item from another place to the default place
    public final void restore(CallBackHandler parent, JComponent oldFocusComponent) throws IOException {
        File cdCardDir = handler.getCardDir();
        if(!cdCardDir.mkdirs()) throw new IOException();
        File fromFile = new File(filePath);
        OS.copyFile(fromFile, new File(cdCardDir, fromFile.getName()));
        // todo: do we show it 2 times now? (in the drop-down list)
        DlgInformation dlgInformation = new DlgInformation(handler.getCallBackHandler(), oldFocusComponent, "Info", "Certifikatet er kopieret fra kortet til " + cdCardDir.getAbsolutePath());
        dlgInformation.show();

        handler.forgetScan();
    }
}