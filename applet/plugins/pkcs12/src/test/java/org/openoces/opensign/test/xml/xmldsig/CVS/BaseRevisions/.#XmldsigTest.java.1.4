package org.openoces.opensign.test.xml.xmldsig;

import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.utils.IgnoreAllErrorHandler;
import org.apache.xml.security.utils.XMLUtils;
import org.apache.xpath.XPathAPI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openoces.opensign.appletsupport.Attachment;
import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.certificate.plugin.pkcs12.Pkcs12CertificateHandler;
import org.openoces.opensign.certificate.support.suncrypto.SunCryptoSupport;
import org.openoces.opensign.client.applet.OS;
import org.openoces.opensign.client.applet.dialogs.listeners.SignText;
import org.openoces.opensign.client.applet.dialogs.listeners.StringSignText;
import org.openoces.opensign.crypto.DefaultSignatureAlgorithm;
import org.openoces.opensign.crypto.SignatureAlgorithmFactory;
import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.xml.xmldsig.BasicSignatureGenerator;
import org.openoces.opensign.xml.xmldsig.SignatureGenerator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Properties;

public class XmldsigTest {
	CertificateHandler certHandler;

    @Before
	public void setUp() throws Exception {
		OS.setCryptoSupport((new SunCryptoSupport()));
		URL url = this.getClass().getResource("/test.p12");
		File signerp12 = new File(url.getFile());
		certHandler = new Pkcs12CertificateHandler(signerp12,"Test1234".toCharArray());
		certHandler.isInfoAvailable();
		System.out.println("Found signerCertificate - info available " + certHandler.isInfoAvailable());
	}


	private Document createObject(String s) throws Exception {
		Document doc = null;

		org.apache.xml.security.Init.init();
		
		javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();

		dbf.setNamespaceAware(true);
		dbf.setAttribute("http://xml.org/sax/features/namespaces", Boolean.TRUE);

		javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
		db.setErrorHandler(new IgnoreAllErrorHandler());
		doc = db.parse(new ByteArrayInputStream(s.getBytes("UTF-8")));

		return doc;
	}

	@Test
	public void testBasicSignature() throws Exception {
		StringSignText toBeSigned = new StringSignText("OpenOces");
		String propertyName0 = "aproperty";
		String propertyValue0 = "avalue";

		Properties invisibleProperties = new Properties();
		invisibleProperties.setProperty(propertyName0, propertyValue0);

		String visiblePropertyName1 = "visibleProperty";
		String visiblePropertyValue1 = "visibleValue";
		Properties visibleProperties = new Properties();
		visibleProperties.setProperty(visiblePropertyName1, visiblePropertyValue1);

		SignatureGenerator sigGen = new BasicSignatureGenerator();

		SignatureAlgorithmFactory signatureAlgorithm = new DefaultSignatureAlgorithm();
		String xml = sigGen.sign(certHandler,toBeSigned, visibleProperties, invisibleProperties, null, signatureAlgorithm);
		System.out.println(xml);

		Document doc = this.createObject(xml);
		Element nscontext = XMLUtils.createElementInSignatureSpace(doc,"ds");

		Element sigElement = (Element) XPathAPI.selectSingleNode(doc,"//ds:Signature[1]", nscontext);
		XMLSignature signature = new XMLSignature(sigElement, null);
		X509Certificate cert = signature.getKeyInfo().getX509Certificate();
		
		Assert.assertTrue(signature.checkSignatureValue(cert));
		
		Element canonicalizationMethodElement = (Element) XPathAPI.selectSingleNode(doc,"//ds:CanonicalizationMethod", nscontext);
        Assert.assertNotNull(canonicalizationMethodElement);
        Assert.assertEquals("http://www.w3.org/TR/2001/REC-xml-c14n-20010315", canonicalizationMethodElement.getAttribute("Algorithm"));

		Element signatureMethodElement = (Element) XPathAPI.selectSingleNode(doc,"//ds:SignatureMethod", nscontext);
        Assert.assertNotNull(signatureMethodElement);
        Assert.assertEquals("http://www.w3.org/2000/09/xmldsig#rsa-sha1", signatureMethodElement.getAttribute("Algorithm"));

		Element signtextSignatureProperty = (Element) XPathAPI.selectSingleNode(doc,"//openoces:Name[.='signtext']");
        Assert.assertNotNull(signtextSignatureProperty);
		Element signtextSignaturePropertyValue = (Element) XPathAPI.selectSingleNode(doc,"//openoces:Name[.='signtext']/../openoces:Value");
        Assert.assertEquals("base64", signtextSignaturePropertyValue.getAttribute("Encoding"));
        Assert.assertEquals("yes", signtextSignaturePropertyValue.getAttribute("VisibleToSigner"));
		String signTextInDoc = new String(Base64.decode(signtextSignaturePropertyValue.getFirstChild().getNodeValue().getBytes()));
        Assert.assertEquals(signTextInDoc, toBeSigned.getSignText());
		
		Element invisibleSignatureProperty = (Element) XPathAPI.selectSingleNode(doc,"//openoces:Name[.='aproperty']");
        Assert.assertNotNull(invisibleSignatureProperty);
		Element invisibleSignaturePropertyValue = (Element) XPathAPI.selectSingleNode(doc,"//openoces:Name[.='aproperty']/../openoces:Value");
        Assert.assertEquals("base64", invisibleSignaturePropertyValue.getAttribute("Encoding"));
        Assert.assertEquals("no", invisibleSignaturePropertyValue.getAttribute("VisibleToSigner"));
		String signedValue = new String(Base64.decode(invisibleSignaturePropertyValue.getFirstChild().getNodeValue().getBytes()));
        Assert.assertEquals(signedValue, propertyValue0);
		
		Element visibleSignatureProperty = (Element) XPathAPI.selectSingleNode(doc,"//openoces:Name[.='visibleProperty']");
        Assert.assertNotNull(visibleSignatureProperty);
		Element visibleSignaturePropertyValue = (Element) XPathAPI.selectSingleNode(doc,"//openoces:Name[.='visibleProperty']/../openoces:Value");
        Assert.assertEquals("base64", visibleSignaturePropertyValue.getAttribute("Encoding"));
        Assert.assertEquals("yes", visibleSignaturePropertyValue.getAttribute("VisibleToSigner"));
		signedValue = new String(Base64.decode(visibleSignaturePropertyValue.getFirstChild().getNodeValue().getBytes()));
        Assert.assertEquals(signedValue, visiblePropertyValue1);
		
		Element actionSignatureProperty = (Element) XPathAPI.selectSingleNode(doc,"//openoces:Name[.='action']");
        Assert.assertNotNull(actionSignatureProperty);
		Element actionPropertyValue = (Element) XPathAPI.selectSingleNode(doc,"//openoces:Name[.='action']/../openoces:Value");
        Assert.assertEquals("base64", actionPropertyValue.getAttribute("Encoding"));
        Assert.assertEquals("no", actionPropertyValue.getAttribute("VisibleToSigner"));
		signedValue = new String(Base64.decode(actionPropertyValue.getFirstChild().getNodeValue().getBytes()));
        Assert.assertEquals(signedValue, "sign");
		
	}

    @Test
	public void testPrimaryAttachmentSignature() throws Exception {
		SignText toBeSigned = new StringSignText("OpenOces");

		Properties invisibleProperties = new Properties();
		Properties visibleProperties = new Properties();
		
		TestAttachmentImpl pdfAttachmentImpl = new TestAttachmentImpl("test.pdf");
		//TestAttachmentImpl pdfAttachmentImpl = new TestAttachmentImpl("Stor_PDF_Eksempel1.pdf");
		
		pdfAttachmentImpl.setPrimary(true);
		Attachment[] signedAttachments = new Attachment[] {pdfAttachmentImpl};
		
		SignatureGenerator sigGen = new BasicSignatureGenerator();

		SignatureAlgorithmFactory signatureAlgorithm = new DefaultSignatureAlgorithm();
		String xml = sigGen.sign(certHandler,toBeSigned, visibleProperties, invisibleProperties, signedAttachments, signatureAlgorithm);
		System.out.println("XMLDsig length " + xml.length());
		//System.out.println("XMLDsig " + s);
		Document doc = this.createObject(xml);
		Element nscontext = XMLUtils.createElementInSignatureSpace(doc,"ds");

		Element sigElement = (Element) XPathAPI.selectSingleNode(doc,"//ds:Signature[1]", nscontext);
		XMLSignature signature = new XMLSignature(sigElement, null);
		X509Certificate cert = signature.getKeyInfo().getX509Certificate();

        Assert.assertTrue(signature.checkSignatureValue(cert));
		
		Element signTextElement = (Element) XPathAPI.selectSingleNode(doc,"//ds:Object[@Id='signText']", nscontext);
        Assert.assertNotNull(signTextElement);
        Assert.assertEquals("base64", signTextElement.getAttribute("Encoding"));
        Assert.assertEquals("text/plain", signTextElement.getAttribute("MimeType"));
        Assert.assertEquals("http://www.w3.org/2000/09/xmldsig#", signTextElement.getAttribute("xmlns:ds"));
        Assert.assertEquals("http://www.openoces.org/2006/07/signature#", signTextElement.getAttribute("xmlns:openoces"));
		
		String signTextContentBase64 = signTextElement.getChildNodes().item(0).getNodeValue();
		signTextContentBase64 = signTextContentBase64.replaceAll("\\n", "");
		byte[] signTextContent = Base64.decode(signTextContentBase64 .getBytes());
        Assert.assertEquals(signTextContent.length, pdfAttachmentImpl.getContents().length);
		Assert.assertArrayEquals(signTextContent,pdfAttachmentImpl.getContents());
		
	}

    @Test
	public void testAttachmentWithMoreAttachmentsSignature() throws Exception {
		SignText toBeSigned = new StringSignText("OpenOces");

		Properties invisibleProperties = new Properties();
		Properties visibleProperties = new Properties();
		
		TestAttachmentImpl pdfAttachmentImpl = new TestAttachmentImpl("test.pdf");
		pdfAttachmentImpl.setMimetype("application/pdf");
		pdfAttachmentImpl.setPrimary(true);
		
		TestAttachmentImpl gifAttachmentImpl = new TestAttachmentImpl("opensign.gif");
		gifAttachmentImpl.setPrimary(false);
		gifAttachmentImpl.setMimetype("image/gif");
		Attachment[] signedAttachments = new Attachment[] {pdfAttachmentImpl, gifAttachmentImpl};
		
		SignatureGenerator sigGen = new BasicSignatureGenerator();

		SignatureAlgorithmFactory signatureAlgorithm = new DefaultSignatureAlgorithm();
		String xml = sigGen.sign(certHandler,toBeSigned, visibleProperties, invisibleProperties, signedAttachments, signatureAlgorithm);
		System.out.println("XMLDsig " + xml);
		Document doc = this.createObject(xml);
		Element nscontext = XMLUtils.createElementInSignatureSpace(doc,"ds");

		Element sigElement = (Element) XPathAPI.selectSingleNode(doc,"//ds:Signature[1]", nscontext);
		XMLSignature signature = new XMLSignature(sigElement, null);
		X509Certificate cert = signature.getKeyInfo().getX509Certificate();

        Assert.assertTrue(signature.checkSignatureValue(cert));
		
		Element signTextElement = (Element) XPathAPI.selectSingleNode(doc,"//ds:Object[@Id='signText']", nscontext);
        Assert.assertNotNull(signTextElement);
		
		Element nextAttachmentElement = (Element) XPathAPI.selectSingleNode(doc,"//ds:Object[@Id='attachment-1']", nscontext);
        Assert.assertNotNull(nextAttachmentElement);


	}

    @Test
    public void testXmlDsigCompliance() throws Exception {
        StringSignText toBeSigned = new StringSignText("OpenOces");
        String propertyName0 = "aproperty";
        String propertyValue0 = "avalue";

        Properties invisibleProperties = new Properties();
        invisibleProperties.setProperty(propertyName0, propertyValue0);

        String visiblePropertyName1 = "visibleProperty";
        String visiblePropertyValue1 = "visibleValue";
        Properties visibleProperties = new Properties();
        visibleProperties.setProperty(visiblePropertyName1, visiblePropertyValue1);

        SignatureGenerator sigGen = new BasicSignatureGenerator();

        SignatureAlgorithmFactory signatureAlgorithm = new DefaultSignatureAlgorithm();
        String xml = sigGen.sign(certHandler, toBeSigned, visibleProperties, invisibleProperties, null, signatureAlgorithm);

        URL schemaFile = getClass().getResource("/openoces-schema.xsd");
        Source xmlFile = new StreamSource(new ByteArrayInputStream(xml.getBytes()));
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(schemaFile);
        Validator validator = schema.newValidator();
        validator.validate(xmlFile);
    }

    @Test
    public void testXmlDsigComplianceWithAttachments() throws Exception {
        SignText toBeSigned = new StringSignText("OpenOces");

        Properties invisibleProperties = new Properties();
        Properties visibleProperties = new Properties();

        TestAttachmentImpl pdfAttachmentImpl = new TestAttachmentImpl("test.pdf");
        pdfAttachmentImpl.setMimetype("application/pdf");
        pdfAttachmentImpl.setPrimary(true);

        TestAttachmentImpl gifAttachmentImpl = new TestAttachmentImpl("opensign.gif");
        gifAttachmentImpl.setPrimary(false);
        gifAttachmentImpl.setMimetype("image/gif");
        Attachment[] signedAttachments = new Attachment[] {pdfAttachmentImpl, gifAttachmentImpl};

        SignatureGenerator sigGen = new BasicSignatureGenerator();

        SignatureAlgorithmFactory signatureAlgorithm = new DefaultSignatureAlgorithm();
        String xml = sigGen.sign(certHandler,toBeSigned, visibleProperties, invisibleProperties, signedAttachments, signatureAlgorithm);

        URL schemaFile = getClass().getResource("/openoces-schema.xsd");
        Source xmlFile = new StreamSource(new ByteArrayInputStream(xml.getBytes()));
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(schemaFile);
        Validator validator = schema.newValidator();
        validator.validate(xmlFile);
    }



}
