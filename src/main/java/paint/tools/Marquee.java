package paint.tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import paint.App;
import paint.Settings;
import paint.gui.Canvas;
import paint.gui.Selection;

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
        Canvas canvas = app.getEasel().getCanvas();
        canvas.repaint();
        SwingUtilities.invokeLater(() -> {
            int x1 = beginning.getX();
            int y1 = beginning.getY();
            int x2 = end.getX();
            int y2 = end.getY();
            int x = Math.min(x1, x2);
            int y = Math.min(y1, y2);
            int width = Math.abs(x1 - x2);
            int height = Math.abs(y1 - y2);
            Graphics2D cg = (Graphics2D) canvas.getGraphics();
            cg.setColor(Color.BLACK);
            cg.setStroke(stroke);
            cg.drawRect(x, y, width, height);
            Settings settings = app.getSettings();
            settings.setSelection(new Selection(x, y, width, height));
        });
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
}
