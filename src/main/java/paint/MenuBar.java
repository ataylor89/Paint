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
import javax.swing.JOptionPane;

/**
 *
 * @author andrewtaylor
 */
public class MenuBar extends JMenuBar implements ActionListener {

    private Paint paint;
    private JMenu fileMenu;
    private JMenuItem create, open, save, saveAs, exit;
    private JMenu toolsMenu;
    private JMenuItem fit, resize;
    
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
        save.setEnabled(false);
        save.addActionListener(this);
        fileMenu.add(save);
        saveAs = new JMenuItem("Save as");
        saveAs.addActionListener(this);
        fileMenu.add(saveAs);
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        fileMenu.add(exit);
        add(fileMenu);
        toolsMenu = new JMenu("Tools");
        fit = new JMenuItem("Fit to image");
        fit.addActionListener(this);
        toolsMenu.add(fit);
        resize = new JMenuItem("Resize image");
        resize.addActionListener(this);
        toolsMenu.add(resize);
        add(toolsMenu);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            paint.setFile(null);
            paint.getCanvas().clear();
            save.setEnabled(false);
        }
        else if (e.getSource() == open) {
            JFileChooser fileChooser = paint.getFileChooser();
            if (fileChooser.showOpenDialog(paint) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedImage image = ImageIO.read(file);
                    Canvas canvas = paint.getCanvas();
                    canvas.setImage(image);
                    canvas.repaint();
                    paint.setFile(file);
                    save.setEnabled(true);
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }
        else if (e.getSource() == save) {
            File file = paint.getFile();
            BufferedImage image = paint.getCanvas().getImage();
            try {
                ImageIO.write(image, "png", file);
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
        else if (e.getSource() == saveAs) {
            JFileChooser fileChooser = paint.getFileChooser();
            if (fileChooser.showSaveDialog(paint) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                BufferedImage image = paint.getCanvas().getImage();
                try {
                    ImageIO.write(image, "png", file);
                    paint.setFile(file);
                    save.setEnabled(true);
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }
        else if (e.getSource() == exit) {
            dispatchEvent(new WindowEvent(paint, WindowEvent.WINDOW_CLOSING));
            System.exit(0);
        }
        else if (e.getSource() == fit) {
            paint.getCanvas().fit();
        }
        else if (e.getSource() == resize) {
            String message = "Are you sure you want to resize the underlying image?";
            String title = "Resize image";
            int optionType = JOptionPane.YES_NO_OPTION;
            if (JOptionPane.showConfirmDialog(paint, message, title, optionType) == JOptionPane.YES_OPTION) {
                paint.getCanvas().resize();
            }
        }
    }
}
