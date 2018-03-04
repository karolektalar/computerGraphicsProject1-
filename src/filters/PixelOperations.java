package filters;

/**
 * Created by karol on 01.03.2018.
 */
public class PixelOperations {
    public static int getR(int in) {
        return (int) ((in << 8) >> 24) & 0xff;
    }

    public static int getG(int in) {
        return (int) ((in << 16) >> 24) & 0xff;
    }

    public static int getB(int in) {
        return (int) ((in << 24) >> 24) & 0xff;
    }

    public static int toRGB(int r, int g, int b) {
        return (int) ((((r << 8) | g) << 8) | b);
    }

}
