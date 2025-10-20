package paint.menuactions;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import paint.App;
import paint.gui.Canvas;
import paint.gui.Easel;
import paint.image.LayeredImage;

/**
 *
 * @author andrewtaylor
 */
public class FitImageToCanvas extends AbstractAction {
    
    private App app;
    private Easel easel;
    private Canvas canvas;

    public FitImageToCanvas(App app) {
        super("Fit image to canvas");
        this.app = app;
        easel = app.getEasel();
        canvas = easel.getCanvas();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String message = "Are you sure you want to resize the image?";
        String title = "Resize image";
        int optionType = JOptionPane.YES_NO_OPTION;
        if (JOptionPane.showConfirmDialog(easel, message, title, optionType) == JOptionPane.YES_OPTION) {
            LayeredImage oldImage = canvas.getLayeredImage();
            LayeredImage newImage = new LayeredImage(canvas.getWidth(), canvas.getHeight());
            Graphics backgroundGraphics = newImage.getBackground().createGraphics();
            Graphics foregroundGraphics = newImage.getForeground().createGraphics();
            backgroundGraphics.drawImage(oldImage.getBackground(), 0, 0, null);
            foregroundGraphics.drawImage(oldImage.getForeground(), 0, 0, null);
            canvas.setLayeredImage(newImage);
            app.notify("resizedImage");
        }
    }
}
