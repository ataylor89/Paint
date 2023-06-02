package paint.transform;

import java.awt.Dimension;
import paint.App;
import paint.Settings;
import paint.gui.Canvas;
import paint.gui.Easel;
import paint.image.LayeredImage;

/**
 *
 * @author andrewtaylor
 */
public class FitCanvasToImage implements Transform {
    
    private App app;
    
    public FitCanvasToImage(App app) {
        this.app = app;
    }
    
    @Override
    public void apply() {
        Settings settings = app.getSettings();
        Easel easel = app.getEasel();
        Canvas canvas = easel.getCanvas();
        LayeredImage image = settings.getLayeredImage();
        canvas.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        easel.pack();
    }
}
