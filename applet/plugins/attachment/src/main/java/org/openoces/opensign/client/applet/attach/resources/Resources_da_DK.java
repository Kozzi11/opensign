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

/* $Id: Resources_da_DK.java,v 1.4 2012/09/27 11:03:47 pakj Exp $ */

package org.openoces.opensign.client.applet.attach.resources;

import java.util.ListResourceBundle;

/**
 * This class defines the da,DK locale for attachments
 *
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 */

public class Resources_da_DK extends ListResourceBundle {
    static final Object[][] contents = {
        // Standard error text
        {
        "ATTACHMENT_CHECKSUM_FAILED_BODY","Et eller flere bilag er muligvis blevet \u00E6ndret. Kontakt afsender."},{
        "ATTACHMENT_CHECKSUM_FAILED_HEADER","Fejl"},{
        "ATTACHMENT_DOWNLOAD_FAILED_BODY","Det er ikke muligt at hente bilaget. Kontakt afsender."},{
        "ATTACHMENT_DOWNLOAD_FAILED_HEADER","Fejl"},{
        "ATTACHMENT_SAVE_FAILED_BODY","Der opstod en fejl ved fors\u00F8g p\u00E5 at gemme bilaget"},{
        "ATTACHMENT_SAVE_FAILED_HEADER","Fejl"},{
        "ATTACHMENTS","Bilag"},{
        "CONFIRM_OVERWRITE_FILE","Overskriv eksisterende fil?"},{
        "FETCHED", "Gemt"},{            
        "NOT_ALL_MANDATORY_ATTACHMENTS_DOWNLOADED_BODY","Der er bilag som ikke er blevet gemt/vist.  Dette er p\u00E5kr\u00E6vet, f\u00F8r der kan underskrives."},{
        "NOT_ALL_MANDATORY_ATTACHMENTS_DOWNLOADED_HEADER","Bilag ikke gemt/vist"},{
        "NOT_FETCHED_IS_MANDATORY","Ikke gemt/vist. Bilaget skal gemmes/vises."},{
        "NOT_FETCHED_IS_OPTIONAL","Ikke gemt/vist."},{
        "OK", "OK"},{
        "SAVE", "Gem..."},{
        "SAVE_ATTACHMENT", "Gem bilag..."},{
        "VIEW", "Vis..."},{
        "SIZE","St\u00F8rrelse"},{
        "TITLE","Titel"},{
        "SEEN", "Gemt/vist"},
            {"ATTACHMENT_SAVE_INVALID_CONTENT_HEADER", "Fejl"},
            {"ATTACHMENT_SAVE_INVALID_CONTENT_BODY", "Dokumentet har en fejl der gør at det ikke kan gemmes. Kontakt dokumentudsteder for at få fejlen rettet"},
            {"ATTACHMENT_VIEW_INVALID_CONTENT_HEADER", "Fejl"},
            {"ATTACHMENT_VIEW_INVALID_CONTENT_BODY", "Dokumentet har en fejl der gør at det ikke kan vises. Kontakt dokumentudsteder for at få fejlen rettet"}


    };

    /**
     * @see java.util.ListResourceBundle#getContents()
     */
    protected Object[][] getContents() {
        return org.openoces.opensign.client.applet.attach.resources.Resources_da_DK.contents;
    }
}