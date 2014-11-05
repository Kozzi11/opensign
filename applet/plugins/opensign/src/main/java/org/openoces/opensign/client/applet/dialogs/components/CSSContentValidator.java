/*
	Copyright 2011 Paw F. Kjeldgaard

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

/* $Id: CSSContentValidator.java,v 1.2 2012/02/28 08:20:47 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs.components;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Validates CSS definition against whitelist
 */
public class CSSContentValidator implements ContentValidator {

    protected Set allowedStyles = new HashSet(Arrays.asList(new String[]{
            "color", "background", "background-color", "float", "overflow",
            "line-height", "position", "top", "bottom", "left", "right",
            "margin", "margin-right", "margin-top", "margin-left", "margin-bottom",
            "width", "height", "float", "clear", "display", "white-space"
    }));

	/** Contains property families, e.g. font-* and padding-*. */
	protected Set allowedFamilies = new HashSet(Arrays.asList(new String[]{
		"border", "font", "text", "list", "padding"
	}));

	/**
	 * Validator entry point. Delegates to <code>parse(String)</code>
	 * @see #parse(String)
	 */
	public String validate(String content) {
		String value = null;
		if (content != null) {
			value = parse(content);
		}
		return value;
	}

	/**
	 * Starting point for CSS validation. This method accept single style definitions or stylesheets (multiple grouped styles)<br />
	 * Input is divided into single style definitions and passed to <code>parseStyle(String)</code> method.
	 * @param content - CSS definition
	 * @return Validation error OR <code>null</code> if no validation errors were found
	 * @see #parseStyle(String)
	 * @see #validateStyleDefinition(String)
	 */
	private String parse(String content) {
		String value = null;

		if (content.indexOf('@') != -1) {
			return "'@XXX' CSS instructions are not allowed";
		}

		if (content.indexOf('{') > 0) {
			//Get rid of name of style
			content = content.substring(content.indexOf('{'));
		}
		StringTokenizer outer = new StringTokenizer(content, "{", false);


		if (outer.hasMoreTokens()) {
			//Content is the body of a style element
			while (value == null && outer.hasMoreTokens()) {
				String token = outer.nextToken();

				if (token.indexOf('}') > -1 && token.indexOf('}') != token.length()-1) {
					String end = token.substring(token.indexOf('}'));
					token = token.substring(0, token.indexOf('}'));
					value = parseStyle(end);
				}
				if (value == null) {
					if (token.indexOf(':') != -1 || token.indexOf(';') != -1) {
						value = parseStyle(token);
					} else if (token.indexOf('}') == -1 && token.indexOf(':') == -1 && token.indexOf(';') == -1 && token.indexOf('#') != -1) {
						continue;
					} else {
						value = "'" + token + "' does not appear to be a valid style definition";
					}
				}
			}
		}

		return value;
	}


	private String parseStyle(String str) {
		String value = null;
		StringTokenizer outer = new StringTokenizer(str, ";", false);

		if (outer.hasMoreTokens()) {
			//Style has multiple definitions
			while (value == null && outer.hasMoreTokens()) {
				String token = outer.nextToken();

				if (token.indexOf(':') == -1 && token.indexOf(';') == -1 && token.indexOf('}') != -1) {
					continue;
				} else {
					value = validateStyleDefinition(token);
				}
			}
		} else {
			value = validateStyleDefinition(str);
		}

		return value;
	}


	private String validateStyleDefinition(String str) {
		if (str == null) {
			return null;
		}
		str = str.trim();
		if (str.equals("")) {
			return null;
		}

		if (str.indexOf(':') <= 0) {
			//Input is not a valid style definition
			return "'" + str + "' is not a valid style definition";
		}

		String name = str.substring(0, str.indexOf(':'));
		name = name.trim().toLowerCase();
		String value = str.substring(name.length());

		boolean allowed = allowedStyles.contains(name);

		if (!allowed) {
			allowed  = allowedFamilies.contains(getFamily(name));
		}

		if (!allowed) {
			return "'" + name + "' is not an allowed style property";
		}

		// Validate value
		if (value.toLowerCase().indexOf("url") > -1) {
			return "Urls are not allowed in styles - '" + name + "' is defined using an url";
		}

		return null;
	}

	private static String getFamily(String name) {
		int dashindex = name.indexOf('-');
		if (dashindex == -1)
			return name;

		return name.substring(0, dashindex);
	}

}
