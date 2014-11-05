/*
	Copyright 2011 Daniel Andersen
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

 * @author Daniel Andersen <daand@nets.eu>
 * @author Anders M. Hansen <consult@ajstemp.dk>
*/

package org.openoces.opensign.certificate.plugin.cryptoki;

import java.io.File;
import java.util.Properties;

import org.openoces.opensign.utils.FileLog;


public class ProviderInformation {
    private String providerName;
    private String libraryPath;
    private Sha256Checksum libraryChecksum;
    private String[] expectedLibraryChecksums;

    public ProviderInformation(String providerName, String libraryPath, String[] expectedLibraryChecksums) {
        this.providerName = providerName;
        this.libraryPath = libraryPath;
        this.expectedLibraryChecksums = expectedLibraryChecksums;
    }
    
    public ProviderInformation(String providerName, String libraryPath) {
        this.providerName = providerName;
        this.libraryPath = libraryPath;
    }
    
    public ProviderInformation(Properties properties) {
        providerName = properties.getProperty(ConfigurationManager.PROP_PROVIDER_NAME);
        libraryPath = properties.getProperty(ConfigurationManager.PROP_LIBRARY_PATH);
    }

    public final Properties toProperties() {
        Properties properties = new Properties();
        properties.setProperty(ConfigurationManager.PROP_PROVIDER_NAME, providerName);
        properties.setProperty(ConfigurationManager.PROP_LIBRARY_PATH, libraryPath);
        return properties;
    }

    public final void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public final String getProviderName() {
        return providerName;
    }

    public final String getLibraryPath() {
        return libraryPath;
    }

    public final boolean exists() {
        File file = new File(libraryPath);
        return file.exists();
    }

    private final String getChecksum() {
    	try {
    		if (libraryChecksum == null) {
    			libraryChecksum = new Sha256Checksum(libraryPath);
    			FileLog.debug("Calculated library checksum: " + libraryChecksum.toString());
    		}
    		return libraryChecksum.toString();
		} catch (Exception e) {
			FileLog.warn("Checksum validation failed on PKCS11 module: " + libraryPath);
		}
    	return "";
    }
    
    public boolean isChecksumValid() {
    	if (expectedLibraryChecksums.length == 0) {
    		return true;
    	}
    	for (String expectedLibraryChecksum : expectedLibraryChecksums) {
    		if (expectedLibraryChecksum.equals(getChecksum().toString())) {
    			return true;
    		}
		}
    	return false;
    }
}
