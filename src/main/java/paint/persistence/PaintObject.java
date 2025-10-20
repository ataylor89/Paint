package paint.persistence;

import java.io.Serializable;
import paint.Settings;
import paint.image.LayeredImage;

public class PaintObject implements Serializable {
    
    private Settings settings;
    private LayeredImage layeredImage;

    private static final long serialVersionUID = 1L;

    public PaintObject(Settings settings, LayeredImage layeredImage) {
        this.settings = settings;
        this.layeredImage = layeredImage;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setLayeredImage(LayeredImage layeredImage) {
        this.layeredImage = layeredImage;
    }

    public LayeredImage getLayeredImage() {
        return layeredImage;
    }
}
