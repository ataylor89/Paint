package paint.algorithm;

/**
 *
 * @author andrewtaylor
 */
public class AlgorithmFactory {
    
    public static final Brush BRUSH = new Brush();
    public static final Pen PEN = new Pen();
    public static final Eraser ERASER = new Eraser();
    
    public static Algorithm getAlgorithm(String name) {
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
