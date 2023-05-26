package paint.algorithm;

/**
 *
 * @author andrewtaylor
 */
public class AlgorithmFactory {
    
    public static final SparseBrush SPARSE_BRUSH = new SparseBrush();
    public static final LineTool LINE_TOOL = new LineTool();
    public static final EraseTool ERASE_TOOL = new EraseTool();
    
    public static Algorithm getAlgorithm(String name) {
        if (name.equals("Sparse")) {
            return SPARSE_BRUSH;
        }
        if (name.equals("Line")) {
            return LINE_TOOL;
        }
        if (name.equals("Erase")) {
            return ERASE_TOOL;
        }
        return null;
    }
}
