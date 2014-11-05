/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard
	Copyright 2006 Paw F. Kjeldgaard
	Copyright 2011 Daniel Andersen
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

/* $Id: CapiKeyStoreHandler.java,v 1.6 2013/03/05 11:24:19 anmha Exp $ */

package org.openoces.opensign.certificate.plugin.capi;

/**
 * This class represents the interface to the Microsoft cryptoapi
 *
 * @author Preben Valeur  <prv@tdc.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 * @author Daniel Andersen <daand@nets.eu>
 * @author Anders M. Hansen <consult@ajstemp.dk>
 */

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.certificate.filter.CertificateFilter;
import org.openoces.opensign.certificate.x509.KeyStoreHandler;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.resources.ResourceManager;
import org.openoces.opensign.exceptions.InputDataError;
import org.openoces.opensign.exceptions.UserCancel;
import org.openoces.opensign.utils.FileLog;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CapiKeyStoreHandler extends KeyStoreHandler {
    private CapiInitializerFactory capiInitializerFactory;
    private MicrosoftCapiFactory microsoftCapiFactory;
    private CertificateHandlerFactory certificateHandlerFactory;
    private CapiInitializer initializer;

    public CapiKeyStoreHandler() throws IOException {
        this(null);
    }

    public CapiKeyStoreHandler(CallBackHandler handler) throws IOException {
        super(handler);
        boolean sunMsCapiInstalled = false; //TODO: Disabled for now ProviderUtil.getProvider("SunMSCAPI") != null;
        capiInitializerFactory = sunMsCapiInstalled ? new SunCapiInitializerFactory() : new DefaultCapiInitializerFactory();
        microsoftCapiFactory = sunMsCapiInstalled ? new SunMicrosoftCapiFactory() : new DefaultMicrosoftCapiFactory();
        certificateHandlerFactory = new DefaultCertificateHandlerFactory(microsoftCapiFactory);
    }

    public final void refreshKeystore() {
        certs = getFilteredCertificates();
    }

    public final String getName() {
        return "KEYSTORE_MS";  // or KEYSTORE_MSCAPI ?
    }

    public final String getLocalizedName() {
        return ResourceManager.getString(getName());
    }

    public final boolean install(String baseUrl) {
        initializer = capiInitializerFactory.createCapiInitializer();
        return initializer.initialize(baseUrl);
    }

    public final boolean supportsCopy() {
        return false;
    }

    public final void destroy() {
    	destroyCapiInitializer();
    }

    public final void stop() {
    	destroyCapiInitializer();
    }
    
    private void destroyCapiInitializer() {
    	try {
        	if(initializer != null) {
        		FileLog.debug("Destroying capi store.");
        		initializer.cleanup();
        		initializer = null;
        	}
        	capiInitializerFactory = null;
        	microsoftCapiFactory = null;
        	certificateHandlerFactory = null;
        	close();
        } finally {
        	//do nothing
        }
    }
    
    protected final void finalize() throws Throwable {
        try {
        	FileLog.debug("CapiKeyStoreHandler Finalized");
        } finally {
        	super.finalize();
        }
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
        return false;
    }

    public final CertificateHandler getCertificateFromFile(File file) {
        return null;
    }

    public final FilenameFilter getFilenameFilter() {
        return null;
    }

    private MicrosoftWindowsCertificateHandler[] getFilteredCertificates() {
        try {
            MicrosoftCapi capi = microsoftCapiFactory.createMicrosoftCapi();
            byte[][][] rs = capi.getCertificatesInSystemStore("My");

            List<MicrosoftWindowsCertificateHandler> filteredList = new ArrayList<MicrosoftWindowsCertificateHandler>();
            if (rs != null) {

                for (byte[][] r : rs) {
                    try {
                        MicrosoftWindowsCertificateHandler h = certificateHandlerFactory.createCertificateHandler(r);
                        if (CertificateFilter.acceptByAll(h)) {
                            filteredList.add(h);
                        }
                    } catch (InputDataError e) {
                        //do nothing
                    } catch (UserCancel e) {
                        //do nothing
                    }
                }
                return filteredList.toArray(new MicrosoftWindowsCertificateHandler[filteredList.size()]);
            } else {
                return null; // throw exception?
            }
        } catch (NullPointerException e) {
            FileLog.error("an exception occurred", e);
            return null;
        }
    }

    public void setCapiInitializerFactory(CapiInitializerFactory capiInitializerFactory) {
        this.capiInitializerFactory = capiInitializerFactory;
    }

    public void setMicrosoftCapiFactory(MicrosoftCapiFactory microsoftCapiFactory) {
        this.microsoftCapiFactory = microsoftCapiFactory;
    }

    public void setCertificateHandlerFactory(CertificateHandlerFactory certificateHandlerFactory) {
        this.certificateHandlerFactory = certificateHandlerFactory;
    }
}