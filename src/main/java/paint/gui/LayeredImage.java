package paint.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author andrewtaylor
 */
public class LayeredImage {
    
    private int width, height;
    private BufferedImage background, foreground;
    
    public LayeredImage(int width, int height) {
        this.width = width;
        this.height = height;
        background = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        foreground = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        background.createGraphics();
        foreground.createGraphics();
    }
    
    public LayeredImage(BufferedImage image) {
        this.width = image.getWidth();
        this.height = image.getHeight();
        background = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        foreground = image;
        background.createGraphics();
        foreground.createGraphics();
    }
    
    public BufferedImage merge() {
        BufferedImage composite = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = composite.createGraphics();
        g.drawImage(background, 0, 0, null);
        g.drawImage(foreground, 0, 0, null);
        return composite;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getWidth() {
        return width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }
    
    public void setBackground(BufferedImage background) {
        this.background = background;
    }
    
    public BufferedImage getBackground() {
        return background;
    }
    
    public void setForeground(BufferedImage foreground) {
        this.foreground = foreground;
    }

    public BufferedImage getForeground() {
        return foreground;
    }
}
