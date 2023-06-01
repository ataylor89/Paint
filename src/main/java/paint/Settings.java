package paint;

import java.awt.Color;
import java.io.File;
import paint.gui.LayeredImage;
import paint.listener.SettingChangeListener;
import paint.tools.Tool;

/**
 *
 * @author andrewtaylor
 */
public class Settings {
    
    private SettingChangeListener listener;
    
    private int brushSize;
    private Color paintColor;
    private int mode;
    private Tool tool;
    private File file;
    private LayeredImage layeredImage;
    
    public static final int GLIDE = 0;
    public static final int DRAG = 1;
        
    public Settings() {}
    
    public void notify(String signal) {
        listener.notify(signal);
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
    
    public void setTool(Tool tool) {
        this.tool = tool;
        
        if (listener != null) 
            listener.notify("toolChanged");
    }
    
    public Tool getTool() {
        return tool;
    }

    public void setFile(File file) {
        this.file = file;
        
        if (listener != null) 
            listener.notify("fileChanged");
    }
    
    public File getFile() {
        return file;
    }

    public void setLayeredImage(LayeredImage layeredImage) {
        this.layeredImage = layeredImage;
        
        if (listener != null)
            listener.notify("imageChanged");
    }
    
    public LayeredImage getLayeredImage() {
        return layeredImage;
    }
    
    public void setListener(SettingChangeListener listener) {
        this.listener = listener;
    }
    
    public SettingChangeListener getListener() {
        return listener;
    }
}