package paint.tools;

import paint.Paint;

/**
 *
 * @author andrewtaylor
 */
public class Toolbox {
    
    private Brush brush;
    private Pen pen;
    private Eraser eraser;
    
    public Toolbox(Paint paint) {
        brush = new Brush(paint);
        pen = new Pen(paint);
        eraser = new Eraser(paint);
    }

    public Tool get(String name) {
        switch (name.toLowerCase()) {
            case "brush":
                return brush;
            case "pen":
                return pen;
            case "eraser":
                return eraser;
            default:
                return null;
        }
    }
}
