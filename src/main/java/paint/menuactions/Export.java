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
import paint.gui.Easel;
import paint.gui.Canvas;

/**
 *
 * @author andrewtaylor
 */
public class Export extends AbstractAction {

    private App app;
    private JFileChooser fileChooser;

    public Export(App app) {
        super("Export");
        this.app = app;
        fileChooser = new JFileChooser(System.getProperty("user.dir"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG", "png"));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Easel easel = app.getEasel();
        Canvas canvas = easel.getCanvas();
        if (fileChooser.showSaveDialog(easel) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedImage composite = canvas.getLayeredImage().merge();
            try {
                ImageIO.write(composite, "png", file);
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
    
}
