package org.typowriter.intellij.plugins.backgroundchibichara;

import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class BackgroundImageBorder implements Border {
    private final java.util.List<Image> imageList;
    private final float alpha;
    private final int spacing;
    private final int margin;
    private final Align align;

    BackgroundImageBorder(List<Image> imageList, float alpha, int spacing, Align align, int margin) {
        this.imageList = imageList;
        this.alpha = alpha;
        this.spacing = spacing;
        this.align = align;
        this.margin = margin;
    }

    @Override
    public void paintBorder(Component component, Graphics graphics, int x, int y, int width, int height) {
        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        // todo paint here
        int yi = y;
        int imageIdx = 0;
        while (yi < height) {
            Image image = imageList.get(imageIdx);
            int xi = this.align.calcX(width, image.getWidth(null), margin);
            graphics2d.drawImage(image, xi, yi, component);
            yi += image.getHeight(null);
            imageIdx++;
            imageIdx %= imageList.size();
            yi += spacing;
        }
    }

    @Override
    public Insets getBorderInsets(Component component) {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    public enum Align {
        LEFT {
            @Override
            int calcX(int width, int imageWidth, int margin) {
                return margin;
            }
        },
        CETNER {
            @Override
            int calcX(int width, int imageWidth, int margin) {
                return (width - imageWidth) / 2;
            }
        },
        RIGHT {
            @Override
            int calcX(int width, int imageWidth, int margin) {
                return width - margin - imageWidth;
            }
        };

        abstract int calcX(int width, int imageWidth, int margin);
    }
}
