package paint;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import paint.tools.Toolbox;

/**
 *
 * @author andrewtaylor
 */
public class Paint extends JFrame {

    private Settings settings;
    private MenuBar menuBar;
    private JFileChooser fileChooser;
    private File file;
    private JPanel contentPane;
    private TopPanel toolbar;
    private Canvas canvas;
    
    public Paint() {
        super("Paint - 1200x725");
    }
    
    public void createAndShowGui() {
        settings = new Settings();
        menuBar = new MenuBar(this);
        setJMenuBar(menuBar);
        fileChooser = new JFileChooser(System.getProperty("user.dir"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG", "png"));
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        toolbar = new TopPanel(this);
        contentPane.add(toolbar, BorderLayout.NORTH);
        canvas = new Canvas(this, 1200, 725);
        Toolbox toolbox = new Toolbox(this);
        canvas.setToolbox(toolbox);
        canvas.setTool(toolbox.get("Brush"));
        contentPane.add(canvas, BorderLayout.CENTER);
        setContentPane(contentPane);
        pack();
        setVisible(true);
    }
    
    public void updateTitle() {
        BufferedImage image = canvas.getImage();
        setTitle("Paint - " + image.getWidth() + "x" + image.getHeight());
    }
    
    public Settings getSettings() {
        return settings;
    }
    
    public JFileChooser getFileChooser() {
        return fileChooser;
    }
    
    public void setFile(File file) {
        this.file = file;
    }
    
    public File getFile() {
        return file;
    }
    
    public Canvas getCanvas() {
        return canvas;
    }
        
    public static void main(String[] args) {
        Paint app = new Paint();
        app.createAndShowGui();
    }
}
