package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author andrewtaylor
 */
public class Canvas extends JPanel {
    public Canvas() {
        super.setPreferredSize(new Dimension(1000, 1000));
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawLine(200, 250, 500, 550);
    }
}
