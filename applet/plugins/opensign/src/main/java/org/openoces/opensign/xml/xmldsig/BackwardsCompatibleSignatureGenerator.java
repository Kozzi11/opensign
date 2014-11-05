/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC A/S
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

/* $Id: BackwardsCompatibleSignatureGenerator.java,v 1.4 2012/09/27 11:03:47 pakj Exp $ */

package org.openoces.opensign.xml.xmldsig;

import org.openoces.opensign.appletsupport.Attachment;
import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.client.applet.dialogs.listeners.SignText;
import org.openoces.opensign.client.applet.dialogs.listeners.StringSignText;
import org.openoces.opensign.crypto.SignatureAlgorithmFactory;
import org.openoces.opensign.crypto.SignatureAlgorithmHandler;
import org.openoces.opensign.exceptions.InputDataError;
import org.openoces.opensign.exceptions.UserCancel;
import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.utils.FileLog;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Set;

/**
 * This class implements a xmldsig generator for the standard xmldsig format
 *
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 * @author Daniel Andersen    <dani_ande@yahoo.dk>
 * @author Mads Jensen <mjn@trifork.com>
 * @author Jeppe Burchhardt <Jeppe.Burchhardt@Cryptomathic.com>
 * @author Ole Friis Østergaard <ofo@trifork.com>
 */

public class BackwardsCompatibleSignatureGenerator implements SignatureGenerator {

    public String logon(CertificateHandler certificate, Map<Object, Object> visibleProps, Map<Object, Object> invisibleProps, SignatureAlgorithmFactory signatureAlgorithm) throws GeneralSecurityException, IOException, InputDataError, UserCancel {
        invisibleProps.put(ACTION, ACTION_LOGON);

        return getSignature(certificate, null, visibleProps, invisibleProps, signatureAlgorithm);
    }

    public String sign(CertificateHandler certificate, SignText signText, Map<Object, Object> visibleProps, Map<Object, Object> invisibleProps, Attachment[] attachments, SignatureAlgorithmFactory signatureAlgorithm) throws GeneralSecurityException, IOException, InputDataError, UserCancel {

        invisibleProps.put(ACTION, ACTION_SIGN);
        return getSignature(certificate, signText, visibleProps, invisibleProps, signatureAlgorithm);
    }

    /**
     * Get xmldsig signature message from parameters
     *
     * @param invisibleProperties
     * @param visibleProperties
     * @param signatureAlg
     * @return StringBuffer
     * @throws java.security.NoSuchAlgorithmException
     *
     * @throws java.io.UnsupportedEncodingException
     *
     * @throws java.security.NoSuchProviderException
     *
     */
    private String getSignature(CertificateHandler certificateHandler, SignText signText, Map<Object, Object> visibleProperties, Map<Object, Object> invisibleProperties, SignatureAlgorithmFactory signatureAlg) throws IOException, GeneralSecurityException, InputDataError, UserCancel {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
        sb.append("<openoces:signature xmlns:openoces=\"http://www.openoces.org/2003/10/signature#\" version=\"0.1\">");
        sb.append("<ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\">\n");
        String objectText = getObject(visibleProperties, signText, invisibleProperties);
        String signedInfo = getSignedInfo(certificateHandler, objectText, signatureAlg.getHandler(certificateHandler));
        sb.append(signedInfo.toString()).append("\n");
        sb.append(getSignatureValue(certificateHandler, signedInfo, signatureAlg).toString());
        String keyInfo = getKeyInfo(certificateHandler);
        sb.append(keyInfo);
        sb.append(objectText.toString()).append("\n");
        sb.append("</ds:Signature>");
        sb.append("</openoces:signature>");
        return sb.toString();
    }

    /**
     * Get KEYINFO section of xmldsig
     *
     * @return String
     */
    private String getKeyInfo(CertificateHandler certificateHandler) throws IOException {
        StringBuffer sb = new StringBuffer();
        sb.append("<ds:KeyInfo>\n");
        sb.append(getX509Data(certificateHandler));
        sb.append("</ds:KeyInfo>\n");
        return sb.toString();
    }

    /**
     * Get X509Data section of xmldsig
     *
     * @return String
     */
    private String getX509Data(CertificateHandler certificateHandler) throws IOException {
        StringBuffer sb = new StringBuffer();
        byte[][] chain = certificateHandler.getCertificateChain();

        if (chain != null) {
            for (int i = 0; i < chain.length; i++) {
                sb.append("<ds:X509Data>\n");
                sb.append(getX509Certificate(chain[i]));
                sb.append("</ds:X509Data>\n");
            }
        }

        return sb.toString();
    }

    /**
     * Get X509Data section of xmldsig
     *
     * @param cert
     * @return String
     */
    private String getX509Certificate(byte[] cert) {
        StringBuffer sb = new StringBuffer();
        sb.append("<ds:X509Certificate>\n");
        sb.append(wrapLines(new String(Base64.encode(cert)), BASE64_LINELENGTH).toString());
        sb.append("\n</ds:X509Certificate>\n");
        return sb.toString();
    }

    /**
     * Get the Object section of the xmldsig message. Properties are encoded as NAME VALUE
     * pairs, where name and value are encoded ad BASE64, to avoid XML message syntaxt conflicts.
     * When decoded, signtext section sould be likewise decoded.
     * The invisible properties that have not been visible to the signer, whereas the visible
     * properties have been visible.
     *
     * @param visibleProperties
     * @param invisibleProperties
     * @return StringBuffer
     */
    private String getObject(Map<Object, Object> visibleProperties, SignText signText, Map<Object, Object> invisibleProperties) throws IOException, InputDataError {
        StringBuffer sb = new StringBuffer();
        sb.append("<ds:Object xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:openoces=\"http://www.openoces.org/2003/10/signature#\" Id=\"ToBeSigned\">");
        sb.append("<ds:SignatureProperties>\n");
        sb.append(getPropertiesFragment(visibleProperties, signText, true).toString());
        sb.append(getPropertiesFragment(invisibleProperties, null, false).toString());
        sb.append("</ds:SignatureProperties>");
        sb.append("</ds:Object>");
        return sb.toString();
    }

    /**
     * Get SignedInfo section of xmldsig
     *
     * @param toBeSigned
     * @param signatureAlgorithmHandler
     * @return StringBuffer
     * @throws java.io.UnsupportedEncodingException
     *
     */
    private String getSignedInfo(CertificateHandler certificateHandler, String toBeSigned, SignatureAlgorithmHandler signatureAlgorithmHandler) throws IOException, GeneralSecurityException  {
        StringBuffer sb = new StringBuffer();
        sb.append("<ds:SignedInfo xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:openoces=\"http://www.openoces.org/2003/10/signature#\">\n");
        sb.append("<ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"></ds:CanonicalizationMethod>\n");

        sb.append(signatureAlgorithmHandler.getSignature(certificateHandler, toBeSigned));

        sb.append("</ds:SignedInfo>");
        return sb.toString();
    }

    /**
     * Get SignatureValue section of xmldsig
     *
     * @param signedInfo
     * @param signatureAlgorithm
     * @return String
     * @throws java.security.InvalidKeyException
     *
     * @throws java.security.SignatureException
     *
     * @throws java.security.NoSuchAlgorithmException
     *
     * @throws java.security.NoSuchProviderException
     *
     */
    private StringBuffer getSignatureValue(CertificateHandler certificateHandler, String signedInfo, SignatureAlgorithmFactory signatureAlgorithm) throws IOException, GeneralSecurityException, UserCancel {
        StringBuffer sb = new StringBuffer();

        byte[] data = signedInfo.getBytes("UTF8");
        sb.append("<ds:SignatureValue>\n");
        sb.append(wrapLines(new String(Base64.encode(certificateHandler.sign(data, signatureAlgorithm))), BASE64_LINELENGTH).toString());
        sb.append("\n</ds:SignatureValue>\n");
        return sb;
    }

    private StringBuffer getPropertiesFragment(Map<Object, Object> properties, SignText signText, boolean visible) throws IOException, InputDataError {
        StringBuffer s = new StringBuffer();
        if (signText != null) {
            s.append(getSignatureProperty("signtext", ((StringSignText)signText).getSignText(), true).toString()).append("\n");
        }

        if (properties != null) {
            Set keys = properties.keySet();
            for(Object name : keys) {
                Object value = properties.get(name);
                s.append(getSignatureProperty(name.toString(), value.toString(), visible).toString()).append("\n");
            }
        }
        return s;
    }

    /**
     * Generate a <SignatureProperty> node, encode name and value in order to support special chars
     *
     * @param name
     * @param value
     * @param setToBeSignedId
     * @return the <SignatureProperty> node
     */
    private StringBuffer getSignatureProperty(String name, String value, boolean setToBeSignedId) throws IOException, InputDataError {
        StringBuffer sb = new StringBuffer();

/*
            Better safe than sorry:
            An extra check in case we are not escaping all necessary characters correctly
        */
        if (!isWellformed(name)) {
            FileLog.warn("Name not wellformed: " + name);
            throw new InputDataError();
        }

        StringBuffer s;
        if (value == null || value.length() == 0) {
            s = new StringBuffer("");
        } else {
            s = new StringBuffer(new String(Base64.encode(value.getBytes("UTF8"))));
        }

        sb.append("<ds:SignatureProperty>");
        sb.append("<Name>");
        sb.append(name);
        sb.append("</Name>");
        sb.append("<Value VisibleToSigner=\"").append(setToBeSignedId ? "yes" : "no").append("\">");
        sb.append(s.toString());
        sb.append("</Value>");
        sb.append("</ds:SignatureProperty>");
        return sb;
    }


    private static boolean isWellformed(String name) {
        for (int i = 0; i < name.length(); i++) {
            int c = name.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == '_')) {
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Do newline wrapping of a string
     *
     * @param s
     * @param len
     * @return the wrapped string
     */
    public static StringBuffer wrapLines(String s, int len) {

        /* invariant: points at the first character not yet copied */
        int sPos = 0;

        // sanity check
        if (s == null) {
            return null;
        }

        StringBuffer r = new StringBuffer();

        while (sPos < s.length()) {
            r.append(r.length() > 0 ? "\n" : "");
            if (s.length() - sPos <= len) {
                r.append(s.substring(sPos));
                sPos = s.length();
            } else {
                r.append(s.substring(sPos, sPos + len));
                sPos = sPos + len;
            }
        }
        return r;
    }

}