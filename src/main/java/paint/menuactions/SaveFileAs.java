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
    private Easel easel;
    private JFileChooser fileChooser;

    public SaveFileAs(App app) {
        super("Save as");
        this.app = app;
        easel = app.getEasel();
        fileChooser = new JFileChooser(System.getProperty("user.dir"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNT", "pnt"));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Settings settings = app.getSettings();
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
