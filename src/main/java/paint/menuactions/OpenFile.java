package paint.menuactions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
public class OpenFile extends AbstractAction {

    private App app;
    
    public OpenFile(App app) {
        super("Open");
        this.app = app;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Easel easel = app.getEasel();
        JFileChooser fileChooser = easel.getFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNT", "pnt"));
        if (fileChooser.showOpenDialog(easel) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                app.setSettings((Settings) in.readObject());
                new FitCanvasToImage(app).actionPerformed(e);
                app.notify("openedFile");
            } catch (IOException | ClassNotFoundException ex) {
                System.err.println(ex);
            }
        }
    }
    
}
