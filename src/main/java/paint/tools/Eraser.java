package paint.tools;

import paint.Canvas;
import paint.Settings;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

/**
 *
 * @author andrewtaylor
 */
public class Eraser implements Tool {

    private boolean brushDown;
    
    public Eraser() {
        brushDown = false;
    }
    
    public void dot(MouseEvent event) {
        Canvas canvas = (Canvas) event.getSource();
        int x = event.getX();
        int y = event.getY();
        Graphics graphics = canvas.getGraphics();
        Settings settings = Settings.getInstance();
        Color color = canvas.getBackground();
        int diameter = settings.getBrushSize();
        graphics.setColor(color);
        graphics.drawOval(x, y, diameter, diameter);
        graphics.fillOval(x, y, diameter, diameter);
    }
    
    @Override
    public void press(MouseEvent event) {
        brushDown = !brushDown;
        if (brushDown) {
            dot(event);
        }
    }
    
    @Override
    public void move(MouseEvent event) {
        if (brushDown) {
            dot(event);
        }
    }  
}
