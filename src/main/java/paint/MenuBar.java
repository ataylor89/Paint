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
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import paint.tools.Marquee;
import paint.tools.Tool;
import paint.util.MenuAdapter;

/**
 *
 * @author andrewtaylor
 */
public class MenuBar extends JMenuBar implements ActionListener {

    private Paint paint;
    private JMenu fileMenu;
    private JMenuItem clear, open, save, saveAs, exit;
    private JMenu toolsMenu;
    private JMenuItem fit, resize, fill;
    
    public MenuBar(Paint paint) {
        super();
        this.paint = paint;
        buildUI();
    }
    
    private void buildUI() {
        fileMenu = new JMenu("File");
        clear = new JMenuItem("New");
        clear.addActionListener(this);
        fileMenu.add(clear);
        open = new JMenuItem("Open");
        open.addActionListener(this);
        fileMenu.add(open);
        save = new JMenuItem("Save");
        save.addActionListener(this);
        fileMenu.add(save);
        saveAs = new JMenuItem("Save as");
        saveAs.addActionListener(this);
        fileMenu.add(saveAs);
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        fileMenu.add(exit);
        fileMenu.addMenuListener(new FileMenuListener());
        add(fileMenu);
        toolsMenu = new JMenu("Tools");
        fit = new JMenuItem("Fit to image");
        fit.addActionListener(this);
        toolsMenu.add(fit);
        resize = new JMenuItem("Resize image");
        resize.addActionListener(this);
        toolsMenu.add(resize);
        fill = new JMenuItem("Fill selection");
        fill.addActionListener(this);
        toolsMenu.add(fill);
        toolsMenu.addMenuListener(new ToolsMenuListener());
        add(toolsMenu);
    }
    
    private void open() {
        JFileChooser fileChooser = paint.getFileChooser();
        if (fileChooser.showOpenDialog(paint) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedImage image = ImageIO.read(file);
                Canvas canvas = paint.getCanvas();
                canvas.setImage(image);
                canvas.fit();
                paint.setFile(file);
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
    
    private void save() {
        File file = paint.getFile();
        BufferedImage image = paint.getCanvas().getImage();
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    private void saveAs() {
        JFileChooser fileChooser = paint.getFileChooser();
        if (fileChooser.showSaveDialog(paint) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedImage image = paint.getCanvas().getImage();
            try {
                ImageIO.write(image, "png", file);
                paint.setFile(file);
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
    
    private void resize() {
        String message = "Are you sure you want to resize the image?";
        String title = "Resize image";
        int optionType = JOptionPane.YES_NO_OPTION;
        if (JOptionPane.showConfirmDialog(paint, message, title, optionType) == JOptionPane.YES_OPTION) {
            paint.getCanvas().resize();
            paint.refreshTitle();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clear) {
            paint.getCanvas().clear();
            paint.setFile(null);
        }
        else if (e.getSource() == open) {
            open();
        }
        else if (e.getSource() == save) {
            save();
        }
        else if (e.getSource() == saveAs) {
            saveAs();
        }
        else if (e.getSource() == exit) {
            dispatchEvent(new WindowEvent(paint, WindowEvent.WINDOW_CLOSING));
            System.exit(0);
        }
        else if (e.getSource() == fit) {
            paint.getCanvas().fit();
        }
        else if (e.getSource() == resize) {
            resize();
        }
        else if (e.getSource() == fill) {
            Marquee marquee = (Marquee) paint.getCanvas().getTool();
            marquee.fill();
        }
    }
    
    private class FileMenuListener extends MenuAdapter {     
        @Override
        public void menuSelected(MenuEvent e) {
            save.setEnabled(paint.getFile() != null);
        }     
    }
    
    private class ToolsMenuListener extends MenuAdapter {      
        @Override
        public void menuSelected(MenuEvent e) {
            Canvas canvas = paint.getCanvas();
            Tool tool = canvas.getTool();
            if (tool instanceof Marquee) {
                Marquee marquee = (Marquee) tool;
                fill.setEnabled(marquee.active());
            }
            else {
                fill.setEnabled(false);
            }
        } 
    }
}
