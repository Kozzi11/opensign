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

/* $Id: ASN1.java,v 1.2 2012/02/28 08:21:58 pakj Exp $ */

package org.openoces.opensign.certificate.plugin.cdcard.util;

/**
 * This class represents ASN1 functionality
 *
 * simple conversion of parts of JavaScript ASN1-parser from
 * http://www.geocities.co.jp/SiliconValley-SanJose/3377/asn1JS.html
 *
 * @author Preben Valeur  <prv@tdc.dk>
 */

import org.openoces.opensign.utils.Base64;

import java.util.ArrayList;

public class ASN1 {
    private static final int Bitstring_hex_limit = 4;

    private static final String [] NAME = {"Unsupported Tag",
                                   "BOOLEAN",
                                   "INTEGER",
                                   "BITSTRING",
                                   "OCTETSTRING",
                                   "NULL",
                                   "OBJECTIDENTIFIER",
                                   "ObjectDescripter",
                                   "Unsupported Tag",
                                   "Unsupported Tag",
                                   "Unsupported Tag",
                                   "Unsupported Tag",
                                   "UTF8String",
                                   "Unsupported Tag",
                                   "Unsupported Tag",
                                   "Unsupported Tag",
                                   "SEQUENCE",
                                   "SET",
                                   "NumericString",
                                   "PrintableString",
                                   "TeletexString",
                                   "Unsupported Tag",
                                   "IA5String",
                                   "UTCTime",
                                   "GeneralizedTime"
    };
/*
    ID['BOOLEAN']          = 0x01;
    ID['INTEGER']          = 0x02;
    ID['BITSTRING']        = 0x03;
    ID['OCTETSTRING']      = 0x04;
    ID['NULL']             = 0x05;
    ID['OBJECTIDENTIFIER'] = 0x06;
    ID['ObjectDescripter'] = 0x07;
    08
    09
    0a
    0b
    ID['UTF8String']       = 0x0c;
    0d
    0e
    0f
    ID['SEQUENCE']         = 0x10;
    ID['SET']              = 0x11;
    ID['NumericString']    = 0x12;
    ID['PrintableString']  = 0x13;
    ID['TeletexString']    = 0x14;
    15
    ID['IA5String']        = 0x16;
    ID['UTCTime']          = 0x17;
    ID['GeneralizedTime']  = 0x18;
*/

    protected ASN1() {}

    public static void parseb64(String b64cert, ASN1Listener listener){
        byte[] base64decoded = Base64.decode(b64cert.getBytes());
        parsehex(bin2hex(base64decoded), listener);
    }

    private static String bin2hex(byte [] hex){
        StringBuffer buf = new StringBuffer();
        for (byte aHex : hex) {
            buf.append(tohex(aHex, 2));
        }
        return buf.toString();
    }
    /* taken from hexdump */
    private static char[] hexstr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    private static String tohex(int value, int digits) {
        char[] ret = new char[digits];
        int n;
        byte a;

        for (n = 0; n < digits; n++) {
            a = (byte) (value & 0x0F);
            value >>= 4;

            ret[digits - n - 1] = hexstr[a];
        }

        return String.valueOf(ret);
    }
    /* end taken from hexdump */

    private static void parsehex(String src,  ASN1Listener listener){
        if ( src.length() % 2 != 0 ){
            listener.error("Illegal length. Hex string\'s length must be even.");
        }
        parseASN1(src, listener);
    }

    private static void parseASN1(String data, ASN1Listener listener){
        int point = 0;
        while ( point < data.length() ){

            // Detecting TAG field (Max 1 octet)

            int tag10 = Integer.parseInt( data.substring(point, point+2),16);
            int isSeq = tag10 & 32;
            int isContext = tag10 & 128;
            int tag = tag10 & 31;
            String tagName;
            if (tag > NAME.length){
                tagName = "Unsupported_TAG";
            } else {
                tagName = (isContext!= 0) ? "[" + tag + "]" : NAME[tag];
            }

            point += 2;

            // Detecting LENGTH field (Max 2 octets)

            int len = 0;
            if ( tag != 0x5){	// Ignore NULL
                if ( (Integer.parseInt(data.substring(point, point+2), 16) & 128) != 0){
                    int lenLength = Integer.parseInt(data.substring(point, point+2), 16) & 127;
                    if ( lenLength > 2 ){
                        String error_message = "LENGTH field is too long.(at " + point
                         + ")\nThis program accepts up to 2 octets of Length field.";
                        listener.error(error_message);
                        return;
                    }
                    len = Integer.parseInt(data.substring( point+2, point+2+lenLength*2), 16);
                    point += lenLength*2 + 2;  // Special thanks to Mr.(or Ms.) T (Mon, 25 Nov 2002 23:49:29)
                }
                else
//                    if ( lenLength != 0 )
                    {  // Special thanks to Mr.(or Ms.) T (Mon, 25 Nov 2002 23:49:29)
                    len = Integer.parseInt(data.substring(point, point+2),16);
                    point += 2;
                }

                if ( len > data.length() - point ){
                    String error_message = "LENGTH is longer than the rest.\n" + "(LENGTH: " + len + ", rest: " + data.length() + ")";

                    listener.error(error_message);
                    return;
                }
            }
            else{
                point += 2;
            }

            // Detecting VALUE

            String val = "";
            if ( len != 0){
                val = data.substring( point, point+len*2);
                point += len*2;
            }

            if ( isSeq != 0 ){
                listener.beginTag(tagName);
                parseASN1(val, listener);
                listener.endTag(tagName);
            } else {
                String value = getValue( isContext != 0 ? 4 : tag , val);
                listener.tag(tagName, value);
            }
        }
    }

    private static String getValue(int tag, String data){
        StringBuffer ret = new StringBuffer();

        if (tag == 1)/*boolean*/{
            ret.append(Boolean.valueOf(data) ? "TRUE" : "FALSE");
        }
        else if (tag == 2)/*integer*/{
            // prv: always return as hex
            ret.append(data.trim());
        }
        else if (tag == 3)/*octetstring*/{
            int unUse = Integer.parseInt(data.substring(0, 2), 16);
            String bits  = data.substring(2);

            if ( bits.length() > Bitstring_hex_limit ){
                ret.append("0x").append(bits);
            }
            else{
                ret.append(Integer.toString(Integer.parseInt(bits, 16), 2));
            }
            ret.append(" : ").append(unUse).append(" unused bit(s)");
        }
        else if (tag == 5){
            ret.append("");
        }
        else if (tag == 6){
            ArrayList<Integer> res = new ArrayList<Integer>();
            int d0 = Integer.parseInt(data.substring(0, 2), 16);
            int res0 = (int)Math.floor(d0 / 40);
            res.add(res0);
            res.add(d0 - res0 * 40);

            ArrayList<Integer> stack = new ArrayList<Integer>();
            int powNum = 0;
            for(int i=1; i < data.length() -2; i=i+2){
                int token = Integer.parseInt(data.substring(i+1, i+1+2), 16);
                stack.add(token & 127);

                if ( (token & 128) != 0 ){
                    powNum++;
                }
                else{
                    int sum = 0;
                    for (Integer aStack : stack) {
                        sum += aStack * Math.pow(128, powNum--);
                    }
                    res.add(sum);
                    powNum = 0;
                    stack = new ArrayList<Integer>();
                }
            }
            for (int i=0; i<res.size(); i++){
                if (i>0) ret.append(".");
                ret.append(res.get(i));
            }
        }
        else if (NAME[tag].endsWith("Time") ||NAME[tag].endsWith("String")) {
            int k = 0;
            ret.append("'");
            while ( k < data.length() ){
                ret.append(new Character( (char)Integer.parseInt(data.substring(k, k+2),16)));
                k += 2;
            }
            ret.append("'");
         }
        else{
            ret.append(data);
        }
        return ret.toString();
    }

}

