/*
    Copyright 2012 Paw F. Kjeldgaard


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

/* $Id: AttachmentValidatorManager.java,v 1.2 2012/09/26 09:32:09 pakj Exp $ */

package org.openoces.opensign.client.applet.attach;

import java.util.HashMap;
import java.util.Map;

public class AttachmentValidatorManager {

    private static Map<String, AttachmentValidator> register = new HashMap<String, AttachmentValidator>();

    protected AttachmentValidatorManager() {}

    public static void registerValidator(String mimeType, AttachmentValidator validator) {
        register.put(mimeType, validator);
    }

    public static boolean hasValidator(String mimeType) {
        return (getValidator(mimeType) != null);
    }

    public static AttachmentValidator getValidator(String mimeType) {
        return register.get(mimeType);
    }
}
