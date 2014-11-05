/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard
    Copyright 2010 Paw F. Kjeldgaard
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

/* $Id: CdCardKeyStoreHandler.java,v 1.5 2013/03/05 11:24:22 anmha Exp $ */

package org.openoces.opensign.certificate.plugin.cdcard;

/**
 * This class represents the interface to the CdCard keystore
 *
 * @author Preben Valeur  <prv@tdc.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 * @author Daniel Andersen <daand@nets.eu>
 * @author Anders M. Hansen <consult@ajstemp.dk>
 */

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.certificate.filter.CertificateFilter;
import org.openoces.opensign.certificate.plugin.cdcard.dialogs.DlgImport;
import org.openoces.opensign.certificate.plugin.cdcard.util.AksClient;
import org.openoces.opensign.certificate.x509.KeyStoreHandler;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.OS;
import org.openoces.opensign.client.applet.dialogs.OpenSignDialog;
import org.openoces.opensign.client.applet.resources.ResourceManager;
import org.openoces.opensign.utils.FileLog;

import javax.swing.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CdCardKeyStoreHandler extends KeyStoreHandler {
	public CdCardKeyStoreHandler() {
		// init();
	}

	public CdCardKeyStoreHandler(CallBackHandler handler) throws IOException {
		super(handler);
		init();
	}

	public final void init() {
		cardDir = new File(keyStoreHomeDir, CDCARD_DIR_NAME);
	}

	public final void refreshKeystore() {
		// certs = CdCardCertificateHandler.scanForCerts();
		certs = scanForCerts();
	}

	public final String getName() {
		return "KEYSTORE_CDCARD";
	}

	public final String getLocalizedName() {
		return ResourceManager.getString(getName());
	}

	private static final String CDCARD_DIR_NAME = "CDCARDS";

	protected static final String CDCARD_FILE_NAME_PREFIX = "cdkort";
	protected static final String CDCARD_FILE_NAME_POSTFIX = ".html";
	protected static final String CDCARD_FILE_NAME_ALT_POSTFIX = ".htm";

	private boolean scannedForCards = false; // determine policy for when to
												// scan
	private List<String> homeCardPaths = null; // paths to cards in home dir
	private List<String> otherCardPaths = null; // paths to cards not in home dir
	private File cardDir;
	private CertificateHandler[] allCerts;

	protected final File getCardDir() {
		return cardDir;
	}

	public final void forgetScan() {
		scannedForCards = false;
	}

	final boolean hasCard() {
		// scan the machine for cd cards
		if (!scannedForCards) {
			otherCardPaths = scanForRootCards();
			homeCardPaths = scanForHomeCards();
			readAllCerts();
			scannedForCards = true;
		}
		return (otherCardPaths.size() > 0) || (homeCardPaths.size() > 0);
	}

	/**
	 * Find the CD CARD files in "dir" and add them to the vector "cardPaths" if
	 * any found
	 */
	protected final void addFromDir(File dir, List<String> cardPaths) {
		String[] fileNames = dir.list();
		if (fileNames == null)
			return;
        for (String fileName : fileNames) {
            // if (getFilenameFilter().accept(dir, fileName)) { // alternative
            // and maybe nicer way
            if (fileName.indexOf(CDCARD_FILE_NAME_PREFIX) == 0
                    && (fileName.endsWith(CDCARD_FILE_NAME_POSTFIX) || fileName
                    .endsWith(CDCARD_FILE_NAME_ALT_POSTFIX))) {
                String path = dir.getAbsolutePath() + File.separator + fileName;
                cardPaths.add(path);
            }
        }
	}

	private List<String> scanForHomeCards() {
		List<String> cardPaths = new ArrayList<String>();
		File cardHome = getCardDir();
		cardHome.mkdirs();
		addFromDir(cardHome, cardPaths);
		return cardPaths;
	}

	private List<String> scanForRootCards() {
		List<String> cardPaths = new ArrayList<String>();
		if (OS.isOSWindows()) {
			for (char c = 'a'; c <= 'z'; c++) {
				File f = new File(c + ":\\");
				if (f.exists()) {
					addFromDir(f, cardPaths);
				}
			}
		}
		return cardPaths;
	}

	/**
	 * Scan the system for CD Card certs
	 * 
	 * @return array of CD Card certs or null if none found
	 */

	final CertificateHandler[] scanForCerts() {
		if (!scannedForCards) {
			otherCardPaths = scanForRootCards();
			homeCardPaths = scanForHomeCards();
			readAllCerts();
			scannedForCards = true;
		}
		return allCerts.clone();
	}

	private void readAllCerts() {
		// some of the cards found may contain errors - so
		List<CertificateHandler> certs = new ArrayList<CertificateHandler>();

		for (String cardPath : otherCardPaths) {
			try {
				CertificateHandler h = new RestoreableCdCardCertificateHandler(
						this, cardPath);
				if (CertificateFilter.acceptByAll(h)) {
					// you COULD check for duplicates here but we choose not to
					certs.add(h);
				}
			} catch (Exception e) {
				FileLog.error("Error in cd card file from path " + cardPath
                        + " : " + e.getMessage(), e);
			}
		}

		// as toArray() is not 1.1
		// CertificateHandler [] cards =
		// (RestoreableCdCardCertificateHandler []) certList.toArray(new
		// RestoreableCdCardCertificateHandler[0]);
		// then the homecards
		// todo: find out what to do with duplicates!
		for (String cardPath : homeCardPaths) {
			try {
				CopyableCdCardCertificateHandler handler = new CopyableCdCardCertificateHandler(this, cardPath);
				certs.add(handler);
			} catch (Exception e) {
				FileLog.error("Error in cd card file from path " + cardPath
                        + " : " + e.getMessage(), e);
			}
		}

		allCerts = certs.toArray(new CertificateHandler[certs.size()]);
	}

	public final boolean supportsCopy() {
		return true;
	}

	private FilenameFilter filenameFilter;

	public final FilenameFilter getFilenameFilter() {
		if (filenameFilter == null) {
			filenameFilter = new FilenameFilter() {
				public boolean accept(File path, String file) {
					// return ((file.indexOf("cdkort-") == 0));
					return ((file.indexOf("cdkort-") == 0) && (file
							.endsWith(".html") || file.endsWith(".htm")));
				}
			};
		}
		return filenameFilter;
	}

	public final CertificateHandler getCertificateFromFile(File file) {
		File parentDir = new File(file.getParent());
		if (!getFilenameFilter().accept(parentDir, file.getName())) {
			return null;
		}
		CertificateHandler cert = null;
		try {
			cert = new RestoreableCdCardCertificateHandler(this, file
					.getAbsolutePath());
		} catch (Exception e) {
			FileLog.info("Not a cdcard though it looked like one");
		}
		return cert;
	}

	private class ShowDialog implements Runnable {
		private OpenSignDialog dialog;

		public ShowDialog(OpenSignDialog dialog) {
			this.dialog = dialog;
		}

		public void run() {
			dialog.show();
		}
	}

	public final void service(JApplet applet, String serviceName, String serviceParam) {
		String prodParam = applet.getParameter("cdkortprod");
		boolean testEnv = (prodParam == null || prodParam.length() == 0);
		if (testEnv) {
			AksClient.setEnv(AksClient.ENV_TEST);
		} else {
			if (prodParam.equalsIgnoreCase("dev")) {
				AksClient.setEnv(AksClient.ENV_DEV);
			} else if (prodParam.equalsIgnoreCase("stub")) {
				AksClient.setEnv(AksClient.ENV_STUB);
			} else {
				AksClient.setEnv(AksClient.ENV_PROD);
			}
		}
		DlgImport dlg = new DlgImport(callBackHandler, null, "Indlæs Digital Signatur", serviceParam);
		Thread showDialog = new Thread(new ShowDialog(dlg), "ShowDialog");
		showDialog.start();
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
    
    public final void destroy() {
    	destroyCdCardStore();
    }

    public final void stop() {
    	destroyCdCardStore();
    }
    
    private void destroyCdCardStore() {
    	try {
    		scannedForCards = true; // Don't try to load keystore again
        	if (cardDir != null) {
        		FileLog.debug("Destroying cdcard store.");
        		cardDir = null;
        	}
    		if(allCerts != null){
            	for(CertificateHandler ch : allCerts) {
            		if (ch instanceof CdCardCertificateHandler) {
            			((CdCardCertificateHandler) ch).close();
            			ch = null;
            		} 
            	}
            	allCerts = null;
            }
    		otherCardPaths = null;
    		homeCardPaths = null;
    		filenameFilter = null;
        	close();
        } finally {
        	//do nothing
        }
    }
    
    protected final void finalize() throws Throwable {
        try {
        	FileLog.debug("Pkcs12KeyStoreHandler Finalized");
        } finally {
        	super.finalize();
        }
    }
}