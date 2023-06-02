package paint.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import paint.App;
import paint.image.LayeredImage;
import paint.gui.Easel;
import paint.Settings;
import paint.transform.BackgroundTransform;
import paint.transform.ClearTransform;
import paint.transform.FillTransform;
import paint.transform.FitCanvasToImage;
import paint.transform.FitImageToCanvas;

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
                settings.setFile(null);
                ClearTransform transform = new ClearTransform(app);
                transform.apply();
                app.restoreDefaults();
                app.notify("fileChanged");
            }
            case "open" -> {
                Easel easel = app.getEasel();
                JFileChooser fileChooser = easel.getFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("PNT", "pnt"));
                if (fileChooser.showOpenDialog(easel) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                        Settings settings = (Settings) in.readObject();
                        app.setSettings(settings);
                        FitCanvasToImage transform = new FitCanvasToImage(app);
                        transform.apply();
                        settings.setFile(file);
                        app.notify("fileChanged");
                    } catch (IOException | ClassNotFoundException ex) {
                        System.err.println(ex);
                    }
                }
            }
            case "save" -> {
                Settings settings = app.getSettings();
                File file = settings.getFile();
                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                    out.writeObject(settings);
                    out.flush();
                } catch (IOException ex) {
                    System.err.println(ex);
                }       
            }
            case "saveas" -> {
                Settings settings = app.getSettings();
                Easel easel = app.getEasel();
                JFileChooser fileChooser = easel.getFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("PNT", "pnt"));
                if (fileChooser.showSaveDialog(easel) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                        out.writeObject(settings);
                        out.flush();
                        settings.setFile(file);
                        app.notify("fileChanged");
                    } catch (IOException ex) {
                        System.err.println(ex);
                    }                  
                }
            }
            case "export" -> {
                Settings settings = app.getSettings();
                Easel easel = app.getEasel();
                JFileChooser fileChooser = easel.getFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("PNG", "png"));
                if (fileChooser.showSaveDialog(easel) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    LayeredImage image = settings.getLayeredImage();
                    BufferedImage composite = image.merge();
                    try {
                        ImageIO.write(composite, "png", file);
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
                FitCanvasToImage transform = new FitCanvasToImage(app);
                transform.apply();
            }
            case "fitimagetocanvas" -> {
                Easel easel = app.getEasel();
                String message = "Are you sure you want to resize the image?";
                String title = "Resize image";
                int optionType = JOptionPane.YES_NO_OPTION;
                if (JOptionPane.showConfirmDialog(easel, message, title, optionType) == JOptionPane.YES_OPTION) {
                    FitImageToCanvas transform = new FitImageToCanvas(app);
                    transform.apply();
                }
            }
            case "fillselection" -> {
                FillTransform transform = new FillTransform(app);
                transform.apply();
            }
            case "setbackgroundcolor" -> {
                Settings settings = app.getSettings();
                Easel easel = app.getEasel();
                Color initial = settings.getBackgroundColor();
                Color color = JColorChooser.showDialog(easel, "Choose a color", initial);
                if (color != null) {
                    settings.setBackgroundColor(color);
                    BackgroundTransform transform = new BackgroundTransform(app);
                    transform.apply();
                }
            }
        }
    }
    
}
