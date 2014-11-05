package org.openoces.opensign.client.applet.attach.dialogs;

import org.openoces.opensign.client.applet.GuiCallback;
import org.openoces.opensign.client.applet.attach.AttachmentImpl;
import org.openoces.opensign.client.applet.attach.resources.AttachmentResourceManager;
import org.openoces.opensign.client.applet.dialogs.GuiUtil;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

class SeenObserver implements Observer {
    private JLabel statusIndicator;

    SeenObserver(AttachmentImpl a, JLabel statusIndicator) {
        this.statusIndicator = statusIndicator;
        a.addObserver(this);
    }

    public void update(final Observable o, Object arg) {
        GuiUtil.doInGui(new GuiCallback() {
            @Override
            public void doInGui() {
                AttachmentImpl a = (AttachmentImpl) o;
                if (a.isHasSeen()) {
                    statusIndicator.setText(AttachmentResourceManager.getString("FETCHED"));

                    statusIndicator.validate();
                    statusIndicator.repaint();
                }
            }
        });
    }
}
