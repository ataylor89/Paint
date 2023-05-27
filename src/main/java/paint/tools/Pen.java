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
public class Pen implements Tool {

    private boolean penDown;
    private MouseEvent lastEvent;
    
    public Pen() {
        penDown = false;
    }
    
    @Override
    public void press(MouseEvent event) {
        penDown = !penDown;
        if (penDown) {
            lastEvent = event;
        } else {
            lastEvent = null;
        }
    }

    @Override
    public void move(MouseEvent event) {
        if (penDown) {
            if (lastEvent != null) {
                Canvas canvas = (Canvas) event.getSource();
                Graphics graphics = canvas.getGraphics();
                Settings settings = Settings.getInstance();
                Color paintColor = settings.getPaintColor();
                graphics.setColor(paintColor);
                graphics.drawLine(lastEvent.getX(), lastEvent.getY(), event.getX(), event.getY());
            }
            lastEvent = event;
        }
    }
}
