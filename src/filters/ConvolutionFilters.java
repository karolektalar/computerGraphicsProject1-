package filters;

import java.awt.*;
import java.awt.image.BufferedImage;

import static filters.PixelOperations.toRGB;
import static java.lang.Double.min;
import static sun.swing.MenuItemLayoutHelper.max;

/**
 * Created by karol on 04.03.2018.
 */
public class ConvolutionFilters {
    public static BufferedImage blur(BufferedImage img, BufferedImage oldImg) {
        double[][] matrix = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        applyFilterWithMatrix(img, oldImg, matrix, 0, 9, 1, 1);
        return oldImg;
    }
    public static BufferedImage gaussianBlur(BufferedImage img, BufferedImage oldImg){
        double[][] matrix = {{0, 1, 0}, {1, 4, 1}, {0, 1, 0}};
        applyFilterWithMatrix(img, oldImg, matrix, 0, 8, 1, 1);
        return oldImg;
    }

    public static void sharpenFilter(BufferedImage img, BufferedImage oldImg){
        double[][] matrix = {{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};
        applyFilterWithMatrix(img, oldImg, matrix, 0, 1, 1, 1);
    }

    public static void edgeDetection(BufferedImage img, BufferedImage oldImg){
        double[][] matrix = {{0, -1, 0}, {-1, 4, -1}, {0, -1, 0}};
        applyFilterWithMatrix(img, oldImg, matrix, 0, 1, 1, 1);
    }

    public static void embos(BufferedImage img, BufferedImage oldImg){
        double[][] matrix = {{-1, -1, -1}, {0, 1, 0}, {1, 1, 1}};
        applyFilterWithMatrix(img, oldImg, matrix, 0, 1, 1, 1);
    }


    public static BufferedImage applyFilterWithMatrix(BufferedImage img, BufferedImage oldImg, double[][] matrix, double offspring, double divisor, int kernelX, int kernelY) {

        for (int i = 0; i < img.getWidth(); i++)
            for (int j = 0; j < img.getHeight(); j++) {
                double tempR = 0, tempB = 0, tempG = 0;
                for (int k = 0; k < matrix.length; k++)
                    for (int l = 0; l < matrix[0].length; l++) {
                        try {
                            Color color = new Color(img.getRGB(i - kernelX + k, j - kernelY + l));

                            try {
                                tempR += matrix[k][l] * color.getRed();
                            } catch (Exception e) {
                            }

                            try {
                                tempB += matrix[k][l] * color.getBlue();
                            } catch (Exception e) {
                            }

                            try {
                                tempG += matrix[k][l] * color.getGreen();
                            } catch (Exception e) {
                            }
                        } catch (Exception e) {
                        }
                    }
                tempR = tempR / divisor + offspring;
                tempB = tempB / divisor + offspring;
                tempG = tempG / divisor + offspring;
                tempB = (max(0, (int) tempB));
                tempG = (max(0, (int) tempG));
                tempR = (max(0, (int) tempR));

                tempB = (min(255, (int) tempB));
                tempG = (min(255, (int) tempG));
                tempR = (min(255, (int) tempR));

                oldImg.setRGB(i, j, toRGB((int) tempR, (int) tempG, (int) tempB));


            }


        return oldImg;
    }
}
