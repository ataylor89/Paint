package paint.util;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 *
 * @author andrewtaylor
 */
public abstract class MenuAdapter implements MenuListener {

    @Override
    public void menuSelected(MenuEvent e) {}

    @Override
    public void menuDeselected(MenuEvent e) {}

    @Override
    public void menuCanceled(MenuEvent e) {}
    
}
