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

/* $Id: BasicTest.java,v 1.4 2012/09/27 11:03:51 pakj Exp $ */

package org.openoces.opensign.test.wrappers.microsoftcryptoapi;

import org.junit.Ignore;
import org.openoces.opensign.certificate.plugin.capi.MicrosoftWindowsCertificateHandler;
import org.openoces.opensign.utils.HexDump;
import org.openoces.opensign.wrappers.microsoftcryptoapi.MicrosoftCryptoApi;

import java.io.ByteArrayInputStream;

/**
 * This class implements tests of the capi wrapper.
 *
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 * @author Mads Jensen <mjn@trifork.com>
 * @author Jeppe Burchhardt <Jeppe.Burchhardt@Cryptomathic.com>
 * @author Ole Friis Østergaard <ofo@trifork.com>
 */

@Ignore
public class BasicTest {

    private MicrosoftCryptoApi mscapi;
    private byte[] toBeSigned = {'o', 'p', 'e', 'n', 'o', 'c', 'e', 's'};

    private void signTest(byte[] certificate) {

        byte[] signature = mscapi.signMessage(toBeSigned, certificate, "sha1");
        HexDump.xdump(signature);
    }

    public void certificateListingTest() {

        byte[][][] rs = mscapi.getCertificatesInSystemStore("My");
        if (rs != null) {
            System.out.println("Certificates returned: " + rs.length);
            for (int i = 0; i < rs.length; i++) {

                try {
                    ByteArrayInputStream bIn;

                    bIn = new ByteArrayInputStream(rs[i][0]);

                    System.out.println("Certificate size: " + rs[i].length);
                    javax.security.cert.X509Certificate c = javax.security.cert.X509Certificate.getInstance(bIn);
                    System.out.println("Certificate version: " + c.getVersion());
                    System.out.println("Certificate serial: " + c.getSerialNumber());
                    System.out.println("Certificate subject dn: " + c.getSubjectDN());
                    System.out.println("Certificate issuer dn: " + c.getIssuerDN());
                    System.out.println("Certificate notbefore date: " + c.getNotBefore());
                    System.out.println("Certificate notafter date: " + c.getNotAfter());
                    System.out.println("Certificate key usage: ");
                    // todo: insert again somehow
//                    KeyUsage usage = mscapi.getIntendedKeyUsage(rs[i]);
//                    System.out.println("  digital signature: "+usage.includesDigitalSignature());
//                    System.out.println("  non repudiation: "+usage.includesNonRepudiation());
//                    System.out.println("  key encipherment: "+usage.includesKeyEncipherment());
//                    System.out.println("  data encipherment: "+usage.includesDataEncipherment());
//                    System.out.println("  key agreement: "+usage.includesKeyAgreement());
//                    System.out.println("  certificate signing: "+usage.includesKeyCertSign());
//                    System.out.println("  crl signing: "+usage.includesKeyCRLSign());
//					System.out.println("Certificate information:");
//					System.out.println(c.toString());

                } catch (javax.security.cert.CertificateException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Null returned from getCertificatesInSystemStore");
        }
    }

    private void setup() {
//		if (!(new CapiInitializer().install(CapiInitializer.OS_WIN32))) {
//			System.out.println("Unable to install the Microsoft CryptoAPI wrapper dll. Exiting ..");
//			System.exit(-1);
//		} else {
//			System.out.println("Installed the Microsoft CryptoAPI wrapper dll.");
//		}

        mscapi = new MicrosoftCryptoApi();
    }

    private void digestTest() {
//		try {
        byte[] digestValueCAPI = mscapi.digest(toBeSigned, "SHA1");
//			byte[] digestValueJCE = JCETools.sha1(toBeSigned);

        System.out.println("Last error code: " + mscapi.getLastErrorCode());

        System.out.println("Digest value created using MS CAPI:");
        System.out.println(HexDump.xdump(digestValueCAPI));
        System.out.println("Digest value created using JCE provider:");
//			System.out.println(HexDump.xdump(digestValueJCE));

//		} catch (NoSuchProviderException e) {
//			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}

    }

    /**
     * private void pkcs12SignTest() {
     * try {
     * System.out.println("PKCS12 test");
     * Pkcs12CertificateHandler h = new Pkcs12CertificateHandler(new File("c:\\oces.pfx"), "Nik33!____".toCharArray());
     * System.out.println(h.getSubjectDN());
     * //			byte[] signature = JCETools.sign(toBeSigned, h.getPrivateKey("Nik33!____".toCharArray()));
     * <p/>
     * //			HexDump.xdump(signature);
     * System.out.println();
     * <p/>
     * } catch (KeyStoreException e) {
     * e.printStackTrace();
     * } catch (IOException e) {
     * e.printStackTrace();
     * } catch (NoSuchAlgorithmException e) {
     * e.printStackTrace();
     * } catch (CertificateException e) {
     * e.printStackTrace();
     * } catch (NoSuchProviderException e) {
     * e.printStackTrace();
     * } catch (UnrecoverableKeyException e) {
     * e.printStackTrace();
     * } catch (InvalidKeyException e) {
     * e.printStackTrace();
     * } catch (SignatureException e) {
     * e.printStackTrace();
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * }
     */

    private void capiSignTest() {
        byte[][][] rs = mscapi.getCertificatesInSystemStore("My");

        if (rs != null) {
            for (int i = 0; i < rs.length; i++) {

                try {
                    // todo: make next line smarter
                    MicrosoftWindowsCertificateHandler h = new MicrosoftWindowsCertificateHandler(rs[i], new MicrosoftCryptoApi());
                    HexDump.xdump(h.sign(toBeSigned, null));
                    System.out.println();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Null returned from getCertificatesInSystemStore");
        }
    }

    public static void main(String args[]) {
        BasicTest t = new BasicTest();
        t.setup();
        t.certificateListingTest();
        //t.pkcs12SignTest();
        t.capiSignTest();
        t.digestTest();
    }
}