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

 @author: Michael Martinsen (mmart)
 @auther: Søren Dines Jensen sdj@miracleas.dk
 *
 */

package org.openoces.opensign.pdf.validator;


import java.util.ArrayList;

/**

 */
public class PDFValidatorFactory {

    private static ArrayList<String> availVersions = new ArrayList<String>() {{
        add("2.0.0");
    }};


    public static PDFValidatorFactory getInstance() {
        return new PDFValidatorFactory();
    }

    private PDFValidatorFactory() {

    }

    public static ArrayList<String> getAllVersionsInternal() {
        ArrayList<String> internalVersion = new ArrayList<String>();
        for (String vers : availVersions) {
            internalVersion.add(vers.replaceAll("\\.", ""));
        }
        return internalVersion;
    }

    public static ArrayList<String> getAllVersions() {

        return availVersions;
    }


    public PDFValidator getPDFValidator(String version) throws Exception {
        String versionStr = version.replaceAll("\\.", "");
        if (!getAllVersionsInternal().contains(versionStr)) {
            throw new UnsupportedVersionException("Version " + version + " is not supported. Available versions are " + getAllVersions());
        }
        Class validatorClass;
        try {
            validatorClass = Class.forName("org.openoces.opensign.pdf.validator.v" + versionStr + ".PDFValidatorImpl");
        } catch (Exception ee) {
            throw new UnsupportedVersionException("Version " + version + " is not supported. Available versions are " + getAllVersions());
        }
        try {
            return (PDFValidator) validatorClass.newInstance();
        } catch (Exception e) {
            throw new UnsupportedVersionException("Version " + version + " is not supported. Available versions are " + getAllVersions());
        }
    }

    /**
     * Get latest version
     *
     * @return a PDFvalidator
     */
    public PDFValidator getPDFValidator() throws Exception {
        return getPDFValidator(getAllVersions().get(getAllVersions().size() - 1));
    }
}