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

/* $Id: FixedHeightTextArea.java,v 1.2 2012/02/28 08:20:47 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class FixedHeightTextArea extends JTextArea {
    private static final String uiClassID = "FixedHightTextAreaUI";

	private static final int LINK = 1;
	private static final int NONE = 0;
	private int fixedRows;
	private boolean asLabel;

	public FixedHeightTextArea(int fixedRows, boolean asLabel) {
		this.fixedRows = fixedRows;
		this.asLabel = asLabel;
		if (fixedRows > 0)
			setRows(fixedRows);

		if (asLabel) {
			setBorder(null);
			setDisabledTextColor(DefaultComponentFactory.textColor);
			setEnabled(false);
			setCaretColor(getBackground());
			setFocusable(false);
			setFont(DefaultComponentFactory.labelFont);
			setOpaque(false);
		}

		setWrapStyleWord(true);
		setLineWrap(true);
		addMouseListener();
		setDropTarget(null);
	}

	public Insets getInsets() {
		return new Insets(0, 0, 0, 0);
	}

	public Dimension getPreferredSize() {
		Dimension d = super.getPreferredSize();
		d = (d == null) ? new Dimension(400, 400) : d;
		if (fixedRows > 0) {
			Insets insets = getInsets();
			d.height = fixedRows * getRowHeight() + insets.top + insets.bottom;
		}
		return d;
	}

	public Dimension getSize() {
		Dimension d = super.getSize();
		d = (d == null) ? new Dimension(400, 400) : d;
		if (fixedRows > 0) {
			Insets insets = getInsets();
			d.height = fixedRows * getRowHeight() + insets.top + insets.bottom;
		}
		return d;
	}

	private void addMouseListener() {
		addMouseMotionListener( new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				handleMouseCursor(e);
			}
		});
	}

	public void handleMouseCursor(MouseEvent e) {
		setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
	}

	public static int getWordType(String word) {
		if (isUrl(word)) {
			return LINK;
		}

		return NONE;
	}

	public boolean isFocusable() {
		return !asLabel;
	}

	public static boolean isUrl( String word ) {
		return false;
	}

	public void setUI(FixedHeightTextAreaUI ui) {
		super.setUI(ui);
	}

	public void updateUI() {
		setUI(new FixedHeightTextAreaUI());
	}

	public String getUIClassID() {
		return uiClassID;
	}
}
