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

/* $Id: DefaultComponentFactory.java,v 1.4 2012/09/27 11:03:44 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs.components;

import org.openoces.opensign.client.applet.OS;
import org.openoces.opensign.client.applet.dialogs.GuiUtil;
import org.openoces.opensign.client.applet.resources.ResourceManager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.table.TableModel;
import javax.swing.text.html.parser.ParserDelegator;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DefaultComponentFactory implements ComponentFactory {
    public final static String NAME_PASSWORD_TEXTFIELD = "tfPassword";

    public final static Color backgroundColor = Color.white;
    public final static Color backgroundGradientTopColor = new Color(0xecf1f0);
    public final static Color backgroundGradientBottomColor = new Color(0xffffff);
    public final static Color frameColor = new Color(0xbccccb);
    public final static Color textColor = new Color(0x000000);
    public final static Color linkColor = new Color(0x0d9395);
    public final static Color topBackgroungColor = new Color(0xffffff);
    public final static Color textFieldBorderColor = frameColor;

    public final static Font textFont;
    public final static Font titleFont;
    public final static Font headerFont;
    public final static Font labelFont;
    public final static Font buttonFont;
    public final static Font linkFont;
    public final static Font errorFont;

    public final static Border textFieldBorder = new VariableSizeLineBorder(1, 2, textFieldBorderColor);

    public final static int labelFontWith = 6;

    static {
        try {
            if (!UIManager.getLookAndFeel().getClass().getName().toLowerCase().contains("metal")) {
                UIManager.setLookAndFeel(MetalLookAndFeel.class.getName());
            }
        } catch (Exception e) {
            //do nothing
        }

        if (OS.isOSLinux()) {
            textFont = new Font("Sans-serif", Font.PLAIN, 11);
            titleFont = new Font("Sans-serif", Font.BOLD, 13);
            headerFont = new Font("Sans-serif", Font.BOLD, 11);
        } else {
            textFont = new Font("Sans-serif", Font.PLAIN, 12);
            titleFont = new Font("Sans-serif", Font.BOLD, 14);
            headerFont = new Font("Sans-serif", Font.BOLD, 12);
        }
        labelFont = textFont;

        errorFont = textFont;
        buttonFont = headerFont;
        linkFont = textFont;

        UIManager.getLookAndFeel().getDefaults().put("LinkButtonUI", LinkButtonUI.class.getName());
        UIManager.getDefaults().put("LinkButtonUI", LinkButtonUI.class.getName());
    }

    public DefaultComponentFactory() {
    }

    public JButton createLinkButton(String textId) {
        String resourceText = ResourceManager.getString(textId);

        JButton b = new JButton(resourceText) {
            private static final String uiClassID = "LinkButtonUI";

            public String getUIClassID() {
                return uiClassID;
            }

            public boolean isFocusable() {
                return true;
            }

            public ButtonUI getUI() {
                return new LinkButtonUI();
            }

            public void setUI(LinkButtonUI linkui) {
                super.setUI(linkui);
            }

            public void updateUI() {
                setUI(new LinkButtonUI());
            }

            public Insets getInsets() {
                return new Insets(2, 2, 2, 2);
            }
        };

        b.setName(textId);
        b.getAccessibleContext().setAccessibleName(resourceText);
        b.setFocusable(true);
        addMouseHandLinkListener(b, Color.RED);
        GuiUtil.addEnterKeyListener(b, b); //Now the button is clicked when you press enter.

        return b;
    }

    public JButton createImageButton(String toolTipTextId, String imageFile) {
        JButton button = new JButton();
        button.setBorderPainted(false);
        Image image = GuiUtil.createImage(imageFile);
        button.setIcon(new ImageIcon(image));
        button.setToolTipText(ResourceManager.getString(toolTipTextId));
        button.setFont(labelFont);
        button.setForeground(textColor);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusable(true);
        button.setBorderPainted(false);
        button.getAccessibleContext().setAccessibleName(ResourceManager.getString(toolTipTextId));
        GuiUtil.addEnterKeyListener(button, button); //Now the button is clicked when you press enter.
        return button;
    }

    @Override
    public JButton createLinkImageButton(String textId, String toolTipTextId, String imageFile) {
        String text = ResourceManager.getString(textId);
        JButton b = new JButton(text) {
            private static final String uiClassID = "LinkButtonUI";

            public String getUIClassID() {
                return uiClassID;
            }

            public boolean isFocusable() {
                return true;
            }

            public ButtonUI getUI() {
                return new LinkButtonUI();
            }

            public void setUI(LinkButtonUI linkui) {
                super.setUI(linkui);
            }

            public void updateUI() {
                setUI(new LinkButtonUI());
            }

            public Insets getInsets() {
                return new Insets(2, 2, 2, 2);
            }
        };
        b.setName(textId);
        b.setFocusable(true);
        b.setRequestFocusEnabled(true);
        b.setToolTipText(ResourceManager.getString(toolTipTextId));
        b.getAccessibleContext().setAccessibleName(ResourceManager.getString(toolTipTextId));
        addMouseHandLinkListener(b, Color.RED);

        Image image = GuiUtil.createImage(imageFile);
        b.setIcon(new ImageIcon(image));
        GuiUtil.addEnterKeyListener(b, b); //Now the button is clicked when you press enter.
        return b;
    }

    public JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setOpaque(false);
        toolBar.setBorderPainted(false);
        toolBar.setRollover(false);


        return toolBar;
    }

    public JButton createNormalButton(String textId, boolean boldText) {
        String name = textId;
        String resourceText = ResourceManager.getString(textId);
        if (resourceText != null) textId = resourceText;

        JButton b = new JButton(textId) {

            protected void paintComponent(Graphics g) {
                GradientPaint gp = new GradientPaint(0, 0, backgroundGradientBottomColor, 0,
                        getHeight(), backgroundGradientTopColor);
                ((Graphics2D) g).setPaint(gp);
                g.fillRect(0, 0, getWidth(), getHeight());

                if (ui != null) {
                    Graphics scratchGraphics = g.create();
                    try {
                        ui.update(scratchGraphics, this);
                    } finally {
                        scratchGraphics.dispose();
                    }
                }
            }

            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                d.height = 20;
                return d;
            }

            public boolean isFocusable() {
                return true;
            }
        };

        b.setName(name);
        b.getAccessibleContext().setAccessibleName(resourceText);
        b.setFont(boldText ? buttonFont : labelFont);
        b.setForeground(textColor);
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setFocusable(true);
        b.setBorder(
                new BorderUIResource.CompoundBorderUIResource(
                        new LineBorder(frameColor, 1),
                        new BasicBorders.MarginBorder()));
        GuiUtil.addEnterKeyListener(b, b); //Now the button is clicked when you press enter.
        return b;
    }

    public JLabel createLabel(String textId) {
        return createLabel(textId, labelFont, null);
    }

    public JLabel createHeaderLabel(String text) {
        return createHeaderLabel(text, null);
    }

    public JLabel createHeaderLabel(String text, Icon icon) {
        JLabel label = createLabel("", headerFont, icon);
        label.setText(text);
        return label;
    }

    public JLabel createTextLabel(String text) {
        return createTextLabel(text, null);
    }

    public JLabel createTextLabel(String text, Icon icon) {
        JLabel label = createLabel("", labelFont, icon);
        label.setText(text);
        return label;
    }


    public JComboBox createComboBox(ComboBoxModel model) {
        JComboBox combo = new JComboBox(model);
        combo.setUI((ComboBoxUI) OpensignComboboxUI.createUI(combo));
        combo.setBorder(new LineBorder(frameColor, 1));
        combo.setOpaque(true);
        combo.setBackground(backgroundColor);
        combo.setFont(labelFont);
        return combo;
    }

    public OpensignPasswordField createPasswordField() {
        final OpensignPasswordField tf = new OpensignPasswordField(textFont);

        tf.setBorder(textFieldBorder);
        tf.setName(NAME_PASSWORD_TEXTFIELD);
        tf.setColumns(16);
        tf.setAllowPaste(true);

        tf.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GuiUtil.requestFocus(tf);
            }
        });

        return tf;
    }

    public JTextField createTextField() {
        return createTextField(16);
    }

    public JTextField createTextField(int columns) {
        final OpensignTextField tf = new OpensignTextField(textFont);

        tf.setBorder(textFieldBorder);
        tf.setName(NAME_PASSWORD_TEXTFIELD);
        tf.setColumns(columns);
        tf.setAllowPaste(true);

        tf.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GuiUtil.requestFocus(tf);
            }
        });

        return tf;
    }

    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        return panel;
    }

    public JPanel createPanel(LayoutManager layoutManager) {
        JPanel panel = new JPanel(layoutManager);
        panel.setOpaque(false);
        return panel;
    }

    public JScrollPane createScrollPane(Component component) {
        JScrollPane scrollPane = new JScrollPane(component, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(new LineBorder(DefaultComponentFactory.frameColor));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        return scrollPane;
    }

    public JButton createNormalTextButton(String text, boolean boldText) {

        JButton b = new JButton(text) {

            protected void paintComponent(Graphics g) {
                GradientPaint gp = new GradientPaint(0, 0, backgroundGradientBottomColor, 0,
                        getHeight(), backgroundGradientTopColor);
                ((Graphics2D) g).setPaint(gp);
                g.fillRect(0, 0, getWidth(), getHeight());

                if (ui != null) {
                    Graphics scratchGraphics = g.create();
                    try {
                        ui.update(scratchGraphics, this);
                    } finally {
                        scratchGraphics.dispose();
                    }
                }
            }

            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                d.height = 20;
                return d;
            }

            public boolean isFocusable() {
                return true;
            }
        };

        b.setFont(boldText ? buttonFont : labelFont);
        b.setForeground(textColor);
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorder(
                new BorderUIResource.CompoundBorderUIResource(
                        new LineBorder(frameColor, 1),
                        new BasicBorders.MarginBorder()));
        GuiUtil.addEnterKeyListener(b, b); //Now the button is clicked when you press enter.
        b.getAccessibleContext().setAccessibleName(text);
        return b;
    }

    private void addMouseHandLinkListener(final JComponent c, final Color highlightColour) {
        c.addMouseListener(new MouseAdapter() {
            private Color originalColour;

            public void mouseEntered(MouseEvent e) {
                if (c.isEnabled()) {
                    c.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    if (highlightColour != null) {
                        originalColour = c.getForeground();
                        c.setForeground(highlightColour);
                    }
                }
            }

            public void mouseExited(MouseEvent e) {
                c.setCursor(Cursor.getDefaultCursor());
                if (highlightColour != null) {
                    c.setForeground(originalColour);
                    originalColour = null;
                }
            }
        });
    }

    private JLabel createLabel(String text, Font font, Icon icon) {
        String name = text;
        MultiLineLabel l;

        if (!"".equals(text)) {
            String resourceText = ResourceManager.getString(text);
            if (resourceText != null) text = resourceText;
        }

        if (text == null) l = new MultiLineLabel(icon);
        else l = new MultiLineLabel(text, icon);

        l.setOpaque(false);
        l.setFont(font);
        l.setName(name);
        l.setForeground(textColor);
        return l;
    }

    public SignTextDisplay createSignTextDisplay(TextMimeType signTextFormat, String signText, String stylesheet) throws SigntextValidationException {
        new ParserDelegator();
        SignTextDisplay signTextDisplay = new SignTextDisplay();
        signTextDisplay.setText(signTextFormat, signText, stylesheet);
        return signTextDisplay;
    }

    @Override
    public JTable createTable(TableModel model) {
        JTable table = new JTable(model);
        table.setOpaque(false);
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setFont(headerFont);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(false);
        return table;
    }
}