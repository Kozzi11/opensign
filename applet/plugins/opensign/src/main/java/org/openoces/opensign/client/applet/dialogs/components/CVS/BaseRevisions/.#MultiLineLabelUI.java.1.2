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

/* $Id: MultiLineLabelUI.java,v 1.2 2012/02/28 08:20:48 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs.components;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.*;
import java.util.StringTokenizer;

public class MultiLineLabelUI extends BasicLabelUI {
	protected String str;
	protected String strs[];
	protected int textXPos[];
	protected final static MultiLineLabelUI multiLabelUI = new MultiLineLabelUI();

	public MultiLineLabelUI() {
	}

	public static ComponentUI createUI(JComponent jcomponent) {
		return multiLabelUI;
	}

	protected String layoutCL(
		JLabel jlabel,
		FontMetrics fontmetrics,
		String s,
		Icon icon,
		Rectangle rectangle,
		Rectangle rectangle1,
		Rectangle rectangle2) {
		String s1 =
			layoutCompoundLabel(
				jlabel,
				fontmetrics,
				splitStringByLines(s),
				icon,
				jlabel.getVerticalAlignment(),
				jlabel.getHorizontalAlignment(),
				jlabel.getVerticalTextPosition(),
				jlabel.getHorizontalTextPosition(),
				rectangle,
				rectangle1,
				rectangle2,
				jlabel.getIconTextGap());
		textXPos = new int[strs.length];
		for (int i = 0; i < strs.length; i++) {
			textXPos[i] = rectangle2.x;
			if (jlabel.getHorizontalTextPosition() == 0) {
				int j = SwingUtilities.computeStringWidth(fontmetrics, strs[i]);
				if (j < rectangle2.width)
					textXPos[i] += (rectangle2.width - j) / 2;
			}
		}

		if (s1.equals(""))
			return s;
		else
			return s1;
	}

	public static String layoutCompoundLabel(
		JComponent jcomponent,
		FontMetrics fontmetrics,
		String as[],
		Icon icon,
		int i,
		int j,
		int k,
		int l,
		Rectangle rectangle,
		Rectangle rectangle1,
		Rectangle rectangle2,
		int i1) {
		boolean flag = true;
		int j1 = j;
		int k1 = l;
		if (jcomponent != null
			&& !jcomponent.getComponentOrientation().isLeftToRight())
			flag = false;
		switch (j) {
			case 10 : // '\n'
				j1 = flag ? 2 : 4;
				break;

			case 11 : // '\013'
				j1 = flag ? 4 : 2;
				break;
		}
		switch (l) {
			case 10 : // '\n'
				k1 = flag ? 2 : 4;
				break;

			case 11 : // '\013'
				k1 = flag ? 4 : 2;
				break;
		}
		return layoutCompoundLabel(
			fontmetrics,
			as,
			icon,
			i,
			j1,
			k,
			k1,
			rectangle,
			rectangle1,
			rectangle2,
			i1);
	}

	public static String layoutCompoundLabel(
		FontMetrics fontmetrics,
		String as[],
		Icon icon,
		int i,
		int j,
		int k,
		int l,
		Rectangle rectangle,
		Rectangle rectangle1,
		Rectangle rectangle2,
		int i1) {
		if (icon != null) {
			rectangle1.width = icon.getIconWidth();
			rectangle1.height = icon.getIconHeight();
		} else {
			rectangle1.width = rectangle1.height = 0;
		}
		boolean flag =
			as == null
				|| as.length == 0
				|| as.length == 1
				&& (as[0] == null || as[0].equals(""));
		String s = "";
		if (flag) {
			rectangle2.width = rectangle2.height = 0;
		} else {
			Dimension dimension = computeMultiLineDimension(fontmetrics, as);
			rectangle2.width = dimension.width;
			rectangle2.height = dimension.height;
		}
		int j1 = !flag && icon != null ? i1 : 0;
		if (!flag) {
			int k1;
			if (l == 0)
				k1 = rectangle.width;
			else
				k1 = rectangle.width - (rectangle1.width + j1);
			if (rectangle2.width > k1 && as.length == 1) {
				String s1 = "...";
				int j2 = SwingUtilities.computeStringWidth(fontmetrics, s1);
				int l2;
				for (l2 = 0; l2 < as[0].length(); l2++) {
					j2 += fontmetrics.charWidth(as[0].charAt(l2));
					if (j2 > k1)
						break;
				}

				s = as[0].substring(0, l2) + s1;
				rectangle2.width =
					SwingUtilities.computeStringWidth(fontmetrics, s);
			}
		}
		if (k == 1) {
			if (l != 0)
				rectangle2.y = 0;
			else
				rectangle2.y = - (rectangle2.height + j1);
		} else if (k == 0)
			rectangle2.y = rectangle1.height / 2 - rectangle2.height / 2;
		else if (l != 0)
			rectangle2.y = rectangle1.height - rectangle2.height;
		else
			rectangle2.y = rectangle1.height + j1;
		if (l == 2)
			rectangle2.x = - (rectangle2.width + j1);
		else if (l == 0)
			rectangle2.x = rectangle1.width / 2 - rectangle2.width / 2;
		else
			rectangle2.x = rectangle1.width + j1;
		int l1 = Math.min(rectangle1.x, rectangle2.x);
		int i2 =
			Math.max(
				rectangle1.x + rectangle1.width,
				rectangle2.x + rectangle2.width)
				- l1;
		int k2 = Math.min(rectangle1.y, rectangle2.y);
		int i3 =
			Math.max(
				rectangle1.y + rectangle1.height,
				rectangle2.y + rectangle2.height)
				- k2;
		int k3;
		if (i == 1)
			k3 = rectangle.y - k2;
		else if (i == 0)
			k3 = (rectangle.y + rectangle.height / 2) - (k2 + i3 / 2);
		else
			k3 = (rectangle.y + rectangle.height) - (k2 + i3);
		int j3;
		if (j == 2)
			j3 = rectangle.x - l1;
		else if (j == 4)
			j3 = (rectangle.x + rectangle.width) - (l1 + i2);
		else
			j3 = (rectangle.x + rectangle.width / 2) - (l1 + i2 / 2);
		rectangle2.x += j3;
		rectangle2.y += k3;
		rectangle1.x += j3;
		rectangle1.y += k3;
		return s;
	}

	protected void paintEnabledText(
		JLabel jlabel,
		Graphics g,
		String s,
		int i,
		int j) {
		int k = jlabel.getDisplayedMnemonic();
		g.setColor(jlabel.getForeground());
		drawString(g, s, k, i, j);
	}

	protected void paintDisabledText(
		JLabel jlabel,
		Graphics g,
		String s,
		int i,
		int j) {
		int k = jlabel.getDisplayedMnemonic();
		g.setColor(jlabel.getBackground());
		drawString(g, s, k, i, j);
	}

	protected void drawString(Graphics g, String s, int i, int j, int k) {
		if (s.indexOf(10) == -1) {
			BasicGraphicsUtils.drawString(g, s, i, j, k);
		} else {
			String as[] = splitStringByLines(s);
			int l = g.getFontMetrics().getHeight();
			BasicGraphicsUtils.drawString(g, as[0], i, textXPos[0], k);
			for (int i1 = 1; i1 < as.length; i1++)
				g.drawString(as[i1], textXPos[i1], k + l * i1);

		}
	}

	public static Dimension computeMultiLineDimension(
		FontMetrics fontmetrics,
		String as[]) {
		int k = 0;
		int i = 0;
		for (int j = as.length; i < j; i++) {
			if( as[i] != null ) {
				k =
					Math.max(
						k,
						SwingUtilities.computeStringWidth(fontmetrics, as[i]));
			}
		}

		return new Dimension(k, fontmetrics.getHeight() * as.length);
	}

	public String[] splitStringByLines(String s) {
		if (s.equals(str))
			return strs;
		str = s;
		int i = 1;
		int j = 0;
		for (int k = s.length(); j < k; j++)
			if (s.charAt(j) == '\n')
				i++;

		strs = new String[i];
		StringTokenizer stringtokenizer = new StringTokenizer(s, "\n");
		int l = 0;
		while (stringtokenizer.hasMoreTokens())
			strs[l++] = stringtokenizer.nextToken();
		return strs;
	}

}
