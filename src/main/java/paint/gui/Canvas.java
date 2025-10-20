package paint.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import paint.App;
import paint.listeners.CanvasListener;
import paint.image.LayeredImage;

/**
 *
 * @author andrewtaylor
 */
public class Canvas extends JPanel {
    
    private App app;
    private LayeredImage layeredImage;

    public Canvas(App app, int width, int height) {
        this.app = app;
        layeredImage = new LayeredImage(1200, 725);
        super.setPreferredSize(new Dimension(width, height));
        super.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        CanvasListener listener = new CanvasListener(app);
        super.addMouseListener(listener);
        super.addMouseMotionListener(listener);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage composite = layeredImage.merge();
        g.drawImage(composite, 0, 0, null);
    }

    public void setLayeredImage(LayeredImage layeredImage) {
        this.layeredImage = layeredImage;
    }

    public LayeredImage getLayeredImage() {
        return layeredImage;
    }
}
