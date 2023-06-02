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
import javax.swing.event.MenuEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import paint.App;
import paint.Settings;
import paint.gui.Easel;
import paint.gui.MenuBar;
import paint.transform.BackgroundTransform;
import paint.transform.FillTransform;
import paint.transform.FitCanvasToImage;
import paint.transform.FitImageToCanvas;
import paint.util.MenuAdapter;

/**
 *
 * @author andrewtaylor
 */
public class MenuBarListener extends MenuAdapter implements ActionListener {
    
    private App app;
    
    public MenuBarListener(App app) {
        this.app = app;
    }
    
    @Override
    public void menuSelected(MenuEvent e) {
        MenuBar menuBar = (MenuBar) app.getEasel().getJMenuBar();
        menuBar.refresh();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = (JMenuItem) e.getSource();
        String actionCommand = menuItem.getActionCommand();
        switch (actionCommand) {
            case "clear" -> {               
                app.restoreDefaults();
                app.notify("restoredDefaults");
            }
            case "open" -> {
                Easel easel = app.getEasel();
                JFileChooser fileChooser = easel.getFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("PNT", "pnt"));
                if (fileChooser.showOpenDialog(easel) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                        app.setSettings((Settings) in.readObject());
                        app.notify("openedFile");
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
            case "saveAs" -> {
                Settings settings = app.getSettings();
                Easel easel = app.getEasel();
                JFileChooser fileChooser = easel.getFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("PNT", "pnt"));
                if (fileChooser.showSaveDialog(easel) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                        settings.setFile(file);
                        out.writeObject(settings);
                        out.flush();
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
                    BufferedImage composite = settings.getLayeredImage().merge();
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
            case "fitCanvasToImage" -> {
                new FitCanvasToImage(app).apply();
                app.notify("resizedCanvas");
            }
            case "fitImageToCanvas" -> {
                Easel easel = app.getEasel();
                String message = "Are you sure you want to resize the image?";
                String title = "Resize image";
                int optionType = JOptionPane.YES_NO_OPTION;
                if (JOptionPane.showConfirmDialog(easel, message, title, optionType) == JOptionPane.YES_OPTION) {
                    new FitImageToCanvas(app).apply();
                    app.notify("resizedImage");
                }
            }
            case "fillSelection" -> {             
                new FillTransform(app).apply();
            }
            case "setBackgroundColor" -> {
                Settings settings = app.getSettings();
                Easel easel = app.getEasel();
                Color initial = settings.getBackgroundColor();
                Color color = JColorChooser.showDialog(easel, "Choose a color", initial);
                if (color != null) {
                    settings.setBackgroundColor(color);
                    new BackgroundTransform(app).apply();
                    app.notify("changedBackgroundColor");
                }
            }
        }
    }
    
}
