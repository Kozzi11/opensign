package org.openoces.opensign.client.applet.attach;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import javax.swing.JComponent;
import org.openoces.opensign.appletsupport.Attachment;
import org.openoces.opensign.client.applet.attach.resources.AttachmentResourceManager;
import org.openoces.opensign.client.applet.dialogs.AbstractDialog;
import org.openoces.opensign.client.applet.dialogs.DlgConfirm;
import org.openoces.opensign.client.applet.dialogs.FileDialogStrategy;
import org.openoces.opensign.client.applet.dialogs.SwingFileDialogStrategy;
import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.xml.nanoxml.NanoXMLParser;
import org.openoces.opensign.xml.nanoxml.NanoXMLReader;
import org.openoces.opensign.xml.nanoxml.XMLElement;
import org.openoces.opensign.xml.nanoxml.XMLException;

class AttachmentServer
{
    private AbstractDialog parent;
    private AttachmentImpl[] attachments;
    private String homeDir;

    AttachmentServer(AbstractDialog dialog, String attachmentParam, String homeDir)
    {
        try
        {
            parent = dialog;
            this.homeDir = homeDir;

            NanoXMLParser parser = new NanoXMLParser(true);
            NanoXMLReader reader = NanoXMLReader.stringReader(attachmentParam);
            parser.setReader(reader);
            XMLElement doc = parser.parse();

            List children = doc.getChildren();
            attachments = new AttachmentImpl[children.size()];
            int i = 0;
            for (Iterator iterator = children.iterator(); iterator.hasNext(); i++) {
                XMLElement child = (XMLElement)iterator.next();
                attachments[i] = new AttachmentImpl(child, this);
            }
        } catch (XMLException e) {
            FileLog.error("Could not parse attachment xml", e);
            throw new RuntimeException(e);
        }
    }

    Attachment[] getAttachments() {
        return attachments;
    }

    private boolean copyStream(Attachment a, InputStream in, OutputStream out)
            throws IOException
    {
        CheckSummedInputerStream checksummedStream;
        synchronized (in) {
            synchronized (out) {
                try {
                    checksummedStream = new Sha1ChecksummedInputStream(in, a.getChecksum());
                } catch (NoSuchAlgorithmException e) {
                    return false;
                }
                byte[] buffer = new byte[65536];
                int c;
                while ((c = checksummedStream.read(buffer, 0, 65536)) > -1) {
                    out.write(buffer, 0, c);
                }
            }
        }
        try
        {
            if ((a.isHasSeen()) && (!checksummedStream.getCalculatedChecksum().matches(a.getLocalChecksum())))
            {
                FileLog.debug("Checksum has changed between two downloads of the same attachment");
                throw new Exception();
            }

            a.setLocalChecksum(checksummedStream.getCalculatedChecksum());

            if (checksummedStream.hasChecksumValid()) {
                a.setChecksumVerified();
                return true;
            }
            FileLog.warn("Invalid checksum");
            FileLog.debug("Calculated checksum (base64 encoded): " + new String(Base64.encode(checksummedStream.getCalculatedChecksum().getShaValue())));
            FileLog.debug("Expected checksum (base64 encoded): " + new String(Base64.encode(a.getChecksum().getShaValue())));
            return false;
        }
        catch (Exception e) {
            FileLog.warn("Exception checking checksum " + e.getMessage());
            throw new IOException(e.getMessage());
        }
    }

    String getAttachmentFilePath(String fileName, JComponent actionButton)
    {
        FileDialogStrategy strategy = new SwingFileDialogStrategy(parent, actionButton);
        File f = strategy.getSaveFile(homeDir, fileName, AttachmentResourceManager.getString("SAVE_ATTACHMENT"));

        if (f == null) {
            return null;
        }

        if (f.exists()) {
            String message = AttachmentResourceManager.getString("CONFIRM_OVERWRITE_FILE");
            DlgConfirm dlgConfirm = new DlgConfirm(parent, actionButton, message, message);
            dlgConfirm.show();
            if (dlgConfirm.isCancelled()) {
                return null;
            }
        }

        return f.getAbsolutePath();
    }

    byte[] getContent(Attachment attachment, boolean validate)
            throws IOException, InvalidContentException
    {
        return attachment.getContents();
    }
}