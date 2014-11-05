package org.openoces.opensign.client.applet.dialogs;

import org.openoces.opensign.appletsupport.AttachmentSupport;
import org.openoces.opensign.client.applet.CallBackHandler;

import javax.swing.*;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public interface AttachmentSupportFactory {

    AttachmentSupport createAttachmentSupport(CallBackHandler callbackHandler, JComponent oldFocusComponent);

}
