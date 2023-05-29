package paint.tools;

import java.awt.event.MouseEvent;

/**
 *
 * @author andrewtaylor
 */
public abstract class Tool {
    
    protected boolean gliding;
        
    public abstract void press(MouseEvent event);
    public abstract void move(MouseEvent event);
    public abstract void drag(MouseEvent event);
}
