package paint.transform;

import java.awt.Dimension;
import paint.App;
import paint.Settings;
import paint.gui.Canvas;
import paint.image.LayeredImage;
import paint.gui.Easel;

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
        int w = image.getWidth();
        int h = image.getHeight();
        canvas.setPreferredSize(new Dimension(w, h));
        easel.pack();
    }
}
