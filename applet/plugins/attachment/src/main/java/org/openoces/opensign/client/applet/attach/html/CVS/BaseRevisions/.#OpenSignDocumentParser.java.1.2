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

/* $Id: OpenSignDocumentParser.java,v 1.2 2012/02/28 08:21:53 pakj Exp $ */

package org.openoces.opensign.client.applet.attach.html;

import org.openoces.opensign.utils.FileLog;

import javax.swing.text.html.parser.DocumentParser;
import javax.swing.text.html.parser.DTD;
import javax.swing.text.html.parser.TagElement;
import javax.swing.text.SimpleAttributeSet;
import java.util.*;

/**
 * This class implements a html parser
 *
 * @author Preben Rosendal Valeur <prv@tdc.dk>
 */

class OpenSignDocumentParser extends DocumentParser {

    OpenSignDocumentParser(DTD dtd) {
        super(dtd);
    }

    protected boolean supportedTag(TagElement tag) {
        String name = tag.getElement().getName();
        boolean supported = supportedTagTable.containsKey(name.toUpperCase());
        FileLog.debug("tag " + name + " is " + (supported ? "" : "not ")
                + "supported");
        return supported;
    }

    protected boolean conditionallySupportedTag(TagElement tag) {
        String name = tag.getElement().getName();
        boolean conditionallySupported = conditionallySupportedTagTable
                .containsKey(name.toUpperCase());
        FileLog.debug("tag " + name + " is "
                + (conditionallySupported ? "" : "not ")
                + "conditionally supported");
        return conditionallySupported;
    }

    protected void filterAttributes(TagElement tag) {
        SimpleAttributeSet attrs = getAttributes();
        // if any attributes

        Set<String> keys = unSupportedAttributes.keySet();
        for (String attrName : keys) {
            // todo: find out if case matters here!
            attrs.removeAttribute(attrName);
            FileLog.debug("Removing attribute " + attrName + " from tag "
                    + tag.getElement().getName());
        }
    }

    protected void unsupportedOrUnknown(TagElement tag) {
        String name = tag.getElement().getName();
        boolean unSupported = unSupportedTagTable.containsKey(name
                .toUpperCase());
        FileLog.debug("tag " + name + " is "
                + (unSupported ? "unsupported" : "unknown"));
    }

    protected void handleStartTag(TagElement tag) {
        if (supportedTag(tag)) {
            super.handleStartTag(tag);
        } else if (conditionallySupportedTag(tag)) {
            filterAttributes(tag);
            super.handleStartTag(tag);
        } else {
            // ignore tag
            unsupportedOrUnknown(tag);
        }

    }

    protected void handleEndTag(TagElement tag) {
        if (supportedTag(tag)) {
            super.handleEndTag(tag);
        } else if (conditionallySupportedTag(tag)) {
            super.handleEndTag(tag);
        } else {
            // ignore tag
        }
    }

    // http://www.w3.org/TR/REC-html32#head
    // support HTML 3.2 except
    // - things that relies on external URLS
    // - conditionally things that can have external urls (like?)
    // - dynamic stuff like scripts

    // sample conditional:
    // <body background="url"...>

    // just for the record!
    // HTML 3.2 standard:
    // http://www.w3.org/TR/REC-html32
    private static String[] CONDITIONALLY_SUPPORTED_TAGS = {"BODY"};
    private static String[] SUPPORTED_FLOW_NONCONTAINER_TAGS = {"H1", "H2",
            "H3", "H4", "H5", "H6", "ADDRESS"};
    private static String[] SUPPORTED_FLOW_TAGS = {
            "HTML",// not really correct level
            "P", "UL", "OL", "LI", "DIR",
            "MENU", // old style lists
            "DL", "DT", "DD", "PRE",
            // obsolete - pre-pre: "XMP", "LISTING", "PLAINTEXT",
            "DIV", "CENTER", "BLOCKQUOTE", "HR", "TABLE", "CAPTION", "TH",
            "TD", "TR"};
    private static String[] UN_SUPPORTED_FLOW_TAGS = {"FORM", "ISINDEX"};
    private static String[] SUPPORTED_TEXT_TAGS = {"TT", "I", "B", "U",
            "STRIKE", "BIG", "SMALL", "SUB", "SUP", "EM", "STRONG", "DFN",
            "CODE", "SAMP", "KBD", "VAR", "CITE", "BR", "FONT", "BASEFONT",};
    private static String[] UN_SUPPORTED_TEXT_TAGS = {"INPUT", "SELECT",
            "OPTION", "TEXTAREA", // FORM parts
            "A", // or ok for linking inside page?
            "IMG", "APPLET", // definitely not
            "MAP"};
    private static String[] UN_SUPPORTED_ATTRIBUTES = {"BACKGROUND", "SRC",
            "HREF" // should they ever pass the tag filter...
    };
    private static Map<String, String> supportedTagTable = new HashMap<String, String>();
    private static Map<String, String> conditionallySupportedTagTable = new HashMap<String, String>();
    private static Map<String, String> unSupportedTagTable = new HashMap<String, String>();
    private static Map<String, String> unSupportedAttributes = new HashMap<String, String>();

    static void addTagsToTable(String[] tags, Map<String, String> table) {
        for (String tagName : tags) {
            table.put(tagName, tagName);
        }
    }

    static {
        addTagsToTable(SUPPORTED_FLOW_NONCONTAINER_TAGS, supportedTagTable);
        addTagsToTable(SUPPORTED_FLOW_TAGS, supportedTagTable);
        addTagsToTable(SUPPORTED_TEXT_TAGS, supportedTagTable);
        // conditional:
        addTagsToTable(CONDITIONALLY_SUPPORTED_TAGS,
                conditionallySupportedTagTable);
        // unsupported:
        addTagsToTable(UN_SUPPORTED_FLOW_TAGS, unSupportedTagTable);
        addTagsToTable(UN_SUPPORTED_TEXT_TAGS, unSupportedTagTable);
        // unsupported attributes:
        addTagsToTable(UN_SUPPORTED_ATTRIBUTES, unSupportedAttributes);
    }
}
