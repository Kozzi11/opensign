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

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.utils.Base64;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class RsaWithSha1Handler implements SignatureAlgorithmHandler{

    public String getSignature(CertificateHandler certificateHandler, String toBeSigned) throws IOException, GeneralSecurityException {
        StringBuffer sb = new StringBuffer();
        sb.append("<ds:SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"></ds:SignatureMethod>\n");
        sb.append("<ds:Reference URI=\"#ToBeSigned\">\n");
        sb.append("<ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"></ds:DigestMethod>\n");
        sb.append("<ds:DigestValue>");
        sb.append(getDigestValueSha1(certificateHandler, toBeSigned));
        sb.append("</ds:DigestValue>\n");
        sb.append("</ds:Reference>\n");        
        return sb.toString();
    }

    public String getAttachmentDigest(CertificateHandler certificateHandler, String attachmentObject) throws IOException, GeneralSecurityException {
        StringBuffer sb = new StringBuffer();
        sb.append("<ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"></ds:DigestMethod>\n");
        sb.append("<ds:DigestValue>");
        sb.append(getDigestValueSha1(certificateHandler, attachmentObject));
        sb.append("</ds:DigestValue>\n");
        return sb.toString();
    }

    public String getSignatureAlgorithm() {
        return "SHA1withRSA";
    }

    public String getDigSigAlgorithm() {
        return "http://www.w3.org/2000/09/xmldsig#rsa-sha1";
    }

    public String getDigestAlgorithm() {
        return "http://www.w3.org/2000/09/xmldsig#sha1";
    }

    public String getCapiAlgorithm() {
        return "sha1";
    }

    private String getDigestValueSha1(CertificateHandler certificateHandler, String signText) throws IOException, GeneralSecurityException {
        byte[] data = signText.getBytes("UTF8");
        return new String(Base64.encode(certificateHandler.digest(data, this)));
    }
}
