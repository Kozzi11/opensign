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

/* $Id: FixedHeightTextAreaUI.java,v 1.2 2012/02/28 08:20:47 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs.components;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTextAreaUI;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainView;
import javax.swing.text.View;

public class FixedHeightTextAreaUI extends BasicTextAreaUI {
    public static ComponentUI createUI(JComponent x) {
		return new FixedHeightTextAreaUI();
	}

	public FixedHeightTextAreaUI() {
		super();
	}

	/**
	 * Creates the view for an element. Returns a WrappedPlainView or PlainView.
	 *
	 * @param elem the element
	 * @return the view
	 */
	public View create(Element elem) {
		JTextComponent c = getComponent();

		if (!(c instanceof JTextArea)) {
			return null;
		}

		JTextArea area = (JTextArea) c;
		area.setDropTarget(null);
		View v;

		if (area.getLineWrap()) {
			v = new FixedHeightPlainView(elem, area.getWrapStyleWord(), getComponent() );
		}
		else {
			v = new PlainView(elem);
		}

		return v;
	}
}
