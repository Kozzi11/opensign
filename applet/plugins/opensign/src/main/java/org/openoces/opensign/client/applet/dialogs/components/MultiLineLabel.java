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

/* $Id: MultiLineLabel.java,v 1.2 2012/02/28 08:20:47 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs.components;

import javax.swing.*;

public class MultiLineLabel extends JLabel {
    private static final String uiClassID = "MultiLineLabelUI";

	public MultiLineLabel(String s, Icon icon, int i) {
		super(s, icon, i);
	}

	public MultiLineLabel(String s, int i) {
		super(s, null, i);
	}

    public MultiLineLabel(String s, Icon icon) {
        this(s, icon, 10);
    }

	public MultiLineLabel(String s) {
		this(s, null, 10);
	}

    public MultiLineLabel(Icon icon) {
        this("", icon, 10);
    }

	public MultiLineLabel() {
		this("", null, 10);
	}

	public void setUI(MultiLineLabelUI multilinelabelui) {
		super.setUI(multilinelabelui);
	}

	public boolean isFocusable() {
		return false;
	}

	public void updateUI() {
		setUI(new MultiLineLabelUI());
	}

	public String getUIClassID() {
		return uiClassID;
	}
}
