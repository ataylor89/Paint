package paint.image;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author andrewtaylor
 */
public class LayeredImage implements Serializable {
    
    private int width, height;   
    private transient BufferedImage background, foreground;
    private static final long serialVersionUID = 1L;
    
    public LayeredImage() {}
    
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
    
    private void writeObject(ObjectOutputStream out) {
        try {
            out.defaultWriteObject();
            byte[] buffer1 = ImageOps.serialize(background);
            byte[] buffer2 = ImageOps.serialize(foreground);
            out.writeObject(buffer1);
            out.writeObject(buffer2);
            out.flush();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    private void readObject(ObjectInputStream in) {
        try {
            in.defaultReadObject();
            byte[] buffer1 = (byte[]) in.readObject();
            byte[] buffer2 = (byte[]) in.readObject();
            background = ImageOps.deserialize(buffer1, width, height);
            foreground = ImageOps.deserialize(buffer2, width, height);
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex);
        }
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
