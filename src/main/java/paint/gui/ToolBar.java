package paint.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import paint.App;
import paint.Settings;
import paint.listeners.ToolBarListener;

/**
 *
 * @author andrewtaylor
 */
public class ToolBar extends JPanel {
    
    private App app;
    private JLabel sizeLabel, colorLabel, toolLabel, glideLabel;
    private JSpinner sizeSpinner;
    private ColorSample colorButton;
    private JComboBox toolCombo;
    private JCheckBox glideCheckBox;
    
    public ToolBar(App app) {
        super();
        this.app = app;
        buildUI();
    }
    
    public void refresh() {
        Settings settings = app.getSettings();
        sizeSpinner.setValue(settings.getBrushSize());
        colorButton.setColor(settings.getPaintColor());
        colorButton.repaint();
        toolCombo.setSelectedItem(settings.getTool());
        glideCheckBox.setSelected(settings.getMode() == Settings.GLIDE);
    }
    
    private void buildUI() {
        ToolBarListener listener = new ToolBarListener(app);
        Settings settings = app.getSettings();
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        int brushSize = settings.getBrushSize();
        Color brushColor = settings.getPaintColor();
        sizeLabel = new JLabel("Diameter:");
        add(sizeLabel);
        SpinnerNumberModel model = new SpinnerNumberModel(brushSize, 1, 100, 1);
        sizeSpinner = new JSpinner(model);
        sizeSpinner.addChangeListener(listener);
        add(sizeSpinner);
        colorLabel = new JLabel("Color:");
        add(colorLabel);
        colorButton = new ColorSample(brushColor, 16, 16);
        colorButton.setActionCommand("chooseColor");
        colorButton.addActionListener(listener);
        add(colorButton);
        toolLabel = new JLabel("Tool:");
        add(toolLabel);
        String[] tools = new String[] {"Brush", "Pen", "Eraser", "Marquee", "Line"};
        toolCombo = new JComboBox(tools);
        toolCombo.setActionCommand("chooseTool");
        toolCombo.addActionListener(listener);
        add(toolCombo);
        glideLabel = new JLabel("Glide");
        add(glideLabel);
        glideCheckBox = new JCheckBox();
        glideCheckBox.setSelected(true);
        glideCheckBox.addItemListener(listener);
        add(glideCheckBox);
    }
}
