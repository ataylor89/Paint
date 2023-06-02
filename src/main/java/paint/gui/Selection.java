package paint.gui;

import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author andrewtaylor
 */
public class Selection implements Serializable {
    
    private Point coordinate;
    private int width, height;
    
    private final static long serialVersionUID = 1L;
    
    public Selection() {}
    
    public Selection(Point coordinate, int width, int height) {
        this.coordinate = coordinate;
        this.width = width;
        this.height = height;
    }
    
    public Selection(int x, int y, int width, int height) {
        this(new Point(x, y), width, height);
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}