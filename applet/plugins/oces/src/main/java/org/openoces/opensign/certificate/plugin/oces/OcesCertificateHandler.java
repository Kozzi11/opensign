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

 * @author Mads Jensen <mjn@trifork.com>
 * @author Jeppe Burchhardt <Jeppe.Burchhardt@Cryptomathic.com>
 * @author Ole Friis Østergaard <ofo@trifork.com>
 */

/* $Id: OcesCertificateHandler.java,v 1.3 2012/12/19 12:28:02 pakj Exp $ */

package org.openoces.opensign.certificate.plugin.oces;

import org.openoces.opensign.certificate.DecryptingCertificateHandler;
import org.openoces.opensign.certificate.NotExportableException;
import org.openoces.opensign.certificate.PwCertInfo;
import org.openoces.opensign.client.applet.OS;
import org.openoces.opensign.client.applet.dialogs.InputValidator;
import org.openoces.opensign.client.applet.resources.ResourceManager;
import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.utils.FileUtils;
import org.openoces.opensign.utils.IOUtils;

import java.io.*;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Properties;

/**
 * This class represents a certificate stored in a OCES specific html backup
 *
 * @author Preben Valeur <prv@tdc.dk>
 * @author Carsten Raskgaard <carsten@raskgaard.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 */
public class OcesCertificateHandler extends PwCertInfo implements DecryptingCertificateHandler {

    public OcesCertificateHandler(File file) {
        super(file);
    }

    private String getPkcs12FromFile(File file) {
        String pkcs12 = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            for (; ;) {
                String line = reader.readLine();
                if (line == null)
                    break;
                if ((line.indexOf("pkcs12=") == 0)
                        || (line.indexOf("CSPUtil.ImportHTMLKey") > 0)) {
                    int lastQuote = line.lastIndexOf('\"');
                    int secondLastQuote = line.lastIndexOf("\"", lastQuote - 1);
                    pkcs12 = line.substring(secondLastQuote + 1, lastQuote);
                    break;
                }
            }
        } catch (IOException e) {
            FileLog.error("Error reading OCES pkcs12 at " + file.getAbsolutePath(), e);
        } finally {
            IOUtils.close(reader);
        }

        return pkcs12;
    }

    protected final synchronized KeyStore getKeyStore(char[] password) throws IOException {
        try {
            String pkcs12 = getPkcs12FromFile(file);
            return OS.getCryptoSupport().getKeyStore(pkcs12, password);
        } catch (KeyStoreException e) {
            throw new IOException(e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            throw new IOException(e.getMessage(), e);
        } catch (CertificateException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    public final String getUserFriendlyName() {
        String certificateId = getCertificateID();
        return FileUtils.getFileName(certificateId);
    }

    public final String getCertificateID() {
        return getFile().toString();
    }

    public final String getStoreName() {
        return ResourceManager
                .getString("DLG_CERTIFICATEDETAILS_LABEL_CERTIFICATESTORE_OCES")
                + " (" + this.getFile().toString() + ")";
    }

    // if you want to pass extra info about a certificate (like path to
    // cd-card...)
    public final Properties getExtraCertificateProperties() {
        return null; // To change body of implemented methods use File |
        // Settings | File Templates.
    }

    protected final InputValidator getInputValidator() {
        return new OcesCertificateHandlerPasswordValidator(this);
    }

    private final class OcesCertificateHandlerPasswordValidator implements InputValidator {
        private OcesCertificateHandler ocesCertificateHandler;

        public OcesCertificateHandlerPasswordValidator(OcesCertificateHandler ocesCertificateHandler) {
            super();
            this.ocesCertificateHandler = ocesCertificateHandler;
        }

        public String getErrorMsg() {
            return ResourceManager.getString("ERR_WRONG_PASSWORD");
        }

        public String getInfo() {
            return ResourceManager.getString("DLG_PASSWORD_HEADER_PREFIX") + " " + file;
        }

        public boolean isValid(char[] input) {
            try {
                return ocesCertificateHandler.getCertInfo(input) != null;
            } catch (Exception e) {
                FileLog.warn("Unable to extract information from certificate. An invalid password is a likely cause of this (" + e + ")", e);
                return false;
            }
        }

    }

    @Override
    public boolean isExportable() {
        return true;
    }

    @Override
    public byte[] getPkcs12() throws NotExportableException {
        String pkcs12 = getPkcs12FromFile(file);
        return Base64.decode(pkcs12.getBytes());
    }


}