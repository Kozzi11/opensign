/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard
	Copyright 2013 Anders M. Hansen

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

/* $Id: ResourceManager.java,v 1.3 2013/03/05 11:24:17 anmha Exp $ */

package org.openoces.opensign.client.applet.resources;

/**
 * This class manages client resources
 *
 * @author Kim Rasmussen  <?@it-practice.dk>
 * @author Anders M. Hansen <consult@ajstemp.dk>
 */

import java.util.Locale;
import java.util.ResourceBundle;

import org.openoces.opensign.utils.FileLog;

public class ResourceManager {
	public static final String DEFAULT_ERROR_MSG = "DEFAULT_ERROR_MSG";
	private static Locale locale = null;
	private static ResourceBundle resources = null;

    protected ResourceManager() {}

	/**
	 * This method returns the resourceBundle for a given locale
	 * @return java.util.ResourceBundle
	 */
	private static ResourceBundle getResources() {
		if (resources == null) {
			// resources for that locale was not found - load the resources
			if (locale == null) {
				// default language
				locale = new Locale("en", "US");
			}
			try {
				resources = ResourceBundle.getBundle("org.openoces.opensign.client.applet.resources.Resources", locale);
			} catch (java.util.MissingResourceException e) {
				FileLog.error("Unable to load resources.", e);
			}
		}
		return resources;
	}
	/**
	 * This method returns the resource string for a given key
	 * If the key doesn't exist the default error message is returned.
	 *
	 * @return java.lang.String  The error message
	 * @param key java.lang.String  The index of the message
	 */
	public static String getString(String key) {
		String result = "Unknown Error";
		if (resources == null) {
			getResources();
		}

		try {
			result = resources.getString(key);

		} catch (java.util.MissingResourceException e) {
			try {
				FileLog.error("Unable to find ressource for key: " + key, e);
				result = resources.getString(DEFAULT_ERROR_MSG);
			} catch (java.util.MissingResourceException ee) {
				FileLog.error("Unable to find ressource for key: " + DEFAULT_ERROR_MSG, ee);
			}
		}
		return result;
	}
	/**
	 * Sets the current locale
	 * @param loc Locale
	 */
	public static void setLocale(String lang, String country) {
		locale = new Locale(lang, country);
		resources = null;
		getResources();
	}
    public static Locale getLocale(){
        return locale;
    }
    
    public static void close() {
    	locale = null;
    	resources = null;
    }
}