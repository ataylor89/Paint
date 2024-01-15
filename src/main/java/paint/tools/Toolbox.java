package paint.tools;

import paint.App;

/**
 *
 * @author andrewtaylor
 */
public class Toolbox {
    
    private Brush brush;
    private Pen pen;
    private Eraser eraser;
    private Marquee marquee;
    private Line line;
    
    public Toolbox(App app) {
        brush = new Brush(app);
        pen = new Pen(app);
        eraser = new Eraser(app);
        marquee = new Marquee(app);
        line = new Line(app);
    }

    public Tool get(String name) {
        return switch (name.toLowerCase()) {
            case "brush" -> brush;
            case "pen" -> pen;
            case "eraser" -> eraser;
            case "marquee" -> marquee;
            case "line" -> line;
            default -> null;
        };
    }
}
