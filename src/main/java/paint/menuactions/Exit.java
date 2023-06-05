package paint.menuactions;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import paint.App;
import paint.gui.Easel;

/**
 *
 * @author andrewtaylor
 */
public class Exit extends AbstractAction {

    private App app;
    
    public Exit(App app) {
        super("Exit");
        this.app = app;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Easel easel = app.getEasel();
        easel.dispatchEvent(new WindowEvent(easel, WindowEvent.WINDOW_CLOSING));
        System.exit(0);
    }
    
}
