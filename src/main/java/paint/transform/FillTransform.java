package paint.transform;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;
import paint.App;
import paint.gui.Canvas;
import paint.image.LayeredImage;
import paint.gui.Easel;
import paint.Settings;
import paint.gui.Selection;

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
        Easel easel = app.getEasel();
        Canvas canvas = easel.getCanvas();
        LayeredImage layers = settings.getLayeredImage();
        BufferedImage image = layers.getForeground();
        canvas.repaint();
        SwingUtilities.invokeLater(() -> {
            Selection selection = settings.getSelection();
            Point coordinate = selection.getCoordinate();
            int x = coordinate.x;
            int y = coordinate.y;
            int width = selection.getWidth();
            int height = selection.getHeight();
            Color color = settings.getPaintColor();
            Graphics cg = canvas.getGraphics();
            cg.setColor(color);
            cg.fillRect(x, y, width, height);    
            Graphics ig = image.getGraphics();
            ig.setColor(color);
            ig.fillRect(x, y, width, height);
            settings.setSelection(null);
            app.notify("selectionChanged");
        });
    }
}
