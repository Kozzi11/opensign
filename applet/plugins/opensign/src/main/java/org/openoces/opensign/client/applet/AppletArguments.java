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

/* $Id: AppletArguments.java,v 1.2 2012/02/28 08:21:20 pakj Exp $ */

package org.openoces.opensign.client.applet;

/**
 * This class implements the general applet functionality.
 *
 * @author Jens Bo Friis  <jbf@it-practice.dk>
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 * @author rh
 * @author Michael Motet
 */

import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.xml.nanoxml.NanoXMLParser;
import org.openoces.opensign.xml.nanoxml.NanoXMLReader;
import org.openoces.opensign.xml.nanoxml.XMLElement;
import org.openoces.opensign.xml.nanoxml.XMLException;
import org.openoces.opensign.xml.xmldsig.SignatureGenerator;
import org.openoces.opensign.xml.xmldsig.VirkSignatureGenerator;

import java.applet.Applet;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class AppletArguments implements ParamReader {

    protected XMLElement _doc; // this will hold the top element in the XML
    private String xml;
    private Applet applet;
    private Map<String, String> ht = new HashMap<String, String>();

    //private XMLElement dataContainerElement;

//	AppletLayoutInfo layoutInfo;
//	AttachmentInfoCollection attachments;

    AppletArguments(Applet applet) throws XMLException {
        this.xml = applet.getParameter("signtext");
        if (xml != null) {
            getDocument();
        }
        this.applet = applet;
    }

    protected String getDefaultXML() {
        return "<?xml version=\"1.0\" encoding=\"utf8\" ?>";
    }

    /**
     * Returns a dom containing xml parameter given to the applet.
     *
     * @return
     */
    // todo: make private when done testing
    XMLElement getDocument() throws XMLException {
        if (xml == null) {
            xml = getDefaultXML();
        }

        NanoXMLParser parser = new NanoXMLParser(true);
        NanoXMLReader reader = NanoXMLReader.stringReader(xml);
        parser.setReader(reader);
        _doc = parser.parse();

        return _doc;
    }

    private XMLElement getNode(XMLElement node, String xmlTag) {
        XMLElement lNode = null;
        Iterator<XMLElement> enumeration = node.enumerateChildren();
        while (enumeration.hasNext()) {
            XMLElement child = enumeration.next();
            if (child.getName().compareTo(xmlTag) == 0) {
                lNode = child;
                break;
            }
        }
        return lNode;
    }


    private XMLElement getNodeWithAttribute(XMLElement node, String xmlTag, String Attribute, String AttribParam) {
        XMLElement lNode = null;

        Iterator<XMLElement> enumeration = node.enumerateChildren();
        while (enumeration.hasNext()) {
            XMLElement child = enumeration.next();

            if ((child.getName().compareTo(xmlTag) == 0) && (getNodeAttribute(child, Attribute))) {
                if ((child.getAttribute(Attribute)).equalsIgnoreCase(AttribParam)) {
                    lNode = child;
                    break;
                }
            }
        }
        return lNode;
    }


    private boolean getNodeAttribute(XMLElement node, String Attribute) {
        boolean attributeFound = false;

        Iterator<String> enumeration = node.enumerateAttributeNames();
        while (enumeration.hasNext()) {

            String attributeString = enumeration.next();

            if (attributeString.equalsIgnoreCase(Attribute)) {
                attributeFound = true;
                break;
            }
        }

        return attributeFound;
    }

    /**
     * Returns the input xml attachment element containing the sign text.
     *
     * @return Sign text (html).
     */
    private XMLElement getSignTextAttachmentElement() {
        XMLElement el = null;

        XMLElement firstChild = getNode(_doc, "virk:DataContainer");
        XMLElement secondChild = getNode(firstChild, "virk:DataObjectCollection");
        XMLElement finalchild = getNodeWithAttribute(secondChild, "virk:DataObject", "fileContentType", "SignText");

        Iterator<String> enumeration = finalchild.enumerateAttributeNames();
        while (enumeration.hasNext()) {

            String attributeString = enumeration.next();
            if (attributeString.equalsIgnoreCase("fileContentType")) {
                String attStr = finalchild.getAttribute(attributeString);
                if (attStr.compareTo("SignText") == 0) {
                    el = finalchild;
                    break;
                }
            }
        }


        if (el == null) {
            FileLog.error("Could not locate sign text attachment structure.");
        }

        return el;
    }

    /**
     * Returns the text or html that should be presented to the user.
     *
     * @return Text or html.
     */
    // todo: make private when done testing
    String getSignText() {
        //String xpath;
        String rawSignText, signText;
        XMLElement el;

        el = getSignTextAttachmentElement();

        rawSignText = el.getContent();
        signText = "";
        try {
            byte[] ba = Base64.decode(rawSignText.getBytes());
            signText = new String(ba, "UTF8");
        } catch (UnsupportedEncodingException ex) {
            FileLog.error(ex.getMessage(), ex);
        }

        return signText;
    }

    public String getParameter(String name) {
        if ("signtext".equals(name)) {
            return getSignText();
        } else {
            String v;
            if (!ht.containsKey(name)) {
                v = applet.getParameter(name);
                ht.put(name, v);
            } else {
                v = ht.get(name);
            }
            return v;
        }
    }

    public SignatureGenerator createSignatureGenerator() throws XMLException {
        if (xml == null) {
            return new VirkSignatureGenerator();
        } else {
            return new VirkSignatureGenerator(getDocument());
        }
    }

    public StringBuffer getSigntext() {
        return new StringBuffer(getParameter("signtext"));
    }
}