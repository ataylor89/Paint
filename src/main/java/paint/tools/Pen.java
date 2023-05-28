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
public class Pen implements Tool {

    private Paint paint;
    private boolean on;
    private MouseEvent lastEvent;
    
    public Pen(Paint paint) {
        this.paint = paint;
        on = false;
    }
      
    @Override
    public void press(MouseEvent event) {
        on = !on;
        if (on) {
            lastEvent = event;
        } else {
            lastEvent = null;
        }
    }

    @Override
    public void move(MouseEvent event) {
        if (on) {
            Settings settings = paint.getSettings();
            Color paintColor = settings.getPaintColor();
            int x1 = lastEvent.getX();
            int y1 = lastEvent.getY();
            int x2 = event.getX();
            int y2 = event.getY();
            Canvas canvas = paint.getCanvas();
            BufferedImage image = canvas.getImage();
            Graphics cg = canvas.getGraphics();
            Graphics ig = image.getGraphics();
            cg.setColor(paintColor);
            cg.drawLine(x1, y1, x2, y2);
            ig.setColor(paintColor);
            ig.drawLine(x1, y1, x2, y2);
            lastEvent = event;
        }
    }
}
