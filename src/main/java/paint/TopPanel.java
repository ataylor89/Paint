package paint;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import paint.tools.Tool;

/**
 *
 * @author andrewtaylor
 */
public class TopPanel extends JPanel implements ActionListener, ChangeListener, ItemListener {
    
    private Paint paint;
    private Settings settings;
    private JLabel sizeLabel, colorLabel, toolLabel, glideLabel;
    private JSpinner sizeSpinner;
    private ColorSample colorButton;
    private JComboBox toolCombo;
    private JCheckBox glideCheckBox;
    
    public TopPanel(Paint paint) {
        super();
        this.paint = paint;
        this.settings = paint.getSettings();
        buildUI();
    }
    
    private void buildUI() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        int brushSize = settings.getBrushSize();
        Color brushColor = settings.getPaintColor();
        sizeLabel = new JLabel("Diameter:");
        add(sizeLabel);
        SpinnerNumberModel model = new SpinnerNumberModel(brushSize, 1, 100, 1);
        sizeSpinner = new JSpinner(model);
        sizeSpinner.addChangeListener(this);
        add(sizeSpinner);
        colorLabel = new JLabel("Color:");
        add(colorLabel);
        colorButton = new ColorSample(brushColor, 16, 16);
        colorButton.addActionListener(this);
        add(colorButton);
        toolLabel = new JLabel("Tool:");
        add(toolLabel);
        String[] tools = new String[] {"Brush", "Pen", "Eraser", "Marquee"};
        toolCombo = new JComboBox(tools);
        toolCombo.addActionListener(this);
        add(toolCombo);
        glideLabel = new JLabel("Glide");
        add(glideLabel);
        glideCheckBox = new JCheckBox();
        glideCheckBox.setSelected(true);
        glideCheckBox.addItemListener(this);
        add(glideCheckBox);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        Integer value = (Integer) spinner.getValue();
        settings.setBrushSize(value); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == colorButton) {
            Color choice = JColorChooser.showDialog(this, "Chooose a color", settings.getPaintColor());
            if (choice != null) {
                colorButton.setColor(choice);
                settings.setPaintColor(choice);
            }
        }
        else if (e.getSource() == toolCombo) {
            String value = (String) toolCombo.getSelectedItem();
            Canvas canvas = paint.getCanvas();
            Tool tool = canvas.getToolbox().get(value);
            canvas.setTool(tool);
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        settings.setMode(glideCheckBox.isSelected() ? Settings.GLIDE : Settings.DRAG);
    }
}
