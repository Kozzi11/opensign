package org.openoces.opensign.client.applet.dialogs;

import org.openoces.opensign.client.applet.dialogs.components.ComponentFactory;
import org.openoces.opensign.client.applet.dialogs.components.DefaultComponentFactory;

import javax.swing.*;
import java.awt.*;

/**
 * @author Paw Figgé Kjeldgaard (pfk@miracleas.dk)
 */
public class GlassPane extends JPanel {
    private JLabel waitLabel;
    private JLabel informationLabel1;
    private JLabel informationLabel2;

    public GlassPane(String waitLabelId, String informationId, String informationId2) {
        AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.92f);

        setLayout(new GridBagLayout());
        JLabel l = new JLabel();
        l.setOpaque(false);
        l.setIcon(new ImageIcon(GuiUtil.createImage("loader.gif")));
        l.setHorizontalTextPosition(SwingConstants.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;

        ComponentFactory componentFactory = new DefaultComponentFactory();

        add(l, gbc);
        gbc.gridy++;
        waitLabel = componentFactory.createLabel(waitLabelId);
        add(waitLabel, gbc);
        gbc.gridy++;
        informationLabel1 = componentFactory.createLabel(informationId);
        add(informationLabel1, gbc);
        gbc.gridy++;
        informationLabel2 = componentFactory.createLabel(informationId2);
        add(informationLabel2, gbc);

        validate();
        setOpaque(false);
    }

    public JLabel getInformationLabel2() {
        return informationLabel2;
    }
}
