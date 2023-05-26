package paint.algorithm;

import paint.Canvas;
import java.awt.event.MouseEvent;

/**
 *
 * @author andrewtaylor
 */
public interface Algorithm {
    public abstract void mousePressed(MouseEvent event);
    public abstract void mouseReleased(MouseEvent event);
    public abstract void mouseMoved(MouseEvent event);
    public abstract void mouseDragged(MouseEvent event);
}
