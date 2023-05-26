package paint;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author andrewtaylor
 */
public class TopPanel extends JPanel implements ActionListener, ChangeListener {
    
    private Settings settings;
    private JLabel brushSizeLabel, brushColorLabel;
    private JSpinner brushSizeSpinner;
    private ColorSample brushColorButton;
    
    public TopPanel() {
        super();
        init();
    }
    
    private void init() {
        settings = Settings.getInstance();        
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        int brushSize = settings.getBrushSize();
        Color brushColor= settings.getBrushColor();
        brushSizeLabel = new JLabel("Brush size:");
        add(brushSizeLabel);
        SpinnerNumberModel model = new SpinnerNumberModel(brushSize, 1, 20, 1);
        brushSizeSpinner = new JSpinner(model);
        brushSizeSpinner.addChangeListener(this);
        add(brushSizeSpinner);
        brushColorLabel = new JLabel("Brush color:");
        add(brushColorLabel);
        brushColorButton = new ColorSample(brushColor, 16, 16);
        brushColorButton.addActionListener(this);
        add(brushColorButton);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSpinner spinner = (JSpinner) e.getSource();
        Integer value = (Integer) spinner.getValue();
        settings.setBrushSize(value); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Color initial = settings.getBrushColor();
        Color choice = JColorChooser.showDialog(this, "Chooose brush color", initial);
        if (choice != null) {
            brushColorButton.setColor(choice);
            settings.setBrushColor(choice);
        }
    }
}
