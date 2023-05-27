package paint.tools;

import java.awt.event.MouseEvent;

/**
 *
 * @author andrewtaylor
 */
public interface Tool {
    public void mousePressed(MouseEvent event);
    public void mouseMoved(MouseEvent event);
}
