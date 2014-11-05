package org.openoces.opensign.client.applet.dialogs.components;

import javax.swing.border.AbstractBorder;
import java.awt.*;

/**
 * User: pakj
 * Date: 04-02-11
 * Time: 14:23
 */
public class DoubleBorder extends AbstractBorder {
    protected int thickness;
    protected Color innerColor, outerColor;

    /**
     * Creates a line border with the specified color and a thickness = 4.
     */
    public DoubleBorder(Color outerColor, Color innerColor) {
        this(outerColor, innerColor, 4);
    }

    /**
     * Creates a line border with the specified color and thickness.
     */
    public DoubleBorder(Color outerColor, Color innerColor, int thickness) {
        this.outerColor = outerColor;
        this.innerColor = innerColor;
        this.thickness = thickness;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Color oldColor = g.getColor();
        int i;

        g.setColor(outerColor);

        int index = 0;
        for (i = 0; i < thickness; i++, index++) {
            g.drawRect(x + index, y + index, width - index - index - 1, height - index - index - 1);
        }

        g.setColor(innerColor);

        for (i = 0; i < thickness; i++, index++) {
            g.drawRect(x + index, y + index, width - index - index - 1, height - index - index - 1);
        }

        g.setColor(oldColor);
    }

    /**
     * Returns the insets of the border.
     *
     * @param c the component for which this border insets value applies
     */
    public Insets getBorderInsets(Component c) {
        return new Insets(thickness * 2, thickness * 2, thickness * 2, thickness * 2);
    }

    /**
     * Reinitialize the insets parameter with this Border's current Insets.
     *
     * @param c      the component for which this border insets value applies
     * @param insets the object to be reinitialized
     */
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = thickness * 2;
        return insets;
    }

    /**
     * Returns the color of the outer border.
     */
    public Color getOuterColor() {
        return outerColor;
    }

    /**
     * Returns the color of the inner border.
     */
    public Color getInnterColor() {
        return innerColor;
    }

    /**
     * Returns the thickness of the border.
     */
    public int getThickness() {
        return thickness;
    }
}
