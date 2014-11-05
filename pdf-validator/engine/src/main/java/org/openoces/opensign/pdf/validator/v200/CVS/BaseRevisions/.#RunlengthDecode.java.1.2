/**
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
 @author sdj sdj@miracleas.dk
 */
package org.openoces.opensign.pdf.validator.v200;

import java.io.ByteArrayOutputStream;


public class RunlengthDecode {

    public static byte[] decodeRunLength(byte[] data) {

        ByteArrayOutputStream bos;

        int counter;
        int lengthElement;
        int element;

        counter = data.length;
        bos = new ByteArrayOutputStream(counter);

        for (int i = 0; i < counter; i++) {

            lengthElement = data[i];

            if (lengthElement < 0)
                lengthElement = 256 + lengthElement;

            if (lengthElement == 128) {

                i = counter;

            } else if (lengthElement > 128) {
                i++;
                lengthElement = 257 - lengthElement;
                element = data[i];
                for (int idx = 0; idx < lengthElement; idx++){
                    bos.write(element);
                }

            } else {
                i++;
                lengthElement++;
                for (int idx = 0; idx < lengthElement; idx++){
                    element = data[i + idx];
                    bos.write(element);
                }
                i = i + lengthElement - 1;
            }
        }
        return bos.toByteArray();

    }

}
