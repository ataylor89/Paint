package paint.listener;

import paint.App;

/**
 *
 * @author andrewtaylor
 */
public class TransformListener {
    
    private App app;
    
    public TransformListener(App app) {
        this.app = app;
    }
    
    public void notify(String signal) {}
}
