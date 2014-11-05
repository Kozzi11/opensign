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

/* $Id: GifPanel.java,v 1.4 2012/09/27 11:03:49 pakj Exp $ */

package org.openoces.opensign.client.applet.attach.dialogs;

import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.dialogs.OpenSignDialog;
import org.openoces.opensign.utils.FileLog;

import javax.swing.*;
import java.awt.*;

public class GifPanel extends AbstractViewPanel {

    public GifPanel(CallBackHandler callbackHandler, OpenSignDialog dialog, String title, byte[] data) {
        super(callbackHandler, dialog, title, data);
    }

    @Override
    protected JComponent getContentPanel() {
        JPanel panel = componentFactory.createPanel(new BorderLayout());
        panel.add(title, BorderLayout.NORTH);

        Component comp = new ImageCanvas(data);
        JScrollPane scrollPane = componentFactory.createScrollPane(comp);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    static class ImageCanvas extends Canvas {
        private static final long serialVersionUID = -3604468005581881821L;
        private Image image;

        ImageCanvas(byte[] data) {
            image = Toolkit.getDefaultToolkit().createImage(data);
            init(image);
        }

        private void init(Image image) {
            MediaTracker mt = new MediaTracker(this);
            // add the image to group #0
            mt.addImage(image, 0);
            try {
                // wait for all of the images to load
                mt.waitForAll();
            } catch (InterruptedException ie) {
                FileLog.error(ie.getMessage());
            }
        }

        public void paint(Graphics graphics) {
            if (image != null) {
                graphics.drawImage(image, 0, 0, null);
            }
        }

        public Dimension getPreferredSize() {
            // check if image downloaded/created?
            return new Dimension(image.getWidth(null), image.getHeight(null));
        }
    }
}