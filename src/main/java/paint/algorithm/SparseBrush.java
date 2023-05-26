package paint.algorithm;

import paint.Settings;
import paint.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

/**
 *
 * @author andrewtaylor
 */
public class SparseBrush implements Algorithm {
    
    private boolean brushDown;
    
    public SparseBrush() {
        brushDown = false;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        Canvas canvas = (Canvas) event.getSource();
        if (!brushDown) {
            brushDown = true;
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
            canvas.setCursor(cursor);
        }
        else {
            brushDown = false;
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
            canvas.setCursor(cursor);
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {}

    @Override
    public void mouseMoved(MouseEvent event) {
        if (brushDown) {
            Canvas canvas = (Canvas) event.getSource();
            Graphics graphics = canvas.getGraphics();
            Settings settings = Settings.getInstance();
            int x = event.getX();
            int y = event.getY();
            Color brushColor = settings.getPaintColor();
            int brushSize = settings.getBrushSize();
            graphics.setColor(brushColor);
            graphics.drawOval(x, y, brushSize, brushSize);
            graphics.fillOval(x, y, brushSize, brushSize);
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {}
}
