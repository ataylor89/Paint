package paint.menuactions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import paint.App;

/**
 *
 * @author andrewtaylor
 */
public class NewFile extends AbstractAction {

    private App app;
    
    public NewFile(App app) {
        super("New");
        this.app = app;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        app.restoreDefaults();
        new FitCanvasToImage(app).actionPerformed(e);
        app.notify("restoredDefaults");
    }
    
}
