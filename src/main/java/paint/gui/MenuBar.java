package paint.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import paint.App;
import paint.listener.MenuListener;

/**
 *
 * @author andrewtaylor
 */
public class MenuBar extends JMenuBar {

    private App app;
    private MenuListener listener;
    private JMenu fileMenu;
    private JMenuItem clear, open, save, saveAs, exit;
    private JMenu toolsMenu;
    private JMenuItem fitCanvasToImage, fitImageToCanvas, fillSelection;
    
    public MenuBar(App app) {
        super();
        this.app = app;
        this.listener = new MenuListener(app);
        buildUI();
    }
    
    private void buildUI() {
        fileMenu = new JMenu("File");
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
        exit = new JMenuItem("Exit");
        exit.setActionCommand("exit");
        exit.addActionListener(listener);
        fileMenu.add(getExit());
        add(fileMenu);
        toolsMenu = new JMenu("Tools");
        fitCanvasToImage = new JMenuItem("Fit canvas to image");
        fitCanvasToImage.setActionCommand("fitCanvasToImage");
        fitCanvasToImage.addActionListener(listener);
        toolsMenu.add(fitCanvasToImage);
        fitImageToCanvas = new JMenuItem("Fit image to canvas");
        fitImageToCanvas.setActionCommand("fitImageToCanvas");
        fitImageToCanvas.addActionListener(listener);
        toolsMenu.add(getFitImageToCanvas());
        fillSelection = new JMenuItem("Fill selection");
        fillSelection.setActionCommand("fillSelection");
        fillSelection.setEnabled(false);
        fillSelection.addActionListener(listener);
        toolsMenu.add(fillSelection);
        add(toolsMenu);
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

    public JMenu getToolsMenu() {
        return toolsMenu;
    }

    public void setToolsMenu(JMenu toolsMenu) {
        this.toolsMenu = toolsMenu;
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
