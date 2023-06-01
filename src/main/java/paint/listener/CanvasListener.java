package paint.listener;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import paint.App;
import paint.Settings;
import paint.tools.Tool;

/**
 *
 * @author andrewtaylor
 */
public class CanvasListener extends MouseInputAdapter {
    
    private App app;
    
    public CanvasListener(App app) {
        this.app = app;
    }
    
    @Override
    public void mousePressed(MouseEvent event) {
        Settings settings = app.getSettings();
        Tool tool = settings.getTool();
        tool.press(event);
    }
    
    @Override
    public void mouseMoved(MouseEvent event) {
        Settings settings = app.getSettings();
        Tool tool = settings.getTool();
        tool.move(event);
    }
    
    @Override
    public void mouseDragged(MouseEvent event) {
        Settings settings = app.getSettings();
        Tool tool = settings.getTool();
        tool.drag(event);
    }
}
