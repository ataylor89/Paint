package paint.menuactions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import paint.App;
import paint.gui.Easel;
import paint.gui.Canvas;
import paint.image.LayeredImage;

/**
 *
 * @author andrewtaylor
 */
public class FitCanvasToImage extends AbstractAction {
    
    private App app;

    public FitCanvasToImage(App app) {
        super("Fit canvas to image");
        this.app = app;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Easel easel = app.getEasel();
        Canvas canvas = easel.getCanvas();
        LayeredImage image = canvas.getLayeredImage();
        canvas.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        easel.pack();
        app.notify("resizedCanvas");
    }
}
