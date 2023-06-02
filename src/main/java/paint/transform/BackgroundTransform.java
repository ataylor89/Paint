package paint.transform;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import paint.App;
import paint.Settings;

/**
 *
 * @author andrewtaylor
 */
public class BackgroundTransform implements Transform {

    private App app;
    
    public BackgroundTransform(App app) {
        this.app = app;
    }
    
    @Override
    public void apply() {
        Settings settings = app.getSettings();
        Color color = settings.getBackgroundColor();
        BufferedImage background = settings.getLayeredImage().getBackground();
        Graphics g = background.getGraphics();
        g.setColor(color);
        g.fillRect(0, 0, background.getWidth(), background.getHeight());
    }
    
}
