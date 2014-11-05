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

/* $Id: AttachmentResourceManager.java,v 1.2 2012/02/28 08:22:05 pakj Exp $ */

package org.openoces.opensign.client.applet.attach.resources;

import org.openoces.opensign.client.applet.resources.PluginResourceManager;
import java.util.ResourceBundle;
import java.util.MissingResourceException;
import java.util.Locale;

/**
 * This class implements a resource manager for attachments
 *
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 */

public class AttachmentResourceManager extends PluginResourceManager {
    private static AttachmentResourceManager instance = null;
    public static String getString(String key) {
        if (instance == null){
            instance = new AttachmentResourceManager("org.openoces.opensign.client.applet.attach.resources.Resources");
        }
        return instance.getResourceString(key);
    }
    private String resourcePackage;
    public AttachmentResourceManager(String resourcePackage){
        this.resourcePackage = resourcePackage;
    }
    protected ResourceBundle getResources(Locale locale) throws MissingResourceException {
        return ResourceBundle.getBundle(resourcePackage, locale);
    }
}
