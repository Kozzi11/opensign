/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard
	Copyright 2006 Paw F. Kjeldgaard
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
*/

package org.openoces.opensign.certificate.plugin.cryptoki;

import iaik.pkcs.pkcs11.TokenException;
import org.openoces.opensign.certificate.AbstractCertificateHandler;
import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.certificate.filter.KeyUsageFilter;
import org.openoces.opensign.certificate.x509.KeyStoreHandler;
import org.openoces.opensign.client.applet.dialogs.DlgConfirmInformation;
import org.openoces.opensign.client.applet.resources.ResourceManager;
import org.openoces.opensign.exceptions.InputDataError;
import org.openoces.opensign.exceptions.UserCancel;
import org.openoces.opensign.utils.FileLog;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.security.Security;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * KeyStore handler for hardware tokens
 * 
 * @author Anders M. Hansen <consult@ajstemp.dk>
 */
public class CryptokiKeyStoreHandler extends KeyStoreHandler {
    private boolean scannedForCards = false;
    private FilenameFilter filenameFilter;
    private AbstractCertificateHandler[] allCerts;
    private PKCS11Handler pkcs11Handler;
    private ConfigurationManager configurationManager;
    private boolean triedInitializing = false;
    //Max number of tries to init hardware token.
    private int maxShowTokenInfoDialog = 2; 
    private String currentProviderName;

    public CryptokiKeyStoreHandler() {
    }

    public final void init() {
        super.init();
        pkcs11Handler = new PKCS11Handler();
        configurationManager = new ConfigurationManager();
        currentProviderName = null;
    }

    public final void destroy() {
        destroyPkcs11Handler();
    }

    public final void stop() {
        destroyPkcs11Handler();
    }

    protected final void finalize() throws Throwable {
    	try {
    		FileLog.debug("CryptokiKeyStoreHandler Finalized");
    	} finally {
    		super.finalize();
    	}
    }
    
    private void destroyPkcs11Handler() {
    	scannedForCards = true; // Don't try to load keystore again
        try {
            if (pkcs11Handler != null) {
            	FileLog.debug("Destroying cryptoki key store.");
            	pkcs11Handler.destroy();
                pkcs11Handler = null;
                if (currentProviderName != null) {
                    Security.removeProvider(currentProviderName);
                }
                FileLog.debug("Cryptoki key store destroy completed.");
            }
            if(allCerts != null){
            	for(AbstractCertificateHandler ach : allCerts) {
            		if (ach instanceof CryptokiCertificateHandler) {
            			((CryptokiCertificateHandler) ach).close();
            			ach = null;
            		}
            	}
            	allCerts = null;
            }
            configurationManager = null;
            currentProviderName = null;
            filenameFilter = null;
            close();
        } finally {   	
        	//Do nothing
        }
    }

    public final void refreshKeystore() {
        super.certs = scanForCerts();
    }

    public final boolean supportsCopy() {
        return false;
    }

    public final boolean supportsImport() {
        return false;
    }

    public final boolean supportsExport() {
        return false;
    }

    public final boolean supportsDelete() {
        return false;
    }

    public final boolean isPollingForCertificates() {
        return !scannedForCards;
    }

    public final String getName() {
        return "KEYSTORE_CRYPTOKI";
    }

    public final String getLocalizedName() {
        return ResourceManager.getString(getName());
    }

    public final AbstractCertificateHandler getCertificateFromFile(File file) {
    	if (acceptsFile(file.getName())) {
            try {
                if (configurationManager.setProviderFromLibrary(file.getCanonicalPath())) {
                    pkcs11Handler.reset();
                    scannedForCards = false; // Starts polling
                    triedInitializing = false;
                }
            } catch (IOException e) {
                // Ignore!
            }
        }
        return null;
    }

    public final FilenameFilter getFilenameFilter() {
        if (filenameFilter == null) {
            filenameFilter = new FilenameFilter() {
                public boolean accept(File path, String file) {
                    return acceptsFile(file);
                }
            };
        }
        return filenameFilter;
    }

    private boolean acceptsFile(String file) {
    	if(file != null) {
    		String lowerCaseFilename = file.toLowerCase();
    		return lowerCaseFilename.endsWith(".dll") || lowerCaseFilename.endsWith(".so") || lowerCaseFilename.endsWith(".jnilib") || lowerCaseFilename.endsWith(".dylib");
    	}
    	return false;
    }

    private AbstractCertificateHandler[] scanForCerts() {
        if (scannedForCards) {
            return allCerts;
        }
        
        if (!initializeConfiguration()) {
        	scannedForCards = true; // Give up no token driver present.
            return null;
        } else {
            if (!triedInitializing) {
                triedInitializing = true;
                if (!installAndLoadNativePkcs11Library()) {
                    showCouldNotInitializePKCS11();
                    return null;
                }
            }
            if (!initializeModuleAndToken()) {
                if (maxShowTokenInfoDialog > 0) {
                    showInsertTokenInfo();
                    maxShowTokenInfoDialog--;
                } else {
                	scannedForCards = true; // Give up and don't try to load token keystore again
                }
                return null;
            }
            scannedForCards = true; // Don't try to load keystore again
            if (!initializeSession()) {
                showCouldNotInitializePKCS11();
                return null;
            }
            try {
            	FileLog.info("Loading certificates for KEYSTORE_HARDWARE");
                allCerts = loadCertificates();
            } catch (Exception e) {
                showCouldNotInitializePKCS11();
            }
            return allCerts;
        }
    }

	private void showInsertTokenInfo() {
        DlgConfirmInformation dlgInformation = new DlgConfirmInformation(callBackHandler, null, ResourceManager.getString("DLG_INFO_HEADER"), ResourceManager.getString("INSERT_HARDWARETOKEN"));
        dlgInformation.show();
    }

    private void showCouldNotInitializePKCS11() {
        DlgConfirmInformation dlgInformation = new DlgConfirmInformation(callBackHandler, null, ResourceManager.getString("DLG_INFO_HEADER"), ResourceManager.getString("HARDWARETOKEN_COULD_NOT_INITIALIZE"));
        dlgInformation.show();
    }

    private boolean initializeConfiguration() {
        try {
            if (configurationManager.isConfigured()) {
                return true;
            }
            configurationManager.findProvider();
            return configurationManager.isConfigured();
        } catch (Exception e) {
        }
        return false;
    }

    private boolean installAndLoadNativePkcs11Library() {
        try {
            pkcs11Handler.installAndLoadNativeLibrary();
            return true;
        } catch (Exception e) {
            FileLog.error("Error loading native pkcs#11 library", e);
            return false;
        } catch (UnsatisfiedLinkError e) {
            FileLog.error("Error loading native pkcs#11 library", e);
            return false;
        }
    }

    private boolean initializeModuleAndToken() {
        try {
        	currentProviderName = pkcs11Handler.initializeModuleAndToken(configurationManager.getCurrentProviders());
            return pkcs11Handler.isModuleInitialized() && pkcs11Handler.isTokenInitialized();
        } catch (TokenException e) {
        	FileLog.warn("No hardware found: "+e.getMessage());
        } catch (Exception e) {
            FileLog.warn("Error initializing module", e);
        }
        return false;
    }

    private boolean initializeSession() {
        try {
            pkcs11Handler.initializeSession();
            return pkcs11Handler.isSessionInitialized();
        } catch (Exception e) {
            FileLog.warn("Error initializing session", e);
            return false;
        }
    }

    @SuppressWarnings("unchecked")
	private AbstractCertificateHandler[] loadCertificates() throws TokenException, CertificateException {
        WrappedCertificateChains wrappedCertificateChains = pkcs11Handler.loadCertificates();
        List<AbstractCertificateHandler> certificateHandlers = new ArrayList<AbstractCertificateHandler>();
        for (String alias : wrappedCertificateChains.getAliases()) {
        	FileLog.debug("Get chain for alias: "+alias);
        	LinkedList<X509Certificate> certificateChain = wrappedCertificateChains.getCertificateChain(alias);
            byte[][] certificateBytes = certificateChainToByteArray(certificateChain);
            if (certificateBytes != null) {
                X509Certificate userCertificate = certificateChain.get(0);
                try {
                	CertificateHandler cert = new CryptokiCertificateHandler(certificateBytes, userCertificate, pkcs11Handler);
                	if(new KeyUsageFilter().accept(cert)) {
                		certificateHandlers.add(new CryptokiCertificateHandler(certificateBytes, userCertificate, pkcs11Handler));
                	}
                } catch (InputDataError e) {
                    //do nothing
                } catch (UserCancel e) {
                    //do nothing
                }
            }
        }
        Collections.sort(certificateHandlers);
        return certificateHandlers.toArray(new AbstractCertificateHandler[certificateHandlers.size()]);
    }

    private byte[][] certificateChainToByteArray(List<X509Certificate> certificates) throws CertificateEncodingException {
        byte[][] certificateChain = new byte[certificates.size()][];
        int i = 0;
        for (X509Certificate certificate : certificates) {
        	FileLog.debug(i+" Cert to bytes: "+certificate.getSubjectDN().getName());
            certificateChain[i++] = certificate.getEncoded();
        }
        return certificateChain;
    }
}