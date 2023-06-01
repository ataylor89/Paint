package paint.transform;

import paint.App;
import paint.listener.TransformListener;

/**
 *
 * @author andrewtaylor
 */
public class TransformFactory {
    
    private App app;
    private TransformListener listener;
    
    public TransformFactory(App app) {
        this.app = app;
        listener = new TransformListener(app);
    }
    
    public Transform get(String transform) {
        return switch (transform.toLowerCase()) {
            case "clear" -> new ClearTransform(app);
            case "fill" -> new FillTransform(app);
            case "fitcanvastoimage" -> new FitCanvasToImage(app, listener);
            case "fitimagetocanvas" -> new FitImageToCanvas(app);
            default -> null;
        };
    }
}
