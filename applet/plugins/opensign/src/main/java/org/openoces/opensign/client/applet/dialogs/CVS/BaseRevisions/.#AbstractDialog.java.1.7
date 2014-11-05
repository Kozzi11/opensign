/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard
	Copyright 2006 Paw F. Kjeldgaard
	Copyright 2011 Daniel Andersen
	Copyright 2013 Anders M. Hansen

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

/* $Id: AbstractDialog.java,v 1.7 2013/03/13 09:31:18 anmha Exp $ */

package org.openoces.opensign.client.applet.dialogs;

/**
 * This class represents a common dialog superclass
 *
 * @author Preben Valeur  <prv@tdc.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 * @author Daniel Andersen <daand@nets.eu>
 * @author Anders M. Hansen <consult@ajstemp.dk>
 */


import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.dialogs.panel.AbstractPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public abstract class AbstractDialog implements WindowListener, OpenSignDialog {
    protected CallBackHandler callBackHandler;
    protected JDialog dialog;
    protected int width;
    protected int height;
    protected JComponent oldFocusComponent;

    protected AbstractDialog(CallBackHandler callBackHandler, final JComponent oldFocusComponent, int width, int height) {
        this.callBackHandler = callBackHandler;
        this.width = width;
        this.height = height;
        this.oldFocusComponent = oldFocusComponent;

        dialog = new JDialog(callBackHandler.getBrowserFrame(), "", true, callBackHandler.getContentPane().getGraphicsConfiguration());

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.addWindowListener(this);
        dialog.setUndecorated(true);
        dialog.setPreferredSize(new Dimension(width, height));

        dialog.addWindowListener(this);

        dialog.addFocusListener(
                new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        final AbstractPanel panel = getPanel();
                        if (panel != null) {
                            panel.grabFocus();
                        }
                    }
                }
        );
    }

    protected AbstractDialog(AbstractDialog parent, final JComponent oldFocusComponent, int width, int height) {
        this.callBackHandler = parent.callBackHandler;
        this.width = width;
        this.height = height;
        this.oldFocusComponent = oldFocusComponent;

        dialog = new JDialog(parent.dialog, "", true, null);

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.addWindowListener(this);
        dialog.setUndecorated(true);
        dialog.setPreferredSize(new Dimension(width, height));

        dialog.addWindowListener(this);

        dialog.addFocusListener(
                new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        final AbstractPanel panel = getPanel();
                        if (panel != null) {
                            panel.grabFocus();
                        }
                    }
                }
        );
    }

    @Override
    public final void hide() {
        if (dialog != null) {
            dialog.setVisible(false);

        }
        if (oldFocusComponent != null) GuiUtil.requestFocus(oldFocusComponent);
    }

    protected abstract void initialize();

    protected abstract AbstractPanel getPanel();

    @Override
    public final CallBackHandler getCallBackHandler() {
        return callBackHandler;
    }

    @Override
    public final void show() {
        initialize();

        final AbstractPanel panel = getPanel();
        show(panel);
    }

    private void show(final AbstractPanel panel) {
        Container container = dialog.getContentPane();
        container.removeAll();

        container.setLayout(new BorderLayout());
        container.add(panel, BorderLayout.CENTER);
        container.setSize(width, height);

        panel.setSize(width, height);
        panel.setLocation(0, 0);
        panel.showPanel();

        GraphicsConfiguration gc = container.getGraphicsConfiguration();
        Rectangle bounds = gc.getBounds();
        int screenId = GuiUtil.getScreenID(callBackHandler.getContentPane());
        Dimension screenDimension = GuiUtil.getScreenDimension(screenId);

        int x = ((screenDimension.width - dialog.getPreferredSize().width) / 2) + bounds.x;
        int y = ((screenDimension.height - dialog.getPreferredSize().height) / 2) + bounds.y;
        dialog.setLocation(x, y);
        dialog.pack();

        dialog.validate();
        dialog.repaint();

        if (dialog.getParent() != null) {
            dialog.getParent().validate();
            dialog.getParent().repaint();
        }

        dialog.setVisible(true);
    }

    public void windowOpened(WindowEvent e) {
        JComponent focusComponent = getPanel().getFocusComponent();
        if (focusComponent != null) {
            GuiUtil.requestFocus(focusComponent);
        }
    }

    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (oldFocusComponent != null) {
            GuiUtil.requestFocus(oldFocusComponent);
        }
    }

    public void windowIconified(WindowEvent e) {

    }

    public void windowDeiconified(WindowEvent e) {

    }

    public void windowActivated(WindowEvent e) {

    }

    public void windowDeactivated(WindowEvent e) {

    }

    public JDialog getDialog() {
        return dialog;
    }

    protected static int calcWidth(CallBackHandler callBackHandler, String str) {
    	int width = 200;//standard width
    	if(str != null && str.length() > 0) {
    		int calcWidth = 0;
    		try {
    			calcWidth = 100 + SwingUtilities.computeStringWidth(callBackHandler.getBrowserFrame().getFontMetrics(callBackHandler.getContentPane().getFont()), str);
    		} catch (Exception e) {
    			calcWidth = 100 + str.trim().length() * 7; //Default fall-back
    		} finally {
    			if(calcWidth > width) {
    				width = calcWidth;
    			}
    		}
    	}
    	return width;
    }
}