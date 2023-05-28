package paint;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import paint.tools.Tool;
import paint.tools.Toolbox;

/**
 *
 * @author andrewtaylor
 */
public class Canvas extends JPanel {
    
    private BufferedImage image;
    private Tool tool;
    private Toolbox toolbox;
    private int width, height;
    
    public Canvas(Paint paint) {
        toolbox = new Toolbox(paint);
        tool = toolbox.get("Brush");
        width = 1200;
        height = 725;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        image.createGraphics();
        super.setBackground(Color.WHITE);
        super.setPreferredSize(new Dimension(width, height));
        super.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        super.addMouseListener(new MousePressListener());
        super.addMouseMotionListener(new MouseMotionListener());
    }
        
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    public BufferedImage getImage() {
        return image;
    }
    
    public void setTool(Tool tool) {
        this.tool = tool;
    }
    
    public Tool getTool() {
        return tool;
    }
    
    public Toolbox getToolbox() {
        return toolbox;
    }
    
    private class MousePressListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event) {
            tool.press(event);
        }
    }
    
    private class MouseMotionListener extends MouseMotionAdapter {
        @Override
        public void mouseMoved(MouseEvent event) {
            tool.move(event);
        }
    }
}
