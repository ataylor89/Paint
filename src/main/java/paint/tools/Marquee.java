package paint.tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;
import paint.Canvas;
import paint.Paint;
import paint.Settings;

/**
 *
 * @author andrewtaylor
 */
public class Marquee extends Tool {

    private Paint paint;
    private Settings settings;
    private MouseEvent beginning, end;
    private Stroke stroke;
    
    public Marquee(Paint paint) {
        this.paint = paint;
        this.settings = paint.getSettings();
        stroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {10}, 0);
    }
    
    public void draw() {
        Canvas canvas = paint.getCanvas();
        canvas.repaint();
        SwingUtilities.invokeLater(() -> {
            Graphics2D cg = (Graphics2D) canvas.getGraphics();
            cg.setColor(Color.DARK_GRAY);
            cg.setStroke(stroke);
            int x = Math.min(beginning.getX(), end.getX());
            int y = Math.min(beginning.getY(), end.getY());
            int width = Math.abs(beginning.getX() - end.getX());
            int height = Math.abs(beginning.getY() - end.getY());
            cg.drawRect(x, y, width, height);
        });
    }
    
    public void fill() {
        Canvas canvas = paint.getCanvas();
        BufferedImage image = canvas.getImage();
        canvas.repaint();
        SwingUtilities.invokeLater(() -> {
            Color color = settings.getPaintColor();
            int x = Math.min(beginning.getX(), end.getX());
            int y = Math.min(beginning.getY(), end.getY());
            int width = Math.abs(beginning.getX() - end.getX());
            int height = Math.abs(beginning.getY() - end.getY());
            Graphics cg = canvas.getGraphics();
            cg.setColor(color);
            cg.fillRect(x, y, width, height);    
            Graphics ig = image.getGraphics();
            ig.setColor(color);
            ig.fillRect(x, y, width, height);
            reset();
        });
    }
    
    public void reset() {
        beginning = null;
        end = null;
    }
    
    public boolean active() {
        return beginning != null;
    }
    
    @Override
    public void press(MouseEvent event) {
        if (settings.getMode() == Settings.GLIDE) {
            gliding = !gliding;
            if (gliding) {
                beginning = event;
            }
            else {
                end = event;
                draw();
            }
        }
        else {
            beginning = event;
        }
    }
   
    @Override
    public void move(MouseEvent event) {
        if (settings.getMode() == Settings.GLIDE) {
            if (gliding) {
                end = event;
                draw();
            }
        }
    }

    @Override
    public void drag(MouseEvent event) {
        if (settings.getMode() == Settings.DRAG) {
            end = event;
            draw();
        }
    }
}
