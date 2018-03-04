package filters;

import java.awt.*;
import java.awt.image.BufferedImage;

import static filters.PixelOperations.toRGB;

/**
 * Created by karol on 01.03.2018.
 */
public class Inversion {
    public static BufferedImage inversion(BufferedImage img) {
        for (int i = 0; i < img.getWidth(); i++)
            for (int j = 0; j < img.getHeight(); j++) {
                Color mycolor = new Color(img.getRGB(i, j));
                img.setRGB(i, j, toRGB(255 - mycolor.getRed(), 255 - mycolor.getGreen(), 255 - mycolor.getBlue()));
            }
        return img;
    }
}
