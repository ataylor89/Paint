package paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author andrewtaylor
 */
public class MenuBar extends JMenuBar implements ActionListener {

    private Paint paint;
    private JMenu fileMenu;
    private JMenuItem create, open, save, saveAs, export, exit;
    
    public MenuBar(Paint paint) {
        super();
        this.paint = paint;
        init();
    }
    
    private void init() {
        fileMenu = new JMenu("File");
        create = new JMenuItem("New");
        create.addActionListener(this);
        fileMenu.add(create);
        open = new JMenuItem("Open");
        open.addActionListener(this);
        fileMenu.add(open);
        save = new JMenuItem("Save");
        save.addActionListener(this);
        fileMenu.add(save);
        saveAs = new JMenuItem("Save as");
        saveAs.addActionListener(this);
        fileMenu.add(saveAs);
        export = new JMenuItem("Export");
        export.addActionListener(this);
        fileMenu.add(export);
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        fileMenu.add(exit);
        add(fileMenu);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {    
        if (e.getSource() == export) {
            JFileChooser fileChooser = paint.getFileChooser();
            if (fileChooser.showSaveDialog(paint) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                BufferedImage image = paint.getCanvas().getImage();
                try {
                    ImageIO.write(image, "png", file);
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }
        else if (e.getSource() == exit) {
            dispatchEvent(new WindowEvent(paint, WindowEvent.WINDOW_CLOSING));
            System.exit(0);
        }
    }    
}
