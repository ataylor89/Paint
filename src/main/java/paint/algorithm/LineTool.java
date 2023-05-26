package paint.algorithm;

import paint.Canvas;
import paint.Settings;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

/**
 *
 * @author andrewtaylor
 */
public class LineTool implements Algorithm {

    private boolean penDown;
    private int lastX, lastY;
    
    public LineTool() {
        penDown = false;
        lastX = -1;
        lastY = -1;
    }
    
    @Override
    public void mousePressed(MouseEvent event) {
        penDown = !penDown;
        if (penDown) {
            lastX = event.getX();
            lastY = event.getY();
        } else {
            lastX = -1;
            lastY = -1;
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {}

    @Override
    public void mouseMoved(MouseEvent event) {
        if (penDown) {
            if (lastX > 0 && lastY > 0) {
                Canvas canvas = (Canvas) event.getSource();
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

    @Override
    public void mouseDragged(MouseEvent event) {}  
}
