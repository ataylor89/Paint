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
    private Easel easel;
    private Canvas canvas;

    public FitCanvasToImage(App app) {
        super("Fit canvas to image");
        this.app = app;
        easel = app.getEasel();
        canvas = easel.getCanvas();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        LayeredImage image = canvas.getLayeredImage();
        canvas.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        easel.pack();
        app.notify("resizedCanvas");
    }
}
