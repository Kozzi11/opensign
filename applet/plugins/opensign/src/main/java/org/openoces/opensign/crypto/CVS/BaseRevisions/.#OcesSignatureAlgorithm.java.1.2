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
package org.openoces.opensign.crypto;

import org.openoces.opensign.certificate.CertificateInfo;
import org.openoces.opensign.utils.FileLog;

public class OcesSignatureAlgorithm implements SignatureAlgorithmFactory {

	public SignatureAlgorithmHandler getHandler(CertificateInfo info) {
		try {
			if (info.getIssuerDN().getName().toLowerCase().indexOf("trust2408") != -1) {
				return new RsaWithSha256Handler();
			}
		} catch (Exception e) {
			throw new RuntimeException("Could not read Issuer DN", e);
		}
		return new RsaWithSha1Handler();
	}

    public void setPassword(char[] password) {
    }
}
