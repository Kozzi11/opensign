/*
	Copyright 2011 Paw F. Kjeldgaard
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
    
    @author Anders M. Hansen <consult@ajstemp.dk>
*/

/* $Id: AbstractPanel.java,v 1.5 2013/03/05 11:24:16 anmha Exp $ */

package org.openoces.opensign.client.applet.dialogs.panel;

import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.dialogs.GuiUtil;
import org.openoces.opensign.client.applet.dialogs.components.ComponentFactory;
import org.openoces.opensign.client.applet.dialogs.components.DefaultComponentFactory;
import org.openoces.opensign.client.applet.dialogs.components.DoubleBorder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AbstractPanel extends JPanel {
    protected boolean initialized;
    protected JButton closeButton;
    protected CallBackHandler callbackHandler;
    protected ActionListener closeButtonListener;
    protected ComponentFactory componentFactory;

    protected abstract JComponent getActionPanel();

    protected abstract JComponent getContentPanel();

    public abstract JComponent getFocusComponent();

    protected AbstractPanel(CallBackHandler callbackHandler) {
        this.callbackHandler = callbackHandler;
        this.componentFactory = new DefaultComponentFactory();
        setLayout(new BorderLayout());
        setFocusTraversalPolicy(new OpensignFocusTraversalPolicy(this));
        setFocusCycleRoot(true);
        setBorder(new DoubleBorder(DefaultComponentFactory.frameColor, DefaultComponentFactory.backgroundColor, getBorderThickness()));

        if (addCloseButton()) {
            closeButton = componentFactory.createLinkButton(getCloseButtonTextId());
            closeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (closeButtonListener != null) {
                        closeButtonListener.actionPerformed(e);
                    }
                }
            });
        }
    }

    protected int getBorderThickness() {
        return 3;
    }

    protected JPanel createContentPanel() {
        return new GradientPanel(new BorderLayout());
    }

    public void showPanel() {
        if (!initialized) {
            JPanel contentPanel = createContentPanel();
            JPanel innerPanel = componentFactory.createPanel(new BorderLayout());
            innerPanel.setBorder(getMarginBorder());
            contentPanel.add(innerPanel, BorderLayout.CENTER);
            add(contentPanel, BorderLayout.CENTER);


            JComponent p = getActionPanel();
            if (p != null) {
                p.setOpaque(false);
                innerPanel.add(p, BorderLayout.SOUTH);
            }

            p = getLogoPanel();
            if (p != null) {
                add(p, BorderLayout.NORTH);
            }
            p = getContentPanel();

            if (p != null) {
                p.setOpaque(false);
                innerPanel.add(p, BorderLayout.CENTER);
            }
            initialized = true;
        }
        grabFocus();
    }

    public void grabFocus() {
        JComponent focusComponent = getFocusComponent();
        if (focusComponent != null) {
            focusComponent.grabFocus();
        }
    }

    protected JComponent getLogoPanel() {
        if (addCloseButton()) {
            JPanel p = new JPanel(new BorderLayout(0, 0));
            p.setBorder(new EmptyBorder(2, 1, 2, 1));
            p.setOpaque(true);
            p.setBackground(DefaultComponentFactory.topBackgroungColor);

            JPanel _p = new JPanel();
            _p.setBackground(DefaultComponentFactory.topBackgroungColor);
            _p.setOpaque(true);
            p.add(_p, BorderLayout.CENTER);

            closeButton.setIcon(new ImageIcon(GuiUtil.createImage("close.jpg")));
            closeButton.setHorizontalTextPosition(SwingConstants.LEFT);
            p.add(closeButton, BorderLayout.EAST);

            return p;
        } else return null;
    }

    protected boolean addCloseButton() {
        return false;
    }

    protected Border getMarginBorder() {
        return new EmptyBorder(2, 2, 2, 2);
    }

    protected String getCloseButtonTextId() {
        return "DLG_CERTIFICATEDETAILS_BUTTON_CLOSE";
    }

    protected void finalize() throws Throwable {
    	try{
    		closeButton = null;
    		callbackHandler = null;
    		closeButtonListener = null;
    		componentFactory = null;
    	} finally {
    		super.finalize();
    	}
    }
}