package paint.tools;

import paint.App;

/**
 *
 * @author andrewtaylor
 */
public class Toolbox {
    
    private App app;
    
    public Toolbox(App app) {
        this.app = app;
    }

    public Tool get(String name) {
        return switch (name.toLowerCase()) {
            case "brush" -> new Brush(app);
            case "pen" -> new Pen(app);
            case "eraser" -> new Eraser(app);
            case "marquee" -> new Marquee(app);
            default -> null;
        };
    }
}
