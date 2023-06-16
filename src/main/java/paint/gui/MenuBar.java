package paint.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import paint.App;
import paint.Settings;
import paint.listeners.MenuBarListener;
import paint.menuactions.Exit;
import paint.menuactions.Export;
import paint.menuactions.FillSelection;
import paint.menuactions.FitCanvasToImage;
import paint.menuactions.FitImageToCanvas;
import paint.menuactions.NewFile;
import paint.menuactions.OpenFile;
import paint.menuactions.SaveFile;
import paint.menuactions.SaveFileAs;
import paint.menuactions.SetBackground;

/**
 *
 * @author andrewtaylor
 */
public class MenuBar extends JMenuBar {

    private App app;
    private JMenu fileMenu;
    private JMenuItem newFile, openFile, saveFile, saveFileAs, export, exit;
    private JMenu editMenu;
    private JMenuItem fitCanvasToImage, fitImageToCanvas, fillSelection, setBackgroundColor;
    
    public MenuBar(App app) {
        super();
        this.app = app;
        buildUI();
    }
    
    public void refresh() {
        Settings settings = app.getSettings();
        saveFile.setEnabled(settings.getFile() != null);
        fillSelection.setEnabled(settings.hasMarquee());
    }
    
    private void buildUI() {
        MenuBarListener listener = new MenuBarListener(app);
        fileMenu = new JMenu("File");
        fileMenu.addMenuListener(listener);
        newFile = new JMenuItem(new NewFile(app));
        openFile = new JMenuItem(new OpenFile(app));
        saveFile = new JMenuItem(new SaveFile(app));
        saveFile.setEnabled(false);
        saveFileAs = new JMenuItem(new SaveFileAs(app));
        export = new JMenuItem(new Export(app));
        exit = new JMenuItem(new Exit(app));
        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(saveFileAs);
        fileMenu.add(export);
        fileMenu.add(exit);
        add(fileMenu); 
        editMenu = new JMenu("Edit");
        editMenu.addMenuListener(listener);
        fitCanvasToImage = new JMenuItem(new FitCanvasToImage(app));
        fitImageToCanvas = new JMenuItem(new FitImageToCanvas(app));
        fillSelection = new JMenuItem(new FillSelection(app));
        fillSelection.setEnabled(false);
        setBackgroundColor = new JMenuItem(new SetBackground(app));
        editMenu.add(fitCanvasToImage);
        editMenu.add(fitImageToCanvas);
        editMenu.add(fillSelection);
        editMenu.add(setBackgroundColor);
        add(editMenu);
    }
}
