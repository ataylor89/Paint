package paint;

import java.awt.Color;
import java.io.File;
import java.io.Serializable;
import paint.image.LayeredImage;

/**
 *
 * @author andrewtaylor
 */
public class Settings implements Serializable {
    
    private int brushSize;
    private Color paintColor;
    private Color backgroundColor;
    private int mode;
    private String tool;
    private File file;
    private LayeredImage layeredImage;   
    
    private transient boolean marquee;
    
    public static final int GLIDE = 0;
    public static final int DRAG = 1;
    
    private static final long serialVersionUID = 1L;    
    
    public Settings() {}
    
    public void setBrushSize(int brushSize) {
        this.brushSize = brushSize;
    }
    
    public int getBrushSize() {
        return brushSize;
    }
    
    public void setPaintColor(Color brushColor) {
        this.paintColor = brushColor;
    }
    
    public Color getPaintColor() {
        return paintColor;
    }
    
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    public Color getBackgroundColor() {
        return backgroundColor;
    }
    
    public void setMode(int mode) {
        this.mode = mode;
    }
    
    public int getMode() {
        return mode;
    }
    
    public void setTool(String tool) {
        this.tool = tool;
    }
    
    public String getTool() {
        return tool;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    public File getFile() {
        return file;
    }

    public void setLayeredImage(LayeredImage layeredImage) {
        this.layeredImage = layeredImage;
    }
    
    public LayeredImage getLayeredImage() {
        return layeredImage;
    }
    
    public void setMarquee(boolean marquee) {
        this.marquee = marquee;
    }
    
    public boolean hasMarquee() {
        return marquee;
    }
}