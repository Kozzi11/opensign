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

/* $Id: GuiUtil.java,v 1.4 2012/09/27 11:03:50 pakj Exp $ */
package org.openoces.opensign.client.applet.bootstrap;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

class GuiUtil {
    private final static Cursor myWaitCursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
    private final static Cursor myDefaultCursor = Cursor.getDefaultCursor();
    private static JComponent newGlassPane = new GlassPane();
    private static Component oldGlassPane;

    protected GuiUtil() {
    }

    static synchronized void doInGui(final GuiCallback callback) {
        if (SwingUtilities.isEventDispatchThread()) {
            callback.doInGui();
        } else {
            GuiRunner guiRunner = new SwingGuiRunner();
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
    }

    static void showProgressUI(final JApplet applet, final boolean showProgress) {
        doInGui(new GuiCallback() {
            public void doInGui() {
                if (showProgress) {
                    //The glass pane should not fill out all of the screen if the applet is started in supersize mode.
                    //Only the part that is taken up by the current panel should be overlayed.
                    JPanel glassPanel = new JPanel(new BorderLayout());
                    glassPanel.setOpaque(false);
                    newGlassPane.setBounds(0, 0, applet.getWidth(), applet.getHeight());
                    glassPanel.add(newGlassPane, BorderLayout.CENTER);
                    applet.setGlassPane(glassPanel);
                    glassPanel.setVisible(true);

                    //if (glassPanel.getComponentCount() > 0 && glassPanel.getComponent(0) instanceof JComponent) {
                    //    ((JComponent) glassPanel.getComponent(0)).grabFocus();
                    //}

                    applet.setCursor(myWaitCursor);
                } else {
                    if (applet.getGlassPane() != null)
                        applet.getGlassPane().setVisible(false);
                    applet.setGlassPane(oldGlassPane);
                    applet.setCursor(myDefaultCursor);
                }
            }

        });
    }

    static void setOldGlassPane(Component oldGlassPane) {
        GuiUtil.oldGlassPane = oldGlassPane;
    }

    private static class GlassPane extends JPanel {

        public GlassPane() {
            AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.92f);
            setLayout(new GridBagLayout());
            JLabel l = new JLabel();
            l.setOpaque(false);
            l.setIcon(new ImageIcon(createLoaderImage()));
            l.setHorizontalTextPosition(SwingConstants.CENTER);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridy = 0;
            gbc.gridx = 0;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 1.0;

            add(l, gbc);

            validate();
            setOpaque(false);
        }

        private Image createLoaderImage() {
            try {
                byte[] byBuf = loadResource("/img/loader.gif");
                return Toolkit.getDefaultToolkit().createImage(byBuf);
            } catch (Exception e) {
                FileLog.error("Could not read image: /img/loader.gif", e);
                return null;
            }
        }

        private byte[] loadResource(String name) throws IOException {
            InputStream is = null;
            ByteArrayOutputStream baos = null;
            try {
                is = BootApplet.class.getResourceAsStream(name);
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
                try {
                    if (is != null) is.close();
                } catch (IOException e) {
                    //do nothing
                }
                try {
                    if (baos != null) baos.close();
                } catch (IOException e) {
                    //do nothing
                }
            }
        }
    }
}