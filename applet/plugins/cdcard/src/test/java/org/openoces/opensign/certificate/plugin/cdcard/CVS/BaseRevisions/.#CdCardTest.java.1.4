/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard
    Copyright 2006 Paw F. Kjeldgaard


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

/* $Id: CdCardTest.java,v 1.4 2012/09/27 11:03:53 pakj Exp $ */

package org.openoces.opensign.certificate.plugin.cdcard;

/**
 * This class is used to test CdCard functionality
 *
 * @author Preben Valeur  <prv@tdc.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 * @author Mads Jensen <mjn@trifork.com>
 * @author Jeppe Burchhardt <Jeppe.Burchhardt@Cryptomathic.com>
 * @author Ole Friis �stergaard <ofo@trifork.com>
 */

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.certificate.CertificateInfo;
import org.openoces.opensign.certificate.plugin.cdcard.util.AksClient;
import org.openoces.opensign.certificate.plugin.cdcard.util.AksResponse;
import org.openoces.opensign.certificate.support.suncrypto.SunCryptoSupport;
import org.openoces.opensign.certificate.x509.KeyStoreHandler;
import org.openoces.opensign.client.applet.OS;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.crypto.CryptoSupport;
import org.openoces.opensign.utils.HexDump;

import java.io.IOException;
import java.util.Hashtable;

public class CdCardTest {
    /**
     * ********************************** test program **************************
     */
    private static Hashtable passwords = new Hashtable();

    private static void testSupport(CdCardCertificateHandler cert, CryptoSupport support, String pw) throws Exception {

        System.out.println("*********** Begin testing support with " + support.getClass().getName());
        byte[] toBeSigned = "Aftaletekst".getBytes();

        char[] realPassword = pw.toCharArray();
        OS.setCryptoSupport(support);
        byte[] res = cert.realSign(toBeSigned, realPassword, "SHA256withRSA");
        System.out.println("Received signed bytes of length: " + res.length);
//        cert.testGetPrivateKey();

        byte[] digestValue = OS.getCryptoSupport().sha1(toBeSigned);

        System.out.println("Digest value :");
        System.out.println(HexDump.xdump(digestValue));

        String b64clientCert = (String) cert.props.get(CdCardCertificateHandler.CLIENT_CERT_PROP);
        CertificateInfo x509info = OS.getCryptoSupport().getCertInfo(b64clientCert);
        System.out.println("x509info : " + x509info);
        System.out.println("Not after " + x509info.getNotAfter());

        System.out.println("*********** End testing support with " + support.getClass().getName());
    }

    public static void aksClientTest() {
        AksClient client = AksClient.getInstance();
        String nid = "10ff9527bccf2d8c22d99200f224a4ef";
        String service = "TDC Self Service";
        try {
            AksResponse response = client.getPassword(nid, "0", service);
            System.out.println("Response: " + response);
            response = client.getPassword(nid, "1", service);
            System.out.println("Response: " + response);
            response = client.getPassword(nid, "2", service);
            System.out.println("Response: " + response);
            response = client.getPassword(nid, "3", service);
            System.out.println("Response: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cryptoSupportTest() throws IOException {
//        passwords.put("Testperson 0102560507","ae9e17e17417cda989c98ad659167e7349c14b201ab0f7509b2b6209b2470e52");
        passwords.put("Testperson 0102560507", "f74eb7f7043d4921ef5f9192a8ee2179623c103b20084922bbeabf07d2f6227c");
        CryptoSupport sun = new SunCryptoSupport();
        KeyStoreHandler handler = new CdCardKeyStoreHandler(null);
        handler.refreshKeystore();
        CertificateHandler[] certs = handler.getCertificates();
//        CertificateHandler [] certs = CdCardCertificateHandler.scanForCerts();

        FileLog.debug("cert count: " + certs.length);

        for (CertificateHandler abstractCertificateHandler : certs) {
            try {
                testSupport((CdCardCertificateHandler) abstractCertificateHandler, sun, (String) passwords.get(abstractCertificateHandler.getUserFriendlyName()));
                FileLog.debug("********************* SUCCES for SUN cert" + abstractCertificateHandler.getUserFriendlyName());
            } catch (Exception e) {
                FileLog.debug("********************* cert " + abstractCertificateHandler.getUserFriendlyName() + " failed: " + e.getMessage());
            }
        }

    }

    public static void main(String[] args) throws Exception {
        // select the one you wanna test
//        aksClientTest();
        cryptoSupportTest();
    }
}