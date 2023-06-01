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
import paint.listener.ToolBarListener;

/**
 *
 * @author andrewtaylor
 */
public class ToolBar extends JPanel {
    
    private App app;
    private ToolBarListener listener;
    private JLabel sizeLabel, colorLabel, toolLabel, glideLabel;
    private JSpinner sizeSpinner;
    private ColorSample colorButton;
    private JComboBox toolCombo;
    private JCheckBox glideCheckBox;
    
    public ToolBar(App app) {
        super();
        this.app = app;
        listener = new ToolBarListener(app);
        buildUI();
    }
    
    private void buildUI() {
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
        String[] tools = new String[] {"Brush", "Pen", "Eraser", "Marquee"};
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

    public JLabel getSizeLabel() {
        return sizeLabel;
    }

    public void setSizeLabel(JLabel sizeLabel) {
        this.sizeLabel = sizeLabel;
    }

    public JLabel getColorLabel() {
        return colorLabel;
    }

    public void setColorLabel(JLabel colorLabel) {
        this.colorLabel = colorLabel;
    }

    public JLabel getToolLabel() {
        return toolLabel;
    }

    public void setToolLabel(JLabel toolLabel) {
        this.toolLabel = toolLabel;
    }

    public JLabel getGlideLabel() {
        return glideLabel;
    }

    public void setGlideLabel(JLabel glideLabel) {
        this.glideLabel = glideLabel;
    }

    public JSpinner getSizeSpinner() {
        return sizeSpinner;
    }

    public void setSizeSpinner(JSpinner sizeSpinner) {
        this.sizeSpinner = sizeSpinner;
    }

    public ColorSample getColorButton() {
        return colorButton;
    }

    public void setColorButton(ColorSample colorButton) {
        this.colorButton = colorButton;
    }

    public JComboBox getToolCombo() {
        return toolCombo;
    }

    public void setToolCombo(JComboBox toolCombo) {
        this.toolCombo = toolCombo;
    }

    public JCheckBox getGlideCheckBox() {
        return glideCheckBox;
    }

    public void setGlideCheckBox(JCheckBox glideCheckBox) {
        this.glideCheckBox = glideCheckBox;
    }
}
