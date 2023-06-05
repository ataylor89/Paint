package paint;

import paint.gui.Canvas;
import paint.gui.Easel;

/**
 *
 * @author andrewtaylor
 */
public class Notifications {
    
    private App app;
    
    public Notifications(App app) {
        this.app = app;
    }
    
    public void forward(String event) {
        switch (event) {
            case "restoredDefaults", "openedFile" -> {
                Easel easel = app.getEasel();
                easel.refreshTitle();
                easel.getToolBar().refresh();
                Canvas canvas = easel.getCanvas();
                canvas.setBackground(app.getSettings().getBackgroundColor());
                canvas.repaint();
            }
            case "resizedImage", "resizedCanvas" -> {
                Easel easel = app.getEasel();
                easel.refreshTitle();
                Canvas canvas = easel.getCanvas();
                canvas.repaint();
            }
            case "changedBackgroundColor" -> {
                Canvas canvas = app.getEasel().getCanvas();
                canvas.setBackground(app.getSettings().getBackgroundColor());
                canvas.repaint();
            }
            case "changedTool" -> {
                Settings settings = app.getSettings();
                settings.setMarquee(false);
                Canvas canvas = app.getEasel().getCanvas();
                canvas.repaint();
            }
        }
    }
}
