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

/* $Id: SignatureGenerator.java,v 1.5 2012/09/27 11:03:47 pakj Exp $ */

package org.openoces.opensign.xml.xmldsig;

import org.openoces.opensign.certificate.CertificateHandler;
import org.openoces.opensign.appletsupport.Attachment;
import org.openoces.opensign.client.applet.dialogs.listeners.SignText;
import org.openoces.opensign.crypto.SignatureAlgorithmFactory;
import org.openoces.opensign.exceptions.InputDataError;
import org.openoces.opensign.exceptions.UserCancel;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Properties;

/**
 * This interface describes the interface for xmldsig generators.
 *
 * @author Michael Motet
 * @author Mads Jensen <mjn@trifork.com>
 * @author Jeppe Burchhardt <Jeppe.Burchhardt@Cryptomathic.com>
 * @author Ole Friis Østergaard <ofo@trifork.com>
 */

public interface SignatureGenerator {
    public static final String SIGNTEXT = "signtext";
    public final static String STYLESHEETDIGEST = "stylesheetDigest";
    public final static String STYLESHEETIDENTIFIER = "stylesheetIdentifier";
    public static final String SOCIALSECURITYNUMBER = "socialsecuritynumber";
    public static String OPTIONALFIELD2 = "optionalfieldtwo";
    public static String HOSTNAME = "host";
    public static String LOGONTO = "logonto";

    static final String ACTION = "action";
    static final String ACTION_SIGN = "sign";
    static final String ACTION_LOGON = "logon";
    static final int BASE64_LINELENGTH = 76;
    /**
     * Generate a Base64 representation of an XMLDSIG document visible and invisible properties are signed
     * by the certificate and password.
     *
     * VisibleProps is supposed to contain what was visible to the user during signing.
     * InvisibleProps is supposed to contain extra info relevant - like layout of applet.
     *
     * @param certificate
     * @param signText will be added to visibleProps - just to make it more explicit
     * @param visibleProps
     * @param invisibleProps
     * @param attachments
     * @return The signed document
     */
    String sign(CertificateHandler certificate, SignText signText, Map<Object, Object> visibleProps, Map<Object, Object> invisibleProps, Attachment[] attachments, SignatureAlgorithmFactory signatureAlgorithm) throws GeneralSecurityException, IOException, InputDataError, UserCancel;
    /**
     * Generate a Base64 representation of an XMLDSIG document visible and invisible properties are signed
     * by the certificate and password.
     *
     * VisibleProps is supposed to contain what was visible to the user during signing.
     * InvisibleProps is supposed to contain extra info relevant - like layout of applet.
     * @param certificate
     * @param visibleProps - can contain text to be signed in visibleProps under key SIGNTEXT when used for signing.
     * @param invisibleProps - can contain text to be signed in invisibleProps under key SIGNTEXT when used for logon.
     * @param signatureAlgorithm 
     * @return the signed document 
     */
    String logon(CertificateHandler certificate, Map<Object, Object> visibleProps, Map<Object, Object> invisibleProps, SignatureAlgorithmFactory signatureAlgorithm) throws GeneralSecurityException, IOException, InputDataError, UserCancel;
}