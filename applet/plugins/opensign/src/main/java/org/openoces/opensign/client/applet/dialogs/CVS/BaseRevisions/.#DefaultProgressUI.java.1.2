package org.openoces.opensign.client.applet.dialogs;

import org.openoces.opensign.client.applet.GuiCallback;

import javax.swing.*;
import java.awt.*;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class DefaultProgressUI implements ProgressUI {
    private Component oldGlassPane;

    private String waitLabelId;
    private String informationId1;
    private String informationId2;

    public DefaultProgressUI(Component defaultGlassPane, String waitLabelId, String informationId1, String informationId2) {
        this.oldGlassPane = defaultGlassPane;

        this.waitLabelId = waitLabelId;
        this.informationId1 = informationId1;
        this.informationId2 = informationId2;
    }

    public DefaultProgressUI(Component defaultGlassPane) {
        this(defaultGlassPane, "generic.wait", "generic.information1", "generic.information2");
    }


    @Override
    public void show(JDialog dialog) {
        show(dialog, dialog);
    }

    @Override
    public void show(JApplet applet) {
        show(applet, applet);
    }

    @Override
    public void hide(JDialog dialog) {
        hide(dialog, dialog);
    }

    @Override
    public void hide(JApplet applet) {
        hide(applet, applet);
    }

    private void show(final Component dialogComponent, final RootPaneContainer rootPaneContainer) {
        GuiUtil.doInGui(new GuiCallback() {
            public void doInGui() {
                //The glass pane should not fill out all of the screen if the applet is started in supersize mode.
                //Only the part that is taken up by the current panel should be overlayed.
                JPanel glassPanel = new JPanel(new BorderLayout());
                glassPanel.setFocusable(false);
                glassPanel.setOpaque(false);
                GlassPane newGlassPane = new GlassPane(waitLabelId, informationId1, informationId2);
                newGlassPane.setFocusable(false);
                newGlassPane.setBounds(0, 0, dialogComponent.getWidth(), dialogComponent.getHeight());
                glassPanel.add(newGlassPane, BorderLayout.CENTER);
                rootPaneContainer.getRootPane().setGlassPane(glassPanel);
                glassPanel.setVisible(true);
                dialogComponent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            }
        }, new ProgressGuiRunner());
    }

    private void hide(final Component dialogComponent, final RootPaneContainer rootPaneContainer) {
        GuiUtil.doInGui(new GuiCallback() {
            public void doInGui() {
                rootPaneContainer.getRootPane().getGlassPane().setVisible(false);
                rootPaneContainer.getRootPane().setGlassPane(oldGlassPane);
                dialogComponent.setCursor(Cursor.getDefaultCursor());

            }
        }, new ProgressGuiRunner());
    }


}
