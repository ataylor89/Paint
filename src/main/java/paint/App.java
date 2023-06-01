package paint;

import java.awt.Color;
import paint.gui.Easel;
import paint.gui.LayeredImage;
import paint.listener.SettingChangeListener;

/**
 *
 * @author andrewtaylor
 */
public class App {
    
    private Settings settings;
    private Easel easel;
    
    public App() {}
    
    public void setSettings(Settings settings) {
        this.settings = settings;
    }
    
    public Settings getSettings() {
        return settings;
    }
    
    public void setEasel(Easel easel) {
        this.easel = easel;
    }
    
    public Easel getEasel() {
        return easel;
    }
    
    public static void main(String[] args) {
        App app = new App(); 
        
        Settings settings = new Settings();
        app.setSettings(settings);      
        
        Easel easel = new Easel(app);
        app.setEasel(easel);
        
        settings.setBrushSize(20);
        settings.setPaintColor(new Color(0,153,255));
        settings.setMode(Settings.GLIDE);
        settings.setTool(easel.getToolbox().get("Brush"));
        settings.setLayeredImage(new LayeredImage(1200, 725));
        settings.setListener(new SettingChangeListener(app));
              
        easel.createAndShowGui();
    }
}
