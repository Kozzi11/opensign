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

/* $Id: KeyStoreHandler.java,v 1.4 2013/03/05 11:24:17 anmha Exp $ */

package org.openoces.opensign.certificate.x509;

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.utils.FileLog;

import javax.swing.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * This class represents the interface to keystores
 *
 * @author Jens Bo Friis  <jbf@it-practice.dk>
 * @author Preben Valeur  <prv@tdc.dk>
 * @author Carsten Raskgaard  <carsten@raskgaard.dk> 
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 * @author Daniel Andersen <daand@nets.eu>
 * @author Anders M. Hansen <consult@ajstemp.dk>
 */
public abstract class KeyStoreHandler {

    protected CertificateHandler[] certs = null;
    protected File currentPath;
    protected File initialPath;
    protected CallBackHandler callBackHandler;
    protected String keyStoreHomeDir;
    // this happens to be a RestrictedActor too - probably
    public KeyStoreHandler() {

    }
    public void setCallBackHandler(CallBackHandler handler) {
        this.callBackHandler = handler;
    }

    public CallBackHandler getCallBackHandler() {
        return callBackHandler;
    }

    public void setKeyStoreHome(String keyStoreHomeDir){
    	this.keyStoreHomeDir = keyStoreHomeDir;
    }

    protected String getKeyStoreHome() {
        return keyStoreHomeDir;
    }

    public void init() {
    }

    public boolean install(String baseUrl){
    	return true;
    }
    /**
     * Microsoft keystore
     *
     * @throws IOException
     */
    public KeyStoreHandler(CallBackHandler handler) throws IOException {
        this.callBackHandler = handler;
    }

    public void destroy() {
    }

    public void stop() {

    }
    /**
     * Directory of PKCS12 files
     *
     * @param path
     * @throws IOException
     */
    public void setInitialPath(File path) {
        //		this.passwordHandler = passwordHandler;
        this.currentPath = path;
        this.initialPath = path;
    }

    public abstract void refreshKeystore();

    public abstract boolean supportsCopy();

    public abstract boolean supportsImport();
    public abstract boolean supportsExport();
    public abstract boolean supportsDelete();

    public abstract boolean isPollingForCertificates();

    public abstract String getName();

    public abstract String getLocalizedName();

    // enable the password field in the applet - we don't like this pt
    public boolean requiresPasswordSupplied() {
        return false;
    }

    public CertificateHandler[] getCertificates() {
        return certs;
    }

    public abstract CertificateHandler getCertificateFromFile(File file);
   
    public abstract FilenameFilter getFilenameFilter();

    public boolean isEmpty() {
        // todo: refresh or?
        refreshKeystore();
        return (certs == null || certs.length == 0);
    }

    public CertificateHandler getCertificate(String certificateID) {
        if (certificateID == null)
            return null;
        for (CertificateHandler cert : certs) {
            if (certificateID.equals(cert.getUserFriendlyName()))
                return cert;
        }
        return null;
    }

    public File getPath() {
        return currentPath;
    }

    public void setPath(File path) {
        if (path != null) {
            // change, temporarily, to specified path
            this.currentPath = path;
        } else {
            // if null'ed, return to initial default path
            this.currentPath = initialPath;
        }
        refreshKeystore();
    }

    // override if you want to provide a service
    public void service(JApplet applet, String serviceName, String serviceParam) {
    }

    protected void close() {
        certs = null;
        currentPath = null;
        initialPath = null;
        callBackHandler = null;
        keyStoreHomeDir = null;
    }
    
    protected void finalize() throws Throwable {
    	try{
    		close();
    	} finally {
    		FileLog.debug("KeyStoreHandler Finalized");
    	}
    }
}