package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author andrewtaylor
 */
public class Toolbar extends JPanel implements ActionListener {
    
    private DrawLineTest app;
    private JLabel x1label, y1label, x2label, y2label;
    private JTextField x1input, y1input, x2input, y2input;
    private JButton drawBtn;
    
    public Toolbar(DrawLineTest app) {
        this.app = app;
        init();
    }
    
    private void init() {
        x1label = new JLabel("x1");
        y1label = new JLabel("y1");
        x2label = new JLabel("x2");
        y2label = new JLabel("y2");
        x1input = new JTextField(10);
        y1input = new JTextField(10);
        x2input = new JTextField(10);
        y2input = new JTextField(10);
        drawBtn = new JButton("Draw");
        add(x1label);
        add(x1input);
        add(y1label);
        add(y1input);
        add(x2label);
        add(x2input);
        add(y2label);
        add(y2input);
        add(drawBtn);
        drawBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == drawBtn) {
            int x1 = Integer.parseInt(x1input.getText());
            int y1 = Integer.parseInt(y1input.getText());
            int x2 = Integer.parseInt(x2input.getText());
            int y2 = Integer.parseInt(y2input.getText());
            Graphics g = app.getCanvas().getGraphics();
            g.setColor(Color.BLUE);
            g.drawLine(x1, y1, x2, y2);
        }
    }
}
