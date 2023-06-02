package paint.image;

import java.awt.image.BufferedImage;

/**
 *
 * @author andrewtaylor
 */
public class ImageOps {
    
    public static byte[] serialize(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        byte[] data = new byte[width * height * 4];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                int offset = (y * width + x) * 4;
                data[offset] = (byte) ((pixel & 0xFF000000) >> 24);
                data[offset + 1] = (byte) ((pixel & 0x00FF0000) >> 16);
                data[offset + 2] = (byte) ((pixel & 0x0000FF00) >> 8);
                data[offset + 3] = (byte) (pixel & 0x000000FF);
            }
        }
        return data;
    }
    
    public static BufferedImage deserialize(byte[] data, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {                
                int offset = (y * width + x) * 4;
                int pixel = ((data[offset] & 0xFF) << 24);
                pixel |= ((data[offset + 1] & 0xFF) << 16);
                pixel |= ((data[offset + 2] & 0xFF) << 8);
                pixel |= (data[offset + 3] & 0xFF);
                image.setRGB(x, y, pixel);
            }
        }
        return image;
    }
}
