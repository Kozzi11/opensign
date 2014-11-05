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

/* $Id: BasicSignatureGenerator.java,v 1.6 2013/02/12 06:36:41 pakj Exp $ */

package org.openoces.opensign.xml.xmldsig;

import org.openoces.opensign.appletsupport.Attachment;
import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.client.applet.dialogs.listeners.SignText;
import org.openoces.opensign.crypto.SignatureAlgorithmFactory;
import org.openoces.opensign.crypto.SignatureAlgorithmHandler;
import org.openoces.opensign.exceptions.InputDataError;
import org.openoces.opensign.exceptions.UserCancel;
import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.utils.StringUtils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements a xmldsig generator for the standard xmldsig format
 *
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 * @author Mads Jensen <mjn@trifork.com>
 * @author Jeppe Burchhardt <Jeppe.Burchhardt@Cryptomathic.com>
 * @author Ole Friis Østergaard <ofo@trifork.com>
 */

public class BasicSignatureGenerator implements SignatureGenerator {

    public static final String NS_OPENOCES_SIGNATURE = "http://www.openoces.org/2006/07/signature#";
    public static final String NS_W3_XMLDSIG = "http://www.w3.org/2000/09/xmldsig#";

    private static Map<String, SignatureFactory> signatureFactories = new HashMap<String, SignatureFactory>();

    public BasicSignatureGenerator() {
    }

    public String logon(CertificateHandler certificate, Map<Object, Object> visibleProps, Map<Object, Object> invisibleProps, SignatureAlgorithmFactory signatureAlgorithm) throws IOException, GeneralSecurityException, InputDataError, UserCancel {
        invisibleProps.put(ACTION, ACTION_LOGON);
        return getSignature(certificate, null, visibleProps, invisibleProps, null, signatureAlgorithm);
    }

    public String sign(CertificateHandler certificate, SignText signText, Map<Object, Object> visibleProps, Map<Object, Object> invisibleProps, Attachment[] attachments, SignatureAlgorithmFactory signatureAlgorithm) throws IOException, GeneralSecurityException, InputDataError, UserCancel {
        invisibleProps.put(ACTION, ACTION_SIGN);
        return getSignature(certificate, signText, visibleProps, invisibleProps, attachments, signatureAlgorithm);
    }

    /**
     * Get xmldsig signature message from parameters
     *
     * @param visibleProperties
     * @param invisibleProperties
     * @param attachments
     * @return StringBuffer
     * @throws java.security.NoSuchAlgorithmException
     *
     * @throws java.io.UnsupportedEncodingException
     *
     * @throws java.security.NoSuchProviderException
     *
     */
    private String getSignature(CertificateHandler certificateHandler, SignText signText, Map<Object, Object> visibleProperties, Map<Object, Object> invisibleProperties, Attachment[] attachments, SignatureAlgorithmFactory signatureAlgorithm) throws IOException, InputDataError, GeneralSecurityException, UserCancel {
        StringBuilder builder = new StringBuilder();

        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
        builder.append("<openoces:signature xmlns:openoces=\"").append(NS_OPENOCES_SIGNATURE).append("\" version=\"0.1\">");
        builder.append("<ds:Signature xmlns:ds=\"").append(NS_W3_XMLDSIG).append("\" Id=\"OpensignSignature\">\n");

        SignatureFactory signatureFactory = getSignatureFactory(signText);
        String objectText = signatureFactory.getObjectText(visibleProperties, signText, invisibleProperties);

        AttachmentInfo[] attachmentObjects = signatureFactory.getObjectsForAttachments(signText, attachments);

        String signedInfo = getSignedInfo(certificateHandler, objectText, attachmentObjects, signatureAlgorithm.getHandler(certificateHandler));

        builder.append(signedInfo).append("\n");
        addSignatureValue(builder, certificateHandler, signedInfo, signatureAlgorithm);
        addKeyInfo(builder, certificateHandler);
        if (!StringUtils.isEmpty(objectText.toString())) {
            builder.append(objectText).append("\n");
        }

        if (attachmentObjects != null) {
            for (AttachmentInfo attachmentObject1 : attachmentObjects) {
                String attachmentObject = attachmentObject1.attachment;
                if (attachmentObject != null) {
                    builder.append(attachmentObject).append("\n");
                }
            }
        }

        builder.append("</ds:Signature>");
        builder.append("</openoces:signature>");
        return builder.toString();
    }

    /**
     * Get KEYINFO section of xmldsig
     *
     * @return String
     */
    private void addKeyInfo(StringBuilder builder, CertificateHandler certificateHandler) throws IOException {
        builder.append("<ds:KeyInfo>\n");
        addX509Data(builder, certificateHandler);
        builder.append("</ds:KeyInfo>\n");
    }

    /**
     * Get X509Data section of xmldsig
     *
     * @return String
     */
    private void addX509Data(StringBuilder builder, CertificateHandler certificateHandler) throws IOException {
        byte[][] chain = certificateHandler.getCertificateChain();
        if (chain != null) {
            for (byte[] aChain : chain) {
                builder.append("<ds:X509Data>\n");
                addX509Certificate(builder, aChain);
                builder.append("</ds:X509Data>\n");
            }
        }
    }

    /**
     * Get X509Data section of xmldsig
     *
     * @param cert
     * @return String
     */
    private void addX509Certificate(StringBuilder builder, byte[] cert) {
        builder.append("<ds:X509Certificate>\n");
        builder.append(wrapLines(new String(Base64.encode(cert)), BASE64_LINELENGTH).toString());
        builder.append("\n</ds:X509Certificate>\n");
    }


    /**
     * Get SignedInfo section of xmldsig
     *
     * @param toBeSigned
     * @param attachmentObjects
     * @param signatureAlgorithmHandler
     * @return StringBuffer
     * @throws java.io.UnsupportedEncodingException
     *
     */
    private String getSignedInfo(CertificateHandler certificateHandler, String toBeSigned, AttachmentInfo[] attachmentObjects, SignatureAlgorithmHandler signatureAlgorithmHandler) throws GeneralSecurityException, IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("<ds:SignedInfo xmlns:ds=\"").append(NS_W3_XMLDSIG).append("\" xmlns:openoces=\"").append(NS_OPENOCES_SIGNATURE).append("\">\n");
        builder.append("<ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"></ds:CanonicalizationMethod>\n");

        if (!StringUtils.isEmpty(toBeSigned.toString())) {
            builder.append(signatureAlgorithmHandler.getSignature(certificateHandler, toBeSigned));
        }

        for (AttachmentInfo attachmentObject1 : attachmentObjects) {
            if (attachmentObject1 != null) {
                String attachmentObject = attachmentObject1.attachment;
                builder.append("<ds:Reference Type=\"http://www.w3.org/2000/09/xmldsig#Object\" URI=\"#").append(attachmentObject1.attachmentURI).append("\">\n");
                builder.append(signatureAlgorithmHandler.getAttachmentDigest(certificateHandler, attachmentObject));
                builder.append("</ds:Reference>\n");
            }
        }

        builder.append("</ds:SignedInfo>");
        return builder.toString();
    }

    /**
     * Get SignatureValue section of xmldsig
     *
     * @param signedInfo
     * @return String
     *         F     * @throws java.security.InvalidKeyException
     * @throws java.security.SignatureException
     *
     * @throws java.security.NoSuchAlgorithmException
     *
     * @throws java.security.NoSuchProviderException
     *
     */
    private void addSignatureValue(StringBuilder builder, CertificateHandler certificateHandler, String signedInfo, SignatureAlgorithmFactory signatureAlgorithm) throws IOException, GeneralSecurityException, UserCancel {
        byte[] data = signedInfo.getBytes("UTF8");
        builder.append("<ds:SignatureValue>\n");
        builder.append(wrapLines(new String(Base64.encode(certificateHandler.sign(data, signatureAlgorithm))), BASE64_LINELENGTH).toString());
        builder.append("\n</ds:SignatureValue>\n");
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

    private SignatureFactory getSignatureFactory(SignText signText) {
        SignatureFactory signatureFactory = signText != null ? signatureFactories.get(signText.getMimeType()) : null;
        if(signatureFactory == null) signatureFactory = new DefaultSignatureFactory();
        return signatureFactory;
    }

    public static void setSignatureFactory(String mimeType, SignatureFactory signatureFactory) {
        signatureFactories.put(mimeType, signatureFactory);
    }


}