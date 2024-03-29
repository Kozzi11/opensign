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

/* $Id: GridLayout2.java,v 1.2 2012/02/28 08:21:56 pakj Exp $ */

package org.openoces.opensign.utils;

import java.awt.*;

/**
 * This class implements a grid layout which allows components of differrent sizes
 *
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 */

public class GridLayout2 extends GridLayout {
	private static final long serialVersionUID = 8687796417895684312L;

	public GridLayout2() {
        this(1, 0, 0, 0);
    }

    public GridLayout2(int rows, int cols) {
        this(rows, cols, 0, 0);
    }

    public GridLayout2(int rows, int cols, int hgap, int vgap) {
        super(rows, cols, hgap, vgap);
    }

    public Dimension preferredLayoutSize(Container parent) {
        //System.err.println("preferredLayoutSize");
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            int ncomponents = parent.getComponentCount();
            int nrows = getRows();
            int ncols = getColumns();
            if (nrows > 0) {
                ncols = (ncomponents + nrows - 1) / nrows;
            } else {
                nrows = (ncomponents + ncols - 1) / ncols;
            }
            int[] w = new int[ncols];
            int[] h = new int[nrows];
            for (int i = 0; i < ncomponents; i++) {
                int r = i / ncols;
                int c = i % ncols;
                Component comp = parent.getComponent(i);
                Dimension d = comp.getPreferredSize();
                if (w[c] < d.width) {
                    w[c] = d.width;
                }
                if (h[r] < d.height) {
                    h[r] = d.height;
                }
            }
            int nw = 0;
            for (int j = 0; j < ncols; j++) {
                nw += w[j];
            }
            int nh = 0;
            for (int i = 0; i < nrows; i++) {
                nh += h[i];
            }
            return new Dimension(insets.left + insets.right + nw + (ncols - 1) * getHgap(),
                    insets.top + insets.bottom + nh + (nrows - 1) * getVgap());
        }
    }

    public Dimension minimumLayoutSize(Container parent) {
//        System.err.println("minimumLayoutSize");
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            int ncomponents = parent.getComponentCount();
            int nrows = getRows();
            int ncols = getColumns();
            if (nrows > 0) {
                ncols = (ncomponents + nrows - 1) / nrows;
            } else {
                nrows = (ncomponents + ncols - 1) / ncols;
            }
            int[] w = new int[ncols];
            int[] h = new int[nrows];
            for (int i = 0; i < ncomponents; i++) {
                int r = i / ncols;
                int c = i % ncols;
                Component comp = parent.getComponent(i);
                Dimension d = comp.getMinimumSize();
                if (w[c] < d.width) {
                    w[c] = d.width;
                }
                if (h[r] < d.height) {
                    h[r] = d.height;
                }
            }
            int nw = 0;
            for (int j = 0; j < ncols; j++) {
                nw += w[j];
            }
            int nh = 0;
            for (int i = 0; i < nrows; i++) {
                nh += h[i];
            }
            return new Dimension(insets.left + insets.right + nw + (ncols - 1) * getHgap(),
                    insets.top + insets.bottom + nh + (nrows - 1) * getVgap());
        }
    }

    public void layoutContainer(Container parent) {
        //System.err.println("layoutContainer");
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            int ncomponents = parent.getComponentCount();
            int nrows = getRows();
            int ncols = getColumns();
            if (ncomponents == 0) {
                return;
            }
            if (nrows > 0) {
                ncols = (ncomponents + nrows - 1) / nrows;
            } else {
                nrows = (ncomponents + ncols - 1) / ncols;
            }
            int hgap = getHgap();
            int vgap = getVgap();
            // scaling factors
            Dimension pd = preferredLayoutSize(parent);
//            double sw = (1.0 * parent.getWidth()) / pd.width;
//            double sh = (1.0 * parent.getHeight()) / pd.height;
            double sw = (1.0 * parent.getSize().width) / pd.width;
            double sh = (1.0 * parent.getSize().height) / pd.height;
            // scale
            int[] w = new int[ncols];
            int[] h = new int[nrows];
            for (int i = 0; i < ncomponents; i++) {
                int r = i / ncols;
                int c = i % ncols;
                Component comp = parent.getComponent(i);
                Dimension d = comp.getPreferredSize();
                d.width = (int) (sw * d.width);
                d.height = (int) (sh * d.height);
                if (w[c] < d.width) {
                    w[c] = d.width;
                }
                if (h[r] < d.height) {
                    h[r] = d.height;
                }
            }
            for (int c = 0, x = insets.left; c < ncols; c++) {
                for (int r = 0, y = insets.top; r < nrows; r++) {
                    int i = r * ncols + c;
                    if (i < ncomponents) {
                        parent.getComponent(i).setBounds(x, y, w[c], h[r]);
                    }
                    y += h[r] + vgap;
                }
                x += w[c] + hgap;
            }
        }
    }
}

