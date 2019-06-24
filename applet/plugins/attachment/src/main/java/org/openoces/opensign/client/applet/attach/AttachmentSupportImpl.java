package org.openoces.opensign.client.applet.attach;

import javax.swing.JComponent;
import org.openoces.opensign.appletsupport.Attachment;
import org.openoces.opensign.appletsupport.AttachmentSupport;
import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.attach.resources.AttachmentResourceManager;
import org.openoces.opensign.client.applet.dialogs.DlgInformation;

public class AttachmentSupportImpl
        implements AttachmentSupport
{
    private AttachmentListViewDialog attachmentListView;
    private AttachmentServer attachmentServer;
    private CallBackHandler view;
    public String attachmentParam;

    public void init(CallBackHandler view, String attachmentParam, String homeDir, JComponent oldFocusComponent)
    {
        this.view = view;
        attachmentListView = new AttachmentListViewDialog(view, oldFocusComponent);
        attachmentServer = new AttachmentServer(attachmentListView, attachmentParam, homeDir);
        registerViewers();
    }

    public void show(CallBackHandler parent) {
        AttachmentImpl[] attachments = (AttachmentImpl[])attachmentServer.getAttachments();
        attachmentListView.setAttachments(attachments);
        attachmentListView.showAttachments();
    }

    public String getAttachmentPart()
    {
        return attachmentParam;
    }

    public Attachment[] getAttachments() {
        return attachmentServer.getAttachments();
    }

    public boolean isSigningOK(JComponent oldFocusComponent) {
        Attachment[] attachments = getAttachments();
        boolean allMandatorySeen = true;
        for (Attachment attachment : attachments) {
            allMandatorySeen &= ((attachment.isOptional()) || (attachment.isHasSeen()));
        }

        if (!allMandatorySeen) {
            String header = AttachmentResourceManager.getString("NOT_ALL_MANDATORY_ATTACHMENTS_DOWNLOADED_HEADER");
            String msg = AttachmentResourceManager.getString("NOT_ALL_MANDATORY_ATTACHMENTS_DOWNLOADED_HEADER");
            DlgInformation dlgInfo = new DlgInformation(view, oldFocusComponent, header, msg);
            dlgInfo.show();
            return false;
        }
        return true;
    }

    private void registerViewers()
    {
        if (PlainTextViewer.isSupported()) {
            AttachmentViewerManager.registerViewer("text/plain", new PlainTextViewer());
        }

        if (GifViewer.isSupported()) {
            AttachmentViewerManager.registerViewer("image/gif", new GifViewer());
        }
        if (HtmlViewer.isSupported()) {
            AttachmentViewerManager.registerViewer("text/html", new HtmlViewer());
        }
        if (RtfViewer.isSupported())
            AttachmentViewerManager.registerViewer("text/rtf", new RtfViewer());
    }
}