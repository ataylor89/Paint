package paint.tools;

import java.io.Serializable;

/**
 *
 * @author andrewtaylor
 */
public class Selection implements Serializable {
    
    public int x, y, width, height;
    private final static long serialVersionUID = 1L;
    
    public Selection() {}
       
    public Selection(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
