package paint.listeners;

import javax.swing.event.MenuEvent;
import paint.App;
import paint.gui.MenuBar;
import paint.util.MenuAdapter;

/**
 *
 * @author andrewtaylor
 */
public class MenuBarListener extends MenuAdapter {
    
    private App app;
    
    public MenuBarListener(App app) {
        this.app = app;
    }
    
    @Override
    public void menuSelected(MenuEvent e) {
        MenuBar menuBar = (MenuBar) app.getEasel().getJMenuBar();
        menuBar.refresh();
    }    
}
