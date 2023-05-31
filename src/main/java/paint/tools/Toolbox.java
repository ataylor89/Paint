package paint.tools;

import paint.Paint;

/**
 *
 * @author andrewtaylor
 */
public class Toolbox {
    
    private Paint paint;
    
    public Toolbox(Paint paint) {
        this.paint = paint;
    }

    public Tool get(String name) {
        return switch (name.toLowerCase()) {
            case "brush" -> new Brush(paint);
            case "pen" -> new Pen(paint);
            case "eraser" -> new Eraser(paint);
            case "marquee" -> new Marquee(paint);
            default -> null;
        };
    }
}
