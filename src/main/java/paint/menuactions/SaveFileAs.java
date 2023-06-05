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

/**
 *
 * @author andrewtaylor
 */
public class SaveFileAs extends AbstractAction {
    
    private App app;
    
    public SaveFileAs(App app) {
        super("Save as");
        this.app = app;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
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
    
}
