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

/* $Id: MimeTypeValidator.java,v 1.2 2012/02/28 08:21:39 pakj Exp $ */

package org.openoces.opensign.appletsupport;

/**
 * This class implementation mime type validation
 *
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 */

public class MimeTypeValidator {

    protected MimeTypeValidator() {}

    public static boolean validate(String mimeType) {
        if ( mimeType == null) {
            return false;
        }

        int boundary = 0;
        for (int i=0;i<mimeType.length();i++) {
            if (mimeType.charAt(i) == '/') {
                boundary = i;
            }
        }

        String mediaType = mimeType.substring(0,boundary);
        String subType = mimeType.substring(boundary+1);

        return validateMediaType(mediaType) && validateSubType(subType);
    }

    private static boolean validateSubType(String subType) {
        if ( subType == null || subType.length()==0 ) {
            return false;
        }

        for ( int i=0;i<subType.length();i++) {
            char ch = subType.charAt(i);
            if ( !((ch>='a' && ch<='z') || ch=='-')) {
                return false;
            }
        }
        return true;

    }

    private static boolean validateMediaType(String mediaType) {
        if ( mediaType == null || mediaType.length()==0 ) {
            return false;
        }

        for ( int i=0;i<mediaType.length();i++) {
            char ch = mediaType.charAt(i);
            if ( !(ch>='a' && ch<='z')) {
                return false;
            }
        }
        return true;
    }
}
