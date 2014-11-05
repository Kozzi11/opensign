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

/* $Id: VirkSignatureGenerator.java,v 1.4 2012/09/27 11:03:47 pakj Exp $ */

package org.openoces.opensign.xml.xmldsig;

import org.openoces.opensign.appletsupport.Attachment;
import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.client.applet.Canonical;
import org.openoces.opensign.client.applet.dialogs.listeners.SignText;
import org.openoces.opensign.client.applet.dialogs.listeners.StringSignText;
import org.openoces.opensign.crypto.SignatureAlgorithmFactory;
import org.openoces.opensign.crypto.SignatureAlgorithmHandler;
import org.openoces.opensign.exceptions.InputDataError;
import org.openoces.opensign.exceptions.UserCancel;
import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.xml.nanoxml.XMLElement;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * This class implements a generator for the virk specific xmldsig document style.
 *
 * @author Michael Motet
 * @author Mads Jensen <mjn@trifork.com>
 * @author Jeppe Burchhardt <Jeppe.Burchhardt@Cryptomathic.com>
 * @author Ole Friis Østergaard <ofo@trifork.com>
 */

public class VirkSignatureGenerator implements SignatureGenerator {
    private XMLElement inputXML;

    /**
     * Constructor for logon
     */
    public VirkSignatureGenerator() {
    }

    /**
     * Constructor for signing
     *
     * @param inputXML
     */
    public VirkSignatureGenerator(XMLElement inputXML) {
        this.inputXML = inputXML;
    }


    public String logon(CertificateHandler certificate, Map<Object, Object> visibleProps, Map<Object, Object> invisibleProps, SignatureAlgorithmFactory signatureAlgorithmHandler) throws IOException, GeneralSecurityException, InputDataError, UserCancel {
        invisibleProps.put(ACTION, ACTION_LOGON);

        String challenge = (String)invisibleProps.get("logonChallenge");
        if (challenge == null) challenge = "Warning: empty challenge";
        XMLElement signTextXML = createDataCollection(createSignTextDataObject(challenge));
        // wrapped in a DataCollection - like Signing:
//        signTextXML = createDataCollection(signTextXML);

        // todo: check
        // NOTE: signTextXML is based on the (hidden) challenge while signText is based on what is visible
        // why??
        SignText signText = new StringSignText((String)visibleProps.get(SIGNTEXT));

        return getSignature(certificate, signText, signTextXML, signatureAlgorithmHandler);
    }

    public String sign(CertificateHandler certificate, SignText signText, Map<Object, Object> visibleProps, Map<Object, Object> invisibleProps, Attachment[] attachments, SignatureAlgorithmFactory signatureAlgorithm) throws IOException, GeneralSecurityException, InputDataError, UserCancel {
        XMLElement signTextXML = getDataObjectCollection(inputXML);
        return getSignature(certificate, signText, signTextXML, signatureAlgorithm);
    }

    private XMLElement createDataCollection(XMLElement dataObject) {
        XMLElement xmlout = new XMLElement();
        xmlout.setName("virk:DataObjectCollection");
        xmlout.addChild(dataObject);
        return xmlout;
    }

    private XMLElement createSignTextDataObject(String signText) {
        XMLElement xmlout = new XMLElement();
        xmlout.setName("virk:DataObject");
        xmlout.setAttribute("Id", "ca2c8970-46a2-410b-b209-1ae99fa5153b");
        xmlout.setAttribute("dataEncoding", "base64");
        xmlout.setAttribute("textEncoding", "utf-8");
        xmlout.setAttribute("fileName", "signdata.txt");
        xmlout.setAttribute("fileSize", "");
        xmlout.setAttribute("mimeType", "text/plain");
        xmlout.setAttribute("fileContentType", "SignText");
        xmlout.setAttribute("fileProcessControl", "");
        xmlout.setAttribute("fileDescription", "");
        try {
            byte[] b64enc = Base64.encode(signText.getBytes("UTF8"));
            xmlout.setContent(new String(b64enc));
        } catch (UnsupportedEncodingException e) {
            FileLog.error(e.getMessage(), e);
        }
        return xmlout;
    }

    /**
     * Get xmldsig signature message from parameters
     *
     * @param signtext
     * @param certificateHandler
     * @return String
     * @throws java.security.NoSuchAlgorithmException
     *
     * @throws java.io.UnsupportedEncodingException
     *
     * @throws java.security.NoSuchProviderException
     *
     */
    private String getSignature(CertificateHandler certificateHandler, SignText signtext, XMLElement signTextXML, SignatureAlgorithmFactory signatureAlgorithm) throws IOException, GeneralSecurityException, UserCancel {
        String xmlOutput = null;

        XMLElement xmlout = new XMLElement();
        xmlout.setName("virk:SignOutputStructure");
        xmlout.setAttribute("xmlns:virk", "http://rep.oio.dk/virk.dk/xml/schemas/2002/06/28/");
        xmlout.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        xmlout.setAttribute("xsi:schemaLocation", "");

        XMLElement signoutHead = new XMLElement();
        signoutHead.setName("virk:SignOutputHeaderStructure");

        XMLElement signResultSucc = new XMLElement();
        signResultSucc.setName("virk:SignResultSuccessIndicator");
        signResultSucc.setContent("true");
        signoutHead.addChild(signResultSucc);

        XMLElement signResultCode = new XMLElement();
        signResultCode.setName("virk:SignResultCode");
        signoutHead.addChild(signResultCode);

        XMLElement signResultText = new XMLElement();
        signResultText.setName("virk:SignResultText");
        signoutHead.addChild(signResultText);

        XMLElement signTimeStamp = new XMLElement();
        signTimeStamp.setName("virk:SignTimestamp");
        Date now;
        DateFormat fmt;
        fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        now = new Date();
        signTimeStamp.setContent(fmt.format(now));
        signoutHead.addChild(signTimeStamp);

        xmlout.addChild(signoutHead);


        XMLElement dataContainer = new XMLElement();
        dataContainer.setName("virk:DataContainer");
        xmlout.addChild(dataContainer);

        XMLElement dataCollection = signTextXML;
        dataContainer.addChild(dataCollection);

        // adding the dataobjectSignatureCollection
        XMLElement dataObjectSignatureCollection = new XMLElement();
        dataObjectSignatureCollection.setName("virk:DataObjectSignatureCollection");
        dataContainer.addChild(dataObjectSignatureCollection);
        // generate something like:
        // <ds:Signature Id="_802987514" xmlns:ds="http://www.w3.org/2000/09/xmldsig#" xmlns="http://www.w3.org/2000/09/xmldsig#" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.w3.org/2000/09/xmldsig#
        // http://www.w3.org/TR/2002/REC-xmldsig-core-20020212/xmldsig-core-schema.xsd">

        XMLElement dsSignature = new XMLElement();
        dsSignature.setName("ds:Signature");
        dsSignature.setAttribute("xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
        dsSignature.setAttribute("Id", generateElementId());
        dataObjectSignatureCollection.addChild(dsSignature);

        XMLElement dsSignedInfo = getSignedInfo(certificateHandler, signtext, signTextXML, signatureAlgorithm.getHandler(certificateHandler));

        // We need this for the canonalization. It is removed after signing.
        dsSignedInfo.setAttribute("xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
        dsSignature.addChild(dsSignedInfo);

        Canonical canonicalizer = new Canonical();
        canonicalizer.canonicalize(dsSignedInfo);
        XMLElement dsSignatureValue = getSignatureValue(certificateHandler, canonicalizer.getCanonicalXML(), signatureAlgorithm);
        //XMLElement dsSignatureValue = getSignatureValue(certificateHandler, password, signtxt);
        dsSignature.addChild(dsSignatureValue);

        dsSignedInfo.removeAttribute("xmlns:ds");

        // XMLElement keyInfo = getKeyInfoXML(certificateHandler, password);
        XMLElement keyInfo = new XMLElement();
        keyInfo.setName("ds:KeyInfo");
        dsSignature.addChild(keyInfo);

        // keyInfo.addChild(getX509DataXML(certificateHandler, password));
        XMLElement x509Data = new XMLElement();
        x509Data.setName("ds:X509Data");
        keyInfo.addChild(x509Data);

        // Encoded cert.
        x509Data.addChild(getX509Certificate(certificateHandler.getCertificateChain()[0]));

        // Subject.
        XMLElement x509SubjectName = new XMLElement();
        x509SubjectName.setName("ds:X509SubjectName");
        x509SubjectName.setContent(certificateHandler.getSubjectDN().getName());
        x509Data.addChild(x509SubjectName);

        // Issuer.
        XMLElement x509IssuerSerial = new XMLElement();
        x509IssuerSerial.setName("ds:X509IssuerSerial");
        x509Data.addChild(x509IssuerSerial);

        XMLElement x509IssuerName = new XMLElement();
        x509IssuerName.setName("ds:X509IssuerName");
        String x509IssuerInfo = certificateHandler.getIssuerDN().getName();
        x509IssuerName.setContent(x509IssuerInfo);
        x509IssuerSerial.addChild(x509IssuerName);

        XMLElement x509IssuerSerialNumber = new XMLElement();
        x509IssuerSerialNumber.setName("ds:X509SerialNumber");
        String x509IssuerSer = certificateHandler.getSerialNumber().toString();
        x509IssuerSerialNumber.setContent(x509IssuerSer);
        x509IssuerSerial.addChild(x509IssuerSerialNumber);


        String prolog = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";
        xmlOutput = prolog + xmlout.toString();
        return xmlOutput;
    }

    /**
     * Get DataObjectCollection from inputXML
     *
     * @return XMLElement
     */
    private XMLElement getDataObjectCollection(XMLElement inputXML) {
        XMLElement dataObjCollection = null;

        XMLElement firstChild = getNode(inputXML, "virk:DataContainer");
        XMLElement secondChild = getNode(firstChild, "virk:DataObjectCollection");
        dataObjCollection = secondChild;

        return dataObjCollection;

    }

    /**
     * Get SignedInfo section of xmldsig
     *
     * @param certificateHandler
     * @param signText
     * @param signatureAlgorithmHandler
     * @return XMLElement
     * @throws java.io.UnsupportedEncodingException
     *
     */
    private XMLElement getSignedInfo(CertificateHandler certificateHandler, SignText signText, XMLElement signTextXML, SignatureAlgorithmHandler signatureAlgorithmHandler) throws IOException, GeneralSecurityException, UserCancel {
        XMLElement signedInfo = new XMLElement();
        signedInfo.setName("ds:SignedInfo");

        XMLElement canon = new XMLElement();
        canon.setName("ds:CanonicalizationMethod");
        canon.setAttribute("Algorithm", "http://www.w3.org/2001/10/xml-exc-c14n#");
        signedInfo.addChild(canon);

        XMLElement sigMethod = new XMLElement();
        sigMethod.setName("ds:SignatureMethod");
        sigMethod.setAttribute("Algorithm", signatureAlgorithmHandler.getDigSigAlgorithm());
        signedInfo.addChild(sigMethod);

        XMLElement attachmentElement = getSignTextAttachment(signTextXML);

        // We need to help the canonicalizer a litte with the namespaces.
        boolean addedVirkPrefix = false;
        if (attachmentElement.getAttribute("xmlns:virk") == null) {
            attachmentElement.setAttribute("xmlns:virk", "http://rep.oio.dk/virk.dk/xml/schemas/2002/06/28/");
            addedVirkPrefix = true;
        }

        //TODO this section must be corrected to take the correct URI value
        XMLElement reference1 = new XMLElement();
        reference1.setName("ds:Reference");
        reference1.setAttribute("URI", "#" + attachmentElement.getAttribute("Id"));

        signedInfo.addChild(reference1);

        XMLElement transforms = new XMLElement();
        transforms.setName("ds:Transforms");
        reference1.addChild(transforms);

        XMLElement transformAlgo = new XMLElement();
        transformAlgo.setName("ds:Transform");
        transformAlgo.setAttribute("Algorithm", "http://www.w3.org/2001/10/xml-exc-c14n#");
        transforms.addChild(transformAlgo);

        XMLElement digestMethod = new XMLElement();
        digestMethod.setName("ds:DigestMethod");
        digestMethod.setAttribute("Algorithm", signatureAlgorithmHandler.getDigestAlgorithm());
        reference1.addChild(digestMethod);

        XMLElement digestValue = new XMLElement();
        digestValue.setName("ds:DigestValue");

//				XMLElement signTextDataObject = getDataObjectCollection();
        Canonical canonicalizer = new Canonical();
        canonicalizer.canonicalize(attachmentElement);
        FileLog.debug("Canonicalize of digestValue ");
        FileLog.debug(canonicalizer.getCanonicalXML());
        digestValue.setContent(getDigestValue(certificateHandler, canonicalizer.getCanonicalXML(), signatureAlgorithmHandler));
        reference1.addChild(digestValue);

        if (addedVirkPrefix)
            attachmentElement.removeAttribute("xmlns:virk");

        return signedInfo;
    }

    /**
     * Get Digest from signtext, coded as BASE64
     *
     * @param signText
     * @return String
     * @throws java.security.NoSuchProviderException
     *
     * @throws java.security.NoSuchAlgorithmException
     *
     * @throws java.io.UnsupportedEncodingException
     *
     */
    private String getDigestValue(CertificateHandler certificateHandler, String signText, SignatureAlgorithmHandler signatureAlgorithmHandler) throws IOException, GeneralSecurityException, UserCancel {
        byte[] data = signText.getBytes("UTF8");
        return new String(Base64.encode(certificateHandler.digest(data, signatureAlgorithmHandler)));
    }

    private String generateElementId() {
        return "_" + Integer.toString((int) Math.ceil(Math.random() * 1000000000));
    }

    private XMLElement getSignTextAttachment(XMLElement parent) {
        return getNodeWithAttribute(parent, "virk:DataObject", "fileContentType", "SignText");
    }


    private XMLElement getNode(XMLElement node, String xmlTag) {
        XMLElement lNode = null;

        Iterator<XMLElement> enumeration = node.enumerateChildren();
        while (enumeration.hasNext()) {
            XMLElement child = enumeration.next();
            if (child.getName().compareTo(xmlTag) == 0) {
                lNode = child;
                break;
            }
        }
        return lNode;
    }

    private XMLElement getNodeWithAttribute(XMLElement node, String xmlTag, String Attribute, String AttriVal) {
        XMLElement lNode = null;

        Iterator<XMLElement> enumeration = node.enumerateChildren();
        while (enumeration.hasNext()) {
            XMLElement child = enumeration.next();

            if ((child.getName().equals(xmlTag)) && (getNodeAttribute(child, Attribute))) {
                if ((child.getAttribute(Attribute)).equals(AttriVal)) {
                    lNode = child;
                    break;
                }
            }
        }

        return lNode;
    }

    private boolean getNodeAttribute(XMLElement node, String Attribute) {
        String attributeString = null;

        boolean attributeFound = false;


        Iterator<String> enumeration = node.enumerateAttributeNames();
        while (enumeration.hasNext()) {

            attributeString = enumeration.next();

            if (attributeString.equalsIgnoreCase(Attribute)) {
                attributeFound = true;
                break;
            } else {
                attributeString = null;
            }
        }

        return attributeFound;
    }

    private XMLElement getX509Certificate(byte[] cert) {
        XMLElement x509Cert = new XMLElement();
        x509Cert.setName("ds:X509Certificate");
        x509Cert.setContent(BasicSignatureGenerator.wrapLines(new String(Base64.encode(cert)), BASE64_LINELENGTH).toString());

        return x509Cert;
    }

    /**
     * Get SignatureValue section of xmldsig
     *
     * @param signedInfo
     * @return String
     * @throws java.security.InvalidKeyException
     *
     * @throws java.security.SignatureException
     *
     * @throws java.security.NoSuchAlgorithmException
     *
     * @throws java.io.UnsupportedEncodingException
     *
     * @throws java.security.NoSuchProviderException
     *
     */
    private XMLElement getSignatureValue(CertificateHandler certificateHandler, String signedInfo, SignatureAlgorithmFactory signatureAlgorithm) throws IOException, GeneralSecurityException, UserCancel {

        byte[] data = signedInfo.getBytes("UTF8");

        XMLElement dsSignatureValue = new XMLElement();
        dsSignatureValue.setName("ds:SignatureValue");
        dsSignatureValue.setContent(BasicSignatureGenerator.wrapLines(new String(Base64.encode(certificateHandler.sign(data, signatureAlgorithm))), BASE64_LINELENGTH).toString());
        return dsSignatureValue;
    }
}