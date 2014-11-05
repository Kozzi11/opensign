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

/* $Id: OS.java,v 1.3 2012/10/23 12:52:52 anmha Exp $ */

package org.openoces.opensign.client.applet;

/**
 * This class implements OS specifics
 *
 * @author Jens Bo Friis  <jbf@it-practice.dk>
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 */

import org.openoces.opensign.appletsupport.AppletSupport;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.crypto.CryptoSupport;
import org.openoces.opensign.utils.IOUtils;

import java.applet.Applet;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OS {
    static {
        init();
    }

    private static CryptoSupport cryptoSupport;
    private static AppletSupport appletSupport;
    private static String cacheHome = null;

    protected OS() {}

    public static AppletSupport getAppletSupport() {
        return appletSupport;
    }

    public static void setAppletSupport(AppletSupport support) {
        appletSupport = support;
    }

    public static CryptoSupport getCryptoSupport() {
        return cryptoSupport;
    }

    public static void setCryptoSupport(CryptoSupport support) {
        cryptoSupport = support;
    }


    private static void init() {
        System.getProperty("javaplugin.version");
    }

    public static boolean isOSLinux() {
        return System.getProperty("os.name").toLowerCase().startsWith("linux");
    }

    public static boolean isOSWindows() {
        return System.getProperty("os.name").toLowerCase().startsWith("win");
    }

    public static boolean isOSMac() {
        return System.getProperty("os.name").toLowerCase().startsWith("mac");
    }

    public static boolean is64BitJre() {
        String dataModel = System.getProperty("os.arch");
        return dataModel != null && dataModel.indexOf("64") != -1;
    }

    public static String getFileSeperator() {
        return System.getProperty("file.separator");
    }


    /**
     * Read system property secured by a security manager. The method mandles the 3 different security managers:
     * <ol>
     * <li>Microsoft native JVM using com. ms.security.*
     * <li>Netscape or Java 1 security manager using netscape.security.*
     * <li>Plugin or Java 2 security managers using AccessController.doPriviledge()
     * </ol>
     *
     */

    public static void copyFile(File srcFile, File destFile) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(srcFile));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destFile));
        try {
            int c;

            while ((c = in.read()) != -1)
                out.write(c);

        } finally {
            IOUtils.close(in);
            IOUtils.close(out);
        }
    }

    public static String getBrowser(Applet applet) {
//      String theBrowser = "APPLICATION";
        // we know it is not - so:
        String appletContext = applet.getAppletContext().toString();
        String theBrowser = appletContext;
        if (appletContext.startsWith("sun.applet.AppletViewer"))
            theBrowser = "APPLETVIEWER";
        else if (appletContext.startsWith("netscape.applet."))
            theBrowser = "NETSCAPE";
        else if (appletContext.startsWith("com.ms.applet."))
            theBrowser = "MICROSOFT";
        else if (appletContext.startsWith("sunw.hotjava.tags.TagAppletPanel"))
            theBrowser = "HOTJAVA";
        else if (appletContext.startsWith("sun.plugin.navig.win32.AppletPlugin"))
            theBrowser = "NETSCAPEPLUGIN";
        else if (appletContext.startsWith("sun.plugin.ocx.ActiveXApplet"))
            theBrowser = "MICROSOFTPLUGIN";
        else if (appletContext.startsWith("sun.plugin.viewer.context.IExplorerAppletContext"))
            theBrowser = "MICROSOFTPLUGINJRE1.4";
        else if (appletContext.startsWith("com.opera."))
            theBrowser = "OPERA";
        return theBrowser;
    }

    public static String getUserHome() {
        return nullChecked(System.getProperty("user.home"));
    }
    
    public static String getProgramFilesDir() {
    	return System.getenv("ProgramFiles");
    }
    
    public static String getProgramFilesDir32bit() {
    	return getProgramFilesDir() +" " +"(x86)";
    }

    private static String nullChecked(String prop) {
        for (int i = 0; i < prop.length(); i++) {
            if (prop.charAt(i) == '\0') {
                throw new RuntimeException("Illegal null char present");
            }
        }
        return prop;
    }

    public static String getCacheHome() {
        if (cacheHome == null)
            updateCacheHome();
        return cacheHome;
    }

    private static void updateCacheHome() {

        // Check if already defined writeable directory
        if (cacheHome != null)
            return;

        // Check if home dir is writeable
        cacheHome = getUserHome();

        // In protected mode in IE7 home directory is not writeable - in this
        // case we find temporary cache directory for writing
        if (!isDirWriteable(cacheHome)) {

            FileLog
                    .error("Home directory "
                            + cacheHome
                            + " not writeable - finding temporary directory to use for cache");

            String tempDir = null;

            for (int i = 0; i < 3; i++) {

                tempDir = null;

                try {

                    if (i == 0) {
                        File tempfile = File.createTempFile("oces", null);
                        tempDir = tempfile.getPath();
                    } else if (i == 1)
                        tempDir = cacheHome + File.separator + "AppData"
                                + File.separator + "local" + File.separator
                                + "temp" + File.separator + "low";
                    else if (i == 2)
                        tempDir = cacheHome + File.separator + "AppData"
                                + File.separator + "locallow";

                    tempDir = tempDir.replace('\\', '/');
                    if (tempDir.lastIndexOf('/') == tempDir.length() - 1)
                        tempDir = tempDir.substring(0, tempDir
                                .lastIndexOf('\\'));
                } catch (Exception e) {
                }

                // Temp dir not writeable
                if (tempDir == null)
                    continue;
                if (isDirWriteable(tempDir))
                    break;

                FileLog.error("Temp directory " + tempDir + " not writeable");
            }

            if (tempDir != null)
                cacheHome = tempDir;
        }

        FileLog.error("Using directory " + cacheHome + " for cache");
    }

    private static boolean isDirWriteable(String dir) {
        OutputStream out = null;
        try {
            File d = new File(dir);
            if (!d.exists())
                d.mkdirs();

            out = new BufferedOutputStream(new FileOutputStream(
                    dir + File.separator + "temp.dat"));
            out.write((byte) 0);
        } catch (Exception e) {
            FileLog.info("Directory " + dir + " is not writeable: "
                    + e.getMessage());
            return false;
        } finally {
            IOUtils.close(out);
        }
        return true;
    }
}
