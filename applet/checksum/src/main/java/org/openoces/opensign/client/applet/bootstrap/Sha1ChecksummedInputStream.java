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

/* $Id: Sha1ChecksummedInputStream.java,v 1.4 2012/09/27 11:03:51 pakj Exp $ */

package org.openoces.opensign.client.applet.bootstrap;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

public class Sha1ChecksummedInputStream extends FilterInputStream {
    private Checksum staticChecksum;
    private Checksum calculatedChecksum;
    private boolean digested = false;

    Sha1ChecksummedInputStream(InputStream in, Checksum checksum) throws NoSuchAlgorithmException {
        super(in);
        this.staticChecksum = checksum;
        this.calculatedChecksum = new Sha1Checksum();
    }

    Sha1Checksum getCalculatedChecksum() {
        return (Sha1Checksum)calculatedChecksum;
    }

    boolean hasChecksumValid() throws Exception {
        if ( !digested ) {
            calculatedChecksum.digest();
        }
        return calculatedChecksum.matches(staticChecksum);
    }

    public int read() throws IOException {
        return super.read();
    };

    public int read(byte[] b) throws IOException {
        int r = super.read(b);
        if ( r > -1 ) {
            calculatedChecksum.update(b,0,r);
        }
        return r;
    }

    public int read(byte[] b, int off, int len) throws IOException {
        int r = super.read(b,off,len);
        if (r > -1 ) {
            calculatedChecksum.update(b,off,r);
        }
        return r;
    }

}