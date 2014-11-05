package org.openoces.opensign.client.applet.dialogs;

import javax.swing.JComponent;
import org.openoces.opensign.appletsupport.AppletSupport;
import org.openoces.opensign.appletsupport.AttachmentSupport;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.JavascriptRunner;
import org.openoces.opensign.client.applet.OS;

public class DefaultAttachmentSupportFactory
        implements AttachmentSupportFactory
{
    public AttachmentSupport createAttachmentSupport(CallBackHandler callbackHandler, JComponent oldFocusComponent)
    {
        try
        {
            callbackHandler.getJavascriptRunner().eval("prepareAttachments()");
            String attachmentParam = null;
            String attachmentParamPart = callbackHandler.getJavascriptRunner().eval("readAttachments()");
            if ((attachmentParamPart != null) && (attachmentParamPart.length() > 0)) {
                attachmentParam = "";
                while (attachmentParamPart.length() > 0) {
                    attachmentParam = attachmentParam + attachmentParamPart;
                    attachmentParamPart = callbackHandler.getJavascriptRunner().eval("readAttachments()");
                }
            }

            if (attachmentParam != null) {
                AppletSupport sup = OS.getAppletSupport();
                if ((sup != null) && ((sup instanceof AttachmentSupport))) {
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