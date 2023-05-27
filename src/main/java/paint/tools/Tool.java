package paint.tools;

import java.awt.event.MouseEvent;

/**
 *
 * @author andrewtaylor
 */
public interface Tool {
    public void press(MouseEvent event);
    public void move(MouseEvent event);
}
