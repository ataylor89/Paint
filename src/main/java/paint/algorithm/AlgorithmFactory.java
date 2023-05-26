package paint.algorithm;

/**
 *
 * @author andrewtaylor
 */
public class AlgorithmFactory {
    
    public static final SparseBrush SPARSE_BRUSH = new SparseBrush();
    public static final LineTool LINE_TOOL = new LineTool();
    
    public static Algorithm getAlgorithm(String name) {
        if (name.equals("Sparse")) {
            return SPARSE_BRUSH;
        }
        if (name.equals("Line")) {
            return LINE_TOOL;
        }
        return null;
    }
}
