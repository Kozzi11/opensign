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

package org.openoces.opensign.appletsupport;

import org.openoces.opensign.client.applet.attach.Checksum;
import org.openoces.opensign.client.applet.attach.InvalidContentException;

/**
 * This interface defines methods for attachments
 *
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 */

public interface Attachment {
    boolean isHasSeen();
    String getHashValue();

    /**
     * Returns the checksum calculated when the attachment was downloaded or viewed
     * @return returns the locally generated checksum of the attachment
     */
    Checksum getLocalChecksum();

    /**
     * Returns whether the attachment is primary, meaning that the user will be presented with the contents of
     * the attachment in the main applet window.  
     *
     * @return whether the attachment is marked as primary
     */
    boolean isPrimary();

    /**
     * Returns whether the attachment is optional or not, meaning that the user is not required to
     * download it before completing the signing. If an attachment is not downloaded it will not be
     * included in the generated signature.
     *
     * @return whether the attachment is marked as optional
     */
    boolean isOptional();

    String getMimeType();

    byte[] getContents() throws InvalidContentException;

    String getPath();

    Checksum getChecksum();

    void setLocalChecksum(Checksum checksum);

    void setChecksumVerified();

    public String getTitle();
}
