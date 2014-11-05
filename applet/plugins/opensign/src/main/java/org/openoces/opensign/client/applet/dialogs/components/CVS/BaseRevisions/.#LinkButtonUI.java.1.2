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

/* $Id: LinkButtonUI.java,v 1.2 2012/02/28 08:20:47 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs.components;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;

/**
 * UI for making a button look like a link on a web page
 */
public class LinkButtonUI extends MetalButtonUI {
    private final static LinkButtonUI linkButtonUI = new LinkButtonUI();

    public static ComponentUI createUI(JComponent c) {
        return linkButtonUI;
    }

    public void installDefaults(AbstractButton b) {
        super.installDefaults(b);

        b.setForeground(DefaultComponentFactory.linkColor);
        b.setFont(DefaultComponentFactory.linkFont);
        b.setBorder(null);
        b.setOpaque(false);
        b.setContentAreaFilled(false);
    }

    protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text) {
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();

        /* Draw the Text */
        if (model.isEnabled()) {
            /*** paint the text normally */
            g.setColor(b.getForeground());
        } else {
            /*** paint the text disabled ***/
            g.setColor(getDisabledTextColor());
        }

        FontMetrics fm = c.getFontMetrics(c.getFont());
        g.drawString(text, textRect.x, textRect.y + fm.getAscent());
        g.drawLine(textRect.x, textRect.y + fm.getAscent() + 2, textRect.x + fm.stringWidth(text), textRect.y + fm.getAscent() + 2);
    }
}
