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

/* $Id: PluginResourceManager.java,v 1.2 2012/02/28 08:21:07 pakj Exp $ */

package org.openoces.opensign.client.applet.resources;

/**
 * This class manages plugin resources
 *
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 */

import org.openoces.opensign.utils.FileLog;

import java.util.ResourceBundle;
import java.util.Locale;

public abstract class PluginResourceManager {
    private ResourceBundle resources = null;
    protected abstract ResourceBundle getResources(Locale locale);
    public String getResourceString(String key) {
        String result = "Unknown Error";
        if (resources == null) {
            try {
                resources = getResources(ResourceManager.getLocale());
            } catch (java.util.MissingResourceException e) {
                FileLog.error("Unable to load resources.", e);
            }
        }
        try {
            result = resources.getString(key);
        } catch (java.util.MissingResourceException e) {
            try {
                result = ResourceManager.getString(key);
            } catch (java.util.MissingResourceException e2) {
                try {
                    FileLog.error("Unable to find ressource for key: " + key, e);
                    result = resources.getString(ResourceManager.DEFAULT_ERROR_MSG);
                } catch (java.util.MissingResourceException ee) {
                    FileLog.error("Unable to find ressource for key: " + ResourceManager.DEFAULT_ERROR_MSG, ee);
                }
            }
        }
        return result;
    }
}
