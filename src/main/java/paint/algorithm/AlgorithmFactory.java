package paint.algorithm;

/**
 *
 * @author andrewtaylor
 */
public class AlgorithmFactory {
    
    private static Algorithm sparse;
    private static Algorithm line;
    
    public static Algorithm getAlgorithm(String name) {
        if (name.equals("Sparse")) {
            if (sparse == null) {
                sparse = new SparseAlgorithm();
            }
            return sparse;
        }
        if (name.equals("Line")) {
            if (line == null) {
                line = new LineAlgorithm();
            }
            return line;
        }
        return null;
    }
}
