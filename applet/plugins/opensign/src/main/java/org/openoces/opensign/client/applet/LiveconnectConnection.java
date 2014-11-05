package org.openoces.opensign.client.applet;

import org.openoces.opensign.utils.FileLog;

import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.net.URL;

public class LiveconnectConnection implements JavascriptService {
    private static final int CHUNK_SIZE = 100000;

    private AbstractApplet applet;

    public LiveconnectConnection(AbstractApplet applet) {
        this.applet = applet;
    }

    @Override
    public void callFunction(String function, String[] args) {
        if (("onLogonOK".equals(function) || "onSignOK".equals(function)) && "true".equals(applet.getParamReader().getParameter("signText.chunk"))) {
            if (chunk(args[0])) return;
        }
        callLiveconnectFunction(function, args);
    }

    private void callLiveconnectFunction(String function, String[] args) {
        try {
            if (call(function, args)) return;

            FileLog.debug("Live connect failed. Trying alternative (liveconnect eval)");
            if (eval(function, args)) return;

            FileLog.debug("Live connect failed. Trying alternative (AppletContext.showDocument)");
            showDocument(function, args);
        } finally {
            FileLog.debug("Calling liveconnect (" + function + ") ended.");
        }
    }

    private boolean chunk(String arg) {
        try {
            FileLog.debug("Calling LiveConnect with chunks of " + CHUNK_SIZE + " bytes, sending " + arg.length() + " bytes");
            int bytesSent = 0;
            while (bytesSent + CHUNK_SIZE < arg.length() - 1) {
                FileLog.debug("Calling LiveConnect addChunk from " + bytesSent + " to " + (bytesSent + CHUNK_SIZE - 1));
                callLiveconnectFunction("addChunk", new String[]{arg.substring(bytesSent, bytesSent + CHUNK_SIZE)});
                bytesSent += CHUNK_SIZE;
            }
            int restBytes = arg.length() - bytesSent;
            FileLog.debug("Calling LiveConnect addChunk from " + bytesSent + " to " + (bytesSent + restBytes - 1));
            callLiveconnectFunction("addChunk", new String[]{arg.substring(bytesSent, bytesSent + restBytes)});
            callLiveconnectFunction("allChunk", new String[0]);
            return true;
        } catch (UnsatisfiedLinkError e) {
            FileLog.error("Error when using live connect to call chunk function", e);
        } catch (Throwable e) {
            FileLog.error("Error when using live connect. Could not call javascript : " + e.toString());
            FileLog.error("Error when using live connect to call chunk function", e);
        }
        return false;
    }

    private void showDocument(String function, String[] args) {
        // If live connect fails. attempt to use AppletContext#showDocument.
        try {

            StringBuilder fn = new StringBuilder("javascript:");
            fn.append(function).append('(');
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    fn.append("'").append(args[i]).append("'");
                    if (i != args.length - 1) fn.append(',');
                }
            }
            fn.append(");");
            FileLog.debug("JS Function : " + function + " number of args: " + (args != null ? args.length : 0));

            FileLog.info("Calling AppletContext.showDocument.");
            AppletContext appletContext = applet.getAppletContext();
            if (appletContext == null) {
                FileLog.info("Could not performe javascript function  " + function + " because the applet context was null (Probably because the user closed the browser).");
                return;
            }
            appletContext.showDocument(new URL(fn.toString()));
        } catch (MalformedURLException e) {
            FileLog.error("Malformed URL when transmitting response: " + function + " number of args: " + (args != null ? args.length : 0), e);
        } catch (Throwable e) {
            FileLog.error(e.getMessage(), e);
        }
    }

    private boolean eval(String function, String[] args) {
        try {
            FileLog.info("Calling liveconnect eval.");

            // Attempt to use live connect eval.
            StringBuilder fn = new StringBuilder("javascript:");
            fn.append(function).append('(');
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    fn.append("'").append(args[i]).append("'");
                    if (i != args.length - 1) fn.append(',');
                }
            }
            fn.append(");");
            FileLog.debug("JS Function : " + function + " number of args: " + args.length);

            netscape.javascript.JSObject.getWindow(applet).eval(fn.toString());
            return true;
        } catch (UnsatisfiedLinkError e) {
            FileLog.error("Error when using live connect to eval function:" + function + " number of args: " + args.length, e);
        } catch (Throwable e) {
            FileLog.error("Error when using live connect. Could not eval javascript : " + e.toString());
            FileLog.error("Error when using live connect to eval function:" + function + " number of args: " + args.length, e);
        }
        return false;
    }

    private boolean call(String function, String[] args) {
        // Attempt to use live connect.
        try {
            FileLog.info("Calling liveconnect (" + function + ").");
            netscape.javascript.JSObject.getWindow(applet).call(function, args);
            return true;
        } catch (UnsatisfiedLinkError e) {
            FileLog.error("Error when using live connect to call function:" + function + " number of args: " + args.length, e);
        } catch (Throwable e) {
            FileLog.error("Error when using live connect. Could not call javascript : " + e.toString());
            FileLog.error("Error when using live connect to call function:" + function + " number of args: " + args.length, e);
        }
        return false;
    }
}
