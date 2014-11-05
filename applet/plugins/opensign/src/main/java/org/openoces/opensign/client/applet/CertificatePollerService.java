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

package org.openoces.opensign.client.applet;

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.certificate.x509.KeyStoreHandler;
import org.openoces.opensign.utils.FileLog;

public class CertificatePollerService implements Runnable {
    private final CallBackHandler callBackHandler;
    private CertificateView certificateView;
    private boolean stopped = false;
    private boolean loggedException = false;

    public CertificatePollerService(CallBackHandler callBackHandler, CertificateView certificateView) {
        this.callBackHandler = callBackHandler;
        this.certificateView = certificateView;
    }

    public void stopProgress() {
        stopped = true;
    }

    public void run() {
        while (!stopped) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
            	FileLog.debug(ie.getMessage());
            	break;
            } catch (Exception e) {
                // Ignore!
            }
            refreshKeystores();
        }
    }

    private void refreshKeystores() {
        KeyStoreHandler[] keystores = callBackHandler.getKeyStoreHandlers();
        for (KeyStoreHandler keystore : keystores) {
            refreshKeystore(keystore);
        }
    }

    private void refreshKeystore(KeyStoreHandler keystore) {
        if (keystore.isPollingForCertificates()) {
            try {
                keystore.refreshKeystore();
                addCertificatesFromKeystore(keystore);
            } catch (Exception e) {
                logRefreshKeystoreException(e);
                // Ignore and keep on refreshing keystores
            }
        }
    }

    private void addCertificatesFromKeystore(KeyStoreHandler keystore) {
        CertificateHandler[] certificateHandlers = keystore.getCertificates();
        if (certificateHandlers != null) {
            for (CertificateHandler certificateHandler : certificateHandlers) {
                certificateView.addCertificate(certificateHandler);
            }
        }
    }

    private void logRefreshKeystoreException(Exception e) {
        if (!loggedException) { // Don't keep on spamming log
            FileLog.warn("Could not refresh keystore", e);
            loggedException = true;
        }
    }

    protected void finalize() {
    	certificateView = null;
    }
}
