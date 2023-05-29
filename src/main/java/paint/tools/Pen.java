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
public class Pen extends Tool {

    private Paint paint;
    private Settings settings;
    private MouseEvent lastEvent;
    
    public Pen(Paint paint) {
        this.paint = paint;
        this.settings = paint.getSettings();
    }
      
    @Override
    public void press(MouseEvent event) {
        if (settings.getMode() == Settings.GLIDE) {
            gliding = !gliding;
        }
        lastEvent = event;
    }

    @Override
    public void move(MouseEvent event) {
        if (settings.getMode() == Settings.GLIDE) {
            if (gliding) {
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
    
    @Override
    public void drag(MouseEvent event) {
        if (settings.getMode() == Settings.DRAG) {
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
