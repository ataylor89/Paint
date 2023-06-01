package paint.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import paint.App;
import paint.Settings;
import paint.gui.ColorSample;
import paint.gui.Easel;
import paint.gui.ToolBar;
import paint.tools.Tool;

/**
 *
 * @author andrewtaylor
 */
public class ToolBarListener implements ActionListener, ChangeListener, ItemListener {
    
    private App app;
    
    public ToolBarListener(App app) {
        this.app = app;
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        Settings settings = app.getSettings();
        JSpinner spinner = (JSpinner) e.getSource();
        Integer value = (Integer) spinner.getValue();
        settings.setBrushSize(value); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand.toLowerCase()) {
            case "choosecolor" -> {
                Settings settings = app.getSettings();
                Easel easel = app.getEasel();
                Color choice = JColorChooser.showDialog(easel, "Chooose a color", settings.getPaintColor());
                if (choice != null) {                    
                    ToolBar toolBar = easel.getToolBar();
                    ColorSample colorButton = toolBar.getColorButton();
                    colorButton.setColor(choice);
                    settings.setPaintColor(choice);
                }
            }
            case "choosetool" -> {
                Settings settings = app.getSettings();
                Easel easel = app.getEasel();
                ToolBar toolBar = easel.getToolBar();
                JComboBox toolCombo = toolBar.getToolCombo();
                String value = (String) toolCombo.getSelectedItem();
                Tool tool = easel.getToolbox().get(value);
                settings.setTool(tool);
                settings.notify("toolChanged");
            }
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        Settings settings = app.getSettings();
        Easel easel = app.getEasel();
        ToolBar toolBar = easel.getToolBar();
        JCheckBox glideCheckBox = toolBar.getGlideCheckBox();
        settings.setMode(glideCheckBox.isSelected() ? Settings.GLIDE : Settings.DRAG);
    }
}
