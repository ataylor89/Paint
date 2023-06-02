package paint.gui;

import paint.image.LayeredImage;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import paint.App;
import paint.Settings;
import paint.listener.CanvasListener;

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
        Settings settings = app.getSettings();
        setBackground(settings.getBackgroundColor());
        LayeredImage image = settings.getLayeredImage();
        BufferedImage composite = image.merge();
        g.drawImage(composite, 0, 0, null);
    }
}
