/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard


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

/* $Id: DenySubjectDnCertificateFilter.java,v 1.2 2012/02/28 08:21:10 pakj Exp $ */

package org.openoces.opensign.certificate.filter;

/**
 * This class represents a subject distinguished name certificate filter
 *
 * @author Preben Valeur  <prv@tdc.dk>
 */

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.utils.Base64;

public class DenySubjectDnCertificateFilter extends SubjectDnCertificateFilter {
    private static final String COMPANY_CERTIFICATE_IDENT = new String(Base64.encode("-UID:".getBytes()));
    
    public final static DenySubjectDnCertificateFilter denyCompanyFilter = new DenySubjectDnCertificateFilter(COMPANY_CERTIFICATE_IDENT);

    private DenySubjectDnCertificateFilter(String parameter) {
        super();
        setParameter(parameter);
    }

    public boolean accept(CertificateHandler certificate) {
        return !super.accept(certificate);
    }

    public boolean compare(String tbm, String pattern) {
        return tbm.indexOf(pattern) != -1;
    }
}
