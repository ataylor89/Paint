package paint.menuactions;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;
import paint.App;
import paint.Settings;
import paint.gui.Easel;
import paint.gui.Canvas;
import paint.tools.Marquee;
import paint.tools.Selection;

/**
 *
 * @author andrewtaylor
 */
public class FillSelection extends AbstractAction {
    
    private App app;

    public FillSelection(App app) {
        super("Fill selection");
        this.app = app;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Settings settings = app.getSettings();
        Easel easel = app.getEasel();
        Canvas canvas = easel.getCanvas();
        canvas.repaint();
        SwingUtilities.invokeLater(() -> {
            Marquee marquee = (Marquee) easel.getToolbox().get("Marquee");
            Selection selection = marquee.calculate();
            Color color = settings.getPaintColor();
            Graphics canvasGraphics = canvas.getGraphics();
            Graphics imageGraphics = canvas.getLayeredImage().getForeground().getGraphics();
            canvasGraphics.setColor(color);
            canvasGraphics.fillRect(selection.x, selection.y, selection.width, selection.height);    
            imageGraphics.setColor(color);
            imageGraphics.fillRect(selection.x, selection.y, selection.width, selection.height);            
            settings.setMarquee(false);
        });
    }
}
