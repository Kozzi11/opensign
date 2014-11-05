package org.openoces.opensign.certificate.plugin.pkcs12;

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

/* $Id: Pkcs12KeyStoreHandler.java,v 1.5 2013/03/05 11:24:24 anmha Exp $ */

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.certificate.x509.KeyStoreHandler;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.OS;
import org.openoces.opensign.client.applet.dialogs.FileDialogStrategy;
import org.openoces.opensign.client.applet.dialogs.SwingFileDialogStrategy;
import org.openoces.opensign.client.applet.resources.ResourceManager;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.utils.FileUtils;
import org.openoces.opensign.utils.IOUtils;

import javax.swing.*;
import java.io.*;

/**
 * This class represents the interface to a pkcs12 keystore
 *
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 * @author Preben Valeur  <prv@tdc.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 * @author Daniel Andersen <daand@nets.eu>
 * @author Anders M. Hansen <consult@ajstemp.dk>
 */

public final class Pkcs12KeyStoreHandler extends KeyStoreHandler {
    private File lastDirectoryName;
    private boolean initialized;

    public Pkcs12KeyStoreHandler() {
    }

    public Pkcs12KeyStoreHandler(CallBackHandler callback) throws IOException {
        super(callback);
        init();
    }

    public void init() {
        if (initialized) return;
        File dir = new File(keyStoreHomeDir);
        dir.mkdirs();
        lastDirectoryName = dir;
        setInitialPath(dir);
        initialized = true;
    }

    File getLastDirectoryName() {
        return lastDirectoryName;
    }

    public void refreshKeystore() {
        String dirlist[] = currentPath.list(getFilenameFilter());

        certs = new Pkcs12CertificateHandler[dirlist.length];

        for (int i = 0; i < dirlist.length; i++) {
            File f = new File(currentPath, dirlist[i]);
            certs[i] = new Pkcs12CertificateHandler(this, f);
        }

    }

    private FilenameFilter filenameFilter;

    public FilenameFilter getFilenameFilter() {
        if (filenameFilter == null) {
            filenameFilter = new FilenameFilter() {
                public boolean accept(File path, String file) {
                    if (file.toLowerCase().endsWith(".p12"))
                        return true;
                    if (file.toLowerCase().endsWith(".pfx"))
                        return true;
                    if (file.toLowerCase().endsWith(".pkcs12"))
                        return true;
                    return false;
                }
            };
        }
        return filenameFilter;
    }

    //private static final String PKCS12_KEYSTORE = "PKCS12";

    /**
     * @return boolean true if PKCS12 algorithm is installed
     *         <p/>
     *         private boolean hasPKCS12() {
     *         return true;
     *         }
     */

    public String getName() {
        return "KEYSTORE_PKCS12";
    }

    public String getLocalizedName() {
        return ResourceManager.getString(getName());
    }

    public boolean supportsCopy() {
        return true;
    }

    public boolean supportsImport() {
        return true;
    }

    public boolean supportsExport() {
        return true;
    }

    public boolean supportsDelete() {
        return true;
    }

    public boolean isPollingForCertificates() {
        return false;
    }


    // todo: change - one day
    public boolean requiresPasswordSupplied() {
        return true;
    }

    public CertificateHandler getCertificateFromFile(File f) {
        if (f == null) return null;
        File parentDir = new File(f.getParent());
        if (!getFilenameFilter().accept(parentDir, f.getName())) {
            return null;
        }
        return new Pkcs12CertificateHandler(this, f);
    }

    void importPKCS12(JComponent oldFocusComponent) throws IOException {
        FileDialogStrategy dlg = new SwingFileDialogStrategy(callBackHandler.getContentPane(), oldFocusComponent);
        File src = dlg.getLoadFile(ResourceManager.getString("RESTORE_KEYFILE"), getFilenameFilter());
        if (src == null) {
            return;
        }

        String fileName = FileUtils.getFileName(src);
        File dest = new File(this.getPath().toString() + OS.getFileSeperator() + fileName);

        prepareKeyDir();

        copy(src, dest);

        this.refreshKeystore();
    }

    protected void copy(File srcFile, File destFile) throws IOException {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(srcFile));
            out = new BufferedOutputStream(new FileOutputStream(destFile));
        int c;

        while ((c = in.read()) != -1) {
            out.write(c);
        }
        } finally {
            IOUtils.close(in);
            IOUtils.close(out);
        }
    }

    public void prepareKeyDir() throws IOException {

        File dir = this.getPath();
        if (!dir.exists()) {
            dir.mkdirs();
        }

        if (!dir.exists()) {
            FileLog.warn("Unable to construct directory: " + dir);
            throw new IOException(ResourceManager.getString("ERR_CREATE_KEY_DIR"));
        }

        if (!dir.isDirectory()) {
            FileLog.warn("Unable to construct directory: " + dir + ". The directory name is used as a filename.");
            throw new IOException(ResourceManager.getString("ERR_CREATE_KEY_DIR"));
        }
    }
    
    public final void destroy() {
    	destroyPkcs12Store();
    }

    public final void stop() {
    	destroyPkcs12Store();
    }
    
    private void destroyPkcs12Store() {
    	try {
        	if(lastDirectoryName != null) {
        		FileLog.debug("Destroying pkcs12 store.");
        		lastDirectoryName = null;
        	}
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