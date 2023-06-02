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
    private JMenuItem clear, open, save, saveAs, export, exit;
    private JMenu transformMenu;
    private JMenuItem fitCanvasToImage, fitImageToCanvas, fillSelection, setBackgroundColor;
    
    public MenuBar(App app) {
        super();
        this.app = app;
        this.listener = new MenuBarListener(app);
        buildUI();
    }
    
    private void buildUI() {
        fileMenu = new JMenu("File");
        fileMenu.addMenuListener(listener);
        clear = new JMenuItem("New");
        clear.setActionCommand("clear");
        clear.addActionListener(listener);
        fileMenu.add(clear);
        open = new JMenuItem("Open");
        open.setActionCommand("open");
        open.addActionListener(listener);
        fileMenu.add(open);
        save = new JMenuItem("Save");
        save.setActionCommand("save");
        save.addActionListener(listener);
        fileMenu.add(save);
        saveAs = new JMenuItem("Save as");
        saveAs.setActionCommand("saveAs");
        saveAs.addActionListener(listener);
        fileMenu.add(saveAs);
        export = new JMenuItem("Export");
        export.setActionCommand("export");
        export.addActionListener(listener);
        fileMenu.add(export);
        exit = new JMenuItem("Exit");
        exit.setActionCommand("exit");
        exit.addActionListener(listener);
        fileMenu.add(getExit());
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
    
    public void refresh() {
        Settings settings = app.getSettings();
        save.setEnabled(settings.getFile() != null);
        fillSelection.setEnabled(settings.hasMarquee());
    }

    public JMenu getFileMenu() {
        return fileMenu;
    }

    public void setFileMenu(JMenu fileMenu) {
        this.fileMenu = fileMenu;
    }

    public JMenuItem getClear() {
        return clear;
    }

    public void setClear(JMenuItem clear) {
        this.clear = clear;
    }

    public JMenuItem getOpen() {
        return open;
    }

    public void setOpen(JMenuItem open) {
        this.open = open;
    }

    public JMenuItem getSave() {
        return save;
    }

    public void setSave(JMenuItem save) {
        this.save = save;
    }

    public JMenuItem getSaveAs() {
        return saveAs;
    }

    public void setSaveAs(JMenuItem saveAs) {
        this.saveAs = saveAs;
    }

    public JMenuItem getExit() {
        return exit;
    }

    public void setExit(JMenuItem exit) {
        this.exit = exit;
    }

    public JMenu getTransformMenu() {
        return transformMenu;
    }

    public void setTransformMenu(JMenu transformMenu) {
        this.transformMenu = transformMenu;
    }

    public JMenuItem getFitCanvasToImage() {
        return fitCanvasToImage;
    }

    public void setFitCanvasToImage(JMenuItem fitCanvasToImage) {
        this.fitCanvasToImage = fitCanvasToImage;
    }

    public JMenuItem getFitImageToCanvas() {
        return fitImageToCanvas;
    }

    public void setFitImageToCanvas(JMenuItem fitImageToCanvas) {
        this.fitImageToCanvas = fitImageToCanvas;
    }

    public JMenuItem getFillSelection() {
        return fillSelection;
    }

    public void setFillSelection(JMenuItem fillSelection) {
        this.fillSelection = fillSelection;
    }
}
