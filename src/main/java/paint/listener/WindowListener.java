package paint.listener;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import paint.App;

/**
 *
 * @author andrewtaylor
 */
public class WindowListener extends ComponentAdapter {
    
    private App app;
    
    public WindowListener(App app) {
        this.app = app;
    }
    
    @Override
    public void componentResized(ComponentEvent e) {
        app.getEasel().refreshTitle();
    }
}
