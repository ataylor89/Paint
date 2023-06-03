package paint.listeners;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import paint.App;
import paint.Settings;
import paint.gui.Easel;
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
        Easel easel = app.getEasel();
        String toolName = settings.getTool();
        Tool tool = easel.getToolbox().get(toolName);
        tool.press(event);
    }
    
    @Override
    public void mouseMoved(MouseEvent event) {
        Settings settings = app.getSettings();
        Easel easel = app.getEasel();
        String toolName = settings.getTool();
        Tool tool = easel.getToolbox().get(toolName);
        tool.move(event);
    }
    
    @Override
    public void mouseDragged(MouseEvent event) {
        Settings settings = app.getSettings();
        Easel easel = app.getEasel();
        String toolName = settings.getTool();
        Tool tool = easel.getToolbox().get(toolName);
        tool.drag(event);
    }
}
