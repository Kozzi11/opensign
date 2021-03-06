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

/* $Id: SwingGuiRunner.java,v 1.2 2012/02/28 08:21:42 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs;

import javax.swing.*;

/**
 * User: pakj
 * Date: 04-02-11
 * Time: 13:56
 */
public class SwingGuiRunner implements GuiRunner {

    public void run(Runnable runnable) {
        SwingUtilities.invokeLater(runnable);
    }
}
