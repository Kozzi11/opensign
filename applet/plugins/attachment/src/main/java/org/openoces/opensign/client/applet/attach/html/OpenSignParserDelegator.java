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

/* $Id: OpenSignParserDelegator.java,v 1.2 2012/02/28 08:21:53 pakj Exp $ */

package org.openoces.opensign.client.applet.attach.html;

import org.openoces.opensign.utils.FileLog;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.DTD;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.Serializable;
import java.io.Reader;
import java.io.IOException;

/**
 * This class implements a parser delegator
 *
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 */

class OpenSignParserDelegator extends ParserDelegator implements Serializable {
	private static final long serialVersionUID = -1529528429680520083L;
	private static DTD dtd = null;

    OpenSignParserDelegator() {
        if (dtd == null) {
            setDefaultDTD();
        }
    }

    protected static synchronized void setDefaultDTD() {
        if (dtd == null) {
            DTD _dtd = null;
            // (PENDING) Hate having to hard code!
            String nm = "html32";
            try {
                _dtd = DTD.getDTD(nm);
            } catch (IOException e) {
                // (PENDING) UGLY!
                FileLog.warn("Throw an exception: could not get default dtd: " + nm, e);
            }
            dtd = ParserDelegator.createDTD(_dtd, nm);
        }
    }

    public void parse(Reader r, HTMLEditorKit.ParserCallback cb, boolean ignoreCharSet) throws IOException {
        new OpenSignDocumentParser(dtd).parse(r, cb, ignoreCharSet);
    }
}
