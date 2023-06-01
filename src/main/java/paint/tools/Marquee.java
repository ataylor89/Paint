package paint.tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import paint.App;
import paint.gui.Canvas;
import paint.gui.Easel;
import paint.Settings;

/**
 *
 * @author andrewtaylor
 */
public class Marquee extends Tool {

    private App app;
    private MouseEvent beginning, end;
    private final Stroke stroke;
    
    public Marquee(App app) {
        this.app = app;
        stroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {10}, 0);
    }
    
    public void draw() {
        Settings settings = app.getSettings();
        Easel easel = app.getEasel();
        Canvas canvas = easel.getCanvas();
        canvas.repaint();
        SwingUtilities.invokeLater(() -> {
            Graphics2D cg = (Graphics2D) canvas.getGraphics();
            cg.setColor(Color.DARK_GRAY);
            cg.setStroke(stroke);
            int x1 = beginning.getX();
            int y1 = beginning.getY();
            int x2 = end.getX();
            int y2 = end.getY();
            int x = Math.min(x1, x2);
            int y = Math.min(y1, y2);
            int width = Math.abs(x1 - x2);
            int height = Math.abs(y1 - y2);
            cg.drawRect(x, y, width, height);
            settings.notify("marqueeActive");
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
        Settings settings = app.getSettings();
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
        Settings settings = app.getSettings();
        if (settings.getMode() == Settings.GLIDE) {
            if (gliding) {
                end = event;
                draw();
            }
        }
    }

    @Override
    public void drag(MouseEvent event) {
        Settings settings = app.getSettings();
        if (settings.getMode() == Settings.DRAG) {
            end = event;
            draw();
        }
    }

    public void setBeginning(MouseEvent beginning) {
        this.beginning = beginning;
    }
    
    public MouseEvent getBeginning() {
        return beginning;
    }

    public void setEnd(MouseEvent end) {
        this.end = end;
    }
    
    public MouseEvent getEnd() {
        return end;
    }
}
