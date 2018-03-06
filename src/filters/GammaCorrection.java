package filters;

import java.awt.*;
import java.awt.image.BufferedImage;

import static filters.PixelOperations.toRGB;
import static java.lang.Double.min;
import static sun.swing.MenuItemLayoutHelper.max;

/**
 * Created by karol on 06.03.2018.
 */
public class GammaCorrection {
    public BufferedImage gammaCorrection(BufferedImage img, double gamma){
        for (int i = 0; i < img.getWidth(); i++)
            for (int j = 0; j < img.getHeight(); j++) {
                Color mycolor = new Color(img.getRGB(i, j));
                double tempR = 0, tempB = 0, tempG = 0;
                tempG = Math.pow((((double)mycolor.getGreen())/255),gamma)*255;
                tempR = Math.pow((((double)mycolor.getRed())/255),gamma)*255;
                tempB = Math.pow((((double)mycolor.getBlue())/255),gamma)*255;

                tempB = (max(0, (int) tempB));
                tempG = (max(0, (int) tempG));
                tempR = (max(0, (int) tempR));

                tempB = (min(255, (int) tempB));
                tempG = (min(255, (int) tempG));
                tempR = (min(255, (int) tempR));

                img.setRGB(i,j, toRGB((int) tempR, (int) tempG, (int) tempB));
            }
        return img;
    }
}
