package paint.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import paint.App;
import paint.gui.Canvas;
import paint.image.LayeredImage;
import paint.gui.Easel;
import paint.Settings;

/**
 *
 * @author andrewtaylor
 */
public class Pen extends Tool {

    private App app;
    private MouseEvent lastEvent;
    
    public Pen(App app) {
        this.app = app;
    }
    
    public void draw(MouseEvent event) {
        Settings settings = app.getSettings();
        Color paintColor = settings.getPaintColor();
        int x1 = lastEvent.getX();
        int y1 = lastEvent.getY();
        int x2 = event.getX();
        int y2 = event.getY();
        Easel easel = app.getEasel();
        Canvas canvas = easel.getCanvas();
        LayeredImage image = settings.getLayeredImage();
        BufferedImage foreground = image.getForeground();
        Graphics cg = canvas.getGraphics();
        cg.setColor(paintColor);
        cg.drawLine(x1, y1, x2, y2);
        Graphics ig = foreground.getGraphics();
        ig.setColor(paintColor);
        ig.drawLine(x1, y1, x2, y2);
        lastEvent = event;
    }
      
    @Override
    public void press(MouseEvent event) {
        Settings settings = app.getSettings();
        if (settings.getMode() == Settings.GLIDE) {
            gliding = !gliding;
        }
        lastEvent = event;
    }

    @Override
    public void move(MouseEvent event) {
        Settings settings = app.getSettings();
        if (settings.getMode() == Settings.GLIDE) {
            if (gliding) {
                draw(event);
            }
        }
    }
    
    @Override
    public void drag(MouseEvent event) {
        Settings settings = app.getSettings();
        if (settings.getMode() == Settings.DRAG) {
            draw(event);
        }
    }
}
