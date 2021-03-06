package org.openoces.opensign.xml.xmldsig;

import org.openoces.opensign.appletsupport.Attachment;
import org.openoces.opensign.appletsupport.MimeTypeValidator;
import org.openoces.opensign.client.applet.attach.InvalidContentException;
import org.openoces.opensign.client.applet.dialogs.listeners.SignText;
import org.openoces.opensign.client.applet.dialogs.listeners.StringSignText;
import org.openoces.opensign.exceptions.InputDataError;
import org.openoces.opensign.utils.Base64;
import org.openoces.opensign.utils.FileLog;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class DefaultSignatureFactory implements SignatureFactory {

    public AttachmentInfo[] getObjectsForAttachments(SignText signText, Attachment[] attachments) {
        try {
            if (attachments == null) {
                return new AttachmentInfo[0];
            }

            AttachmentInfo sbs[] = new AttachmentInfo[attachments.length];

            for (int i = 0; i < attachments.length; i++) {
                Attachment attachment = attachments[i];
                if (attachment.isHasSeen()) {
                    sbs[i] = new AttachmentInfo();
                    StringBuffer sb = new StringBuffer();
                    sb.append("<ds:Object xmlns:ds=\"" + BasicSignatureGenerator.NS_W3_XMLDSIG + "\"");
                    sb.append(" xmlns:openoces=\"" + BasicSignatureGenerator.NS_OPENOCES_SIGNATURE + "\"");
                    sb.append(" Encoding=\"base64\"");
                    if (attachment.isPrimary()) {
                        sbs[i].attachmentURI = "signText";
                    } else {
                        sbs[i].attachmentURI = "attachment-" + i;
                    }
                    sb.append(" Id=\"").append(sbs[i].attachmentURI).append("\"");

                    // FIXME: Instead of validating mime-types we should escape the mime-type appropriately.
                    String mimeType = "text/plain";
                    if (MimeTypeValidator.validate(attachment.getMimeType())) {
                        mimeType = attachment.getMimeType();
                    }

                    sb.append(" MimeType=\"").append(mimeType).append("\"");
                    if (!attachment.getTitle().isEmpty()) {
                        sb.append(" OrigTitle=\"").append(attachment.getTitle()).append("\"");
                    }

                    sb.append(">");
                    byte[] bs = attachment.getEncodeContents();
                    if (bs != null) {
                        sb.append(BasicSignatureGenerator.wrapLines(new String(bs), BasicSignatureGenerator.BASE64_LINELENGTH).toString());
                    }
                    sb.append("</ds:Object>");
                    sbs[i].attachment = sb.toString();
                } else {
                    sbs[i] = null;
                }
            }
            return sbs;
        } catch (InvalidContentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the Object section of the xmldsig message. Properties are encoded as NAME VALUE
     * pairs, where name and value are encoded ad BASE64, to avoid XML message syntaxt conflicts.
     * When decoded, signtext section sould be likewise decoded.
     * The invisible properties that have not been visible to the signer, whereas the visible
     * properties have been visible.
     *
     * @param visibleProperties
     * @param signText            the tbs text
     * @param invisibleProperties
     * @return StringBuffer
     */
    public String getObjectText(Map visibleProperties, SignText signText, Map invisibleProperties) throws IOException, InputDataError {
        StringBuffer sb = new StringBuffer();
        sb.append("<ds:Object xmlns:ds=\"" + BasicSignatureGenerator.NS_W3_XMLDSIG + "\" xmlns:openoces=\"" + BasicSignatureGenerator.NS_OPENOCES_SIGNATURE + "\" Id=\"ToBeSigned\">");
        sb.append("<ds:SignatureProperties>\n");
        sb.append(getPropertiesFragment(visibleProperties, signText, true).toString());
        sb.append(getPropertiesFragment(invisibleProperties, null, false).toString());
        sb.append("</ds:SignatureProperties>");
        sb.append("</ds:Object>");
        return sb.toString();
    }

    private StringBuffer getPropertiesFragment(Map<Object, Object> properties, SignText signText, boolean visible) throws IOException, InputDataError {
        StringBuffer s = new StringBuffer();
        if (signText != null) {
            s.append(getSignatureProperty("signtext", ((StringSignText)signText).getSignText(), true).toString()).append("\n");
        }
        if (properties != null) {
            Set keys = properties.keySet();
            for(Object name : keys) {
                Object value = properties.get(name);
                s.append(getSignatureProperty(name.toString(), value.toString(), visible).toString()).append("\n");
            }
        }
        return s;
    }
    /**
     * Generate a <SignatureProperty> node, encode name and value in order to support special chars
     * s
     *
     * @param name
     * @param value
     * @param setToBeSignedId
     * @return the <SignatureProperty> node
     */

    private StringBuffer getSignatureProperty(String name, String value, boolean setToBeSignedId) throws IOException, InputDataError {
        StringBuffer sb = new StringBuffer();

/*
            Better safe than sorry:
            An extra check in case we are not escaping all necessary characters correctly
        */
        if (!isWellformed(name)) {
            FileLog.warn("Name not wellformed: " + name);
            throw new InputDataError();
        }

        StringBuffer s;
        if (value == null || value.length() == 0) {
            s = new StringBuffer("");
        } else {
            s = new StringBuffer(new String(Base64.encode(value.getBytes("UTF8"))));
        }

        sb.append("<ds:SignatureProperty Target=\"#OpensignSignature\">");
        sb.append("<openoces:Name>");
        sb.append(name);
        sb.append("</openoces:Name>");
        sb.append("<openoces:Value Encoding=\"base64\" VisibleToSigner=\"").append(setToBeSignedId ? "yes" : "no").append("\">");
        sb.append(s.toString());
        sb.append("</openoces:Value>");
        sb.append("</ds:SignatureProperty>");
        return sb;
    }


    private static boolean isWellformed(String name) {
        for (int i = 0; i < name.length(); i++) {
            int c = name.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == '_')) {
            } else {
                return false;
            }
        }
        return true;
    }


}
