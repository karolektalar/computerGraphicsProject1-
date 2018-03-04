package filters;

import java.awt.*;
import java.awt.image.BufferedImage;

import static filters.PixelOperations.toRGB;

/**
 * Created by karol on 01.03.2018.
 */
public class BrightnessCorrection {
    public static BufferedImage correctBrightness(BufferedImage img, int correctionNumber) {
        for (int i = 0; i < img.getWidth(); i++)
            for (int j = 0; j < img.getHeight(); j++) {
                Color mycolor = new Color(img.getRGB(i, j));
                int red, blue, green;

                if (mycolor.getBlue() + correctionNumber > 255)
                    blue = 255;
                else if (mycolor.getBlue() + correctionNumber < 0)
                    blue = 0;
                else blue = mycolor.getBlue() + correctionNumber;

                if (mycolor.getGreen() + correctionNumber > 255)
                    green = 255;
                else if (mycolor.getGreen() + correctionNumber < 0)
                    green = 0;
                else green = mycolor.getGreen() + correctionNumber;

                if (mycolor.getRed() + correctionNumber > 255)
                    red = 255;
                else if (mycolor.getRed() + correctionNumber < 0)
                    red = 0;
                else red = mycolor.getRed() + correctionNumber;

                img.setRGB(i, j, toRGB(red, green, blue));
            }
            return img;
    }
}
