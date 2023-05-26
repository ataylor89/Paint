package paint;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 *
 * @author andrewtaylor
 */
public class Canvas extends JPanel implements MouseListener, MouseMotionListener {
    
    private boolean brushDown = false;
    
    public Canvas() {
        init();
    }
    
    private void init() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if (!brushDown) {
            brushDown = true;
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
            setCursor(cursor);
        }
        else {
            brushDown = false;
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
            setCursor(cursor);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        if (brushDown) {
            Graphics graphics = super.getGraphics();
            Settings settings = Settings.getInstance();
            int x = e.getX();
            int y = e.getY();
            Color brushColor = settings.getBrushColor();
            int brushSize = settings.getBrushSize();
            graphics.setColor(brushColor);
            graphics.drawOval(x, y, brushSize, brushSize);
            graphics.fillOval(x, y, brushSize, brushSize);
        }
    }
}
