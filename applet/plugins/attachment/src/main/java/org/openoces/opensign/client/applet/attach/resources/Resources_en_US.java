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

/* $Id: Resources_en_US.java,v 1.4 2012/09/27 11:03:47 pakj Exp $ */

package org.openoces.opensign.client.applet.attach.resources;

import java.util.ListResourceBundle;

/**
 * This class defines the en,US locale for attachments
 *
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 */

public class Resources_en_US extends ListResourceBundle {
    /**
     * @see java.util.ListResourceBundle#getContents()
     */
    protected Object[][] getContents() {
        return org.openoces.opensign.client.applet.attach.resources.Resources_en_US.contents;
    }

    static final Object[][] contents = {
        {
                "ATTACHMENT_CHECKSUM_FAILED_BODY","Unable to save attachment"},{
                "ATTACHMENT_CHECKSUM_FAILED_HEADER","The checksum of the attachment is incorrect"},{
                "ATTACHMENT_DOWNLOAD_FAILED_BODY","Unable to download attachment"},{
                "ATTACHMENT_DOWNLOAD_FAILED_HEADER","Unable to download attachment"},{
                "ATTACHMENT_SAVE_FAILED_BODY","Unable to save attachment"},{
                "ATTACHMENT_SAVE_FAILED_HEADER","Error while saving attachment"},{
                "ATTACHMENTS","Attachments..."},{
                "CONFIRM_OVERWRITE_FILE","File exists. Overwrite it?"},{
                "FETCHED", "Saved"},{            
                "NOT_ALL_MANDATORY_ATTACHMENTS_DOWNLOADED_BODY","You cannot complete the signing process before all mandatory attachments have been downloaded"},{
                "NOT_ALL_MANDATORY_ATTACHMENTS_DOWNLOADED_HEADER","Not all attachments downloaded"},{
                "NOT_FETCHED_IS_MANDATORY","Not fetched. Must be fetched"},{
                "NOT_FETCHED_IS_OPTIONAL","Not fetched"},{
                "OK", "OK"},{
                "SAVE", "Save..."},{
                "SAVE_ATTACHMENT", "Save attachment..."},{
                "VIEW", "View..."},{
                "TYPE","Type"},{
                "SIZE","Size"},{
                "TITLE","Title"},{
                "SEEN", "Saved/Viewed"},
            {"ATTACHMENT_SAVE_INVALID_CONTENT_HEADER", "Error"},
            {"ATTACHMENT_SAVE_INVALID_CONTENT_BODY", "The document has a bug that makes it can not be saved. Contact document issuer to have the error corrected"},
            {"ATTACHMENT_VIEW_INVALID_CONTENT_HEADER", "Error"},
            {"ATTACHMENT_VIEW_INVALID_CONTENT_BODY", "The document has a bug that makes it can not be displayed. Contact document issuer to have the error corrected"}


    };
}