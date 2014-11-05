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

/* $Id: SignTextDisplay.java,v 1.5 2012/09/27 11:03:44 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs.components;

import org.openoces.opensign.client.applet.GuiCallback;
import org.openoces.opensign.client.applet.dialogs.GuiUtil;
import org.openoces.opensign.utils.FileLog;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.xml.transform.*;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;

public class SignTextDisplay extends JEditorPane {
    private boolean antiAliased;

    private boolean canEdit = false;

    // This must be private to ensure that the workaround below is used.
    SignTextDisplay() {
        init();
    }

    private void init() {
        setBorder(null);
        antiAliased = true;

        setEditable(true);

        setDropTarget(null);
    }

    /**
     * Sets a documentfilter preventing the user from editing editorpane's textarea
     */
    private void setDocumentfilter() {
        ((AbstractDocument) getDocument()).setDocumentFilter(new DocumentFilter() {

            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (canEdit) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                if (canEdit) {
                    super.remove(fb, offset, length);
                }
            }

            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (canEdit) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

    public void setAntiAliased(boolean aa) {
        antiAliased = aa;
        repaint();
    }

    public boolean isAntialiased() {
        return antiAliased;
    }

    public void paintComponent(Graphics g) {
        if (antiAliased) {
            Graphics2D g2d = (Graphics2D) g;
            // Enable all the anti-aliasing that we can muster.
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        }

        super.paintComponent(g);
    }

    /**
     * @param stylesheet contains the XSLT stylesheet, if the sign text is XML. If the sign type is PLAIN, the parameter may contain the point size of the monospace font.
     */
    public void setText(TextMimeType type, String contents, String stylesheet) throws SigntextValidationException {
        canEdit = true;
        try {
            if (type == TextMimeType.PLAIN) {
                // stylesheet parameter holds the contents of the MONOSPACEFONT-parameter, if included during applet startup.
                setPlain(contents, stylesheet);
            } else if (type == TextMimeType.HTML) {
                setHTML(contents);
            } else if (type == TextMimeType.XML) {
                setXML(contents, stylesheet);
            }
            setDocumentfilter();
        } finally {
            canEdit = false;
        }
    }

    private void setHTML(String contents) throws SigntextValidationException {
        HtmlValidator validator = new HtmlValidator();
        if (!validator.validate(contents))
            throw new SigntextValidationException("HTML is not valid: " + validator.getReason());
        setHTMLRendering(contents);
    }

    private void setHTMLRendering(String contents) {
        setContentType("text/html");
        HTMLEditorKit kit = (HTMLEditorKit) getEditorKit();

        // This CSS rule can be modified if we want to change the look of the component's text.
        String bodyRule = "body { font: Calibri; font-size: 15pt;}";
        kit.getStyleSheet().addRule(bodyRule);

        addHyperlinkListener(linkHandler(this));
        // Workaround for Java bug http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6724188
        getDocument().putProperty("IgnoreCharsetDirective", Boolean.TRUE);
        setText(contents);
    }

    private HyperlinkListener linkHandler(final SignTextDisplay signTextDisplay) {
        return new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    if (e.getDescription().length() < 2)
                        return;

                    // Relative URLs are contained in the desc field, not the URL field.
                    String reference = e.getDescription().substring(1); // Remove initial #
                    signTextDisplay.scrollToReference(reference);
                }
            }
        };
    }

    private void setXML(String contents, String stylesheet) throws SigntextValidationException {
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setURIResolver(new URIResolver() {
            public Source resolve(String href, String base) throws TransformerException {
                FileLog.warn("XML transformation is trying to access external resource : " + href + " " + base);
                return new StreamSource(new StringReader(""));
            }
        });


        Transformer transformer;
        try {
            Templates pss = factory.newTemplates(new StreamSource(new StringReader(stylesheet)));
            transformer = pss.newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "html");
            transformer.setOutputProperty(OutputKeys.ENCODING, "utf8");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        } catch (TransformerConfigurationException e) {
            FileLog.error("Unable to create XML Transformer.", e);
            throw new RuntimeException("Unable to perform XML transformation.");
        }

        FileLog.debug("Using XML transformer: " + transformer.getClass().getName());

        XMLReader xmlReader;
        try {
            xmlReader = XMLReaderFactory.createXMLReader();
            configureReaderFeatures(xmlReader);
            xmlReader.setEntityResolver(new EntityResolver() {
                // Override resolveEntity to protect against the document containing references to external resources.
                // http://projects.webappsec.org/XML-External-Entities
                // http://www.ibm.com/developerworks/xml/library/x-tipcfsx.html
                public InputSource resolveEntity(String s, String s1) throws SAXException, IOException {
                    FileLog.warn("XML transformation is trying to access external resource : " + s + " " + s1);
                    return new InputSource(new StringReader(""));
                }
            });
        } catch (SAXException e) {
            FileLog.error("Unable to create XML Reader.", e);
            return;
        }

        StringWriter transformationResult = new StringWriter();

        try {
            transformer.transform(new SAXSource(xmlReader, new InputSource(new StringReader(contents))), new StreamResult(transformationResult));
        } catch (TransformerException e) {
            FileLog.error("Unable to perform XML Transformation.", e);
            throw new RuntimeException("Unable to perform XML transformation.");
        }


        setHTML(transformationResult.toString());
    }

    private static final void configureReaderFeatures(XMLReader xmlReader) {
        // Enable or disable various features that enhances the security of XML signing.
        // Some of these features only apply to specific implementations, while others are generic SAX features.
        String[] disabledFeatures = new String[]{
                "http://xml.org/sax/features/external-general-entities",             // http://xerces.apache.org/xerces-j/features.html#external-parameter-entities
                "http://xml.org/sax/features/external-parameter-entities",             // http://xerces.apache.org/xerces-j/features.html#external-parameter-entities
                "http://apache.org/xml/features/nonvalidating/load-external-dtd",     // http://xerces.apache.org/xerces-j/features.html#load-external-dtd
        };

        String[] enabledFeatures = new String[]{
                "http://javax.xml.XMLConstants/feature/secure-processing"            // javax.xml.XMLConstants#FEATURE_SECURE_PROCESSING
        };

        setFeatures(xmlReader, enabledFeatures, true);
        setFeatures(xmlReader, disabledFeatures, false);
    }

    private static void setFeatures(XMLReader xmlReader, String[] features, boolean enabled) {
        for (int i = 0; i < features.length; i++) {
            try {

                xmlReader.setFeature(features[i], enabled);

            } catch (SAXException e) {
                FileLog.debug("XML reader did not acknowledge feature " + features[i] + ".", e);
            }
        }
    }

    private void setPlain(String contents, String fontSettings) {
        setEditorKit(new StyledEditorKit());

        if (fontSettings != null) {
            int fontSize = 12;
            try {
                fontSize = Integer.parseInt(fontSettings);
            } catch (NumberFormatException e) {
                // Do nothing in this case. Just use the default value of fontSize.
            }

            setFont(new Font("Monospaced", Font.PLAIN, fontSize));
        }

        setContentType("text/plain");
        // Attempt to set a nice font. The HONOR_DISPLAY_PROPERTIES field is not available in JEditorPanes prior to JRE1.5.
        // The field is set using reflection to keep it from failing under JRE1.4.
        try {
            Field field = JEditorPane.class.getField("HONOR_DISPLAY_PROPERTIES");
            putClientProperty(field.get(this), Boolean.TRUE);
        } catch (RuntimeException e) {
            // Catches SecurityException and IllegalArgumentException
            FileLog.warn("Unable to set HONOR_DISPLAY_PROPERTIES on JEditorPane.", e);
        } catch (NoSuchFieldException e) {
            // This will happen under JRE1.4. The default behaviour will be an "ugly" font.
            FileLog.warn("Unable to set HONOR_DISPLAY_PROPERTIES on JEditorPane.", e);
        } catch (IllegalAccessException e) {
            FileLog.warn("Unable to set HONOR_DISPLAY_PROPERTIES on JEditorPane.", e);
        }

        setText(contents);
    }

    public void setTabPressedAction(final GuiCallback tabPressedGuiCallback, final GuiCallback shiftTabPressedGuiCallback) {
        addFocusListener(new FocusListener() {
            private final KeyListener listener = new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_TAB: {
                            //If we use transferFocus() or transferFocusBackward() focus will go back to the sign text display. Therefore transfer focus explicitly.
                            if (e.isShiftDown()) {
                                shiftTabPressedGuiCallback.doInGui();
                            } else {
                                tabPressedGuiCallback.doInGui();
                            }
                            break;
                        }
                    }
                }
            };

            public void focusGained(FocusEvent e) {
                GuiUtil.doInGui(new GuiCallback() {
                    public void doInGui() {
                        addKeyListener(listener);
                    }
                });
            }

            public void focusLost(FocusEvent e) {
                GuiUtil.doInGui(new GuiCallback() {
                    public void doInGui() {
                        removeKeyListener(listener);
                    }
                });
            }
        });
    }

}