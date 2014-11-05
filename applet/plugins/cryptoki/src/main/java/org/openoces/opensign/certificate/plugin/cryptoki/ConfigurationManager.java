/*
	Copyright 2011 Daniel Andersen
	Copyright 2012 Anders M. Hansen
	

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

import org.openoces.opensign.client.applet.OS;
import org.openoces.opensign.utils.FileLog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class ConfigurationManager {
	private static final String configName = "cryptoki.cfg";
	 
    public static final String PROP_PROVIDER_NAME = "providerName";
    public static final String PROP_LIBRARY_PATH = "libraryPath";

    private List<ProviderInformation> providers;
    private List<ProviderInformation> currentProviders = new ArrayList<ProviderInformation>();
    
    static final String DRIVER_WIN32_DOTNET 		= "\\Gemalto\\DotNet PKCS11\\gtop11dotnet.dll";
    static final String DRIVER_WIN64_DOTNET 		= "\\Gemalto\\DotNet PKCS11\\gtop11dotnet64.dll";
    static final String DRIVER_MAC 					= "/Library/Frameworks/GemaltoPKCS11DotNetV2.framework/GemaltoPKCS11DotNetV2";
    static final String DRIVER_LINUX 				= "/usr/local/lib/pkcs11/libgtop11dotnet.so";

    public ConfigurationManager() {
    	if(!load()){
    		populateProviderList();
    	}
    }

    public final void findProvider() {
        FileLog.debug("Searching for PKCS#11 providers...");
        if (!isConfigured()) {
        	searchProviders();
        }
        if (isConfigured()) {
            FileLog.debug("PKCS#11 provider found: " + currentProviders.size());
        } else {
            FileLog.info("No PKCS#11 provider/driver found");
        }
    }

    public final List<ProviderInformation> getCurrentProviders() {
        return currentProviders;
    }

    public final boolean isConfigured() {
        return !currentProviders.isEmpty();
    }

    private void searchProviders() {
        for (ProviderInformation provider : providers) {
            if (provider.isChecksumValid()) {
                currentProviders.add(provider);
            }
        }
    }
    
    /**
     * Load PKCS#11 Provider (name and path) from config file if it exists
     */
    public final boolean load() {
        Properties properties = new Properties();
        try {
            InputStream in = new FileInputStream(getConfigPath());
            properties.load(in);
            in.close();
            ProviderInformation provider = new ProviderInformation(properties);
            return currentProviders.add(provider);
        } catch (Exception e) {
            FileLog.info("No PKCS#11 configuration found");
            return false;
        }
    }
    
    public final boolean save(ProviderInformation provider) {
        Properties properties = provider.toProperties();
        try {
            File file = new File(getConfigDirectory());
            if (!file.exists()) {
                file.mkdir();
            }
            OutputStream out = new FileOutputStream(getConfigPath());
            properties.store(out, "CryptokiApplet properties");
            out.close();
            return true;
        } catch (Exception e) {
            FileLog.warn("Cannot save PKCS#11 configuration to file system", e);
            return false;
        }
    }
    
    private void populateProviderList() {
        providers = new LinkedList<ProviderInformation>();
        if (OS.isOSWindows()) {
        	//IDGo500_PKCS11_2.2.0_build012_Win
        	addDriverIfExists(providers, "Gemalto .NET", OS.getProgramFilesDir()+DRIVER_WIN32_DOTNET, "a3a373d673c1046b29c58ecf1b955c951753faf5a19fa60d23803d84e44a2e45"); //32 bit
        	addDriverIfExists(providers, "Gemalto .NET", OS.getProgramFilesDir32bit()+DRIVER_WIN32_DOTNET, "a3a373d673c1046b29c58ecf1b955c951753faf5a19fa60d23803d84e44a2e45"); //32 bit
        	addDriverIfExists(providers, "Gemalto .NET", OS.getProgramFilesDir()+DRIVER_WIN64_DOTNET, "2adc520d5e4e7a5f0b1407ecfd570632657953075fbec3a5ff0a5bc47dcbba95"); //64 bit
        	addDriverIfExists(providers, "Gemalto .NET", OS.getProgramFilesDir32bit()+DRIVER_WIN64_DOTNET, "2adc520d5e4e7a5f0b1407ecfd570632657953075fbec3a5ff0a5bc47dcbba95"); //64 bit
        	//IDGo500_p11_230_Windows
        	addDriverIfExists(providers, "Gemalto .NET", OS.getProgramFilesDir()+DRIVER_WIN32_DOTNET, "aa6c4ab518ae69ed45ea5b4731ccfe31aebb71dbfd4e292abcb007c3a6485411"); //32 bit
        	addDriverIfExists(providers, "Gemalto .NET", OS.getProgramFilesDir32bit()+DRIVER_WIN32_DOTNET, "aa6c4ab518ae69ed45ea5b4731ccfe31aebb71dbfd4e292abcb007c3a6485411"); //32 bit
        	addDriverIfExists(providers, "Gemalto .NET", OS.getProgramFilesDir()+DRIVER_WIN64_DOTNET, "273dffaaaba0b2028abd6cac9940b4f5fa11e1c60a9e2ae34cd6970c25e6614e"); //64 bit
        	addDriverIfExists(providers, "Gemalto .NET", OS.getProgramFilesDir32bit()+DRIVER_WIN64_DOTNET, "273dffaaaba0b2028abd6cac9940b4f5fa11e1c60a9e2ae34cd6970c25e6614e"); //64 bit
        } else if (OS.isOSMac()) {
        	addDriverIfExists(providers, "Gemalto .NET", DRIVER_MAC, "43e33838c7da44a910bf01648c8837bb89b1d0b7fab95ac496907e5207492edb"); //Gemalto.IDGo500.PKCS11.V2.2
        	addDriverIfExists(providers, "Gemalto .NET", DRIVER_MAC, "d9c31b954af04ed984ce43e8209d643707fbd3b36bdb6cf069ff9d77e0ea0316"); //6665_IDGo500_PKCS11_2.3_MountainLion
        } else if (OS.isOSLinux()) {
        	addDriverIfExists(providers, "Gemalto .NET", DRIVER_LINUX);
        }
    }

    private void addDriverIfExists(List<ProviderInformation> providers, String providerName, String driverPath, String ... expectedLibraryChecksum) {
    	if (driverExists(driverPath)) {
    		providers.add(new ProviderInformation(providerName, driverPath, expectedLibraryChecksum));
    	} else {
    		FileLog.info("Driver not found " + driverPath);
    	}
    }
    
    private boolean driverExists(String driverPath) {
    	return new File(driverPath).exists();
    }

    public final void resetConfiguration() {
        FileLog.debug("Resetting PKCS#11 configuration...");
        currentProviders.clear();
    }
    
    public final boolean setProviderFromLibrary(String path) {
        ProviderInformation provider = new ProviderInformation("Unknown", path);
        save(provider);
        resetConfiguration();
        return currentProviders.add(provider);
    }
    
    private String getConfigPath() {
        return getConfigDirectory() + File.separator + configName;
    }

    private String getConfigDirectory() {
        return OS.getUserHome() + File.separator + ".oces";
    }
    
    protected void finalize() {
    	FileLog.debug("ConfigurationManager Finalized");
    }
}