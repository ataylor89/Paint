package paint;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
        super("Paint");
    }
    
    public void createAndShowGui() {
        settings = new Settings();
        menuBar = new MenuBar(this);
        setJMenuBar(menuBar);
        fileChooser = new JFileChooser(System.getProperty("user.dir"));
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        toolbar = new TopPanel(this);
        contentPane.add(toolbar, BorderLayout.NORTH);
        canvas = new Canvas(this);
        contentPane.add(canvas, BorderLayout.CENTER);
        setContentPane(contentPane);
        setResizable(false);
        pack();
        setVisible(true);
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
