package paint.algorithm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import paint.Canvas;
import paint.Settings;

/**
 *
 * @author andrewtaylor
 */
public class LineAlgorithm implements Algorithm {

    private int lastX, lastY;
    
    public LineAlgorithm() {
        lastX = -1;
        lastY = -1;
    }
    
    @Override
    public void mousePressed(Canvas canvas, MouseEvent event) {
        lastX = event.getX();
        lastY = event.getY();
    }

    @Override
    public void mouseReleased(Canvas canvas, MouseEvent event) {
        lastX = -1;
        lastY = -1;
    }

    @Override
    public void mouseMoved(Canvas canvas, MouseEvent event) {}

    @Override
    public void mouseDragged(Canvas canvas, MouseEvent event) {
        if (lastX > 0 && lastY > 0) {
            Graphics graphics = canvas.getGraphics();
            Settings settings = Settings.getInstance();
            Color paintColor = settings.getPaintColor();
            graphics.setColor(paintColor);
            graphics.drawLine(lastX, lastY, event.getX(), event.getY());            
        }
        lastX = event.getX();
        lastY = event.getY();
    }  
}
