package paint.transform;

import java.awt.Graphics;
import paint.App;
import paint.Settings;
import paint.gui.Canvas;
import paint.image.LayeredImage;

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
        LayeredImage oldImage = settings.getLayeredImage();
        Canvas canvas = app.getEasel().getCanvas();
        LayeredImage newImage = new LayeredImage(canvas.getWidth(), canvas.getHeight());
        Graphics backgroundGraphics = newImage.getBackground().createGraphics();
        Graphics foregroundGraphics = newImage.getForeground().createGraphics();
        backgroundGraphics.drawImage(oldImage.getBackground(), 0, 0, null);
        foregroundGraphics.drawImage(oldImage.getForeground(), 0, 0, null);
        settings.setLayeredImage(newImage);
    }
}
