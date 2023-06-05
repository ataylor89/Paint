package paint.listeners;

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
import paint.gui.Easel;

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
        switch (actionCommand) {
            case "chooseColor" -> {
                Settings settings = app.getSettings();
                Easel easel = app.getEasel();
                Color choice = JColorChooser.showDialog(easel, "Chooose a color", settings.getPaintColor());
                if (choice != null) {
                    settings.setPaintColor(choice);
                    easel.getToolBar().refresh();
                }
            }
            case "chooseTool" -> {
                Settings settings = app.getSettings();
                JComboBox toolCombo = (JComboBox) e.getSource();
                String toolName = (String) toolCombo.getSelectedItem();
                settings.setTool(toolName);
                app.notify("changedTool");
            }
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        Settings settings = app.getSettings();
        JCheckBox glideCheckBox = (JCheckBox) e.getSource();
        settings.setMode(glideCheckBox.isSelected() ? Settings.GLIDE : Settings.DRAG);
    }
}
