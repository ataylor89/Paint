package paint.transform;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import paint.App;
import paint.Settings;
import paint.gui.Canvas;
import paint.gui.LayeredImage;
import paint.gui.Easel;

/**
 *
 * @author andrewtaylor
 */
public class FitImageToCanvas implements Transform {
    
    private App app;
    
    public FitImageToCanvas(App app) {
        this.app = app;
    }
    
    @Override
    public void apply() {
        Settings settings = app.getSettings();
        Easel easel = app.getEasel();
        Canvas canvas = easel.getCanvas();
        int w = canvas.getWidth();
        int h = canvas.getHeight();
        LayeredImage newImage = new LayeredImage(w, h);
        BufferedImage newBackground = newImage.getBackground();
        BufferedImage newForeground = newImage.getForeground();
        Graphics gb = newBackground.createGraphics();
        Graphics gf = newForeground.createGraphics();
        LayeredImage oldImage = settings.getLayeredImage();
        gb.drawImage(oldImage.getBackground(), 0, 0, null);
        gf.drawImage(oldImage.getForeground(), 0, 0, null);
        settings.setLayeredImage(newImage);
        settings.notify("imageChanged");
    }
}
