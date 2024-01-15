package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author andrewtaylor
 */
public class DrawLineTest extends JFrame {
    
    private JPanel contentPane;
    private Canvas canvas;
    private Toolbar toolbar;
    
    public DrawLineTest() {
        super("Draw Line Test");
    }
    
    public void createAndShowGui() {
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        toolbar = new Toolbar(this);
        contentPane.add(toolbar, BorderLayout.NORTH);
        canvas = new Canvas();
        contentPane.add(canvas, BorderLayout.CENTER);
        setContentPane(contentPane);
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args) {
        DrawLineTest test = new DrawLineTest();
        test.createAndShowGui();
    }
    
    public Canvas getCanvas() {
        return canvas;
    }
    
    public Toolbar getToolbar() {
        return toolbar;
    }
}
