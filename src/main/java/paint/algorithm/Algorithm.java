package paint.algorithm;

import java.awt.event.MouseEvent;

/**
 *
 * @author andrewtaylor
 */
public interface Algorithm {
    public void mousePressed(MouseEvent event);
    public void mouseReleased(MouseEvent event);
    public void mouseMoved(MouseEvent event);
    public void mouseDragged(MouseEvent event);
}
