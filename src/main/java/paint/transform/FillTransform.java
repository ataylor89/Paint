package paint.transform;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;
import paint.App;
import paint.gui.Canvas;
import paint.gui.LayeredImage;
import paint.gui.Easel;
import paint.Settings;
import paint.tools.Marquee;

/**
 *
 * @author andrewtaylor
 */
public class FillTransform implements Transform {
    
    private App app;
    
    public FillTransform(App app) {
        this.app = app;
    }
    
    @Override
    public void apply() {
        Settings settings = app.getSettings();
        Marquee marquee = (Marquee) settings.getTool();
        int x1 = marquee.getBeginning().getX();
        int y1 = marquee.getBeginning().getY();
        int x2 = marquee.getEnd().getX();
        int y2 = marquee.getEnd().getY();
        Easel easel = app.getEasel();
        Canvas canvas = easel.getCanvas();
        LayeredImage layers = settings.getLayeredImage();
        BufferedImage image = layers.getForeground();
        canvas.repaint();
        SwingUtilities.invokeLater(() -> {
            Color color = settings.getPaintColor();
            int x = Math.min(x1, x2);
            int y = Math.min(y1, y2);
            int width = Math.abs(x1 - x2);
            int height = Math.abs(y1 - y2);
            Graphics cg = canvas.getGraphics();
            cg.setColor(color);
            cg.fillRect(x, y, width, height);    
            Graphics ig = image.getGraphics();
            ig.setColor(color);
            ig.fillRect(x, y, width, height);
            marquee.reset();
        });
    }
}
