package org.openoces.opensign.client.applet.dialogs;

import javax.swing.*;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public interface ProgressUI {

    void show(JDialog dialog);

    void show(JApplet applet);

    void hide(JDialog dialog);

    void hide(JApplet applet);

}
