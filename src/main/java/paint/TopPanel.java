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
    private JLabel brushSizeLabel, brushColorLabel, algorithmLabel;
    private JSpinner brushSizeSpinner;
    private ColorSample brushColorButton;
    private JComboBox algorithmCombo;
    
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
        Color brushColor= settings.getPaintColor();
        brushSizeLabel = new JLabel("Diameter:");
        add(brushSizeLabel);
        SpinnerNumberModel model = new SpinnerNumberModel(brushSize, 1, 100, 1);
        brushSizeSpinner = new JSpinner(model);
        brushSizeSpinner.addChangeListener(this);
        add(brushSizeSpinner);
        brushColorLabel = new JLabel("Color:");
        add(brushColorLabel);
        brushColorButton = new ColorSample(brushColor, 16, 16);
        brushColorButton.addActionListener(this);
        add(brushColorButton);
        algorithmLabel = new JLabel("Algorithm:");
        add(algorithmLabel);
        String[] algorithms = new String[] {"Brush", "Pen", "Eraser"};
        algorithmCombo = new JComboBox(algorithms);
        algorithmCombo.addActionListener(this);
        add(algorithmCombo);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        Integer value = (Integer) spinner.getValue();
        settings.setBrushSize(value); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == brushColorButton) {
            Color initial = settings.getPaintColor();
            Color choice = JColorChooser.showDialog(this, "Chooose a color", initial);
            if (choice != null) {
                brushColorButton.setColor(choice);
                settings.setPaintColor(choice);
            }
        }
        else if (e.getSource() == algorithmCombo) {
            String value = (String) algorithmCombo.getSelectedItem();
            Tool algorithm = ToolFactory.getTool(value);
            Canvas canvas = paint.getCanvas();
            canvas.setTool(algorithm);
        }
    }
}
