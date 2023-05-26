package paint.algorithm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import paint.Canvas;
import paint.Settings;

/**
 *
 * @author andrewtaylor
 */
public class EraseTool implements Algorithm {

    private boolean brushDown;
    
    public EraseTool() {
        brushDown = false;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        brushDown = !brushDown;
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
            Color brushColor = canvas.getBackground();
            int brushSize = settings.getBrushSize();
            graphics.setColor(brushColor);
            graphics.drawOval(x, y, brushSize, brushSize);
            graphics.fillOval(x, y, brushSize, brushSize);
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {}
    
}
