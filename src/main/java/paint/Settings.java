package paint;

import java.awt.Color;

/**
 *
 * @author andrewtaylor
 */
public class Settings {
    
    private int brushSize;
    private Color paintColor;
    private static Settings instance;
    
    private Settings() {
        brushSize = 10;
        paintColor = new Color(0,153,255);
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
    
    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }
}
