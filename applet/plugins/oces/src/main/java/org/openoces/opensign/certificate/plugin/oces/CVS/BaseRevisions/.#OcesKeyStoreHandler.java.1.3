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

/* $Id: OcesKeyStoreHandler.java,v 1.3 2013/03/05 11:24:23 anmha Exp $ */

package org.openoces.opensign.certificate.plugin.oces;

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.certificate.x509.KeyStoreHandler;
import org.openoces.opensign.client.applet.resources.ResourceManager;
import org.openoces.opensign.utils.FileLog;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an OCES html backup specific keystore
 * <p/>
 * First target is to make this support browsing to and opening an OCES backup html.
 * Later it may also allow a special directory to keep the backups.
 * And later: a new format of the backup - like cd card - where the certificate
 * can be read without opening the pkcs12 with password.
 *
 * @author Preben Valeur  <prv@tdc.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 * @author Daniel Andersen <daand@nets.eu>
 * @author Anders M. Hansen <consult@ajstemp.dk>
 */
public final class OcesKeyStoreHandler extends KeyStoreHandler {
    private boolean scannedForCards = false; // determine policy for when to scan
    private CertificateHandler[] allCerts;

    public OcesKeyStoreHandler() {
    }

    public final void refreshKeystore() {
        super.certs = scanForCerts();
    }

    public final boolean supportsCopy() {
        // TODO: find out if - but probably not used
        return false;
    }

    public final String getName() {
        return "KEYSTORE_OCES";
    }

    public final String getLocalizedName() {
        return ResourceManager.getString(getName());
    }

    private FilenameFilter filenameFilter;

    public final FilenameFilter getFilenameFilter() {
        if (filenameFilter == null) {
            filenameFilter = new FilenameFilter() {
                public boolean accept(File path, String file) {
                    return file.toLowerCase().endsWith(".html") && !file.toLowerCase().startsWith("cdcard");
                }
            };
        }
        return filenameFilter;
    }

    public final CertificateHandler getCertificateFromFile(File f) {
        File parentDir = new File(f.getParent());
        if (!getFilenameFilter().accept(parentDir, f.getName())) {
            return null;
        }
        CertificateHandler cert = new OcesCertificateHandler(f);
        ((OcesCertificateHandler) cert).setCallBackHandler(callBackHandler);
        return cert;
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

    public boolean isPollingForCertificates() {
        return false;
    }

    private CertificateHandler[] scanForCerts() {
        if (!scannedForCards) {
            List<CertificateHandler> ocesFiles = scanForCertsInOcesFolder();
            allCerts = ocesFiles.toArray(new CertificateHandler[ocesFiles.size()]);
            scannedForCards = true;
        }
        return allCerts;
    }

    private List<CertificateHandler> scanForCertsInOcesFolder() {
        List<CertificateHandler> certs = new ArrayList<CertificateHandler>();
        File ocesFolder = new File(getKeyStoreHome());
        if (ocesFolder.exists()) {
            File[] files = ocesFolder.listFiles(getFilenameFilter());
            for (File file : files) {
                FileLog.debug("scanForOces: adding path: " + file.getAbsolutePath());
                CertificateHandler cert = getCertificateFromFile(file);
                if (cert != null)
                    certs.add(cert);
            }
        }
        return certs;
    }
    
    public final void destroy() {
    	destroyOcesStore();
    }

    public final void stop() {
    	destroyOcesStore();
    }
    
    private void destroyOcesStore() {
    	try {
    		scannedForCards = true; // Don't try to load keystore again
    		if(allCerts != null){
    			FileLog.debug("Destroying oces store.");
            	for(CertificateHandler ch : allCerts) {
            		if (ch instanceof OcesCertificateHandler) {
            			((OcesCertificateHandler) ch).close();
            			ch = null;
            		}
            	}
            	allCerts = null;
            }
    		filenameFilter = null;
        	close();
        } finally {
        	//do nothing
        }
    }
    
    protected final void finalize() throws Throwable {
        try {
        	FileLog.debug("OcesKeyStoreHandler Finalized");
        } finally {
        	super.finalize();
        }
    }
}
