package paint.transform;

import java.awt.Dimension;
import paint.App;
import paint.Settings;
import paint.gui.Canvas;
import paint.gui.LayeredImage;
import paint.gui.Easel;
import paint.listener.TransformListener;

/**
 *
 * @author andrewtaylor
 */
public class FitCanvasToImage implements Transform {
    
    private App app;
    private TransformListener listener;
    
    public FitCanvasToImage(App app, TransformListener listener) {
        this.app = app;
        this.listener = listener;
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
        
        if (listener != null) 
            listener.notify("FitCanvasToImage");
    }
}
