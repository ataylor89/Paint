package paint;

import java.awt.Color;
import java.io.File;
import paint.gui.LayeredImage;
import paint.gui.Selection;
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
    private Color backgroundColor;
    private int mode;
    private Tool tool;
    private File file;
    private LayeredImage layeredImage;
    private Selection selection;
    
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
    
    public void setTool(Tool tool) {
        this.tool = tool;
    }
    
    public Tool getTool() {
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
    
    public void setSelection(Selection selection) {
        this.selection = selection;
    }
    
    public Selection getSelection() {
        return selection;
    }
    
    public void setListener(SettingChangeListener listener) {
        this.listener = listener;
    }
    
    public SettingChangeListener getListener() {
        return listener;
    }
}