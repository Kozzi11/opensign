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

import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class WrappedCertificateChains {
    private Map<String, LinkedList<X509Certificate>> certificateChains;

    public WrappedCertificateChains() {
        certificateChains = new HashMap<String, LinkedList<X509Certificate>>();
    }

    public void addCertificateChain(LinkedList<X509Certificate> certificateChain) {
        certificateChains.put(CertificateUtil.getIssuerAndSerial(certificateChain.get(0)), certificateChain);
    }

    public Set<String> getAliases() {
        return certificateChains.keySet();
    }

    public LinkedList<X509Certificate> getCertificateChain(String alias) {
        return certificateChains.get(alias);
    }

    public boolean isChainEmpty() {
        return certificateChains.isEmpty();
    }
}
