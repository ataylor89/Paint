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

    private boolean on;
    private MouseEvent lastEvent;
    
    public Pen() {
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
            Canvas canvas = (Canvas) event.getSource();
            Graphics graphics = canvas.getGraphics();
            Settings settings = Settings.getInstance();
            Color paintColor = settings.getPaintColor();
            graphics.setColor(paintColor);
            graphics.drawLine(lastEvent.getX(), lastEvent.getY(), event.getX(), event.getY());
            lastEvent = event;
        }
    }
}
