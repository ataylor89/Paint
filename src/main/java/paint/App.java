package paint;

import java.awt.Color;
import javax.swing.SwingUtilities;
import paint.gui.Easel;
import paint.image.LayeredImage;
import paint.listener.AppNotifications;

/**
 *
 * @author andrewtaylor
 */
public class App {
    
    private Settings settings;
    private AppNotifications notifications;
    private Easel easel;
    
    public App() {}
    
    public void notify(String event) {
        notifications.forward(event);
    }
    
    public Settings getDefaults() {
        Settings defaults = new Settings();
        defaults.setBrushSize(20);
        defaults.setPaintColor(new Color(0,153,255));
        defaults.setMode(Settings.GLIDE);
        defaults.setTool("Brush");
        defaults.setLayeredImage(new LayeredImage(1200, 725));
        return defaults;
    }
    
    public void restoreDefaults() {
        settings = getDefaults();
    }
    
    public void setSettings(Settings settings) {
        this.settings = settings;
    }
    
    public Settings getSettings() {
        return settings;
    }
    
    public void setNotifications(AppNotifications notifications) {
        this.notifications = notifications;
    }

    public AppNotifications getNotifications() {
        return notifications;
    }
    
    public void setEasel(Easel easel) {
        this.easel = easel;
    }
    
    public Easel getEasel() {
        return easel;
    }
    
    public static void main(String[] args) {
        App app = new App();
        
        Settings defaults = app.getDefaults();
        app.setSettings(defaults);
        
        AppNotifications notifications = new AppNotifications(app);
        app.setNotifications(notifications);
        
        Easel easel = new Easel(app);
        app.setEasel(easel);
        
        SwingUtilities.invokeLater(() -> {
            easel.createAndShowGui();
        });
    }
}
