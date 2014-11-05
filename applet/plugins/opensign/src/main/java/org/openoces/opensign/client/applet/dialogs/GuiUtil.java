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

/* $Id: GuiUtil.java,v 1.5 2012/09/27 11:03:46 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs;

import org.openoces.opensign.client.applet.GuiCallback;
import org.openoces.opensign.client.applet.dialogs.panel.FixedSizePanel;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.utils.IOUtils;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * User: pakj
 * Date: 04-02-11
 * Time: 14:25
 */
public class GuiUtil {
    private final static Insets stdInsets = new Insets(1, 0, 0, 0);
    private final static Insets stdInsetsNoVertical = new Insets(0, 0, 0, 0);

    private static Map<String, Image> images = new HashMap<String, Image>();

    protected GuiUtil() {
    }

    public static synchronized void doInGui(final GuiCallback callback) {
        doInGui(callback, new SwingGuiRunner());
    }

    public static synchronized void doInGui(final GuiCallback callback, GuiRunner guiRunner) {
        guiRunner.run(new Runnable() {
            public void run() {
                try {
                    callback.doInGui();
                } catch (ThreadDeath td) {
                    // From http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Thread.html#stop%28%29
                    // "An application should not normally try to catch ThreadDeath unless it must do some extraordinary cleanup operation (note that the throwing of ThreadDeath
                    // causes finally clauses of try statements to be executed before the thread officially dies). If a catch clause catches a ThreadDeath object, it is important
                    // to rethrow the object so that the thread actually dies."

                    try {
                        // Attempt to log. ThreadDeath may be a symptom of a larger problem, or cause problems that will get reported.
                        FileLog.warn("ThreadDeath caught during UI operation. Continuing.", td);
                    } catch (Throwable t) { /* Nothing to do if logging fails. */ }

                    throw td;
                } catch (Throwable t) {
                    FileLog.error("Throwable caught in gui/event code", t);
                }
            }
        });
    }

    public static synchronized Image createImage(String fileName) {
        Image image = images.get(fileName);
        if (image != null) return image;

        try {
            byte[] byBuf = loadResource("/img/" + fileName);
            image = Toolkit.getDefaultToolkit().createImage(byBuf);
        } catch (Exception e) {
            FileLog.error("Could not read image: " + fileName, e);
            return null;
        }
        images.put(fileName, image);
        return image;
    }

    private static byte[] loadResource(String name) throws IOException {
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            is = GuiUtil.class.getResourceAsStream(name);
            if (is == null) {
                throw new IOException("Unable to locate resource: " + name);
            }

            baos = new ByteArrayOutputStream();
            byte[] bytes = new byte[16384];
            while (is.available() > 0) {
                int c = is.read(bytes, 0, bytes.length);
                baos.write(bytes, 0, c);
            }
            return baos.toByteArray();
        } finally {
            IOUtils.close(baos);
            IOUtils.close(is);
        }
    }


    public static void addEnterKeyListener(JComponent c1, final JComponent c2) {
        c1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 13 || e.getKeyChar() == 10 || e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (c2 instanceof JTextComponent) {
                        c2.grabFocus();
                    } else if (c2 instanceof JButton) {
                        ((JButton) c2).doClick();
                    }
                }
            }
        });
    }

    public static int addSingleLine(JPanel panel, int x, int y, JComponent component) {
        GridBagConstraints gbc = getDefaultConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        panel.add(component, gbc);

        return (++y);
    }

    public static int addSingleButton(JPanel panel, int x, int y, JComponent component) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(0, 2, 5, 2);

        panel.add(component, gbc);

        return (++y);
    }

    public static int addTextFieldLine(JPanel panel, int x, int y, JTextComponent textComponent) {
        GridBagConstraints gbc = getDefaultConstraints();
        gbc.insets = stdInsetsNoVertical;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.NONE;

        panel.add(textComponent, gbc);

        return (y + 1);
    }

    public static int addComboBoxLine(JPanel panel, int x, int y, JComboBox comboBox, JButton button) {
        GridBagConstraints gbc = getDefaultConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.NONE;

        panel.add(comboBox, gbc);

        y = y + 1;

        gbc = getDefaultConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;

        panel.add(button, gbc);

        return (y + 1);
    }

    public static int addDetailsLine(JPanel panel, int x, int y, JLabel name, JLabel value) {
        GridBagConstraints gbc = getDefaultConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(2, 2, 2, 2);

        panel.add(name, gbc);

        x = x + 1;

        gbc = getDefaultConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(2, 2, 2, 2);

        panel.add(value, gbc);

        return (y + 1);
    }


    public static GridBagConstraints getDefaultConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.insets = stdInsets;
        gbc.fill = GridBagConstraints.BOTH;
        return gbc;
    }

    public static void addFillerLine(JPanel panel, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 0, 0, 0);

        panel.add(new FixedSizePanel(0, 0), gbc);
    }

    public static int getScreenID(Component component) {
        int scrID = 1;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        for (int i = 0; i < gd.length; i++) {
            GraphicsConfiguration gc = gd[i].getDefaultConfiguration();
            Rectangle r = gc.getBounds();
            if (r.contains(component.getLocation())) {
                scrID = i + 1;
            }
        }
        return scrID;
    }

    public static Dimension getScreenDimension(int scrID) {
        Dimension d = new Dimension(0, 0);
        if (scrID > 0) {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            DisplayMode mode = ge.getScreenDevices()[scrID - 1].getDisplayMode();
            d.setSize(mode.getWidth(), mode.getHeight());
        }
        return d;
    }

    public static void requestFocus(final JComponent component) {
        doInGui(new GuiCallback() {
            @Override
            public void doInGui() {
                // ensure requestFocus is enabled
                if (!component.isRequestFocusEnabled()) {
                    component.setRequestFocusEnabled(true);
                }
                if (!component.hasFocus()) component.requestFocus();
            }
        });
    }
}