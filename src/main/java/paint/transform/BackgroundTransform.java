package paint.transform;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import paint.App;
import paint.Settings;
import paint.gui.Canvas;
import paint.gui.Easel;
import paint.gui.LayeredImage;

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
        Easel easel = app.getEasel();
        Canvas canvas = easel.getCanvas();
        Color color = settings.getBackgroundColor();
        LayeredImage image = settings.getLayeredImage();
        BufferedImage background = image.getBackground();
        Graphics g = background.getGraphics();
        g.setColor(color);
        g.fillRect(0, 0, background.getWidth(), background.getHeight());
        canvas.setBackground(color);
        settings.notify("imageChanged");
    }
    
}
