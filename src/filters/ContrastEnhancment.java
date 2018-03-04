package filters;

import java.awt.*;
import java.awt.image.BufferedImage;

import static filters.PixelOperations.toRGB;
import static java.lang.Double.min;
import static sun.swing.MenuItemLayoutHelper.max;

/**
 * Created by karol on 01.03.2018.
 */
public class ContrastEnhancment {
    public static BufferedImage contrast (BufferedImage img, double multiplyer){
        for (int i = 0; i < img.getWidth(); i++)
            for (int j = 0; j < img.getHeight(); j++) {
                Color mycolor = new Color(img.getRGB(i, j));

                double tempBlue;
                double tempRed;
                double tempGreen;

                tempBlue = ((mycolor.getBlue() - 127) * multiplyer) + 127;
                tempGreen = ((mycolor.getGreen() - 127) * multiplyer) + 127;
                tempRed = ((mycolor.getRed() - 127) * multiplyer) + 127;

                tempBlue = (max(0, (int)tempBlue));
                tempGreen = (max(0, (int)tempGreen));
                tempRed = (max(0, (int)tempRed));

                tempBlue = (min(255, (int)tempBlue));
                tempGreen = (min(255, (int)tempGreen));
                tempRed = (min(255, (int)tempRed));


                img.setRGB(i, j, toRGB((int)tempRed, (int)tempGreen, (int)tempBlue));
            }
        return img;
    }
}
