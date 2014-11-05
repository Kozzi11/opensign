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

/* $Id: PrintUtil.java,v 1.4 2012/09/27 11:03:53 pakj Exp $ */

package org.openoces.opensign.certificate.plugin.cdcard.util;

/**
 * This class implements printing functionality
 *
 * @author Preben Valeur  <prv@tdc.dk>
 */

import org.openoces.opensign.client.applet.dialogs.OpenSignDialog;

import java.awt.*;
import java.util.Properties;
import java.util.StringTokenizer;

public class PrintUtil {
    private static Properties printPrefs = new Properties();

    protected PrintUtil() {
    }

    private static class LinePrinter {
        private int currentWidth;
        private StringBuffer line = new StringBuffer();
        private FontMetrics fm;
        private int spw;
        private static final String sp = " ";
        private int maxWidth;
        private String savedToken = null;
        private int savedTokenWidth = 0;

        LinePrinter(FontMetrics fm, int maxWidth) {
            currentWidth = 0;
            this.fm = fm;
            spw = fm.stringWidth(" ");
            this.maxWidth = maxWidth;
        }

        void print(Graphics page, Point p) {
            page.drawString(line.toString(), p.x, p.y);
            if (savedToken != null) {
                line = new StringBuffer(savedToken);
                currentWidth = savedTokenWidth;
                line.append(sp);
                currentWidth += spw;
            } else {
                line = new StringBuffer();
                currentWidth = 0;
            }
            savedToken = null;
        }

        boolean append(String token) {
            int tokenWidth = fm.stringWidth(token);
            if (currentWidth > 0) {
                // check
                if (currentWidth + spw + tokenWidth > maxWidth) {
                    savedToken = token;
                    savedTokenWidth = tokenWidth;
                    return false;
                }
            }
            line.append(token);
            currentWidth += tokenWidth;
            line.append(sp);
            currentWidth += spw;
            return true;
        }

        boolean isEmpty() {
            return (currentWidth == 0);
        }

        boolean isFull() {
            return (currentWidth > maxWidth);
        }
    }

    private static class PagePrinter {
        private static Dimension a4mm = new Dimension(210, 297);
        private static int marginmm = 20;
        private static float lineFactor = 1.3f;
        private static Font printFont = new Font("Serif", Font.PLAIN, 8);
        private Point cursor;
        private Graphics page;
        private PrintJob printJob;
        private FontMetrics fm;
        private int lineHeight;
        private int printableWidth;
        private int printableHeight;
        //        private int currentWidth;
        private LinePrinter linePrinter;

        PagePrinter(PrintJob printJob) {
            this.printJob = printJob;
            newPage();
        }

        private void newPage() {
            if (page != null) {
                page.dispose();
            }
            page = printJob.getGraphics();
            Dimension pageSize = printJob.getPageDimension();
            printableWidth = (int) (pageSize.width * (float) (a4mm.width - 2 * marginmm) / a4mm.width);
            printableHeight = (int) (pageSize.height * (float) (a4mm.height - 2 * marginmm) / a4mm.height);
            page.translate((pageSize.width - printableWidth) / 2, (pageSize.height - printableHeight) / 2);
            // draw a border - just for fun
//            page.drawRect(-1, -1, printableWidth+1, printableHeight+1);
            // adding a bit or with to clip as stringWidth is inaccurate...
            page.setClip(0, 0, (int) (printableWidth * 1.1), printableHeight);
            // AND stopping printing  a bit before as stringWidth inaccurate.
            // figure out the line height
            page.setFont(printFont);
            fm = page.getFontMetrics();
            lineHeight = (int) (fm.getHeight() * lineFactor);
            if (linePrinter == null) {
                int netPrintableWidth = (int) (printableWidth * 0.95);
                linePrinter = new LinePrinter(fm, netPrintableWidth);
            }
            cursor = new Point(0, fm.getHeight());
        }

        /**
         * Print a paragraph and result not null (rest of para) if not possible
         *
         * @param paragraph
         */
        void printParagraph(String paragraph) {
            StringTokenizer strtok = new StringTokenizer(paragraph, " ");
            // maybe just a newline:
            if (!strtok.hasMoreTokens()) {
                cursor.y += lineHeight;
                return;
            }
            while (strtok.hasMoreTokens()) {
                // check if it can take another token
                if (!linePrinter.append(strtok.nextToken())) {
                    printLine();
                    while (linePrinter.isFull()) {
                        printLine();
                    }
                }
            }
            // end of paragraph, print the rest:
            if (!linePrinter.isEmpty()) {
                printLine();
            }
        }

        private void printLine() {
            linePrinter.print(page, cursor);
            cursor.y += lineHeight;
            if (cursor.y > printableHeight) {
                // get a new page somehow
                newPage();
            }
        }

        public void dispose() {
            page.dispose();
        }

    }

    public static void print(OpenSignDialog owner, String title, String text) {
        PrintJob printJob = Toolkit.getDefaultToolkit().getPrintJob(owner.getCallBackHandler().getBrowserFrame(), title, printPrefs);
        if (printJob == null) {
            return;
        }
        PagePrinter pagePrinter = new PagePrinter(printJob);
        // replace tabs:
        text = text.replace('\t', ' ');
        StringTokenizer strtok = new StringTokenizer(text, "\n");
        // for each paragraph:
        while (strtok.hasMoreTokens()) {
            pagePrinter.printParagraph(strtok.nextToken());
        }
        pagePrinter.dispose();

        printJob.end();
    }
}