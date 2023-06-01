package paint.gui;

import java.awt.BorderLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import paint.App;
import paint.Settings;
import paint.listener.WindowListener;
import paint.tools.Toolbox;
import paint.transform.TransformFactory;

/**
 *
 * @author andrewtaylor
 */
public class Easel extends JFrame {

    private App app;
    private Toolbox toolbox;
    private TransformFactory transformFactory;
    private MenuBar menuBar;
    private JFileChooser fileChooser;
    private JPanel contentPane;
    private ToolBar toolbar;
    private Canvas canvas;
    
    public Easel(App app) {
        super("Paint ~ Canvas: 1200x725 ~ Image: 1200x75");
        this.app = app;       
        toolbox = new Toolbox(app);
        transformFactory = new TransformFactory(app);
    }
    
    public void createAndShowGui() {
        menuBar = new MenuBar(app);
        setJMenuBar(menuBar);
        fileChooser = new JFileChooser(System.getProperty("user.dir"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG", "png"));
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
        Settings settings = app.getSettings();
        LayeredImage image = settings.getLayeredImage();
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
    
    public void setTransformFactory(TransformFactory transforms) {
        this.transformFactory = transforms;
    }
    
    public TransformFactory getTransformFactory() {
        return transformFactory;
    }
    
    public void setFileChooser(JFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }
    
    public JFileChooser getFileChooser() {
        return fileChooser;
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
