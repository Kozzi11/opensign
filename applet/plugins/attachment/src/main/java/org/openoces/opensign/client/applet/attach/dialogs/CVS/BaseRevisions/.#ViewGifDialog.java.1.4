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

/* $Id: ViewGifDialog.java,v 1.4 2012/09/27 11:03:49 pakj Exp $ */

package org.openoces.opensign.client.applet.attach.dialogs;

import org.openoces.opensign.client.applet.dialogs.AbstractDialog;
import org.openoces.opensign.client.applet.dialogs.panel.AbstractPanel;
import org.openoces.opensign.utils.ArrayUtil;
import org.openoces.opensign.utils.FileLog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ViewGifDialog extends AbstractDialog {
    private GifPanel panel;

    private String title;
    private byte[] data;

    public ViewGifDialog(AbstractDialog parent, final JComponent oldFocusComponent, String title, byte[] data) {
        super(parent, oldFocusComponent, 200, 100);
        this.title = title;
        this.data = ArrayUtil.copyOf(data);

        InputStream inputStream = null;
        try {
            final GraphicsConfiguration config = dialog.getGraphicsConfiguration();
            final int left = Toolkit.getDefaultToolkit().getScreenInsets(config).left;
            final int right = Toolkit.getDefaultToolkit().getScreenInsets(config).right;
            final int top = Toolkit.getDefaultToolkit().getScreenInsets(config).top;
            final int bottom = Toolkit.getDefaultToolkit().getScreenInsets(config).bottom;

            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            final int maxWidth = screenSize.width - left - right;
            final int maxHeight = screenSize.height - top - bottom;

            inputStream = new ByteArrayInputStream(data);
            BufferedImage image = ImageIO.read(inputStream);


            int width = image.getWidth() + 25;
            if (width > maxWidth) width = maxWidth;

            int height = image.getHeight() + 50;
            if (height > maxHeight) height = maxHeight;

            dialog.setPreferredSize(new Dimension(width, height));
        } catch (IOException e) {
            FileLog.error(e.getMessage(), e);
        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                //do nothing
            }
        }
    }


    @Override
    protected void initialize() {
        if (panel == null) panel = new GifPanel(callBackHandler, this, title, data);
    }

    @Override
    protected AbstractPanel getPanel() {
        return panel;
    }
}