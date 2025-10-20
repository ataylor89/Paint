package paint.menuactions;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;
import paint.App;
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
    private Easel easel;
    private Canvas canvas;

    public FillSelection(App app) {
        super("Fill selection");
        this.app = app;
        easel = app.getEasel();
        canvas = easel.getCanvas();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        canvas.repaint();
        SwingUtilities.invokeLater(() -> {
            Marquee marquee = (Marquee) app.getEasel().getToolbox().get("Marquee");
            Selection selection = marquee.calculate();
            Color color = app.getSettings().getPaintColor();
            Graphics canvasGraphics = canvas.getGraphics();
            Graphics imageGraphics = canvas.getLayeredImage().getForeground().getGraphics();
            canvasGraphics.setColor(color);
            canvasGraphics.fillRect(selection.x, selection.y, selection.width, selection.height);    
            imageGraphics.setColor(color);
            imageGraphics.fillRect(selection.x, selection.y, selection.width, selection.height);            
            app.getSettings().setMarquee(false);
        });
    }
}
