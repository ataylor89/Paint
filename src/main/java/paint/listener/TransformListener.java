package paint.listener;

import paint.App;
import paint.gui.Easel;

/**
 *
 * @author andrewtaylor
 */
public class TransformListener {
    
    private App app;
    
    public TransformListener(App app) {
        this.app = app;
    }
    
    public void notify(String signal) {
        switch (signal.toLowerCase()) {
            case "fitcanvastoimage" -> {
                Easel easel = app.getEasel();
                easel.refreshTitle();
            }
        }
    }
}
