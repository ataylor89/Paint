package paint;

import java.awt.Color;

/**
 *
 * @author andrewtaylor
 */
public class Settings {
    
    private int brushSize;
    private Color brushColor;
    private static Settings instance;
    
    private Settings() {
        brushSize = 10;
        brushColor = new Color(0,153,255);
    }
    
    public void setBrushSize(int brushSize) {
        this.brushSize = brushSize;
    }
    
    public int getBrushSize() {
        return brushSize;
    }
    
    public void setBrushColor(Color brushColor) {
        this.brushColor = brushColor;
    }
    
    public Color getBrushColor() {
        return brushColor;
    }
    
    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }
}
