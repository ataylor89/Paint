package paint;

import java.awt.Color;

/**
 *
 * @author andrewtaylor
 */
public class Settings {
    
    private int brushSize;
    private Color paintColor;
    private int mode;
    
    public static final int GLIDE = 0;
    public static final int DRAG = 1;
        
    public Settings() {
        brushSize = 20;
        paintColor = new Color(0,153,255);
        mode = GLIDE;
    }
    
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
    
    public void setMode(int mode) {
        this.mode = mode;
    }
    
    public int getMode() {
        return mode;
    }
}