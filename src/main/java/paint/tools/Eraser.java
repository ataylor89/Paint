package paint.tools;

import java.awt.AlphaComposite;
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
public class Eraser extends Tool {

    private Paint paint;
    private Settings settings;
    private RenderingHints renderingHints;
    
    public Eraser(Paint paint) {
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
        int diameter = settings.getBrushSize();
        Graphics2D cg = (Graphics2D) canvas.getGraphics();
        cg.setRenderingHints(renderingHints);
        cg.setColor(canvas.getBackground());
        cg.drawOval(x, y, diameter, diameter);
        cg.fillOval(x, y, diameter, diameter);
        Graphics2D ig = (Graphics2D) image.getGraphics();
        ig.setRenderingHints(renderingHints);
        ig.setComposite(AlphaComposite.Clear);
        ig.setColor(new Color(0,0,0,0));
        ig.drawOval(x, y, diameter, diameter);
        ig.fillOval(x, y, diameter, diameter);
        ig.setComposite(AlphaComposite.SrcOver);
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
