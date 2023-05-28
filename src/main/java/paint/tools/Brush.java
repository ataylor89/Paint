package paint.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import paint.Canvas;
import paint.Paint;
import paint.Settings;

/**
 *
 * @author andrewtaylor
 */
public class Brush implements Tool {
    
    private Paint paint;
    private boolean on;
    
    public Brush(Paint paint) {
        this.paint = paint;
        on = false;
    }
            
    public void apply(MouseEvent event) {
        Canvas canvas = paint.getCanvas();
        BufferedImage image = canvas.getImage();
        Settings settings = paint.getSettings();
        int x = event.getX();
        int y = event.getY();
        Color color = settings.getPaintColor();
        int diameter = settings.getBrushSize();
        Graphics cg = canvas.getGraphics();
        cg.setColor(color);
        cg.drawOval(x, y, diameter, diameter);
        cg.fillOval(x, y, diameter, diameter);
        Graphics ig = image.getGraphics();
        ig.setColor(color);
        ig.drawOval(x, y, diameter, diameter);
        ig.fillOval(x, y, diameter, diameter);
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
