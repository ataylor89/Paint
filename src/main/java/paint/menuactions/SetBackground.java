package paint.menuactions;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import paint.App;
import paint.Settings;

/**
 *
 * @author andrewtaylor
 */
public class SetBackground {

    private App app;
    
    public SetBackground(App app) {
        this.app = app;
    }
    
    public void execute() {
        Settings settings = app.getSettings();
        Color color = settings.getBackgroundColor();
        BufferedImage background = settings.getLayeredImage().getBackground();
        Graphics g = background.getGraphics();
        g.setColor(color);
        g.fillRect(0, 0, background.getWidth(), background.getHeight());
    }
    
}
