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
public class Eraser implements Algorithm {

    private boolean brushDown;
    
    public Eraser() {
        brushDown = false;
    }
    
    public void applyDot(Canvas canvas, int x, int y) {
        Graphics graphics = canvas.getGraphics();
        Settings settings = Settings.getInstance();
        Color color = canvas.getBackground();
        int diameter = settings.getBrushSize();
        graphics.setColor(color);
        graphics.drawOval(x, y, diameter, diameter);
        graphics.fillOval(x, y, diameter, diameter);
    }
    
    @Override
    public void mousePressed(MouseEvent event) {
        brushDown = !brushDown;
        if (brushDown) {
            Canvas canvas = (Canvas) event.getSource();
            int x = event.getX();
            int y = event.getY();
            applyDot(canvas, x, y);
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {}

    @Override
    public void mouseMoved(MouseEvent event) {
        if (brushDown) {
            Canvas canvas = (Canvas) event.getSource();
            int x = event.getX();
            int y = event.getY();
            applyDot(canvas, x, y);
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {}
    
}
