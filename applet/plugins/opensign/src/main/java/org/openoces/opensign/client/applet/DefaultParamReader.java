/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard
    Copyright 2008 Daniel Andersen


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

/* $Id: DefaultParamReader.java,v 1.2 2012/02/28 08:21:19 pakj Exp $ */

package org.openoces.opensign.client.applet;

import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.xml.xmldsig.BackwardsCompatibleSignatureGenerator;
import org.openoces.opensign.xml.xmldsig.BasicSignatureGenerator;
import org.openoces.opensign.xml.xmldsig.SignatureGenerator;

import java.applet.Applet;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements the default parameters reader
 *
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 * @author Daniel Andersen  <dani_ande@yahoo.dk>
 */

class DefaultParamReader implements ParamReader {

    protected static final int XML_VERSION_1_0 = 0;
    protected static final int XML_VERSION_1_1 = 1;
	
    protected int xmlVersion = XML_VERSION_1_1;
	
    protected Map<String, String> ht = new HashMap<String, String>();
    protected Applet applet;
    protected StringBuffer signtext = null;

    DefaultParamReader(Applet applet) {
        this.applet = applet;
    }

    DefaultParamReader(Applet applet,int xmlVersion) {
        this.applet = applet;
		this.xmlVersion = xmlVersion;
    }

    public String getParameter(String key) {
        String v;
        if ( !ht.containsKey(key)) {
            v = applet.getParameter(key);
            if ( v != null ) {
                ht.put(key,v);
            }
        } else {
            v = ht.get(key);
        }

        return v;
    }

    public SignatureGenerator createSignatureGenerator() {
        switch( xmlVersion ) {
            case XML_VERSION_1_0:
                return new BackwardsCompatibleSignatureGenerator();
            default:
                return new BasicSignatureGenerator();
        }
    }

    /**
     * Returns the text to be signed
     *
     * Note that we keep a separate getter for the signtext, as it can be large and immutable strings therefore should
     * be avoided.
     *
     * @return the text to be signed
     */
    public StringBuffer getSigntext() {
        if ( signtext == null ) {
            byte[] ba = Base64.decode(applet.getParameter("signtext").getBytes());
            try {
                signtext = new StringBuffer(new String(ba, "UTF8"));
            } catch (UnsupportedEncodingException e) {
                FileLog.fatal("utf-8 not supported");
                // FIXME: handle error situation
            }
        }
        return signtext;
    }
}