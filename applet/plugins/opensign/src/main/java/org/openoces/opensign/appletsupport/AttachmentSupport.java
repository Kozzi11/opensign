package org.openoces.opensign.appletsupport;

import javax.swing.JComponent;
import org.openoces.opensign.client.applet.CallBackHandler;

public abstract interface AttachmentSupport extends AppletSupport
{
    public abstract void init(CallBackHandler paramCallBackHandler, String paramString1, String paramString2, JComponent paramJComponent);

    public abstract void show(CallBackHandler paramCallBackHandler);

    public abstract String getAttachmentPart();

    public abstract Attachment[] getAttachments();

    public abstract boolean isSigningOK(JComponent paramJComponent);
}