package org.openoces.opensign.client.applet;

import org.openoces.opensign.utils.FileLog;

public class DefaultJavascriptServiceFactory implements JavascriptServiceFactory {
    private AbstractApplet applet;

    public DefaultJavascriptServiceFactory(AbstractApplet applet) {
        this.applet = applet;
    }

    @Override
    public JavascriptService create() {
        if(doAppletRequest()) {
            return new NonLiveconnectConnection(applet);
        } else {
            return new LiveconnectConnection(applet);
        }
    }

    private boolean doAppletRequest() {
        String doAutoMac = applet.getParameter("opensign.doappletrequestonmac");
        String doAppletRequest = applet.getParameter("opensign.doappletrequest");

        if (doAutoMac != null
                && doAutoMac.equalsIgnoreCase("true")
                && OS.isOSMac()) {
            return true;
        }

        if (doAppletRequest != null
                && doAppletRequest.equalsIgnoreCase("true")) {
            FileLog.debug("doAppletRequest() called - returning true (doappletrequest flag set)");
            return true;
        }

        return false;
    }
}
