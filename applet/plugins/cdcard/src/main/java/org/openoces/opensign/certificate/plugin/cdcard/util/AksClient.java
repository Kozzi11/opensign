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

/* $Id: AksClient.java,v 1.2 2012/02/28 08:21:58 pakj Exp $ */

package org.openoces.opensign.certificate.plugin.cdcard.util;

/**
 * This class represents the AKS client
 *
 * @author Preben Valeur  <prv@tdc.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 */

import org.openoces.opensign.client.applet.OS;
import org.openoces.opensign.crypto.SSLResponse;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.utils.Version;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AksClient {
	private static final String AKS_SERVER_STUB = "http://localhost:8080/aksstub/AksStub";
	private static final String AKS_SERVER_DEV = "https://udv.aks.certifikat.dk/aks/AKS";
	private static final String AKS_SERVER_TEST = "https://test.aks.certifikat.dk/aks/AKS";
	private static final String AKS_SERVER_PROD = "https://aks.certifikat.dk/aks/AKS";
	private static String AKS_SERVER = AKS_SERVER_TEST;
	public static final int ENV_STUB = 0;
	public static final int ENV_DEV = 1;
	public static final int ENV_TEST = 2;
	public static final int ENV_PROD = 3;
	public static final int PIN_CODE_LENGTH = 4;

	public static void setEnv(int env) {
		switch (env) {
		case ENV_STUB:
			AKS_SERVER = AKS_SERVER_STUB;
			break;
		case ENV_DEV:
			AKS_SERVER = AKS_SERVER_DEV;
			break;
		case ENV_TEST:
			AKS_SERVER = AKS_SERVER_TEST;
			break;
		case ENV_PROD:
			AKS_SERVER = AKS_SERVER_PROD;
			break;
		default:
			break;
		}
	}

	public static boolean isValidPinCode(String pinCode) {
		if (pinCode.length() != AksClient.PIN_CODE_LENGTH) {
			return false;
		}
		for (int i = 0; i < pinCode.length(); i++) {
			if (!Character.isDigit(pinCode.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	private static AksClient instance;

	public static AksClient getInstance() {
		if (instance == null) {
			instance = new AksClient();
		}
		return instance;
	}

	public final AksResponse getPassword(String nid, String pin, String service)
			throws Exception {
		return privGetPassword(nid, pin, null, service, null);
	}

	public final AksResponse getPassword(String nid, String pin, String cpr,
			String service) throws Exception {
		return privGetPassword(nid, pin, cpr, service, null);
	}

	public final AksResponse getPasswordAndChangePin(String nid, String oldPin,
			String newPin, String cpr, String service) throws Exception {
		return privGetPassword(nid, newPin, cpr, service, oldPin);
	}

	public final AksResponse privGetPassword(String nid, String pin, String cpr,
			String service, String oldPin) throws Exception {
		Map<String, String> ht = new HashMap<String, String>();
		if (oldPin != null) {
			ht.put("pin", oldPin);
			ht.put("newpin", pin);
		} else {
			ht.put("pin", pin);
		}
		ht.put("nid", nid);
		if (service != null) {
			ht.put("service", service);
		}
		if (cpr != null) {
			ht.put("cpr", cpr);
		}
		ht.put("clientid", "opensign " + Version.getVersion());
		String response = post(ht);
		return new AksResponse(response);
	}

	private String post(Map<String, String> args) throws IOException {
		SSLResponse response = OS.getCryptoSupport().httpsPost(
				new URL(AKS_SERVER), args);
		if (response == null) {
			throw new IOException("Error posting to AKS");
		}
		return response.toString();
	}
}
