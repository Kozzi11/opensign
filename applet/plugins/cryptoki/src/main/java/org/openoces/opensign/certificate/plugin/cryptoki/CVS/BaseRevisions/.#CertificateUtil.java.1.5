/*
	Copyright 2006 Daniel Andersen
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

import iaik.pkcs.pkcs11.Util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.openoces.opensign.utils.FileLog;

public class CertificateUtil {
	public static WrappedCertificateChains chainCertificates(List<X509Certificate> certificates) {
        return getCertificateChains(certificates);
    }
	
    public static String getIssuerDnName(X509Certificate certificate) {
        return certificate.getIssuerDN().getName();
    }

    public static String getSubjectDnName(X509Certificate certificate) {
        return certificate.getSubjectDN().getName();
    }

    /**
     * This provides unique identification for every certificate.
     */
    public static String getIssuerAndSerial(X509Certificate certificate) {
    	return getIssuerDnName(certificate)+certificate.getSerialNumber();
    }
    
    public static boolean isIssuedByAnyTrust2408(X509Certificate certificate) {
        if (certificate != null) {
            String upperCaseIssuerDn = getIssuerDnName(certificate).toUpperCase();
            return upperCaseIssuerDn.indexOf("TRUST2408") != -1;
        } else {
            return false;
        }
    }

    public static byte[] extractModulus(X509Certificate certificate) {
        java.security.interfaces.RSAPublicKey rsaPublicKey = (java.security.interfaces.RSAPublicKey) certificate.getPublicKey();
        return Util.unsignedBigIntergerToByteArray(rsaPublicKey.getModulus());
    }
    
    /**
     * Gets certificate chains for all user certificates
     */
    private static WrappedCertificateChains getCertificateChains(List<X509Certificate> allCerts) {
    	WrappedCertificateChains wrappedCertificateChains = new WrappedCertificateChains();
    	LinkedList<X509Certificate> currentChain = new LinkedList<X509Certificate>();
    	
    	for (X509Certificate currentCert : allCerts) {
    		if(isLeafCert(currentCert)) {
    			try {
					currentChain = getChain(currentCert, allCerts);
					wrappedCertificateChains.addCertificateChain(currentChain);
				} catch (Exception e) {
					FileLog.error("Unable to get Certificate chain for "+currentCert.getSubjectDN().getName()+" : "+e.getMessage());
				}
    		}
    	}
    	return wrappedCertificateChains;
    }
    
    /**
     * Is cert a leaf (user) cert.
     */
    private static boolean isLeafCert(X509Certificate cert) {
    	return cert.getBasicConstraints() < 0;
    }
    
    /**
     * Build certificate chain for leaf (user) certificate using all certs in list.
     */
    private static LinkedList<X509Certificate> getChain(X509Certificate leafCert , List<X509Certificate> certList) throws InvalidKeyException, CertificateException, NoSuchAlgorithmException, NoSuchProviderException {
    	LinkedList<X509Certificate> certificateChain = new LinkedList<X509Certificate>();
    	//Make a copy, so we dont fiddle with the original list
    	LinkedList<X509Certificate> allCerts = new LinkedList<X509Certificate>((LinkedList<X509Certificate>)certList);
    	Collections.copy(allCerts, (LinkedList<X509Certificate>)certList);
    	
    	certificateChain.add(leafCert);
    	boolean nodeAdded = true;
        while (nodeAdded) {
            nodeAdded = false;
            X509Certificate top = (X509Certificate) certificateChain.getLast();
            if (isSelfSigned(top)) {
                break;
            }
            // Not self-signed. Signed by anyone else in list?
            Iterator<X509Certificate> it = allCerts.iterator();
            while (it.hasNext()) {
                X509Certificate x509 = it.next();
                if (verify(top, x509.getPublicKey())) {
                	certificateChain.add(x509);
                    nodeAdded = true;
                    it.remove(); // No need to look at this any more.
                    break;
                }
                // Not signed by this cert
            }
        }
    	return certificateChain;
    }
    
    /**
     * Check if cert is self signed.
     */
    private static boolean isSelfSigned(X509Certificate cert) throws CertificateException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException {
    	    return verify(cert, cert.getPublicKey());
    }
    
    /**
     * Verify that cert is signed by key
     */
    private static boolean verify(X509Certificate cert, java.security.PublicKey key) throws InvalidKeyException, CertificateException, NoSuchAlgorithmException, NoSuchProviderException {
	    String sigAlg = cert.getSigAlgName();
	    String keyAlg = key.getAlgorithm();
	    sigAlg = sigAlg != null ? sigAlg.trim().toUpperCase() : "";
	    keyAlg = keyAlg != null ? keyAlg.trim().toUpperCase() : "";
	    if (keyAlg.length() >= 2 && sigAlg.endsWith(keyAlg)) {
	        try {
	        	cert.verify(key);
	            return true;
	        } catch (SignatureException se) {
	            return false;
	        }
	    } else {
	        return false;
	    }
    }
}
