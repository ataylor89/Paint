package paint.transform;

import paint.App;
import paint.Settings;
import paint.gui.Canvas;
import paint.gui.Easel;
import paint.gui.LayeredImage;

/**
 *
 * @author andrewtaylor
 */
public class ClearTransform implements Transform {
    
    private App app;
    
    public ClearTransform(App app) {
        this.app = app;
    }
    
    @Override
    public void apply() {
        Settings settings = app.getSettings();
        Easel easel = app.getEasel();
        Canvas canvas = easel.getCanvas();
        LayeredImage oldImage = settings.getLayeredImage();
        LayeredImage newImage = new LayeredImage(oldImage.getWidth(), oldImage.getHeight());
        settings.setLayeredImage(newImage);
        settings.setBackgroundColor(null);
        canvas.setBackground(null);
        settings.notify("imageChanged");
    }
}
