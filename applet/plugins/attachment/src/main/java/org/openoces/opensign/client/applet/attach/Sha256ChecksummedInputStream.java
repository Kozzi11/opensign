/*
	Copyright 2010 DanID A/S

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
	
 * @author Mads Jensen <mjn@trifork.com>
 * @author Jeppe Burchhardt <Jeppe.Burchhardt@Cryptomathic.com>
 * @author Ole Friis Østergaard <ofo@trifork.com>
*/

package org.openoces.opensign.client.applet.attach;

import org.openoces.opensign.utils.FileLog;

import java.io.InputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

class Sha256ChecksummedInputStream extends FilterInputStream implements CheckSummedInputerStream{
    private Checksum staticChecksum;
    private Checksum calculatedChecksum;
    private boolean digested = false;

    Sha256ChecksummedInputStream(InputStream in, Checksum checksum) throws NoSuchAlgorithmException {
        super(in);
        this.staticChecksum = checksum;
        this.calculatedChecksum = new Sha256Checksum();
    }

    public Checksum getCalculatedChecksum() {
        if ( !digested ) {
            calculatedChecksum.digest();
            digested = true;
        }
        return calculatedChecksum;
    }

    public boolean hasChecksumValid() throws Exception {
        if ( !digested ) {
            calculatedChecksum.digest();
            digested = true;
        }
        return calculatedChecksum.matches(staticChecksum);
    }

    public int read() throws IOException {
        FileLog.fatal("this is unexpected");
        return super.read();
    }

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
