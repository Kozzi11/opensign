/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard
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
 */

/* $Id: BootApplet.java,v 1.7 2013/03/05 11:24:21 anmha Exp $ */

package org.openoces.opensign.client.applet.bootstrap;

/**
 * This class implements the initial bootstrapping part of OpenSign
 *
 * @author Preben Valeur  <prv@tdc.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 * @author Mads Jensen <mjn@trifork.com>
 * @author Jeppe Burchhardt <Jeppe.Burchhardt@Cryptomathic.com>
 * @author Ole Friis Østergaard <ofo@trifork.com>
 * @author Anders M. Hansen <consult@ajstemp.dk>
 */

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.List;

public final class BootApplet extends JApplet implements ServiceProxyApplet, Observer {
    private JApplet realApplet = null;
    ClassLoader appletLoader;
    ClassLoader realAppletClassLoader;
    ClassLoader extraLoader;
    
    private static final String ZIP_FILE_ALIAS_PARAM = "ZIP_FILE_ALIAS";

    // list of zip-files separated by ; in the form:
    // bla.zip,dk.oces.Register;bla2.zip,bla.bla.Init
    private static final String SUN_SUPPORT_PARAM = "SUN_SUPPORT";
    private static final String APPLET_SUPPORT_PARAM = "APPLET_SUPPORT";// depending
    // on
    // the
    // Applet
    // -
    // currently
    // we do
    // it
    // for
    // OpenMail
    private static final String EXTRA_FILE_NAME_PARAM = "EXTRA_ZIP_FILE_NAMES";
    private static final String ZIP_BASE_URL_PARAM = "ZIP_BASE_URL";
    private static final String LOG_LEVEL_PARAM = "LOG_LEVEL";

    private final Map<String, DownloadItem> downloadTable = new HashMap<String, DownloadItem>();

    private final List<DownloadItem> pluginItems = new ArrayList<DownloadItem>();
    private final List<Object> plugins = new ArrayList<Object>();

    private DownloadItem mainItem = null;
    private Thread installerThread;

    /**
     * This is really what needs to be signed! The description of the potential
     * files with checksums and classnames. The names in the applet-tag will be
     * the ones to download.
     */
    private static final String INTERFACE_PACKAGE = "org.openoces.opensign.client.applet.interfaces.";
    private static final String SERVICE_INTERFACE = INTERFACE_PACKAGE
            + "ServiceApplet";
    private static final String POLLING_INTERFACE = INTERFACE_PACKAGE
            + "PollingApplet";
    private static final String PLUGAWARE_INTERFACE = INTERFACE_PACKAGE
            + "Plugaware";
    private Method doServiceMethod;
    private Method getAppletStateMethod;
    private Method getOutputDataMethod;

    public BootApplet() throws Exception {
    }

    public void init() {
        String loglevel = getParameter(LOG_LEVEL_PARAM);
        if (loglevel == null) {
            FileLog.setVerbose(FileLog.Level.INFO);
        } else if (loglevel.equalsIgnoreCase("debug")) {
            FileLog.setVerbose(FileLog.Level.DEBUG);
        } else if (loglevel.equalsIgnoreCase("info")) {
            FileLog.setVerbose(FileLog.Level.INFO);
        } else if (loglevel.equalsIgnoreCase("warn")) {
            FileLog.setVerbose(FileLog.Level.WARN);
        } else if (loglevel.equalsIgnoreCase("error")) {
            FileLog.setVerbose(FileLog.Level.ERROR);
        } else if (loglevel.equalsIgnoreCase("fatal")) {
            FileLog.setVerbose(FileLog.Level.FATAL);
        } else {
            FileLog.setVerbose(FileLog.Level.INFO);
        }

        FileLog.info("selected loglevel: " + FileLog.getVerbose());
        FileLog.info("Timing: BootApplet.init():" + new Date());

        String jnlpJvm = System.getProperty("jnlpx.jvm");
        if (jnlpJvm != null && jnlpJvm.length() > 0) {
            FileLog.info("BootApplet.init - running as Java Webstart (" + jnlpJvm + ").");
            return; //For security reasons the applet is not allowed to run as a Java Webstart application.
        }

        initDownloadItems();
        setZipBaseUrl();

        initGui();
    }

    public void start() {
        GuiUtil.showProgressUI(this, true);
        loadChecksummedZips();
    }

    public void stop() {
    	try {
    		shutdown();
    	} finally {
    		super.stop();
    	}
    }

    public void destroy() {
    	try {
    		shutdown();
    	} finally {
    		super.destroy();
    		System.runFinalization();
    		System.gc();
    	}
    }

    private void shutdown() {
    	stopInstaller();
        if(plugins != null) {
        	for(Object plugin : plugins) {
        		callDestroyMethod(plugin); //call destroy method on all plugins
        	}
        	plugins.clear();
        }
        if(pluginItems != null) {
        	pluginItems.clear();
        }
        if(downloadTable != null) {
        	downloadTable.clear();
        }
        extraLoader = null;        
        if (realApplet != null) {
        	realApplet.setStub(null);
            realApplet.stop();
            if (realApplet != null) {
    			realApplet.destroy();
    			realApplet = null;
    		}
        }
        appletLoader = null;
        realAppletClassLoader = null;
        if(rootPane != null) {
        	rootPane.removeAll();
        	rootPane = null;
        }
        mainItem = null;
        getOutputDataMethod = null;
        getAppletStateMethod = null;
        doServiceMethod = null;
        setFocusTraversalPolicy(null);
        removeAll();
        setStub(null);
        setLayout(null);
        setRootPane(null);
        setComponentOrientation(null);
        setGlassPane(null);
    }
    
    private void callDestroyMethod(Object plugin) {
        try {
            Class pluginClass = plugin.getClass();
            Method destroyMethod = pluginClass.getMethod("destroy", new Class[0]);
            destroyMethod.invoke(plugin, null);
            plugin = null;
        } catch (NoSuchMethodException e) {
            FileLog.debug("The plugin " + plugin.getClass() + " has not a destroy method!!!");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public void doService() {
        if (realApplet != null && doServiceMethod != null) {
            try {
                doServiceMethod.invoke(realApplet, null);
            } catch (Exception e) {
                FileLog.error("Applet is not a ServiceApplet " + e.getMessage(), e);
            }
        }
    }

    private void initDownloadItems() {
        List<DownloadItem> downloadItems = OpenSignDownloads.getDownloadItems();
        for (DownloadItem downloadItem : downloadItems) {
            downloadTable.put(downloadItem.getAlias(), downloadItem);
        }
    }

    // TODO: check it is not called from constructor/init() directly - MS makes
    // exception then!
    private boolean isSigned() {
        // check if we are part of a signed applet and tell the user if this is
        // not the case
        try {
            System.getProperty("user.name");
            return true;
        } catch (SecurityException e) {
            return false;
        }
    }

    private void showUnsignedMessage() {
        GuiUtil.doInGui(new GuiCallback() {
            public void doInGui() {
                Container appletContainer = getContentPane();
                appletContainer.removeAll();
                appletContainer.setLayout(new BorderLayout());

                JPanel panel = new JPanel();
                panel.setBackground(Color.white);
                panel.add(new JLabel("This applet was not allowed to run as a signed applet."));

                appletContainer.add(panel, BorderLayout.CENTER);

                appletContainer.validate();
                appletContainer.repaint();

            }
        });
    }

    private void loadChecksummedZips() {
        try {
            checkEnvironment();
            if (!isSigned()) {
                showUnsignedMessage();
                return;
            }

            prepareDownload();
            installerThread = new Thread(new InstallerService(this),"InstallerServiceThread");
            installerThread.start();
        } catch (Exception e) {
            // show error msg
            FileLog.error("Failed to prepare download :" + e.getMessage(), e);
            whenFailed(e);
        }
    }
    
    private void stopInstaller() {
    	if(installerThread != null && installerThread.isAlive()) {
    		FileLog.debug("Stopping "+installerThread.getName());
    		installerThread.interrupt();
    		installerThread = null;
    	}
    }

    private void checkEnvironment() throws Exception {
        // determine which OS and JVM etc
        FileLog.info("This browser is a " + OS.getBrowser(this));
        if (OS.isJavaPlugin()) {
            String version = OS.getJavaPluginVersion();
            if (version.indexOf("1.3.") != -1) {
                throw new Exception("Java plugin 1.3.x is not supported");
            }
            if (version.indexOf("1.4.") != -1) {
                throw new Exception("Java plugin 1.4.x is not supported");
            }
        } else if (OS.getBrowser(this).equals("OPERA")) {
            // wonder if it runs - and if it supports pluginversion:
            String version = OS.getJavaPluginVersion();
            FileLog.info("This is an OPERA with java plugin version: "
                    + version);
        } else if (OS.getBrowser(this).equals("APPLETVIEWER")) {
            // not needed?
        } else {
            throw new Exception("Unknown or unsupported Java environment");
        }
    }

    /**
     * Check if any of the zip-files requires OS support lib to be loaded
     *
     * @return whether OS support plugin is needed
     */
    private boolean getRequiresOSSupport() {
        // check what support is needed by the extra zip files (dependencies)
        String extraZipFiles = getParameter(EXTRA_FILE_NAME_PARAM);
        if (extraZipFiles != null) {
            StringTokenizer strTok = new StringTokenizer(extraZipFiles, ",");
            while (strTok.hasMoreTokens()) {
                String pluginFileName = strTok.nextToken();
                DownloadItem pluginItem = downloadTable.get(pluginFileName);
                if (pluginItem.getRequiresOSSupport()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the codebase and corrects for -1 port
     *
     * @return codeBase() with -1 or default port removed.
     */
    private static String getCodeBaseWithoutMinusOnePort(URL codeBase,
                                                         boolean appendCodeBasePath) {
        String vurl = codeBase.getProtocol() + "://" + codeBase.getHost();
        if (codeBase.getProtocol().equalsIgnoreCase("http")
                && codeBase.getPort() != 80 && codeBase.getPort() != -1
                || codeBase.getProtocol().equalsIgnoreCase("https")
                && codeBase.getPort() != 443 && codeBase.getPort() != -1) {
            vurl = vurl + ":";
            vurl = vurl + codeBase.getPort();
        }

        if (appendCodeBasePath) {
            // getPath() not implemented in 1.1.4 so:
            // location of first "/" since location of host:

            String codeBaseString = codeBase.toString();
            int pathSearchBegin = codeBaseString.indexOf(codeBase.getHost());
            int pathBegin = codeBaseString.indexOf('/', pathSearchBegin);
            vurl = vurl + codeBaseString.substring(pathBegin);
        }
        return vurl;
    }

    private void setZipBaseUrl() {
        String zipBaseUrl = getParameter(ZIP_BASE_URL_PARAM);
        if (zipBaseUrl == null) {
            // if not set: assume relative to here codeBase or documentBase
            zipBaseUrl = getCodeBaseWithoutMinusOnePort(getCodeBase(), true);
            FileLog.info("ZIP BASE not set - relative to codeBase - set to " + zipBaseUrl);
        } else if (zipBaseUrl.startsWith("/")) {
            // relative to this server root
            String prefix = getCodeBaseWithoutMinusOnePort(getCodeBase(), false);
            zipBaseUrl = prefix + zipBaseUrl;
            FileLog
                    .info("ZIP BASE server root relative - set to "
                            + zipBaseUrl);

        } else if (zipBaseUrl.startsWith("http")) {
            // absolute
            FileLog.info("ZIP BASE absolute - unchanged " + zipBaseUrl);
        } else {
            // assume relative to here codeBase or documentBase
            zipBaseUrl = getCodeBaseWithoutMinusOnePort(getCodeBase(), true)
                    + zipBaseUrl;
            FileLog
                    .info("ZIP BASE relative to codeBase - set to "
                            + zipBaseUrl);
        }
        DownloadItem.setBaseUrl(zipBaseUrl);
    }

    private void prepareDownload() throws Exception {
        if (!DownloadItem.setZipCacheDir(getPluginCacheDir())) {
            throw new Exception("Unable to create zip cache dir "
                    + getPluginCacheDir());
        }
        String zipFileAlias = getParameter(ZIP_FILE_ALIAS_PARAM);
        // check if relative or absolute:
        mainItem = downloadTable.get(zipFileAlias);
        if (mainItem == null || !mainItem.isMainItem()) {
            throw new Exception("Failed to get main item from alias: "
                    + zipFileAlias);
        }

        if (getRequiresOSSupport()) {
            if (OS.isJavaPlugin()
                    || OS.getBrowser(this).equals("APPLETVIEWER")
                    || OS.getBrowser(this).equals("OPERA")) {
                String sunSupportZip = getParameter(SUN_SUPPORT_PARAM);
                if (sunSupportZip != null && sunSupportZip.length() > 0) {
                    DownloadItem supportItem = downloadTable.get(sunSupportZip);
                    if (supportItem == null) {
                        throw new Exception("Unable to find support item "
                                + sunSupportZip);
                    }
                    pluginItems.add(supportItem);
                }
            }
        }
        String extraZipFiles = getParameter(EXTRA_FILE_NAME_PARAM);
        if (extraZipFiles != null) {
            StringTokenizer strTok = new StringTokenizer(extraZipFiles, ",");
            while (strTok.hasMoreTokens()) {
                String pluginFileName = strTok.nextToken();
                DownloadItem pluginItem = downloadTable.get(pluginFileName);
                if (pluginItem == null) {
                    throw new Exception(
                            "Failed to get plugin item from alias: "
                                    + pluginFileName);
                }
                pluginItems.add(pluginItem);
            }
        }
        // see if any extra support is needed
        String appletSupportZip = getParameter(APPLET_SUPPORT_PARAM);
        if (appletSupportZip != null && appletSupportZip.length() > 0) {
            DownloadItem supportItem = downloadTable.get(appletSupportZip);
            if (supportItem == null) {
                throw new Exception("Unable to find support item "
                        + appletSupportZip);
            }
            pluginItems.add(supportItem);
        }
    }


    public void update(Observable observable, Object obj) {
    }

    private void initGui() {
        GuiUtil.setOldGlassPane(getGlassPane());

        JPanel panel = new JPanel();
        panel.setBackground(Color.white);

        Container appletContainer = getContentPane();
        appletContainer.setLayout(new BorderLayout());
        appletContainer.add(panel, BorderLayout.CENTER);
    }

    boolean loadItems() throws IOException, NoSuchAlgorithmException {
        appletLoader = getClassLoader(mainItem, this);

        Class appletClass;
        try {
            appletClass = appletLoader.loadClass(mainItem.getClassName());
        } catch (Exception ex) {
            FileLog.debug("Load failed", ex);
            return false;
        }
        try {
            realApplet = (JApplet) appletClass.newInstance();
        } catch (Exception ex) {
            FileLog.debug("Failed to instantiate", ex);
            return false;
        }

        if (realApplet != null) {
            realApplet.setStub(new DelegationAppletStub(this));

            saveAppletMethods();
            loadPlugins(realApplet, pluginItems);
            return true;
        }
        return false;
    }

    /**
     * As it is not allowed to call getClassLoader() in a method called from
     * outside (like getAppletState()) we do it here - where it is done in a
     * separate thread also so MS is happy.
     * <p/>
     * It is only when using
     */
    private void saveAppletMethods() {
        realAppletClassLoader = realApplet.getClass()
                .getClassLoader();
        try {
            Class pollingInterface = realAppletClassLoader
                    .loadClass(POLLING_INTERFACE);
            getAppletStateMethod = pollingInterface.getDeclaredMethod(
                    "getAppletState", new Class[0]);
            getOutputDataMethod = pollingInterface.getDeclaredMethod(
                    "getOutputData", new Class[0]);
        } catch (Exception e) {
            FileLog.debug("realApplet is not a PollingApplet", e);
        }
        try {
            Class serviceInterface = realAppletClassLoader
                    .loadClass(SERVICE_INTERFACE);
            doServiceMethod = serviceInterface.getDeclaredMethod("doService",
                    new Class[0]);
        } catch (Exception e) {
            FileLog.debug("realApplet is not a ServiceApplet", e);
        }
    }

    private ClassLoader getClassLoader(DownloadItem item,
                                       Object objectLoadedByParent) throws IOException {

        // when skipping the URLClassLoader it is not possible to insert
        // alternative classes in URL
        // (but even with it, they won't have the right permissions - but
        // better safe than sorry)
        ClassLoader parent = objectLoadedByParent.getClass().getClassLoader();
        if (parent instanceof URLClassLoader) {
            parent = parent.getParent();
        }
        return new ChecksummingClassLoader(item, parent, this.getClass().getProtectionDomain());
    }

    void whenDone() {
        GuiUtil.showProgressUI(this, false);

        GuiUtil.doInGui(new GuiCallback() {
            public void doInGui() {
                realApplet.init();
                setBootApplet(realApplet);

                Container appletContainer = getContentPane();
                appletContainer.removeAll();
                appletContainer.setLayout(new BorderLayout());
                appletContainer.add(realApplet, BorderLayout.CENTER);

                appletContainer.validate();
                appletContainer.repaint();

                realApplet.start();

                // from ServiceBootApplet:
                if (OS.getBrowser(getBootApplet()).equals("APPLETVIEWER")) {
                    doService();
                }
            }
        });
        stopInstaller();
    }

    private JApplet getBootApplet() {
        return this;
    }

    void whenFailed(final Exception e) {
        GuiUtil.doInGui(new GuiCallback() {
            public void doInGui() {
                Container appletContainer = getContentPane();
                appletContainer.removeAll();
                appletContainer.setLayout(new BorderLayout());

                JPanel panel = new JPanel();
                panel.setBackground(Color.white);
                panel.add(new JLabel(e.getMessage()));

                appletContainer.add(panel, BorderLayout.CENTER);

                appletContainer.validate();
                appletContainer.repaint();

            }
        });
    }

    /**
     * If the class of the plugin as method with signature
     * "boolean install(String)" invoke it and return its return value (as
     * primitive method)
     *
     * @param plugin
     * @return
     */
    private boolean canInstall(Object plugin, String baseUrl) {
        Class pluginClass = plugin.getClass();
        Class[] argClasses = new Class[1];
        argClasses[0] = String.class;
        Method installMethod;
        try {
            installMethod = pluginClass
                    .getDeclaredMethod("install", argClasses);
        } catch (SecurityException e) {
            throw new RuntimeException("Security exception when getting install method " + e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            FileLog.debug("no install method for plugin " + plugin
                    + " but that's fine");
            return true;
        }
        Object[] installArgs = new Object[1];
        installArgs[0] = baseUrl;
        Boolean installOk;
        try {
            installOk = (Boolean) installMethod.invoke(plugin, installArgs);
        } catch (Exception e) {
            FileLog.debug(e.getMessage(), e);
            throw new RuntimeException("Problems with checking installation: " + e.getMessage(), e);
        }
        return installOk;
    }

    /**
     * As we use a separate classloader we do not have the same classes in boot
     * and loaded archives. Therefore we must call it with reflection.
     *
     * @param plugin
     */
    private void addPlugin(JApplet applet, Object plugin, String baseUrl) {
        // ((Plugaware) applet).addPlugin(plugin);
        // first check if it can install - like the downloadItem.initialize()
        if (!canInstall(plugin, baseUrl)) {
            FileLog
                    .debug("Skipping plugin as cannot install (unsupported platform maybe):"
                            + plugin);
            return;
        }
        callMethodOnPlugaware(applet, "addPlugin", plugin, Object.class);
    }

    private void setBootApplet(JApplet applet) {
        callMethodOnPlugaware(applet, "setBootApplet", this, JApplet.class);
    }

    /**
     * Calls a unary method on the object receiver (which must implement
     * interface Plugaware) using parameter as the single parameter
     *
     * @param methodName TODO
     */
    private void callMethodOnPlugaware(JApplet receiver, String methodName,
                                       Object parameter, Class parameterClass) {
        Class[] argClasses = new Class[1];
        argClasses[0] = parameterClass;
        try {
            // Class plugawareInterface =
            // applet.getClass().getClassLoader().loadClass
            // (Plugaware.class.getName());
            Class plugawareInterface = receiver.getClass().getClassLoader()
                    .loadClass(PLUGAWARE_INTERFACE);
            Method plugawareMethod = plugawareInterface.getDeclaredMethod(
                    methodName, argClasses);
            Object[] pluginArgs = new Object[1];
            pluginArgs[0] = parameter;
            plugawareMethod.invoke(receiver, pluginArgs);
        } catch (Exception e) {
            throw new RuntimeException("Error adding plugin to applet: " + e.getMessage(), e);
        }
    }

    private boolean loadPlugins(JApplet applet, List<DownloadItem> pluginItems)
            throws IOException, NoSuchAlgorithmException {
        // check extra plugins:
        for (DownloadItem downloadItem : pluginItems) {
            extraLoader = getClassLoader(downloadItem, applet);
            try {
                Class bootClass = extraLoader.loadClass(downloadItem
                        .getClassName());
                try {
                    Object plugin = bootClass.newInstance();
                    plugins.add(plugin);
                    addPlugin(applet, plugin, DownloadItem.getBaseUrl());
                } catch (InstantiationException e) {
                    FileLog.debug(e.getMessage(), e);
                    return false;
                } catch (IllegalAccessException e) {
                    FileLog.debug(e.getMessage(), e);
                    return false;
                }
            } catch (ClassNotFoundException e) {
                FileLog.debug(e.getMessage(), e);
                return false;
            }
        }
        return true;
    }

    private File getPluginCacheDir() {
        String relativePath;
        String OCES_INSTALL_DIRECTORY = ".oces";
        String OPENSIGN_INSTALL_DIRECTORY = "opensign";
        String LIBRARY_INSTALL_DIRECTORY = "plugins";

        relativePath = OCES_INSTALL_DIRECTORY + File.separator
                + OPENSIGN_INSTALL_DIRECTORY + File.separator
                + LIBRARY_INSTALL_DIRECTORY;

        return new File(OS.getUserHome() + File.separator + relativePath);
    }

    public String getAppletState() {
        if (realApplet != null && getAppletStateMethod != null) {
            try {
                // return ((PollingApplet) realApplet).getAppletState();
                return (String) getAppletStateMethod.invoke(realApplet, null);
            } catch (Exception e) {
                FileLog.error("Applet is not pollable");
                return "";
            }
        } else {
            return "";
        }
    }

    public String getOutputData() {
        if (realApplet != null && getOutputDataMethod != null) {
            try {
                // return ((PollingApplet) realApplet).getOutputData();
                return (String) getOutputDataMethod.invoke(realApplet, null);
            } catch (Exception e) {
                FileLog.error("Applet is not pollable");
                return "";
            }
        } else {
            return "";
        }
    }
}
