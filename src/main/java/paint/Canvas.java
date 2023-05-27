package paint;

import paint.tools.Tool;
import paint.tools.ToolFactory;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 *
 * @author andrewtaylor
 */
public class Canvas extends JPanel implements MouseListener, MouseMotionListener {
    
    private Tool tool;
    
    public Canvas() {
        init();
    }
    
    public void setTool(Tool tool) {
        this.tool = tool;
    }
    
    public Tool getTool() {
        return tool;
    }
    
    private void init() {
        tool = ToolFactory.getTool("Brush");
        Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
        setCursor(cursor);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent event) {}

    @Override
    public void mousePressed(MouseEvent event) {
        tool.mousePressed(event);
    }

    @Override
    public void mouseReleased(MouseEvent event) {}

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}

    @Override
    public void mouseDragged(MouseEvent event) {}

    @Override
    public void mouseMoved(MouseEvent event) {
        tool.mouseMoved(event);
    }
}
