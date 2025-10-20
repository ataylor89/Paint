package paint.menuactions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import paint.App;
import paint.Settings;
import paint.gui.Easel;
import paint.gui.Canvas;
import paint.image.LayeredImage;
import paint.persistence.PaintObject;

/**
 *
 * @author andrewtaylor
 */
public class SaveFileAs extends AbstractAction {
    
    private App app;
    private JFileChooser fileChooser;

    public SaveFileAs(App app) {
        super("Save as");
        this.app = app;
        fileChooser = new JFileChooser(System.getProperty("user.dir"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNT", "pnt"));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Settings settings = app.getSettings();
        Easel easel = app.getEasel();
        Canvas canvas = easel.getCanvas();
        LayeredImage layeredImage = canvas.getLayeredImage();
        if (fileChooser.showSaveDialog(easel) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            settings.setFile(file);
            PaintObject paintObject = new PaintObject(settings, layeredImage);
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                out.writeObject(paintObject);
                out.flush();
            } catch (IOException ex) {
                System.err.println(ex);
            }                  
        }
    }
    
}
