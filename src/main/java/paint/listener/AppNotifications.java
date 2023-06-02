package paint.listener;

import java.io.File;
import javax.swing.JMenuItem;
import paint.App;
import paint.gui.MenuBar;
import paint.gui.Easel;
import paint.Settings;
import paint.gui.Canvas;
import paint.gui.Selection;
import paint.gui.ToolBar;

/**
 *
 * @author andrewtaylor
 */
public class AppNotifications {
    
    private App app;
    
    public AppNotifications(App app) {
        this.app = app;
    }
    
    public void forward(String signal) {
        switch (signal.toLowerCase()) {
            case "filechanged" -> {
                Settings settings = app.getSettings();
                Easel easel = app.getEasel();
                easel.refreshTitle();
                ToolBar toolBar = easel.getToolBar();
                toolBar.refresh(); 
                MenuBar menuBar = (MenuBar) easel.getJMenuBar();
                JMenuItem save = menuBar.getSave();
                File file = settings.getFile();
                save.setEnabled(file != null);              
                Canvas canvas = easel.getCanvas();
                canvas.repaint();
            }
            case "imagechanged" -> {
                Easel easel = app.getEasel();
                Canvas canvas = easel.getCanvas();
                canvas.repaint();
                easel.refreshTitle();
            }
            case "toolchanged" -> {
                Settings settings = app.getSettings();
                settings.setSelection(null);
                Easel easel = app.getEasel();
                Canvas canvas = easel.getCanvas();
                canvas.repaint();
                MenuBar menuBar = (MenuBar) easel.getJMenuBar();
                JMenuItem fill = menuBar.getFillSelection();
                fill.setEnabled(false);               
            }
            case "selectionchanged" -> {
                Settings settings = app.getSettings();
                Easel easel = app.getEasel();
                MenuBar menuBar = (MenuBar) easel.getJMenuBar();
                JMenuItem fill = menuBar.getFillSelection();
                Selection selection = settings.getSelection();
                fill.setEnabled(selection != null);
            }
        }
    }
}
