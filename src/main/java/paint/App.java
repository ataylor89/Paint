package paint;

import java.awt.Color;
import javax.swing.SwingUtilities;
import paint.gui.Easel;

/**
 *
 * @author andrewtaylor
 */
public class App {
    
    private Settings settings;
    private Notifications notifications;
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
    
    public void setNotifications(Notifications notifications) {
        this.notifications = notifications;
    }

    public Notifications getNotifications() {
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
        
        Notifications notifications = new Notifications(app);
        app.setNotifications(notifications);
        
        Easel easel = new Easel(app);
        app.setEasel(easel);
        
        SwingUtilities.invokeLater(() -> {
            easel.createAndShowGui();
        });
    }
}
