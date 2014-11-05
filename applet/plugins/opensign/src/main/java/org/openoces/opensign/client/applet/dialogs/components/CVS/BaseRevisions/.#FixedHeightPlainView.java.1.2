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

/* $Id: FixedHeightPlainView.java,v 1.2 2012/02/28 08:20:48 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs.components;

import org.openoces.opensign.client.applet.dialogs.GuiUtil;

import javax.swing.text.*;
import java.awt.*;

public class FixedHeightPlainView extends WrappedPlainView {
    private static Segment segment = new Segment();
    private static Image keyImage = GuiUtil.createImage("key.gif");
    private static Image hashImage = GuiUtil.createImage("hash.gif");
    private JTextComponent c;

    public FixedHeightPlainView(Element elem, boolean wordWrap, JTextComponent c) {
        super(elem, wordWrap);
        this.c = c;
    }

    /**
     * Renders the given range in the model as normal unselected text.
     *
     * @param g  the graphics context
     * @param x  the starting X coordinate >= 0
     * @param y  the starting Y coordinate >= 0
     * @param p0 the beginning position in the model >= 0
     * @param p1 the ending position in the model >= p0
     * @return the X location of the end of the range >= 0
     * @throws javax.swing.text.BadLocationException
     *          if the range is invalid
     */
    protected int drawUnselectedText(Graphics g, int x, int y, int p0, int p1) throws BadLocationException {
        JTextComponent host = (JTextComponent) getContainer();

        Color unselected = (host.isEnabled()) ? host.getForeground() : host.getDisabledTextColor();
        g.setColor(unselected);

        return drawText(g, x, y, p0, p1);
    }

    /**
     * Renders the given range in the model as selected text. This is
     * implemented to render the text in the color specified in the hosting
     * component. It assumes the highlighter will render the selected
     * background.
     *
     * @param g  the graphics context
     * @param x  the starting X coordinate >= 0
     * @param y  the starting Y coordinate >= 0
     * @param p0 the beginning position in the model >= 0
     * @param p1 the ending position in the model >= p0
     * @return the location of the end of the range.
     * @throws BadLocationException if the range is invalid
     */
    protected int drawSelectedText(Graphics g, int x, int y, int p0, int p1) throws BadLocationException {
        JTextComponent host = (JTextComponent) getContainer();

        Color unselected = (host.isEnabled()) ? host.getForeground() : host.getDisabledTextColor();
        Caret c = host.getCaret();
        Color selected = c.isSelectionVisible() ? host.getSelectedTextColor() : unselected;
        g.setColor(selected);

        return drawText(g, x, y, p0, p1);
    }

    private int drawText(Graphics g, int x, int y, int p0, int p1) throws BadLocationException {
        Document doc = getDocument();

        int ret = 0;
        synchronized (segment) {
            doc.getText(p0, p1 - p0, segment);
            ret = drawTabbedText(segment, x, y, g, this, p0);
            segment.array = null;
            segment.count = 0;
        }

        return ret;
    }

    /**
     * Draws the given text, expanding any tabs that are contained using the
     * given tab expansion technique. This particular implementation renders in
     * a 1.1 style coordinate system where ints are used and 72dpi is assumed.
     *
     * @param s           the source of the text
     * @param x           the X origin >= 0
     * @param y           the Y origin >= 0
     * @param g           the graphics context
     * @param e           how to expand the tabs. If this value is null, tabs will be
     *                    expanded as a space character.
     * @param startOffset starting offset of the text in the document >= 0
     * @return the X location at the end of the rendered text
     */
    public int drawTabbedText(Segment s, int x, int y, Graphics g, TabExpander e, int startOffset) {
        FontMetrics metrics = g.getFontMetrics();

        int nextX = x;
        char[] txt = s.array;
        int txtOffset = s.offset;
        int flushLen = 0;
        int flushIndex = s.offset;
        int n = s.offset + s.count;

        for (int i = txtOffset; i < n; i++) {
            boolean hasLink = false;
            boolean hasKey = false;
            boolean hasHash = false;
            if (i == txtOffset || txt[i] == ' ' || txt[i] == '\t' || i == (n - 1)) {
                String word = new String(txt, flushIndex, flushLen);

                if (FixedHeightTextArea.isUrl(word)) {
                    hasLink = true;
                    if (word.startsWith("http://") && word.length() > 7) {
                        word = word.substring(8, word.length());
                    } else if (word.startsWith("https://") && word.length() > 8) {
                        word = word.substring(9, word.length());
                    }
                } else if ("#KEY#".equals(word)) {
                    hasKey = true;
                } else if ("#I#".equals(word)) { //#I# is chosen because with the current implementation, the length of the text determines how much vertical space the image will get.
                    hasHash = true;
                }

                if (hasKey) {
                    g.drawImage(keyImage, x - 9, y - keyImage.getHeight(c) + 3, c);
                    flushIndex = i + 1;
                    flushLen = 0;
                    x = nextX;
                } else if (hasHash) {
                    g.drawImage(hashImage, x, y - hashImage.getHeight(c) + 5, c);
                    flushIndex = i + 1;
                    flushLen = 0;
                    x = nextX;
                } else if (hasLink) {
                    Color oldCol = g.getColor();

                    g.setColor(DefaultComponentFactory.linkColor);

                    if (flushLen > 0) {
                        if (i == (n - 1))
                            flushLen += 1;

                        g.drawChars(txt, flushIndex, flushLen, x, y);

                        FontMetrics fm = g.getFontMetrics();

                        int underlineRectX = x;
                        int underlineRectY = y + fm.getDescent() - 1;
                        int underlineRectWidth = fm.charsWidth(txt, flushIndex, flushLen);

                        int underlineRectHeight = 1;
                        g.fillRect(underlineRectX, underlineRectY, underlineRectWidth, underlineRectHeight);

                        flushLen = 0;
                    }

                    flushIndex = i + 1;
                    x = nextX;
                    g.setColor(oldCol);
                }
            }

            if (txt[i] == '\t') {
                if (flushLen > 0) {
                    g.drawChars(txt, flushIndex, flushLen, x, y);
                    flushLen = 0;
                }

                flushIndex = i + 1;

                if (e != null) {
                    nextX = (int) e.nextTabStop(nextX, startOffset + i - txtOffset);
                } else {
                    nextX += metrics.charWidth(' ');
                }

                x = nextX;
            } else if (txt[i] == ' ') {
                if (flushLen > 0) {
                    g.drawChars(txt, flushIndex, flushLen, x, y);
                    flushLen = 0;
                }

                flushIndex = i + 1;
                nextX += metrics.charWidth(' ');
                x = nextX;
            } else if (txt[i] == '\n' || txt[i] == '\r') {
                if (flushLen > 0) {
                    g.drawChars(txt, flushIndex, flushLen, x, y);
                    flushLen = 0;
                }

                flushIndex = i + 1;
                x = nextX;
            } else if (!hasLink && !hasKey && !hasHash) {
                flushLen += 1;
                nextX += metrics.charWidth(txt[i]);
            }
        }

        if (flushLen > 0) {
            g.drawChars(txt, flushIndex, flushLen, x, y);
        }

        return nextX;
    }
}
