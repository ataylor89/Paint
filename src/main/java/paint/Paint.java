package paint;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author andrewtaylor
 */
public class Paint extends JFrame implements ActionListener {

    private JPanel contentPane;
    private TopPanel toolbar;
    private Canvas canvas;
    
    public Paint() {
        super("Paint");        
    }
    
    public void createAndShowGui() {
        contentPane = new JPanel();
        contentPane.setPreferredSize(new Dimension(1200, 900));
        contentPane.setLayout(new BorderLayout());
        toolbar = new TopPanel();
        contentPane.add(toolbar, BorderLayout.NORTH);
        canvas = new Canvas();
        contentPane.add(canvas, BorderLayout.CENTER);
        setContentPane(contentPane);
        setResizable(false);
        pack();
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    public static void main(String[] args) {
        Paint app = new Paint();
        app.createAndShowGui();
    }
}
