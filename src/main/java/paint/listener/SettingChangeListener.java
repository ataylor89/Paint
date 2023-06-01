package paint.listener;

import java.io.File;
import javax.swing.JMenuItem;
import paint.App;
import paint.gui.MenuBar;
import paint.gui.Easel;
import paint.Settings;
import paint.gui.Canvas;

/**
 *
 * @author andrewtaylor
 */
public class SettingChangeListener {
    
    private App app;
    
    public SettingChangeListener(App app) {
        this.app = app;
    }
    
    public void notify(String signal) {
        switch (signal.toLowerCase()) {
            case "filechanged" -> {
                Settings settings = app.getSettings();
                Easel easel = app.getEasel();
                MenuBar menuBar = (MenuBar) easel.getJMenuBar();
                JMenuItem save = menuBar.getSave();
                File file = settings.getFile();
                save.setEnabled(file != null);
            }
            case "imagechanged" -> {
                Easel easel = app.getEasel();
                Canvas canvas = easel.getCanvas();
                canvas.repaint();
                easel.refreshTitle();
            }
            case "toolchanged" -> {
                Easel easel = app.getEasel();
                MenuBar menuBar = (MenuBar) easel.getJMenuBar();
                JMenuItem fill = menuBar.getFillSelection();
                fill.setEnabled(false);
                Canvas canvas = easel.getCanvas();
                canvas.repaint();
            }
            case "marqueeactive" -> {
                Easel easel = app.getEasel();
                MenuBar menuBar = (MenuBar) easel.getJMenuBar();
                JMenuItem fill = menuBar.getFillSelection();
                fill.setEnabled(true);
            }
        }
    }
}
