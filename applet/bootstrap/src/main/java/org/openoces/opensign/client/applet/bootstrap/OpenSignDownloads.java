/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard
    Copyright 2011 Daniel Andersen


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

/* $Id: OpenSignDownloads.java,v 1.5 2012/12/19 12:28:03 pakj Exp $ */

package org.openoces.opensign.client.applet.bootstrap;


import java.util.ArrayList;
import java.util.List;

/**
 * This class generates the java class generates the list of plugins available for download
 *
 * @author Preben Valeur  <prv@tdc.dk>
 * @author Daniel Andersen <daand@nets.eu>
 */
class OpenSignDownloads {

    protected OpenSignDownloads() {
    }

    static List<DownloadItem> getDownloadItems() {
        List<DownloadItem> downloadItems = new ArrayList<DownloadItem>(20);
        downloadItems.add(new DownloadItem("OpenSign", "opensign", "org.openoces.opensign.client.applet.Sign", new Sha1Checksum(DownloadChecksums.getopensignChecksum()), true));
        downloadItems.add(new DownloadItem("OpenLogon", "opensign", "org.openoces.opensign.client.applet.Logon", new Sha1Checksum(DownloadChecksums.getopensignChecksum()), true));
        downloadItems.add(new DownloadItem("OpenService", "opensign", "org.openoces.opensign.client.applet.PassApplet", new Sha1Checksum(DownloadChecksums.getopensignChecksum()), true));
        downloadItems.add(new DownloadItem("OpenExport", "opensign", "org.openoces.opensign.client.applet.Export", new Sha1Checksum(DownloadChecksums.getopensignChecksum()), true));

        // add initialization class requiring special rights:
        DownloadItem item = new DownloadItem("capi", "capi", "org.openoces.opensign.certificate.plugin.capi.CapiKeyStoreHandler", new Sha1Checksum(DownloadChecksums.getcapiChecksum()), false);
//        item.setInitializer(new CapiInitializer());
        downloadItems.add(item);

        item = new DownloadItem("cdcard", "cdcard", "org.openoces.opensign.certificate.plugin.cdcard.CdCardKeyStoreHandler", new Sha1Checksum(DownloadChecksums.getcdcardChecksum()), false);
        item.setRequiresOSSupport(true);
        downloadItems.add(item);
        item = new DownloadItem("pkcs12", "pkcs12", "org.openoces.opensign.certificate.plugin.pkcs12.Pkcs12KeyStoreHandler", new Sha1Checksum(DownloadChecksums.getpkcs12Checksum()), false);
        item.setRequiresOSSupport(true);
        downloadItems.add(item);
        item = new DownloadItem("oces", "oces", "org.openoces.opensign.certificate.plugin.oces.OcesKeyStoreHandler", new Sha1Checksum(DownloadChecksums.getocesChecksum()), false);
        item.setRequiresOSSupport(true);
        downloadItems.add(item);
        item = new DownloadItem("cryptoki", "cryptoki", "org.openoces.opensign.certificate.plugin.cryptoki.CryptokiKeyStoreHandler", new Sha1Checksum(DownloadChecksums.getcryptokiChecksum()), false);
        item.setRequiresOSSupport(true);
        downloadItems.add(item);
        // the OS support libs:
        downloadItems.add(new DownloadItem("jsse", "jsse", "org.openoces.opensign.certificate.support.suncrypto.SunCryptoSupport", new Sha1Checksum(DownloadChecksums.getjsseChecksum()), false));
        // applet specific support
        // see if it works like this for now:
        downloadItems.add(new DownloadItem("attachment", "attachment", "org.openoces.opensign.client.applet.attach.AttachmentSupportImpl", new Sha1Checksum(DownloadChecksums.getattachmentChecksum()), false));

        //pdf support
        downloadItems.add(new DownloadItem("pdf", "pdf", "org.openoces.opensign.plugin.pdf.dialogs.PdfSignPanelFactory", new Sha1Checksum(DownloadChecksums.getpdfChecksum()), false));


        return downloadItems;
    }
}