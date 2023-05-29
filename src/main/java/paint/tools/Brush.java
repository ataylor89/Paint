package paint.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import paint.Canvas;
import paint.Paint;
import paint.Settings;

/**
 *
 * @author andrewtaylor
 */
public class Brush extends Tool {
    
    private Paint paint;
    private Settings settings;
    private RenderingHints renderingHints;
    
    public Brush(Paint paint) {
        this.paint = paint;
        this.settings = paint.getSettings();
        Map<RenderingHints.Key, Object> hints = new HashMap<>();
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        hints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        renderingHints = new RenderingHints(hints);
    }
            
    public void apply(MouseEvent event) {
        Canvas canvas = paint.getCanvas();
        BufferedImage image = canvas.getImage();
        int x = event.getX();
        int y = event.getY();
        Color color = settings.getPaintColor();
        int diameter = settings.getBrushSize();
        Graphics2D cg = (Graphics2D) canvas.getGraphics();
        cg.setRenderingHints(renderingHints);
        cg.setColor(color);
        cg.drawOval(x, y, diameter, diameter);
        cg.fillOval(x, y, diameter, diameter);
        Graphics2D ig = (Graphics2D) image.getGraphics();
        ig.setRenderingHints(renderingHints);
        ig.setColor(color);
        ig.drawOval(x, y, diameter, diameter);
        ig.fillOval(x, y, diameter, diameter);
    }
    
    @Override
    public void press(MouseEvent event) {
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
        if (settings.getMode() == Settings.GLIDE) {
            if (gliding) {
                apply(event);
            }
        }
    }
    
    @Override
    public void drag(MouseEvent event) {
        if (settings.getMode() == Settings.DRAG) {
            apply(event);
        }
    }
}
