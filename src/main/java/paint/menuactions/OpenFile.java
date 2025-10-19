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
    private Easel easel;
    private JFileChooser fileChooser;

    public OpenFile(App app) {
        super("Open");
        this.app = app;
        easel = app.getEasel();
        fileChooser = new JFileChooser(System.getProperty("user.dir"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNT", "pnt"));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
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
