package org.openoces.opensign.client.applet.dialogs;

import javax.swing.JComponent;
import org.openoces.opensign.appletsupport.AppletSupport;
import org.openoces.opensign.appletsupport.AttachmentSupport;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.JavascriptRunner;
import org.openoces.opensign.client.applet.OS;
import org.openoces.opensign.client.applet.dialogs.listeners.SignActionListener;
import org.openoces.opensign.utils.FileLog;


public class DefaultAttachmentSupportFactory
        implements AttachmentSupportFactory
{
    public AttachmentSupport createAttachmentSupport(CallBackHandler callbackHandler, JComponent oldFocusComponent)
    {
        try
        {
            callbackHandler.getJavascriptRunner().eval("prepareAttachments()");
            String attachmentParam = null;
            SignActionListener.printMem("Part 1");

            String attachmentParamPart = "";
            attachmentParamPart = callbackHandler.getJavascriptRunner().eval("readAttachments()");
            attachmentParam = "";
            while (attachmentParamPart.length() > 0) {
                SignActionListener.printMem("Part cyklus 1");

                attachmentParam = attachmentParam + attachmentParamPart;
                attachmentParamPart = callbackHandler.getJavascriptRunner().eval("readAttachments()");
                FileLog.error("Data size: " + Integer.toString(attachmentParam.length()) + "\n");
            }


            FileLog.error("Data size: " + Integer.toString(attachmentParam.length()) + "\n");
            SignActionListener.printMem("Part 2");

            if (attachmentParam != null) {
                AppletSupport sup = OS.getAppletSupport();
                if (sup != null && sup instanceof AttachmentSupport) {
                    String userHomeDir = OS.getUserHome();
                    AttachmentSupport attachmentSupport = (AttachmentSupport)sup;
                    attachmentSupport.init(callbackHandler, attachmentParam, userHomeDir, oldFocusComponent);
                    return attachmentSupport;
                }
            }
        } catch (Exception e) {  }

        return null;
    }
}