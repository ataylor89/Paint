package paint;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import paint.tools.Tool;
import paint.tools.Toolbox;

/**
 *
 * @author andrewtaylor
 */
public class Canvas extends JPanel {
    
    private Paint paint;
    private BufferedImage image;
    private Tool tool;
    private Toolbox toolbox;
    
    public Canvas(Paint paint, int width, int height) {
        this.paint = paint;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        image.createGraphics();
        super.setBackground(Color.WHITE);
        super.setPreferredSize(new Dimension(width, height));
        super.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        MouseEventHandler handler = new MouseEventHandler();
        super.addMouseListener(handler);
        super.addMouseMotionListener(handler);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
        
    public void clear() {
        image = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        image.createGraphics();
        repaint();
    }
    
    public void resize() {
        BufferedImage newImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        image = newImage;
        paint.updateTitle();
    }
    
    public void fit() {
        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        paint.pack();
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
    
    public void setToolbox(Toolbox toolbox) {
        this.toolbox = toolbox;
    }
    
    public Toolbox getToolbox() {
        return toolbox;
    }
    
    private class MouseEventHandler extends MouseInputAdapter {
        @Override
        public void mousePressed(MouseEvent event) {
            tool.press(event);
        }
        @Override
        public void mouseMoved(MouseEvent event) {
            tool.move(event);
        }
        @Override
        public void mouseDragged(MouseEvent event) {
            tool.drag(event);
        }
    }
}
