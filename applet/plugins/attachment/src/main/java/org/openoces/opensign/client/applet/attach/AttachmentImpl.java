package org.openoces.opensign.client.applet.attach;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Observable;
import javax.swing.JComponent;
import org.openoces.opensign.appletsupport.Attachment;
import org.openoces.opensign.appletsupport.MimeTypeValidator;
import org.openoces.opensign.client.applet.attach.resources.AttachmentResourceManager;
import org.openoces.opensign.client.applet.dialogs.AbstractDialog;
import org.openoces.opensign.client.applet.dialogs.DlgInformation;
import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.utils.IOUtils;
import org.openoces.opensign.xml.nanoxml.XMLElement;

public class AttachmentImpl extends Observable
        implements ActionListener, Attachment
{
    private String title;
    private String path;
    private String mimeType = "undefined";
    private long size;
    private String hashAlg = "sha1";
    private boolean optional;
    private boolean hasSeen;
    private Checksum hashValue;
    private Checksum localHashValue;
    private boolean checksumVerified;
    private AttachmentServer server;
    private AbstractDialog dialogOwner;
    private byte[] content;

    public AttachmentImpl(XMLElement param, AttachmentServer server)
    {
        this.server = server;
        List<XMLElement> children = param.getChildren();
        for (XMLElement child : children) {
            String name = child.getName();
            if (name.equals("title")) {
                title = child.getContent();
            } else if (name.equals("path")) {
                path = child.getContent();
            } else if (name.equals("content")) {
                String cont = child.getContent();
                cont = cont.substring(cont.indexOf("base64,", 4) + 7);
                content = Base64.decode(cont.getBytes());
            } else if (name.equals("size")) {
                size = Long.parseLong(child.getContent());
            } else if (name.equals("hashValue")) {
                byte[] bs = Base64.decode(child.getContent().getBytes());
                hashValue = new Sha1Checksum(bs);
            } else if (name.equals("hashAlg")) {
                hashAlg = child.getContent();
            } else if (name.equals("mimeType")) {
                String unvalidatedMimeType = child.getContent();
                if (MimeTypeValidator.validate(unvalidatedMimeType)) {
                    mimeType = unvalidatedMimeType;
                } else {
                    FileLog.warn("invalid mime type for an attachment: " + unvalidatedMimeType + ". Using default mime-type.");
                    mimeType = "application/octet-stream";
                }
            } else if (name.equals("optional")) {
                optional = true;
            }
        }
        hasSeen = true;
    }

    public String toString() {
        return "Attachment:\n\ttitle: " + getTitle() + "\tpath: " + getPath() + "\tmimeType: " + getMimeType() + "\tsize: " + getSize() + "\thashAlg: " + getHashAlg() + "\thashValue: " + getHashValue();
    }

    public String getTitle()
    {
        return title;
    }

    public boolean isViewable() {
        return AttachmentViewerManager.hasViewer(mimeType);
    }

    public void setDialogOwner(AbstractDialog owner) {
        dialogOwner = owner;
    }

    void view(JComponent oldFocusComponent) {
        try {
            if (!isViewable()) {
                FileLog.warn("Tried to view unviewable attachment");
                return;
            }
            AttachmentViewer viewer = AttachmentViewerManager.getViewer(mimeType);
            byte[] content = server.getContent(this, true);
            viewer.view(dialogOwner, oldFocusComponent, getTitle(), content);
            setHasSeen();
        } catch (IOException e) {
            FileLog.warn("unable to download attachment: " + getPath() + "( + " + e.getMessage() + ")", e);
            DlgInformation dlgInfo = new DlgInformation(dialogOwner, oldFocusComponent, "fixme", "fixme: unable to download attachment");
            dlgInfo.show();
        } catch (InvalidContentException e) {
            DlgInformation dlgInfo = new DlgInformation(dialogOwner, oldFocusComponent, AttachmentResourceManager.getString("ATTACHMENT_VIEW_INVALID_CONTENT_HEADER"), AttachmentResourceManager.getString("ATTACHMENT_VIEW_INVALID_CONTENT_BODY"));

            dlgInfo.show();
        }
    }

    private void setHasSeen() {
        hasSeen = true;
        setChanged();
        notifyObservers();
    }

    void save(JComponent oldFocusComponent)
    {
        String filePath = server.getAttachmentFilePath(getTitle(), oldFocusComponent);

        if (filePath == null) {
            FileLog.fatal("filePath is null");

            return;
        }
        File f = new File(filePath);

        OutputStream toFile = null;
        try {
            try {
                toFile = new BufferedOutputStream(new FileOutputStream(f));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            byte[] content = server.getContent(this, false);
            toFile.write(content);
            toFile.flush();

            setHasSeen();
        } catch (IOException e) {
            if (f.exists()) {
                f.delete();
            }
            DlgInformation dlgInfo = new DlgInformation(dialogOwner, oldFocusComponent, AttachmentResourceManager.getString("ATTACHMENT_SAVE_FAILED_HEADER"), AttachmentResourceManager.getString("ATTACHMENT_SAVE_FAILED_BODY"));

            dlgInfo.show();
        } catch (InvalidContentException e) {
            DlgInformation dlgInfo = new DlgInformation(dialogOwner, oldFocusComponent, AttachmentResourceManager.getString("ATTACHMENT_SAVE_INVALID_CONTENT_HEADER"), AttachmentResourceManager.getString("ATTACHMENT_SAVE_INVALID_CONTENT_BODY"));

            dlgInfo.show();
        } finally {
            IOUtils.close(toFile);
        }
    }

    public String getPath() {
        return path;
    }

    public boolean isHasSeen() {
        return hasSeen;
    }

    public String getHashValue()
    {
        return new String(Base64.encode(hashValue.getShaValue()));
    }

    public Checksum getLocalChecksum() {
        return localHashValue;
    }

    public void setLocalChecksum(Checksum v) {
        localHashValue = v;
    }

    public Checksum getChecksum()
    {
        return new Sha1Checksum(hashValue.getShaValue());
    }

    String getHashAlg() {
        return hashAlg;
    }

    public String getMimeType() {
        return mimeType;
    }

    public byte[] getContents()
    {
        return content;
    }

    public long getSize() {
        return size;
    }

    public boolean isOptional() {
        return optional;
    }

    public boolean isPrimary() {
        return false;
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("VIEW"))
            view((JComponent)e.getSource());
        else if (e.getActionCommand().equals("SAVE"))
            save((JComponent)e.getSource());
    }

    boolean getChecksumVerified()
    {
        return checksumVerified;
    }

    public void setChecksumVerified() {
        checksumVerified = true;
    }
}