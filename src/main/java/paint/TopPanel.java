package paint;

import paint.tools.ToolFactory;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
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
public class TopPanel extends JPanel implements ActionListener, ChangeListener {
    
    private final Paint paint;
    private Settings settings;
    private JLabel sizeLabel, colorLabel, toolLabel;
    private JSpinner sizeSpinner;
    private ColorSample colorButton;
    private JComboBox toolCombo;
    
    public TopPanel(Paint paint) {
        super();
        this.paint = paint;
        init();
    }
    
    private void init() {
        settings = Settings.getInstance();        
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
        String[] tools = new String[] {"Brush", "Pen", "Eraser"};
        toolCombo = new JComboBox(tools);
        toolCombo.addActionListener(this);
        add(toolCombo);
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
            Color initial = settings.getPaintColor();
            Color choice = JColorChooser.showDialog(this, "Chooose a color", initial);
            if (choice != null) {
                colorButton.setColor(choice);
                settings.setPaintColor(choice);
            }
        }
        else if (e.getSource() == toolCombo) {
            String value = (String) toolCombo.getSelectedItem();
            Tool tool = ToolFactory.getTool(value);
            Canvas canvas = paint.getCanvas();
            canvas.setTool(tool);
        }
    }
}
