package paint.menuactions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.AbstractAction;
import paint.App;
import paint.Settings;

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
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(settings);
            out.flush();
        } catch (IOException ex) {
            System.err.println(ex);
        }       
    }
    
}
