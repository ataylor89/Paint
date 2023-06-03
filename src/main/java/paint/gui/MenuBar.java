package paint.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import paint.App;
import paint.Settings;
import paint.listener.MenuBarListener;

/**
 *
 * @author andrewtaylor
 */
public class MenuBar extends JMenuBar {

    private App app;
    private MenuBarListener listener;
    private JMenu fileMenu;
    private JMenuItem newFile, openFile, saveFile, saveFileAs, export, exit;
    private JMenu transformMenu;
    private JMenuItem fitCanvasToImage, fitImageToCanvas, fillSelection, setBackgroundColor;
    
    public MenuBar(App app) {
        super();
        this.app = app;
        this.listener = new MenuBarListener(app);
        buildUI();
    }
    
    public void refresh() {
        Settings settings = app.getSettings();
        saveFile.setEnabled(settings.getFile() != null);
        fillSelection.setEnabled(settings.hasMarquee());
    }
    
    private void buildUI() {
        fileMenu = new JMenu("File");
        fileMenu.addMenuListener(listener);
        newFile = new JMenuItem("New");
        newFile.setActionCommand("newFile");
        newFile.addActionListener(listener);
        fileMenu.add(newFile);
        openFile = new JMenuItem("Open");
        openFile.setActionCommand("openFile");
        openFile.addActionListener(listener);
        fileMenu.add(openFile);
        saveFile = new JMenuItem("Save");
        saveFile.setActionCommand("saveFile");
        saveFile.addActionListener(listener);
        fileMenu.add(saveFile);
        saveFileAs = new JMenuItem("Save as");
        saveFileAs.setActionCommand("saveFileAs");
        saveFileAs.addActionListener(listener);
        fileMenu.add(saveFileAs);
        export = new JMenuItem("Export");
        export.setActionCommand("export");
        export.addActionListener(listener);
        fileMenu.add(export);
        exit = new JMenuItem("Exit");
        exit.setActionCommand("exit");
        exit.addActionListener(listener);
        fileMenu.add(exit);
        add(fileMenu);
        transformMenu = new JMenu("Transform");
        transformMenu.addMenuListener(listener);
        fitCanvasToImage = new JMenuItem("Fit canvas to image");
        fitCanvasToImage.setActionCommand("fitCanvasToImage");
        fitCanvasToImage.addActionListener(listener);
        transformMenu.add(fitCanvasToImage);
        fitImageToCanvas = new JMenuItem("Fit image to canvas");
        fitImageToCanvas.setActionCommand("fitImageToCanvas");
        fitImageToCanvas.addActionListener(listener);
        transformMenu.add(fitImageToCanvas);
        fillSelection = new JMenuItem("Fill selection");
        fillSelection.setActionCommand("fillSelection");
        fillSelection.setEnabled(false);
        fillSelection.addActionListener(listener);
        transformMenu.add(fillSelection);
        setBackgroundColor = new JMenuItem("Set background color");
        setBackgroundColor.setActionCommand("setBackgroundColor");
        setBackgroundColor.setEnabled(true);
        setBackgroundColor.addActionListener(listener);
        transformMenu.add(setBackgroundColor);
        add(transformMenu);
    }
}
