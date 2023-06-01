package paint.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import paint.App;
import paint.gui.LayeredImage;
import paint.gui.Easel;
import paint.Settings;
import paint.transform.Transform;
import paint.transform.TransformFactory;

/**
 *
 * @author andrewtaylor
 */
public class MenuListener implements ActionListener {
    
    private App app;
    
    public MenuListener(App app) {
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = (JMenuItem) e.getSource();
        String actionCommand = menuItem.getActionCommand();
        switch (actionCommand.toLowerCase()) {
            case "clear" -> {
                Settings settings = app.getSettings();
                Easel easel = app.getEasel();
                TransformFactory factory = easel.getTransformFactory();
                Transform clear = factory.get("clear");
                clear.apply();
                settings.setFile(null);
            }
            case "open" -> {
                Settings settings = app.getSettings();
                Easel easel = app.getEasel();
                JFileChooser fileChooser = easel.getFileChooser();
                if (fileChooser.showOpenDialog(easel) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        BufferedImage image = ImageIO.read(file);
                        settings.setLayeredImage(new LayeredImage(image));
                        TransformFactory transforms = easel.getTransformFactory();
                        Transform transform = transforms.get("FitCanvasToImage");
                        transform.apply();
                        settings.setFile(file);
                    } catch (IOException ex) {
                        System.err.println(ex);
                    }
                }
            }
            case "save" -> {
                Settings settings = app.getSettings();
                Easel easel = app.getEasel();
                File file = settings.getFile();
                LayeredImage layers = settings.getLayeredImage();       
                BufferedImage image = layers.merge();
                try {
                    ImageIO.write(image, "png", file);
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
            case "saveas" -> {
                Settings settings = app.getSettings();
                Easel easel = app.getEasel();
                JFileChooser fileChooser = easel.getFileChooser();
                if (fileChooser.showSaveDialog(easel) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    LayeredImage layers = settings.getLayeredImage();
                    BufferedImage image = layers.merge();
                    try {
                        ImageIO.write(image, "png", file);
                        settings.setFile(file);
                    } catch (IOException ex) {
                        System.err.println(ex);
                    }
                }
            }
            case "exit" -> {
                Easel easel = app.getEasel();
                easel.dispatchEvent(new WindowEvent(easel, WindowEvent.WINDOW_CLOSING));
                System.exit(0);
            }
            case "fitcanvastoimage" -> {
                Easel easel = app.getEasel();
                TransformFactory factory = easel.getTransformFactory();
                Transform transform = factory.get("FitCanvasToImage");
                transform.apply();
            }
            case "fitimagetocanvas" -> {
                Easel easel = app.getEasel();
                String message = "Are you sure you want to resize the image?";
                String title = "Resize image";
                int optionType = JOptionPane.YES_NO_OPTION;
                if (JOptionPane.showConfirmDialog(easel, message, title, optionType) == JOptionPane.YES_OPTION) {
                    TransformFactory transforms = easel.getTransformFactory();
                    Transform transform = transforms.get("FitImageToCanvas");
                    transform.apply();
                }
            }
            case "fillselection" -> {
                Easel easel = app.getEasel();
                TransformFactory factory = easel.getTransformFactory();
                Transform transform = factory.get("Fill");
                transform.apply();
            }  
        }
    }
    
}
