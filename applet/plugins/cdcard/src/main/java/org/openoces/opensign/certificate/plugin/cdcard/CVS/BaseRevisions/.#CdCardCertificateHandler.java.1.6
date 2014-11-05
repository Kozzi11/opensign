/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard
	Copyright 2006 Paw F. Kjeldgaard
	Copyright 2013 Anders M. Hansen

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

/* $Id: CdCardCertificateHandler.java,v 1.6 2013/03/05 11:24:22 anmha Exp $ */

package org.openoces.opensign.certificate.plugin.cdcard;

/**
 * This class represents the interface to CdCard certificates
 *
 * @author Preben Valeur  <prv@tdc.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 * @author Mads Jensen <mjn@trifork.com>
 * @author Jeppe Burchhardt <Jeppe.Burchhardt@Cryptomathic.com>
 * @author Ole Friis Østergaard <ofo@trifork.com>
 * @author Anders M. Hansen <consult@ajstemp.dk>
 */

import org.openoces.opensign.certificate.AbstractCertificateHandler;
import org.openoces.opensign.certificate.CertificateInfo;
import org.openoces.opensign.certificate.NotExportableException;
import org.openoces.opensign.certificate.plugin.cdcard.dialogs.ConditionsHeaderPanel;
import org.openoces.opensign.certificate.plugin.cdcard.dialogs.DlgChangePinCode;
import org.openoces.opensign.certificate.plugin.cdcard.dialogs.DlgConditions;
import org.openoces.opensign.certificate.plugin.cdcard.util.AksClient;
import org.openoces.opensign.certificate.plugin.cdcard.util.AksResponse;
import org.openoces.opensign.certificate.x509.KeyUsage;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.OS;
import org.openoces.opensign.client.applet.dialogs.DlgPassword;
import org.openoces.opensign.client.applet.dialogs.InputValidator;
import org.openoces.opensign.client.applet.dialogs.PasswordEnteredListener;
import org.openoces.opensign.client.applet.resources.ResourceManager;
import org.openoces.opensign.crypto.RsaWithSha256Handler;
import org.openoces.opensign.crypto.SignatureAlgorithmFactory;
import org.openoces.opensign.crypto.SignatureAlgorithmHandler;
import org.openoces.opensign.exceptions.UserCancel;
import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.utils.IOUtils;

import javax.swing.*;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.Principal;
import java.security.PrivateKey;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

public class CdCardCertificateHandler extends AbstractCertificateHandler {
    // stuff to get from the file:
    protected static final String NID_PROP = "nid";
    static final String CLIENT_CERT_PROP = "clientCertificate";
    protected static final String ROOT_CERT_PROP = "rootCertificate";
    protected static final String PKCS_12_PROP = "pkcs12";
    protected static final String PKCCS_8_PROP = "pkcs8";
    protected static final String SIGNATURE_PROP = "signature";
    // applet parameters:
    protected static final String CD_CARD_SERVICE = "cdkortservice";
    protected static final String CD_CARD_PIN = "cdkortpin";

    // key usage common for all cd cards:
    // todo: find out if we should fix this or get it from the cert file
    private static boolean digitalSignature = true;
    private static boolean nonRepudiation = true;
    private static boolean keyEncipherment = false;
    private static boolean dataEncipherment = false;
    private static boolean keyAgreement = false;
    private static boolean keyCertSign = false;
    private static boolean keyCRLSign = false;
    private static KeyUsage intendedKeyUsage = new KeyUsage(digitalSignature, nonRepudiation, keyEncipherment, dataEncipherment, keyAgreement, keyCertSign, keyCRLSign);


    /**
     * ********************************** begin instance stuff ************************************
     */
    protected Properties props = new Properties();
    protected String filePath;
    protected CdCardKeyStoreHandler handler;

    // new version
    private boolean acceptTestCard = false;


    protected CdCardCertificateHandler(CdCardKeyStoreHandler handler, String filePath) throws Exception {
        String prodParam = handler.getCallBackHandler().getParameter("cdkortprod");
        // if empty: test
        // if "dev" dev
        // if "stub" stub
        // otherwise: prod
        boolean testEnv = (prodParam == null || prodParam.length() == 0);
        if (testEnv) {
            AksClient.setEnv(AksClient.ENV_TEST);
            acceptTestCard = true;
        } else {
            if (prodParam.equalsIgnoreCase("dev")) {
                AksClient.setEnv(AksClient.ENV_DEV);
                acceptTestCard = true;
            } else if (prodParam.equalsIgnoreCase("stub")) {
                AksClient.setEnv(AksClient.ENV_STUB);
                acceptTestCard = true;
            } else {
                AksClient.setEnv(AksClient.ENV_PROD);
                acceptTestCard = false;
            }
        }
        this.handler = handler;
        this.filePath = filePath;
        // read the certificate file (for info)
        props = getPropsFromFile(filePath);
        if (verify(props)) {
            String b64ClientCert = (String) props.get(CLIENT_CERT_PROP);
            certInfo = OS.getCryptoSupport().getCertInfo(b64ClientCert);
        } else {
            throw new Exception("The CD CARD cannot be verified");
        }
    }

    /**
     * We can compare certs without getting pw here so we do that.
     *
     * @param o
     * @return if the two certificates are equivalent
     */
    public final boolean equals(Object o) {
        if (o instanceof CdCardCertificateHandler) {
            CdCardCertificateHandler h = (CdCardCertificateHandler) o;
            String myB64ClientCert = (String) props.get(CLIENT_CERT_PROP);
            String otherB64ClientCert = (String) h.props.get(CLIENT_CERT_PROP);
            return myB64ClientCert.equals(otherB64ClientCert);
        } else {
            return false;
        }
    }

    @Override
    public final int hashCode() {
        String myB64ClientCert = (String) props.get(CLIENT_CERT_PROP);
        return myB64ClientCert.hashCode();
    }

    public final Properties getExtraCertificateProperties() {
        Properties props = new Properties();
        props.put("cdcardpath", filePath);
        return props;
    }

    /**
     * Go thorugh the file and if there is anything mathing ^var="value";$ insert into
     *
     * @param filePath
     */
    private Properties getPropsFromFile(String filePath) {
//        permit();
        Properties props = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            DataInput dis = new DataInputStream(fis);
            String key;
            String value;
            for (; ; ) {
                String line = dis.readLine();
                if (line == null) break;
                StringTokenizer tok = new StringTokenizer(line, "\"");
                if (tok.hasMoreTokens()) {
                    key = tok.nextToken();
                    if (key.length() > 1 && key.endsWith("=")) {
                        key = key.substring(0, key.length() - 1);
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
                if (tok.hasMoreTokens()) {
                    value = tok.nextToken();
                    if (tok.hasMoreTokens()) {
                        String semiColon = tok.nextToken();
                        if (semiColon.equals(";")) {
                            props.put(key, value);
                        }
                    }
                }
            }
        } catch (IOException e) {
            FileLog.error("Error reading CD CARD at " + filePath, e);
        } finally {
            IOUtils.close(fis);
        }
        return props;
    }

    private static final String TEST_MODULUS = "00:be:cb:d1:ee:9f:45:3e:6d:55:14:7e:c4:0c:8e:3a:ca:1a:7f:35:5f:a8:0d:e8:f9:" +
            "b3:57:27:f5:1f:36:99:22:21:5c:45:b2:c8:a8:3b:d7:98:b9:50:c9:6c:d1:91:a1:24:9f:b6:df:7a:99:ad:4e:09:" +
            "e8:16:e2:f7:df:e7:e2:c1:ee:9f:76:6c:36:70:84:05:93:65:2b:84:d0:f7:39:56:12:bd:8f:4e:42:8c:5e:86:2c:" +
            "b0:f3:e9:32:30:e9:dc:36:c4:1f:2c:c7:8d:76:f3:c0:b4:79:00:93:69:f9:c1:e7:d7:4c:a4:1c:aa:6d:13:7b:fe:4c:8d:07:bc:ef";
    private static final String TEST_EXPONENT = "01:00:01";
    private static final String PROD_MODULUS = "00:eb:c7:d6:24:55:13:f5:08:65:9f:00:73:e8:82:59:94:fb:be:a0:1c:82:8c:2b:c1:4d:10:bf:ea:cf:72:25:1e:71:66:2c:cc:56:c4:81:fe:f3:52:05:84:bd:67:5d:a6:ea:07:0e:bb:c1:03:64:37:e2:8f:a8:fe:85:9c:aa:03:0a:ef:7e:ab:45:d8:30:cd:27:b2:53:67:d2:04:b5:80:91:e5:cc:f5:39:43:6e:ef:23:c3:cc:d9:cf:29:4c:80:c7:28:e4:be:ab:72:a0:e9:7d:27:ea:64:c7:35:21:4c:ed:e0:27:84:bf:49:4f:38:c8:c7:e8:37:6f:c9:1e:a7:25:95:08:09:99:65:4b:5f:fb:83:15:be:df:6b:b5:09:65:0e:f9:53:fa:80:d8:ce:1f:1f:68:3e:d8:5c:21:f7:01:2b:60:00:ac:2c:4f:44:b5:ff:06:a3:7f:5b:5c:7a:77:9c:c1:ae:fb:6c:32:c9:73:7b:75:91:07:2a:eb:d5:d4:fd:e8:90:57:f2:b0:ab:fb:8f:42:d4:98:98:12:c6:0c:57:b4:7c:4d:cf:78:c2:2e:75:df:10:57:55:10:28:db:37:cd:96:65:85:52:fc:0b:76:85:9a:6a:5f:26:11:d6:24:a4:8e:ed:16:fa:20:98:74:29:87:65:fd:f7:df";
    private static final String PROD_EXPONENT = "01:00:01";
    private static final String[] SIGNED_SEQUENCE = {NID_PROP, CLIENT_CERT_PROP, ROOT_CERT_PROP, PKCS_12_PROP, PKCCS_8_PROP};

    private byte[] buildSignedValue() {
        byte[][] buf = new byte[SIGNED_SEQUENCE.length][];
        int size = 0;
        for (int i = 0; i < SIGNED_SEQUENCE.length; i++) {
            String prop = (String) props.get(SIGNED_SEQUENCE[i]);
            if (SIGNED_SEQUENCE[i].equals(NID_PROP)) {
                buf[i] = prop.getBytes();
            } else {
                buf[i] = Base64.decode(prop.getBytes());
            }
            size += buf[i].length;
        }
        byte[] result = new byte[size];
        int index = 0;
        for (int i = 0; i < SIGNED_SEQUENCE.length; i++) {
            System.arraycopy(buf[i], 0, result, index, buf[i].length);
            index += buf[i].length;
        }
        return result;
    }

    private boolean verify(Properties props) throws Exception {
        // check that everything looks fine i.e.
        // the signature matches as it should
        String signature = (String) props.get(SIGNATURE_PROP);
        byte[] signatureBytes = Base64.decode(signature.getBytes());
        byte[] signedValue = buildSignedValue();
        if (acceptTestCard) {
            return OS.getCryptoSupport().verify(TEST_MODULUS, TEST_EXPONENT, signedValue, signatureBytes);
        } else {
            return OS.getCryptoSupport().verify(PROD_MODULUS, PROD_EXPONENT, signedValue, signatureBytes);
        }
    }

    @Override
    protected final CertificateInfo getCertInfo(char[] password) {
        return certInfo;
    }

    public final String getUserFriendlyName() {
        return certInfo.getUserFriendlyName();
    }

    public final Principal getSubjectDN() throws IOException {
        return certInfo.getSubjectDN();
    }

    public final Principal getIssuerDN() throws IOException {
        return certInfo.getIssuerDN();
    }

    public final BigInteger getSerialNumber() throws IOException {
        return certInfo.getSerialNumber();
    }

    public final Date getNotBefore() throws IOException {
        return certInfo.getNotBefore();
    }

    public final Date getNotAfter() throws IOException {
        return certInfo.getNotAfter();
    }

    public final int getVersion() throws IOException {
        return certInfo.getVersion();
    }

    public final void promptForPassword(CallBackHandler callBackHandler, JComponent oldFocusComponent, PasswordEnteredListener listener) {
        try {
// check if it was given as applet parameter:
            String pinParameter = handler.getCallBackHandler().getParameter(CD_CARD_PIN);
            if (pinParameter != null && pinParameter.length() > 0) {
                // attempt at setting pinParameter was made
                if (AksClient.isValidPinCode(pinParameter)) {
                    // we use it
                    password = getRealPassword(pinParameter, callBackHandler, this, oldFocusComponent);
                } else {
                    // failed - we tell the user something went wrong
                    listener.invalidPasswordEntered("Fejl ved læsning af cdkort PIN");
                }
            } else {
                String title = ResourceManager.getString("DLG_PINCODE_HEADER");
                String label = ResourceManager.getString("DLG_PASSWORD_LABEL_PIN");

                CdCardPasswordInputValidator validator = new CdCardPasswordInputValidator(callBackHandler, this, oldFocusComponent);
                DlgPassword dlg = new DlgPassword(callBackHandler, oldFocusComponent, title, label, validator);
                dlg.addPasswordEnteredListener(this);
                dlg.addPasswordEnteredListener(listener);
                dlg.show();
            }
        } catch (UserCancel userCancel) {
            listener.invalidPasswordEntered(userCancel.getMessage());
        } catch (IOException e) {
            FileLog.error(e.getMessage(), e);
            listener.invalidPasswordEntered("Der opstod en fejl");
        }
    }


    public final byte[] sign(byte[] toBeSigned, SignatureAlgorithmFactory signatureAlg) throws GeneralSecurityException {
        return realSign(toBeSigned, password, signatureAlg.getHandler(this).getSignatureAlgorithm());
    }

    final byte[] realSign
            (
                    byte[] toBeSigned,
                    char[] realPassword, String
                    signatureAlg) {
        String pkcs12 = (String) props.get(PKCS_12_PROP);
        try {
            KeyStore keyStore = OS.getCryptoSupport().getKeyStore(pkcs12, realPassword);
            PrivateKey pk = OS.getCryptoSupport().getPrivateKey(keyStore, realPassword);
            return OS.getCryptoSupport().sign(toBeSigned, pk, signatureAlg);
        } catch (Exception e) {
            FileLog.error(e.getMessage(), e);
            return null;
        }
    }

    public final byte[] digest(byte[] toBeHashed, SignatureAlgorithmHandler signatureAlgorithmHandler) throws GeneralSecurityException {
        return signatureAlgorithmHandler instanceof RsaWithSha256Handler ?
                OS.getCryptoSupport().sha256(toBeHashed) : OS.getCryptoSupport().sha1(toBeHashed);
    }

    public final byte[] getCertificate() {
        String b64ClientCert = (String) props.get(CLIENT_CERT_PROP);
        return Base64.decode(b64ClientCert.getBytes());
    }

    public final byte[][] getCertificateChain() {
        byte[][] result = new byte[2][];
        String b64ClientCert = (String) props.get(CLIENT_CERT_PROP);
// todo: determine the correct sequence here
        result[0] = Base64.decode(b64ClientCert.getBytes());
        String b64RootCert = (String) props.get(ROOT_CERT_PROP);
        result[1] = Base64.decode(b64RootCert.getBytes());
        return result;
    }

    public final String getKeyUsage() {
        return "not implemented yet";
    }

    public final boolean canSign() throws IOException {
        return intendedKeyUsage.includesDigitalSignature() || intendedKeyUsage.includesNonRepudiation();
    }

    public final KeyUsage getIntendedKeyUsage() {
        return intendedKeyUsage;
    }

    public final String getStoreName() {
        return "CD kort"; // todo: localize - like pkcs12
    }

    private final class CdCardPasswordInputValidator implements InputValidator {
        private CdCardCertificateHandler certificateHandler;
        private CallBackHandler callBackHandler;
        private char[] password;
        private String errorMessage = "PIN-koden skal indeholde " + AksClient.PIN_CODE_LENGTH + " cifre";
        private JComponent oldFocusComponent;

        private CdCardPasswordInputValidator(CallBackHandler callBackHandler, CdCardCertificateHandler certificateHandler, JComponent oldFocusComponent) {
            this.callBackHandler = callBackHandler;
            this.certificateHandler = certificateHandler;
            this.oldFocusComponent = oldFocusComponent;
        }

        public char[] getPassword() {
            return password;
        }

        public boolean isValid(char[] input) {
            boolean valid = AksClient.isValidPinCode(new String(input));
            if (valid) {
                try {
                    password = getRealPassword(new String(input), callBackHandler, certificateHandler, oldFocusComponent);
                    return true;
                } catch (UserCancel userCancel) {
                    errorMessage = userCancel.getMessage();
                    return false;
                } catch (IOException e) {
                    errorMessage = e.getMessage();
                    return false;
                }
            } else return false;
        }

        public String getInfo() {
            return "Indtast den " + AksClient.PIN_CODE_LENGTH + "-cifrede PIN-kode";
        }

        public String getErrorMsg() {
            return errorMessage;
        }


    }

    /**
     * Check with the AKS server in the following way:
     * - nid and pin is sent to aks server
     * - if firstTime back:
     * - inform user and present conditions
     * - prompt for CPR and send (nid, pin, cpr)
     * - if ok back: pw is also back
     * - if revoked back: inform user
     * - if wrongPwOrNid: inform user
     *
     * @return the correct password
     */
    private char[] getRealPassword(String pin, CallBackHandler callBackHandler, CdCardCertificateHandler certificateHandler, JComponent oldFocusComponent) throws UserCancel, IOException {
        AksClient client = AksClient.getInstance();

        String nid = (String) props.get(NID_PROP);
        String service = callBackHandler.getParameter(CD_CARD_SERVICE);
        AksResponse response;
        try {
            response = client.getPassword(nid, pin, null, service);
        } catch (Exception e) {
            FileLog.error("Problem getting password form aks 1: " + e.getMessage());
            throw new IOException("Problem med adgangskodeserveren", e);
        }
        FileLog.info("Timing: CdCard AksResponse: " + new Date());
        if (response.getStatus() == AksResponse.FIRST_TIME) {
            // ok, it IS FIRST_TIME - present conditions and get CPR
            DlgConditions dlg = new DlgConditions(callBackHandler, oldFocusComponent, certificateHandler, "Godkend digital signatur og vilkår", response.getMsg());
            dlg.show();
            if (dlg.isCancelled()) {
                throw new UserCancel();
            } else {
                String cpr = dlg.getCprNumber();
                try {
                    response = client.getPassword(nid, pin, cpr, service);
                } catch (Exception e) {
                    FileLog.error("Problem getting password form aks 2: " + e.getMessage());
                    throw new IOException("Problem med adgangskodeserveren", e);
                }
                // could prompt again but we just bail out here
                if (response.getStatus() == AksResponse.ERROR_CPR_MISMATCH) {
                    throw new IOException("Forkert cpr nummer");
                }
            }
        } else if (response.getStatus() == AksResponse.FIRST_TIME_POCES) {
            int state = ConditionsHeaderPanel.POCES_ACTIVATE_CPR;

            DlgConditions dlg = new DlgConditions(callBackHandler, oldFocusComponent, certificateHandler, "Godkend digital signatur og vilkår", response.getMsg(), state);
            dlg.show();
            if (dlg.isCancelled()) {
                throw new UserCancel();
            }

            String cpr = dlg.getCprNumber();
            state = ConditionsHeaderPanel.POCES_ACTIVATE_PIN;

            // activation pin-code - ask user to change pincode.
            DlgChangePinCode dlgPin = new DlgChangePinCode(callBackHandler, oldFocusComponent, state,
                    this, "Aktiver Digital Signatur", pin, nid, service,
                    null, null, cpr);
            dlgPin.show();
            if (dlgPin.isCancelled()) {
                throw new UserCancel();
            } else {
                response = dlgPin.getAksResponse();
            }

            if (response.getStatus() != AksResponse.OK) {
                throw new IOException("An error occurred");
            }
        } else if (response.getStatus() == AksResponse.PIN_OK_FIRST_TIME_FORCE_CHANGE)

        {
            // activation pin-code - ask user to change pincode.
            DlgChangePinCode dlg = new DlgChangePinCode(callBackHandler, oldFocusComponent, ConditionsHeaderPanel.LRA_ACTIVATE,
                    this, "Aktiver Digital Signatur", pin, nid, service,
                    response.getMsg(), response.getExtraInfo(), null);
            dlg.show();
            if (dlg.isCancelled()) {
                throw new UserCancel();
            } else {
                response = dlg.getAksResponse();
                // below is now not used anymore
            }
        } else if (response.getStatus() == AksResponse.PIN_OK_REACTIVATE_FORCE_CHANGE ||
                response.getStatus() == AksResponse.PIN_OK_REACTIVATE_FORCE_CHANGE_POCES) {
            // activation pin-code - ask user to change pincode.
            int state = response.getStatus() == AksResponse.PIN_OK_REACTIVATE_FORCE_CHANGE ?
                    ConditionsHeaderPanel.LRA_RE_ACTIVATE : ConditionsHeaderPanel.POCES_REACTIVATE;

            DlgChangePinCode dlg = new DlgChangePinCode(callBackHandler, oldFocusComponent, state,
                    this, "Genåbn Digital Signatur", pin, nid, service,
                    response.getMsg(), response.getExtraInfo(), null);
            dlg.show();
            if (dlg.isCancelled()) {
                throw new UserCancel();
            } else {
                response = dlg.getAksResponse();
                // below is now not used anymore
            }
        }

        if (response.getStatus() == AksResponse.OK) {
            return response.getMsg().toCharArray();
        }

        if (response.getStatus() == AksResponse.FAIL) {
            throw new IOException("Forkert PIN-kode");
        }

        if (response.getStatus() == AksResponse.TOO_MANY) {
            // error
            throw new IOException("For mange PIN-kode forsøg");
        }

        if (response.getStatus() == AksResponse.REVOKED) {
            // error
            throw new IOException("Certifikatet er blevet spærret");
        }

        if (response.getStatus() == AksResponse.ERROR_CARD_CLOSED) {
            // error
            throw new IOException("Kortet er deaktiveret");
        }

        if (response.getStatus() == AksResponse.SYSTEM_ERROR)

        {
            throw new IOException("Der opstod en fejl, prøv senere");
        }

        if (response.getStatus() == AksResponse.ERROR_PIN_EXPIRED)

        {
            // error
            throw new IOException("PIN-koden er udløbet");
        }

        throw new IOException(response.getMsg());
    }

    @Override
    public boolean isExportable() {
        return false;
    }

    @Override
    public byte[] getPkcs12() throws NotExportableException {
        throw new NotExportableException();
    }
    
    protected void close() {
    	filePath = null;
    	handler = null;
    	props = null;
    }
    
    protected void finalize() {
    	FileLog.debug("CdCardCertificateHandler Finalized");
    	super.finalize();
    }
}