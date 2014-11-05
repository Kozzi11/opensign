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

/* $Id: Canonical.java,v 1.2 2012/02/28 08:21:19 pakj Exp $ */

package org.openoces.opensign.client.applet;

import org.openoces.opensign.xml.nanoxml.XMLElement;
import org.openoces.opensign.xml.xmldsig.SortVector;
import org.openoces.opensign.xml.xmldsig.StringCompare;

import java.util.Iterator;

/**
 * This class can canonicalize xml
 *
 * @author christophe
 */

public class Canonical {

    private StringBuffer fullBuffer;

    public Canonical() {
        fullBuffer = new StringBuffer();
    }

    public void canonicalize(XMLElement element) {
        StringBuffer buffer = new StringBuffer();

        startElement(element, buffer);
        write(buffer);
        buffer.setLength(0);

        Iterator<XMLElement> enumeration = element.enumerateChildren();
        Canonical canonicalizer;
        while (enumeration.hasNext()) {
            XMLElement child = enumeration.next();

            canonicalizer = new Canonical();
            canonicalizer.canonicalize(child);
            fullBuffer.append(canonicalizer.getCanonicalXML());
        }


        content(element.getContent(), buffer);

        endElement(element.getName(), buffer);

        write(buffer);

    }

    private void startElement(XMLElement element, StringBuffer buffer) {
        buffer.append("<");
        buffer.append(element.getName());

        String nameSpace = element.getAttribute("xmlns:ds");
        if (nameSpace != null) {
            buffer.append(" ");
            buffer.append("xmlns:ds");
            buffer.append("=\"");
            buffer.append(nameSpace);
            buffer.append("\"");

            element.removeAttribute("xmlns:ds");
        }

        nameSpace = element.getAttribute("xmlns:virk");
        if (nameSpace != null) {
            buffer.append(" ");
            buffer.append("xmlns:virk");
            buffer.append("=\"");
            buffer.append(nameSpace);
            buffer.append("\"");

            element.removeAttribute("xmlns:virk");
        }


        Iterator<String> enumeration = element.enumerateAttributeNames();

        while (enumeration.hasNext()) {
        	enumeration.next();
        }


        SortVector attributeElements = new SortVector(new StringCompare());
        int count = 0;

        Iterator<String> ElemEnum = element.enumerateAttributeNames();
        while (ElemEnum.hasNext()) {
            String s = ElemEnum.next();
            attributeElements.addElement(s);
            count++;
        }
        attributeElements.sort();

        String name;
        String value;
        for (int i = 0; i < count; ++i) {
            buffer.append(" ");
            //name = attributeElements[i];
            name = (String) attributeElements.elementAt(i);
            buffer.append(name);
            buffer.append("=\"");
            value = element.getAttribute(name);
            int valueLen = value.length();
            for (int j = 0; j < valueLen; j++)
                appendChar(value.charAt(j), buffer);

            buffer.append("\"");
        }

        buffer.append(">");
    }

    private void appendChar(char c, StringBuffer buf) {
        switch (c) {
            case '&':
                buf.append("&amp;");
                break;
            case '<':
                buf.append("&lt;");
                break;
            case '>':
                buf.append("&gt;");
                break;
            case '"':
                buf.append("&quot;");
                break;
            case '\t':
                buf.append("&#9;");
                break;
            case '\n':
                buf.append("&#10;");
                break;
            case '\r':
                buf.append("&#13;");
                break;
            default:
                buf.append(c);
                break;
        }
    }

    private void content(String data, StringBuffer buffer) {
        if (data != null) {
            int valueLen = data.length();
            for (int j = 0; j < valueLen; j++)
                appendChar(data.charAt(j), buffer);
        }
    }

    private void endElement(String name, StringBuffer buffer) {
        buffer.append("</");
        buffer.append(name);
        buffer.append(">");
    }

    private void write(StringBuffer s) {

        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c < 0x80)
                fullBuffer.append(c);
            else {
                switch (c & 0xF800) {
                    case 0:
                        fullBuffer.append((((c >> 6) & 0x1F) | 0xC0));
                        fullBuffer.append(((c & 0x3F) | 0x80));
                        break;
                    case 0xD800:
                        char c2;
                        if (i + 1 < len && (c & 0xFC00) == 0xD800
                                && ((c2 = s.charAt(i + 1)) & 0xFC00) == 0xDC00) {
                            ++i;
                            int n = ((c & 0x3FF) << 10) | (c2 & 0x3FF);
                            n += 0x10000;
                            fullBuffer.append((((n >> 18) & 0x7) | 0xF0));
                            fullBuffer.append((((n >> 12) & 0x3F) | 0x80));
                            fullBuffer.append((((n >> 6) & 0x3F) | 0x80));
                            fullBuffer.append(((n & 0x3F) | 0x80));
                            break;
                        }
                        /* this is an error situation really */
                        /* fall through */
                    default:
                        fullBuffer.append((((c >> 12) & 0xF) | 0xE0));
                        fullBuffer.append((((c >> 6) & 0x3F) | 0x80));
                        fullBuffer.append(((c & 0x3F) | 0x80));
                        break;
                }
            }
        }
    }

    public String getCanonicalXML() {
        return fullBuffer.toString();
    }

}
