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

/* $Id: DownloadItem.java,v 1.2 2012/02/28 08:21:00 pakj Exp $ */

package org.openoces.opensign.client.applet.bootstrap;

/**
 * This class defines a downloadable item
 *
 * @author Preben Valeur  <prv@tdc.dk>
 */


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

class DownloadItem {
    private String alias; // the name that distinguishes it - if more than one zip-file is relevant (useful for OpenSign.zip - Logon & Sign)
    private String zipFileName;
    private String pluginClassName;
    private Checksum checksum;
    private static String baseUrl = "";

    private static File zipCacheDir;
    private static final int BUFFER_SIZE = 2048;
    private boolean requiresOSSupport = false;
    private boolean isMainItem = false;

//    private PluginInitializer initializer;
    //private Object permissions; // for SUN JVM: PermissionCollection - for MS JVM: com.ms.security.Permissions

    DownloadItem(String alias, String zipFileName, String pluginClassName, Checksum checksum, boolean mainItem) {
        this.alias = alias;
        this.zipFileName = zipFileName;
        this.pluginClassName = pluginClassName;
        this.checksum = checksum;
        this.isMainItem = mainItem;
    }

    boolean isMainItem() {
        return isMainItem;
    }

    boolean getRequiresOSSupport() {
        return requiresOSSupport;
    }

    void setRequiresOSSupport(boolean requiresOSSupport) {
        this.requiresOSSupport = requiresOSSupport;
    }

    String getAlias() {
        return alias;// what is used as key in a hashtable...
    }

    String getName() {
        return zipFileName;
    }

    /**
     * Returns the filename containig the plugin. No parts of the path is returned.
     *
     * @return the filename
     */
    String getFileName() {
        return getName() + ".zip";
    }

    Checksum getChecksum() {
        return checksum;
    }

    String getClassName() {
        return pluginClassName;
    }

    URL getURL() {
        try {
            return new URL(baseUrl + "/" + getFileName());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    void download() throws IOException {
        URLConnection urlCon = getURL().openConnection();
        InputStream is = urlCon.getInputStream();
        int count;
        byte data[] = new byte[BUFFER_SIZE];
        File cacheDir = new File(getChecksumCacheDir());
        if (!cacheDir.exists()) {
            if (!cacheDir.mkdirs()) {
                throw new IOException("Unable to make directory " + cacheDir);
            }
        }
        FileOutputStream fos = new FileOutputStream(this.getCachedAbsoluteFileName());
        BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER_SIZE);
        while ((count = is.read(data, 0, BUFFER_SIZE)) != -1) {
            dest.write(data, 0, count);
        }
        dest.flush();
        dest.close();
    }

    boolean cachedExists() {
        try {
            getAsStream();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    private String getChecksumCacheDir() {
        String checksumDir = checksum.toString();
        return DownloadItem.zipCacheDir + File.separator + checksumDir;
    }

    private String getCachedAbsoluteFileName() {
        return getChecksumCacheDir() + File.separator + this.getFileName();
    }

    InputStream getAsStream() throws FileNotFoundException {
        return new FileInputStream(this.getCachedAbsoluteFileName());
    }

    static void setBaseUrl(String baseUrl) {
        DownloadItem.baseUrl = baseUrl;
    }

    static boolean setZipCacheDir(File dir) {
        zipCacheDir = dir;
        return zipCacheDir.exists() || zipCacheDir.mkdirs();
    }

    static String getBaseUrl() {
        return baseUrl;
    }

}
