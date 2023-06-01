package paint.transform;

import paint.App;

/**
 *
 * @author andrewtaylor
 */
public class TransformFactory {
    
    private App app;
    
    public TransformFactory(App app) {
        this.app = app;
    }
    
    public Transform get(String transform) {
        return switch (transform.toLowerCase()) {
            case "clear" -> new ClearTransform(app);
            case "fill" -> new FillTransform(app);
            case "fitcanvastoimage" -> new FitCanvasToImage(app);
            case "fitimagetocanvas" -> new FitImageToCanvas(app);
            default -> null;
        };
    }
}
