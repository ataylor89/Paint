package paint.listener;

import paint.App;
import paint.Settings;
import paint.gui.Easel;
import paint.transform.FitCanvasToImage;

/**
 *
 * @author andrewtaylor
 */
public class AppNotifications {
    
    private App app;
    
    public AppNotifications(App app) {
        this.app = app;
    }
    
    public void forward(String event) {
        switch (event) {
            case "restoredDefaults", "openedFile" -> {
                new FitCanvasToImage(app).apply();
                Easel easel = app.getEasel();
                easel.refreshTitle();
                easel.getToolBar().refresh();
                easel.getCanvas().repaint();
            }
            case "resizedImage", "resizedCanvas" -> {
                Easel easel = app.getEasel();
                easel.getCanvas().repaint();
                easel.refreshTitle();
            }
            case "changedBackgroundColor" -> {
                Easel easel = app.getEasel();
                easel.getCanvas().repaint();
            }
            case "changedTool" -> {
                Settings settings = app.getSettings();
                settings.setSelection(null);
                Easel easel = app.getEasel();
                easel.getCanvas().repaint();      
            }
        }
    }
}
