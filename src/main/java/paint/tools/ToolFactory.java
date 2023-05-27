package paint.tools;

/**
 *
 * @author andrewtaylor
 */
public class ToolFactory {
    
    public static final Brush BRUSH = new Brush();
    public static final Pen PEN = new Pen();
    public static final Eraser ERASER = new Eraser();
    
    public static Tool getTool(String name) {
        if (name.equals("Brush")) {
            return BRUSH;
        }
        if (name.equals("Pen")) {
            return PEN;
        }
        if (name.equals("Eraser")) {
            return ERASER;
        }
        return null;
    }
}
