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
public class Eraser implements Tool {

    private Paint paint;
    private boolean on;
    
    public Eraser(Paint paint) {
        this.paint = paint;
        on = false;
    }
    
    public void apply(MouseEvent event) {
        Canvas canvas = paint.getCanvas();
        BufferedImage image = canvas.getImage();
        Settings settings = paint.getSettings();
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
        on = !on;
        if (on) {
            apply(event);
        }
    }
    
    @Override
    public void move(MouseEvent event) {
        if (on) {
            apply(event);
        }
    }
}
