package paint.menuactions;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;
import paint.App;
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
        Canvas canvas = app.getEasel().getCanvas();
        canvas.repaint();
        SwingUtilities.invokeLater(() -> {
            Marquee marquee = (Marquee) app.getEasel().getToolbox().get("Marquee");
            Selection selection = marquee.calculate();
            Color color = app.getSettings().getPaintColor();
            Graphics canvasGraphics = canvas.getGraphics();
            Graphics imageGraphics = app.getSettings().getLayeredImage().getForeground().getGraphics();            
            canvasGraphics.setColor(color);
            canvasGraphics.fillRect(selection.x, selection.y, selection.width, selection.height);    
            imageGraphics.setColor(color);
            imageGraphics.fillRect(selection.x, selection.y, selection.width, selection.height);            
            app.getSettings().setMarquee(false);
        });
    }
}
