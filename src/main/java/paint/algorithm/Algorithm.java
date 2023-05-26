package paint.algorithm;

import paint.Canvas;
import java.awt.event.MouseEvent;

/**
 *
 * @author andrewtaylor
 */
public interface Algorithm {
    public abstract void mousePressed(Canvas canvas, MouseEvent event);
    public abstract void mouseReleased(Canvas canvas, MouseEvent event);
    public abstract void mouseMoved(Canvas canvas, MouseEvent event);
    public abstract void mouseDragged(Canvas canvas, MouseEvent event);
}
