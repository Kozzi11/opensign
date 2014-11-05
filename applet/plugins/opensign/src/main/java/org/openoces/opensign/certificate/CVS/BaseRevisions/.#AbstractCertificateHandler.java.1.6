/*
	Copyright 2011 Paw F. Kjeldgaard
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
package org.openoces.opensign.certificate;

import org.openoces.opensign.client.applet.dialogs.PasswordEnteredListener;
import org.openoces.opensign.utils.FileLog;

import java.io.IOException;
import java.util.Date;

/**
 * User: pakj
 * Date: 04-02-11
 * Time: 09:07
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 * @author Anders M. Hansen <consult@ajstemp.dk>
 */
public abstract class AbstractCertificateHandler implements CertificateHandler, PasswordEnteredListener {
    protected CertificateInfo certInfo;
    protected char[] password;

    public boolean isInfoAvailable() {
        try {
            if (certInfo == null) certInfo = getCertInfo(password);
        } catch (IOException e) {
            FileLog.debug(e.getMessage(), e);
        }
        return certInfo != null;
    }

    public void cancelled() {
    }

    public void invalidPasswordEntered(String message) {
    }

    public void validPasswordEntered(char[] password) {
        if (password != null) {
            this.password = new char[password.length];
            System.arraycopy(password, 0, this.password, 0, password.length);
        }
    }

    protected abstract CertificateInfo getCertInfo(char[] password) throws IOException;

    /**
     * Compare two certificates according to the following rules
     * - new issuance timestamp < old issuance timestamp
     * - timestamp available < no timestamp available
     * - no timestamp available < expired certificates
     * <p/>
     * For now we only automatically retrieve timestamps for certificates accessed throught CAPI
     *
     * @param o a certificate
     * @return -1 if c0 is "larger" than c1, 1 if c0 is "smaller" than c1, otherwise 0 is returned
     */
    public int compareTo(Object o) {
        Date now = new Date();
        try {
            CertificateHandler c1 = (CertificateHandler) o;
            if (isInfoAvailable() && c1.isInfoAvailable()) {
                if (getNotAfter().before(now) && c1.getNotAfter().before(now)) {
                    if (getNotAfter().before(c1.getNotAfter())) {
                        return -1;
                    } else {
                        return 0;
                    }
                } else if (getNotAfter().before(now)) {
                    return -1;
                } else if (c1.getNotAfter().before(now)) {
                    return 1;
                } else {
                    if (getNotBefore().equals(c1.getNotBefore())) {
                        return 0;
                    }
                    if (getNotBefore().before(c1.getNotBefore())) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            } else if (isInfoAvailable()) {
                if (getNotAfter().before(now)) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (c1.isInfoAvailable()) {
                if (c1.getNotAfter().before(now)) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return 0;
            }
        } catch (Exception e) {
            FileLog.error("error while comparing certificates", e);
            return 0;
        }
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof AbstractCertificateHandler)) return false;

        AbstractCertificateHandler thatCert = (AbstractCertificateHandler) that;
        if (isInfoAvailable() && thatCert.isInfoAvailable()) {
            try {
            	if(getIssuerDN().getName().toLowerCase().equals(thatCert.getIssuerDN().getName().toLowerCase())) {
            		if(getSerialNumber().compareTo(thatCert.getSerialNumber()) == 0) {
            			return true;
            		}
            	}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
       final int prime = 31;
       int result = 1;
        try {
            result = prime * result
            		+ ((getIssuerDN() == null) ? 0 : getIssuerDN().hashCode())
                    + ((getSerialNumber() == null) ? 0 : getSerialNumber().hashCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String toString() {
        return getUserFriendlyName();
    }
    
    protected void finalize() {
    	FileLog.debug("AbstractCertificateHandler Finalized");
    }
}
