package paint.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import paint.App;
import paint.listeners.CanvasListener;

/**
 *
 * @author andrewtaylor
 */
public class Canvas extends JPanel {
    
    private App app;
    
    public Canvas(App app, int width, int height) {
        this.app = app;
        super.setPreferredSize(new Dimension(width, height));
        super.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        CanvasListener listener = new CanvasListener(app);
        super.addMouseListener(listener);
        super.addMouseMotionListener(listener);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage composite = app.getSettings().getLayeredImage().merge();
        g.drawImage(composite, 0, 0, null);
    }
}
