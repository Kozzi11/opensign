/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard


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

/* $Id: AbstractAttachmentViewer.java,v 1.4 2012/09/27 11:03:45 pakj Exp $ */

package org.openoces.opensign.client.applet.attach;

/**
 * This class implements generic viewing functionality
 *
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 */

import org.openoces.opensign.client.applet.dialogs.AbstractDialog;
import org.openoces.opensign.client.applet.dialogs.OpenSignDialog;

import javax.swing.*;

public abstract class AbstractAttachmentViewer implements AttachmentViewer {

    public void view(AbstractDialog parent, final JComponent oldFocusComponent, String title, byte [] data) {
        OpenSignDialog dialog = getAttachmentComponent(parent, oldFocusComponent, title, data);
        dialog.show();
    }

    abstract OpenSignDialog getAttachmentComponent(AbstractDialog parent, final JComponent oldFocusComponent, String title, byte [] data);

    public static boolean isSupported(){ return true;}
}