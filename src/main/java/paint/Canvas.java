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
    public void mouseClicked(MouseEvent event) {}

    @Override
    public void mousePressed(MouseEvent event) {
        algorithm.mousePressed(event);
    }

    @Override
    public void mouseReleased(MouseEvent event) {}

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}

    @Override
    public void mouseDragged(MouseEvent event) {
        algorithm.mouseDragged(event);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        algorithm.mouseMoved(event);
    }
}
