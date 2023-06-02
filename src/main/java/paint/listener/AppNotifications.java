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
                Easel easel = app.getEasel();
                easel.refreshTitle();
                easel.getToolBar().refresh();
                new FitCanvasToImage(app).apply();
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
                Easel easel = app.getEasel();
                settings.setSelection(null);
                easel.getCanvas().repaint();      
            }
        }
    }
}
