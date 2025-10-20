package paint.menuactions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.AbstractAction;
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
public class SaveFile extends AbstractAction {

    private App app;
    
    public SaveFile(App app) {
        super("Save");
        this.app = app;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Settings settings = app.getSettings();
        File file = settings.getFile();
        Easel easel = app.getEasel();
        Canvas canvas = easel.getCanvas();
        LayeredImage layeredImage = canvas.getLayeredImage();
        PaintObject paintObject = new PaintObject(settings, layeredImage);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(paintObject);
            out.flush();
        } catch (IOException ex) {
            System.err.println(ex);
        }       
    }
    
}
