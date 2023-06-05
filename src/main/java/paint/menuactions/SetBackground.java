package paint.menuactions;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.swing.AbstractAction;
import javax.swing.JColorChooser;
import paint.App;
import paint.Settings;
import paint.gui.Easel;

/**
 *
 * @author andrewtaylor
 */
public class SetBackground extends AbstractAction {

    private App app;
    
    public SetBackground(App app) {
        super("Set background color");
        this.app = app;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Settings settings = app.getSettings();
        Easel easel = app.getEasel();
        Color initial = settings.getBackgroundColor();
        Color color = JColorChooser.showDialog(easel, "Choose a color", initial);
        if (color != null) {
            settings.setBackgroundColor(color);
            BufferedImage background = settings.getLayeredImage().getBackground();
            Graphics g = background.getGraphics();
            g.setColor(color);
            g.fillRect(0, 0, background.getWidth(), background.getHeight());
            app.notify("changedBackgroundColor");
        }
    }  
}
