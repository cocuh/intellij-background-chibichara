package org.typowriter.intellij.plugins.backgroundchibichara;

import javax.swing.border.Border;
import java.awt.*;

public class BackgroundBorder implements Border {
    private final float alpha;
    private final Image image;

    BackgroundBorder(float alpha, Image image) {
        super();
        this.alpha = alpha;
        this.image = image;
    }

    @Override
    public void paintBorder(Component component, Graphics graphics, int x, int y, int width, int height) {
        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        // todo paint here
        graphics2d.drawImage(image, x, y, component);
    }

    @Override
    public Insets getBorderInsets(Component component) {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}
