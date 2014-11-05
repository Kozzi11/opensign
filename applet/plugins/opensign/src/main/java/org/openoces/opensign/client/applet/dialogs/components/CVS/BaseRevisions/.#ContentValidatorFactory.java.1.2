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

/* $Id: ContentValidatorFactory.java,v 1.2 2012/02/28 08:20:48 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs.components;

public class ContentValidatorFactory {

    protected ContentValidatorFactory() {}

    /** Verify that the contents are a "document internal" URL reference. */
	public static ContentValidator internalURL() {
		return new ContentValidator() {
			public String validate(String content) {
				if ("".equals(content)) // Weird, but we will allow it.
					return null;

				if ('#' == content.charAt(0))
					return null;

				return "Links must reference anchors inside the document. '" + content + "' is not an internal link.";
			}
		};
	}

	public static ContentValidator CSSType() {
		return new ContentValidator() {
			public String validate(String content) {
				if ("text/css".equalsIgnoreCase(content))
					return null;

				return "'type' attribute MUST contain value 'text/css'.";
			}
		};
	}

	public static ContentValidator CSSContent() {
		return new CSSContentValidator();

	}
}
