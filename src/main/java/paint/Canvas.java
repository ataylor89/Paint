package paint;

import paint.algorithm.Algorithm;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import paint.algorithm.AlgorithmFactory;

/**
 *
 * @author andrewtaylor
 */
public class Canvas extends JPanel implements MouseListener, MouseMotionListener {
    
    private Algorithm algorithm;
    
    public Canvas() {
        init();
    }
    
    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }
    
    public Algorithm getAlgorithm() {
        return algorithm;
    }
    
    private void init() {
        algorithm = AlgorithmFactory.getAlgorithm("Sparse");
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        algorithm.mousePressed(this, e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        algorithm.mouseDragged(this, e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        algorithm.mouseMoved(this, e);
    }
}
