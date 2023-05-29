package paint.tools;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import paint.Canvas;
import paint.Paint;
import paint.Settings;

/**
 *
 * @author andrewtaylor
 */
public class Eraser extends Tool {

    private Paint paint;
    private Settings settings;
    
    public Eraser(Paint paint) {
        this.paint = paint;
        this.settings = paint.getSettings();
    }
    
    public void apply(MouseEvent event) {
        Canvas canvas = paint.getCanvas();
        BufferedImage image = canvas.getImage();
        int x = event.getX();
        int y = event.getY();
        int diameter = settings.getBrushSize();
        Graphics cg = canvas.getGraphics();
        cg.setColor(canvas.getBackground());
        cg.drawOval(x, y, diameter, diameter);
        cg.fillOval(x, y, diameter, diameter);
        Graphics2D ig = (Graphics2D) image.getGraphics();
        ig.setComposite(AlphaComposite.Clear);
        ig.setColor(new Color(0,0,0,0));
        ig.drawOval(x, y, diameter, diameter);
        ig.fillOval(x, y, diameter, diameter);
        ig.setComposite(AlphaComposite.SrcOver);
    }
    
    @Override
    public void press(MouseEvent event) {
        if (settings.getMode() == Settings.GLIDE) {
            gliding = !gliding;
            if (gliding) {
                apply(event);
            }
        }
        else {
            apply(event);
        }
    }
    
    @Override
    public void move(MouseEvent event) {
        if (settings.getMode() == Settings.GLIDE) {
            if (gliding) {
                apply(event);
            }
        }
    }
    
    @Override
    public void drag(MouseEvent event) {
        if (settings.getMode() == Settings.DRAG) {
            apply(event);
        }
    }
}
