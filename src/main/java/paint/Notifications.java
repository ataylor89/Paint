package paint;

import paint.gui.Easel;
import paint.gui.Canvas;
import paint.image.LayeredImage;

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
            case "restoredDefaults" -> {
                Easel easel = app.getEasel();
                easel.refreshTitle();
                easel.getToolBar().refresh();
                Canvas canvas = easel.getCanvas();
                canvas.setLayeredImage(new LayeredImage(1200, 725));
                canvas.setBackground(app.getSettings().getBackgroundColor());
                canvas.repaint();
            }
            case "openedFile" -> {
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
