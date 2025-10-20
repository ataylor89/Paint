package paint.gui;

import paint.image.LayeredImage;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import paint.App;
import paint.listeners.WindowListener;
import paint.tools.Toolbox;

/**
 *
 * @author andrewtaylor
 */
public class Easel extends JFrame {

    private App app;
    private Toolbox toolbox;
    private MenuBar menuBar;
    private JPanel contentPane;
    private ToolBar toolbar;
    private Canvas canvas;
    
    public Easel(App app) {
        super("Paint ~ Canvas: 1200x725 ~ Image: 1200x75");
        this.app = app;       
        toolbox = new Toolbox(app);
    }
    
    public void createAndShowGui() {
        menuBar = new MenuBar(app);
        setJMenuBar(menuBar);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        toolbar = new ToolBar(app);
        contentPane.add(toolbar, BorderLayout.NORTH);
        canvas = new Canvas(app, 1200, 725);
        contentPane.add(canvas, BorderLayout.CENTER);
        addComponentListener(new WindowListener(app));
        setContentPane(contentPane);
        pack();
        setVisible(true);
    }
    
    public void refreshTitle() {
        LayeredImage image = canvas.getLayeredImage();
        setTitle(String.format(
            "Paint ~ Canvas: %dx%d ~ Image: %dx%d",
            canvas.getWidth(),
            canvas.getHeight(),
            image.getWidth(),
            image.getHeight()
        ));
    }
    
    public void setToolbox(Toolbox toolbox) {
        this.toolbox = toolbox;
    }
    
    public Toolbox getToolbox() {
        return toolbox;
    }
    
    public void setToolBar(ToolBar toolbar) {
        this.toolbar = toolbar;
    }
    
    public ToolBar getToolBar() {
        return toolbar;
    }
    
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
    
    public Canvas getCanvas() {
        return canvas;
    }
}
