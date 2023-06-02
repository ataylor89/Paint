package paint.transform;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.SwingUtilities;
import paint.App;
import paint.Settings;
import paint.gui.Canvas;
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
        Canvas canvas = app.getEasel().getCanvas();
        canvas.repaint();
        SwingUtilities.invokeLater(() -> {
            Settings settings = app.getSettings();       
            Graphics canvasGraphics = canvas.getGraphics();
            Graphics imageGraphics = settings.getLayeredImage().getForeground().getGraphics();
            Color color = settings.getPaintColor();
            Selection sel = settings.getSelection();
            canvasGraphics.setColor(color);
            canvasGraphics.fillRect(sel.x, sel.y, sel.width, sel.height);    
            imageGraphics.setColor(color);
            imageGraphics.fillRect(sel.x, sel.y, sel.width, sel.height);            
            app.getSettings().setSelection(null);
        });
    }
}
