package paint.menuactions;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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
public class Export extends AbstractAction {

    private App app;
    
    public Export(App app) {
        super("Export");
        this.app = app;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
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
    
}
