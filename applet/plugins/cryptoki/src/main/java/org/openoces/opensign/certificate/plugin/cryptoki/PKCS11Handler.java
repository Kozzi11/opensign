/*
	Copyright 2011 Daniel Andersen
	Copyright 2012 Anders M. Hansen
	

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

 * @author Daniel Andersen <daand@nets.eu>
 * @author Anders M. Hansen <consult@ajstemp.dk>
*/

package org.openoces.opensign.certificate.plugin.cryptoki;

import iaik.pkcs.pkcs11.*;
import iaik.pkcs.pkcs11.wrapper.*;

import org.openoces.opensign.utils.FileLog;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.Object;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.*;

public class PKCS11Handler {
    private Module currentPkcs11Module;
    private Token currentToken;
    private Session currentSession;
    private TokenInfo currentTokenInfo;
    private boolean isLoggedIn = false;
    private int loadNativeLibRetries = 0;

    public final void reset() {
        try {
            destroy();
        } catch (Exception e) {
            FileLog.warn("Failed to destroy module: "+e.getMessage() +" - ignoring!");
            FileLog.debug("StackTrace: ", e);
        }
        currentPkcs11Module = null;
        currentToken = null;
        currentTokenInfo = null;
        currentSession = null;
    }

    public final void installAndLoadNativeLibrary() {
        try {
            NativeLibraryInstaller.detectPlatform();
            NativeLibraryInstaller.installLibrary();
            ensureCorrectChecksumForNativeLibrary(); // In theory a really, really bad guy might change the implementation right after this call...
            try {
                //trying to avoid racecondition in init.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //do nothing
            }
            loadNativeLibrary();
        } catch (UnsatisfiedLinkError e) {
            if(e.getMessage().contains("already loaded in another classloader")) {
                FileLog.warn(""+e.getMessage());
                FileLog.debug("StackTrace: ", e);
                if(loadNativeLibRetries < 1) {
                    try {
                        Thread.sleep(5000);
                        loadNativeLibRetries++;
                        loadNativeLibrary();
                    } catch (InterruptedException ie) {
                        FileLog.error("Thread sleep interrupted: "+ie.getMessage(), ie);
                        
                    }
                }
            } else {
                throw e;
            }
        }
    }

    public final String initializeModuleAndToken(List<ProviderInformation> providers) throws TokenException, IOException {
        FileLog.debug("Initializing PKCS#11 module...");
        for (ProviderInformation provider : providers) {
        	Module pkcs11Module = null;
        	try {
        		pkcs11Module = Module.getInstance(provider.getLibraryPath());
        		pkcs11Module.initialize(null);
                Slot[] slots = pkcs11Module.getSlotList(Module.SlotRequirement.TOKEN_PRESENT);
                if (slots == null || slots.length == 0) {
        			pkcs11Module.finalize(null);
                    throw new RuntimeException("No hardware inserted");
                }
                currentPkcs11Module = pkcs11Module;
                currentToken = slots[0].getToken();
                FileLog.debug(currentToken.getTokenInfo().getLabel().trim() + " was initialized");
        		return provider.getProviderName();
        	} catch(TokenException te) {
        		FileLog.warn("PKCS#11 init failed: "+te.getMessage());
        	} catch (Exception e) {
        		// This is normal if the token is not inserted
        		FileLog.debug("PKCS#11 init failed: "+e.getMessage());
        	}
		}
        
        throw new TokenException("Could not initialize PKCS#11 module");
    }

    public final void initializeToken() throws TokenException {
        Slot[] slots = currentPkcs11Module.getSlotList(Module.SlotRequirement.TOKEN_PRESENT);
        if (slots == null || slots.length == 0) {
            throw new RuntimeException("No hardware inserted");
        }
        currentToken = slots[0].getToken();
    }

    public final void initializeSession() throws TokenException {
        ensureSignSupported();
        currentTokenInfo = currentToken.getTokenInfo();
        currentSession = currentToken.openSession(Token.SessionType.SERIAL_SESSION, Token.SessionReadWriteBehavior.RO_SESSION, null, null);
    }

    private void ensureSignSupported() throws TokenException {
        List<Mechanism> supportedMechanisms = Arrays.asList(currentToken.getMechanismList());
        if (!supportedMechanisms.contains(Mechanism.RSA_PKCS)) {
            throw new RuntimeException("This token does not support the RSA PKCS mechanism");
        }
        MechanismInfo rsaMechanismInfo = currentToken.getMechanismInfo(Mechanism.RSA_PKCS);
        if (!rsaMechanismInfo.isSign()) {
            throw new RuntimeException("This hardware does not support signing");
        }
    }

    public final byte[] sign(byte[] toBeSigned, X509Certificate certificate, String algorithm) throws TokenException {
        Mechanism mechanism = getMechanism(algorithm);
        iaik.pkcs.pkcs11.objects.PrivateKey privateKey = findPrivateKey(certificate);
        currentSession.signInit(mechanism, privateKey);
        return currentSession.sign(toBeSigned);
    }

    public final byte[] digest(byte[] toBeDigested, String algorithm) throws TokenException {
        Mechanism mechanism = getMechanism(algorithm);
        currentSession.digestInit(mechanism);
        return currentSession.digest(toBeDigested);
    }

    public final WrappedCertificateChains loadCertificates() throws TokenException, CertificateException {
        initializeFindCertificates();
        List<X509Certificate> certificates = new LinkedList<X509Certificate>();
        Object[] foundTokenObjects = currentSession.findObjects(1);
        while (foundTokenObjects.length > 0) {
            X509Certificate certificate = getCertificate((iaik.pkcs.pkcs11.objects.X509PublicKeyCertificate) foundTokenObjects[0]);
            if (CertificateUtil.isIssuedByAnyTrust2408(certificate)) {
                certificates.add(certificate);
            }
            foundTokenObjects = currentSession.findObjects(1);
        }
        currentSession.findObjectsFinal();
        return CertificateUtil.chainCertificates(certificates);
    }

    public final boolean isModuleInitialized() {
        return currentPkcs11Module != null;
    }

    public final boolean isTokenInitialized() {
        return currentToken != null;
    }

    public final boolean isSessionInitialized() {
        return currentSession != null;
    }

    public final boolean requiresLogin() {
        return currentTokenInfo.isLoginRequired();
    }

    public final boolean isProtectedAuthenticationPath() {
        return currentTokenInfo.isProtectedAuthenticationPath();
    }

    public final void login(char[] password) throws TokenException {
        currentSession.login(Session.UserType.USER, password);
        isLoggedIn = true;
    }

    public final void logout() {
        try {
            if (isLoggedIn) {
                currentSession.logout();
                isLoggedIn = false;
            }
        } catch (TokenException e) {
            FileLog.warn("Unable to logout from hardware: "+e.getMessage() +" - ignoring!");
            FileLog.debug("StackTrace: ", e);
        }
    }

    public final TokenInfo getTokenInfo() {
        return currentTokenInfo;
    }

    public final void closeSession() {
        try {
            if (isSessionInitialized()) {
                currentSession.closeSession();
                currentSession = null;
            }
        } catch (TokenException e) {
            FileLog.warn("Unable to close hardware session: "+e.getMessage() +" - ignoring!");
            FileLog.debug("StackTrace: ", e);
        }
    }

    public final void destroy() {
        if (!isModuleInitialized()) {
            return;
        }
        logout();
        closeSession();
        try {
            currentPkcs11Module.finalize(null);
        } catch (TokenException e) {
            FileLog.warn("Unable to finalize PKCS#11 module: "+e.getMessage() +"  - ignoring!");
            FileLog.debug("StackTrace: ", e);
        } finally {
        	try {
        		FileLog.debug("PKCS11Handler.destroy(): PKCS#11 module finalized");
        		PKCS11Implementation.ensureUnlinkedAndFinalized();
        	} finally{
        		currentPkcs11Module = null;
                currentToken = null;
                currentTokenInfo = null;
                currentSession = null;
        	}
        }
    }

    protected void finalize() {
    	FileLog.debug("PKCS11Handler Finalized");
    }
    
    private void ensureCorrectChecksumForNativeLibrary() {
        String pathToLibrary = NativeLibraryInstaller.getPathToLibrary();
        try {
            NativeLibraryInstaller.ensureCorrectChecksum(pathToLibrary);
        } catch (Exception e) {
            throw new RuntimeException("Could not verify library checksum: " + pathToLibrary, e);
        }
    }

    private void loadNativeLibrary() {
        String pathToLibrary = NativeLibraryInstaller.getPathToLibrary();
        PKCS11Implementation.ensureLinkedAndInitialized(pathToLibrary);
    }

    private Mechanism getMechanism(String algorithm) {
        if ("SHA-256".equals(algorithm)) {
            return Mechanism.SHA256;
        } else if ("SHA256withRSA".equals(algorithm)) {
            return Mechanism.SHA256_RSA_PKCS;
        } else {
            throw new RuntimeException("Unsupported algorithm: " + algorithm);
        }
    }

    private iaik.pkcs.pkcs11.objects.PrivateKey findPrivateKey(X509Certificate certificate) throws TokenException {
        initializeFindPrivateKey(certificate);
        List<iaik.pkcs.pkcs11.objects.PrivateKey> privateKeyList = new LinkedList<iaik.pkcs.pkcs11.objects.PrivateKey>();
        Object[] foundTokenObjects = currentSession.findObjects(1);
        while (foundTokenObjects.length > 0) {
            privateKeyList.add((iaik.pkcs.pkcs11.objects.RSAPrivateKey) foundTokenObjects[0]);
            foundTokenObjects = currentSession.findObjects(1);
        }
        currentSession.findObjectsFinal();
        if (privateKeyList.size() == 0) {
            throw new RuntimeException("No corresponding private key found for " + CertificateUtil.getSubjectDnName(certificate));
        }
        if (privateKeyList.size() > 1) {
            throw new RuntimeException("More than one private key found for " + CertificateUtil.getSubjectDnName(certificate));
        }
        return privateKeyList.get(0);
    }

    private void initializeFindCertificates() throws TokenException {
        iaik.pkcs.pkcs11.objects.X509PublicKeyCertificate x509PublicKeyCertificateTemplate = new iaik.pkcs.pkcs11.objects.X509PublicKeyCertificate();
        currentSession.findObjectsInit(x509PublicKeyCertificateTemplate);
    }

    private void initializeFindPrivateKey(X509Certificate certificate) throws TokenException {
        iaik.pkcs.pkcs11.objects.RSAPrivateKey privateSignatureKeyTemplate = new iaik.pkcs.pkcs11.objects.RSAPrivateKey();
        privateSignatureKeyTemplate.getSign().setBooleanValue(Boolean.TRUE);
        privateSignatureKeyTemplate.getModulus().setByteArrayValue(CertificateUtil.extractModulus(certificate));
        currentSession.findObjectsInit(privateSignatureKeyTemplate);
    }

    private X509Certificate getCertificate(iaik.pkcs.pkcs11.objects.X509PublicKeyCertificate x509PublicKeyCertificate) throws CertificateException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        byte[] derEncodedCertificate = x509PublicKeyCertificate.getValue().getByteArrayValue();
        return (X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(derEncodedCertificate));
    }
}