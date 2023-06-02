package paint.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import paint.App;
import paint.gui.Canvas;
import paint.image.LayeredImage;
import paint.gui.Easel;
import paint.Settings;

/**
 *
 * @author andrewtaylor
 */
public class Brush extends Tool {
    
    private App app;
    private final RenderingHints renderingHints;
    
    public Brush(App app) {
        this.app = app;
        Map<RenderingHints.Key, Object> hints = new HashMap<>();
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        hints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        renderingHints = new RenderingHints(hints);
    }
            
    public void apply(MouseEvent event) {
        Settings settings = app.getSettings();
        Easel easel = app.getEasel();
        Canvas canvas = easel.getCanvas();
        LayeredImage image = settings.getLayeredImage();
        BufferedImage foreground = image.getForeground();
        int x = event.getX();
        int y = event.getY();
        Color color = settings.getPaintColor();
        int diameter = settings.getBrushSize();
        Graphics2D cg = (Graphics2D) canvas.getGraphics();
        cg.setRenderingHints(renderingHints);
        cg.setColor(color);
        cg.drawOval(x, y, diameter, diameter);
        cg.fillOval(x, y, diameter, diameter);
        Graphics2D ig = (Graphics2D) foreground.getGraphics();
        ig.setRenderingHints(renderingHints);
        ig.setColor(color);
        ig.drawOval(x, y, diameter, diameter);
        ig.fillOval(x, y, diameter, diameter);
    }
    
    @Override
    public void press(MouseEvent event) {
        Settings settings = app.getSettings();
        if (settings.getMode() == Settings.GLIDE) {
            gliding = !gliding;
            if (gliding) {
                apply(event);
            }
        }
        else {
            apply(event);
        }
    }
    
    @Override
    public void move(MouseEvent event) {
        Settings settings = app.getSettings();
        if (settings.getMode() == Settings.GLIDE) {
            if (gliding) {
                apply(event);
            }
        }
    }
    
    @Override
    public void drag(MouseEvent event) {
        Settings settings = app.getSettings();
        if (settings.getMode() == Settings.DRAG) {
            apply(event);
        }
    }
}
