package paint.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import javax.swing.SwingUtilities;
import paint.App;
import paint.Settings;
import paint.gui.Canvas;
import paint.image.LayeredImage;

/**
 *
 * @author andrewtaylor
 */
public class Line extends Tool {

    private App app;
    private MouseEvent start, end;
    private final RenderingHints renderingHints;
    
    public Line(App app) {
        this.app = app;
        Map<RenderingHints.Key, Object> hints = new HashMap<>();
        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        renderingHints = new RenderingHints(hints);
    }
    
    private void draw(boolean finished) {
        Settings settings = app.getSettings();
        Canvas canvas = app.getEasel().getCanvas();
        LayeredImage image = canvas.getLayeredImage();
        BufferedImage foreground = image.getForeground();
        canvas.repaint();
        SwingUtilities.invokeLater(() -> {
            int x1 = start.getX();
            int y1 = start.getY();
            int x2 = end.getX();
            int y2 = end.getY();
            Color color = settings.getPaintColor();
            Graphics2D cg = (Graphics2D) canvas.getGraphics();
            cg.setColor(color);
            cg.setRenderingHints(renderingHints);
            cg.drawLine(x1, y1, x2, y2);
            if (finished) {
                Graphics2D ig = (Graphics2D) foreground.getGraphics();
                ig.setColor(color);
                ig.setRenderingHints(renderingHints);
                ig.drawLine(x1, y1, x2, y2);
                start = null;
                end = null;
            }
        });
    }
    
    @Override
    public void press(MouseEvent event) {
        if (start == null) {
            start = event;
        }
        else {
            end = event;
            draw(true);
        }
    }

    @Override
    public void move(MouseEvent event) {
        if (start != null) {
            end = event;
            draw(false);
        }
    }

    @Override
    public void drag(MouseEvent event) {
    
    }
    
}
