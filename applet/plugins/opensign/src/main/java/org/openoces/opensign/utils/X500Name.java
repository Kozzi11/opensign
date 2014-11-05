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

/* $Id: X500Name.java,v 1.2 2012/02/28 08:21:56 pakj Exp $ */

package org.openoces.opensign.utils;

/**
 * This class implements a X500 name
 *
 * package javasec.samples.ch14;
 * from http://examples.oreilly.com/javasec2/
 * Store an X500 Name and extract its components on demand
 *
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 */

public class X500Name {

    private String CN, OU, O, L, ST, C;
    private String name;
    private char nameChar[];

    public X500Name(String s) {
        if (s == null)
            throw new IllegalArgumentException("Name can�t be null");
        name = s;
    }

    public String getCN() {
        if (CN == null)
            CN = parse("CN=");
        return CN;
    }

    public String getOU() {
        if (OU == null)
            OU = parse("OU=");
        return OU;
    }

    public String getO() {
        if (O == null)
            O = parse("O=");
        return O;
    }

    public String getL() {
        if (L == null)
            L = parse("L=");
        return L;
    }

    public String getST() {
        if (ST == null)
            ST = parse("ST=");
        return ST;
    }

    public String getC() {
        if (C == null)
            C = parse("C=");
        return C;
    }

    // Parse the name for the given target
    private String parse(String target) {
        if (nameChar == null)
            nameChar = name.toCharArray();
        char targetChar[] = target.toCharArray();

        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] == targetChar[0]) {
                // Possible match, check further
                boolean found = true;   // At least so far...
                for (int j = 0; j < targetChar.length; j++) {
                    try {
                        if (nameChar[i + j] != targetChar[j]) {
                            // No match, continue on...
                            found = false;
                            break;
                        }
                    } catch (ArrayIndexOutOfBoundsException aioobe) {
                        // No match, and nothing left in nameChar
                        return null;
                    }
                }
                if (found) {
                    int firstPos = i + targetChar.length;
                    int lastPos;
                    int endChar;
                    int endChar2;
                    if (nameChar[firstPos] == '\"'){
                        endChar = '\"';
                        endChar2 = endChar;
                    } else {
                        endChar = ',';
                        endChar2 = '+';
                    }
                    // The substring will be terminated by a quote if
                    // the substring is quoted (CN="My Name",OU=...)
                    // or by a comma otherwise (L=New York,ST=...)
                    // or by the end of the string
                    // A badly formed substring will throw an
                    // ArrayIndexOutOfBoundsException
                    for (lastPos = firstPos + 1;
                                lastPos < nameChar.length; lastPos++) {
                        if ((nameChar[lastPos] == endChar)|| (nameChar[lastPos] == endChar2))
                            break;
                    }
                    // If the lastPos is a quote, then we need to
                    // include it in the string; if it�s a comma then
                    // we don�t
                    return new String(nameChar, firstPos,
                            (endChar == '\"' ?
                                lastPos - firstPos + 1 :
                                lastPos - firstPos));
                }
                // else try the next index
            }
        }
        return null;
    }

    public String toString() {
        getCN();
        getOU();
        getO();
        getL();
        getST();
        getC();
        return "CN=" + CN + ", " +
               "OU=" + OU + ", " +
               "O=" + O + ", " +
               "L=" + L + ", " +
               "ST=" + ST + ", " +
               "C=" + C;
    }
}
