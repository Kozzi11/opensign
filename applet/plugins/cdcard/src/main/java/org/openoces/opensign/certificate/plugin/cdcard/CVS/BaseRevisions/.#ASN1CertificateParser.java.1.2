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

 * @author Mads Jensen <mjn@trifork.com>
 * @author Jeppe Burchhardt <Jeppe.Burchhardt@Cryptomathic.com>
 * @author Ole Friis Østergaard <ofo@trifork.com>
 */

/* $Id: ASN1CertificateParser.java,v 1.2 2012/02/28 08:21:36 pakj Exp $ */

package org.openoces.opensign.certificate.plugin.cdcard;

/**
 * This class represents implements an ASN.1 parser that can be used to parse X.509 certificates
 *
 * @author Preben Valeur  <prv@tdc.dk>
 */

import org.openoces.opensign.certificate.CertificateInfo;
import org.openoces.opensign.certificate.StringPrincipal;
import org.openoces.opensign.certificate.plugin.cdcard.util.ASN1;
import org.openoces.opensign.certificate.plugin.cdcard.util.ASN1Listener;
import org.openoces.opensign.certificate.x509.KeyUsage;
import org.openoces.opensign.exceptions.UserCancel;
import org.openoces.opensign.utils.FileLog;

import java.math.BigInteger;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class ASN1CertificateParser implements ASN1Listener, CertificateInfo {
	
	ASN1CertificateParser(String b64ClientCert) {
		ASN1.parseb64(b64ClientCert, this);
	}

	// parsing state
	private int level = 0;
	private int interestingLevel = 2;
	private int attributeNumber = -1;
	private String attribValue = "";
	private static final String SERIAL_NUMBER_OID = "2.5.4.5";
	private static final String COMMON_NAME_OID = "2.5.4.3";
	private static final String ORGANIZATION_NAME_OID = "2.5.4.10";
	private static final String COUNTRY_NAME_OID = "2.5.4.6";
	private static final String ORGANIZATIONAL_UNIT_NAME_OID = "2.5.4.11";
	private static Map<String, String> oidToName = new HashMap<String, String>();
	static {
		oidToName.put(SERIAL_NUMBER_OID, "SN");
		oidToName.put(COMMON_NAME_OID, "CN");
		oidToName.put(ORGANIZATION_NAME_OID, "O");
		oidToName.put(COUNTRY_NAME_OID, "C");
		oidToName.put(ORGANIZATIONAL_UNIT_NAME_OID, "OU");
	}
	private String userFriendlyName;
	private Principal subjectDN;
	private Principal issuerDN;
	private BigInteger serialNumber;
	private Date notBefore;
	private Date notAfter;
	private int version;

    public char[] promptForPassword(char[] password) throws UserCancel {
        return password;
    }

    public boolean isInfoAvailable() {
		return true;
	}

	public String getUserFriendlyName() {
		return userFriendlyName;
	}

	public Principal getSubjectDN() {
		return subjectDN;
	}

	public Principal getIssuerDN() {
		return issuerDN;
	}

	public BigInteger getSerialNumber() {
		return serialNumber;
	}

	public Date getNotBefore() {
		return notBefore;
	}

	public Date getNotAfter() {
		return notAfter;
	}

	public int getVersion() {
		return version;
	}

	private void setAttribute(int attrib, String value) {
		switch (attrib) {
		case 0: // VERSION
			version = Integer.parseInt(value.trim(), 16) + 1;
			break;
		case 1:// serialnumber
			serialNumber = new BigInteger(value.trim(), 16);
			break;
		case 2:// signature
			break;
		case 3: {// issuer
			StringTokenizer strtok = new StringTokenizer(value, "'");
			StringBuffer buf = new StringBuffer();
			boolean first = true;
			while (strtok.hasMoreTokens()) {
				String oid = strtok.nextToken().trim();
				if (!strtok.hasMoreTokens()) {
					break;
				}
				if (!first) {
					buf.append(", ");
				} else {
					first = false;
				}
				buf.append(oidToName.get(oid));
				buf.append("=");
				String val = strtok.nextToken();
				buf.append(val);
			}
			issuerDN = new StringPrincipal(buf.toString());
		}
			break;
		case 4:// validity
			// todo: get and parse the dates out of value - two strings in this
			// form:
			// '040322150611Z' '060322153611Z'
			// for 22 mar 2004 at 15:06:11 Zulu
			SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
			StringTokenizer tok = new StringTokenizer(value, "'");
			try {
				// assume local timezone...(skip last char)
				String dateString = tok.nextToken();
				notBefore = format.parse(dateString.substring(0, dateString
						.length() - 1));
				tok.nextToken(); // space between
				dateString = tok.nextToken();
				notAfter = format.parse(dateString.substring(0, dateString
						.length() - 1));
			} catch (ParseException e) {
                FileLog.error(e.getMessage(), e);
			}
			break;
		case 5: {// subject
			StringTokenizer strtok = new StringTokenizer(value, "'");
			StringBuffer buf = new StringBuffer();
			boolean first = true;
			while (strtok.hasMoreTokens()) {
				String oid = strtok.nextToken().trim();
				if (!strtok.hasMoreTokens()) {
					break;
				}
				if (!first) {
					buf.append(", ");
				} else {
					first = false;
				}
				buf.append(oidToName.get(oid));
				buf.append("=");
				String val = strtok.nextToken();
				if (oid.equals(COMMON_NAME_OID)) {
					userFriendlyName = val;
				}
				buf.append(val);
			}
			subjectDN = new StringPrincipal(buf.toString());
		}
			break;

		}
	}

	public void tag(String tagName, String tagValue) {
		// To change body of implemented methods use File | Settings | File
		// Templates.
		if (level == interestingLevel) {
			attributeNumber++;
		}
		attribValue += tagValue + " ";
		// System.out.println(tab.substring(0,
		// 4*level)+ATTRIBUTES[attributeNumber]+" "+tagName+":"+tagValue);
		if (level == interestingLevel) {
			// System.out.println(">>> done collecting info for "+ATTRIBUTES[attributeNumber]+" :"+attribValue);
			setAttribute(attributeNumber, attribValue);
			attribValue = "";
		}
	}

	public void beginTag(String tagName) {
		// To change body of implemented methods use File | Settings | File
		// Templates.
		if (level == interestingLevel) {
			attributeNumber++;
		}
		// System.out.println(tab.substring(0, 4*level)+"begin "+tagName);
		level++;
	}

	public void endTag(String tagName) {
		// To change body of implemented methods use File | Settings | File
		// Templates.
		level--;
		if (level == interestingLevel) {
			// System.out.println(">>> done collecting info for "+ATTRIBUTES[attributeNumber]+" :"+attribValue);
			setAttribute(attributeNumber, attribValue);
			attribValue = "";
		}
		// System.out.println(tab.substring(0, 4*level)+"end  "+tagName);
	}

	public void error(String errorMsg) {
		// System.out.println(tab.substring(0, 4*level)+"error: "+errorMsg);

	}

	public String getKeyUsage() {
		return null; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	public boolean canSign()  {
		return false; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	public KeyUsage getIntendedKeyUsage()  {
		return null; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	public byte[] getCertificate() {
		return new byte[0]; // To change body of implemented methods use File |
							// Settings | File Templates.
	}
}
