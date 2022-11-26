import java.awt.image.BufferedImage;

public class ImageParser {

    public static double getBrightness(BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();
        double sum = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sum += getBrightness(image.getRGB(x,y));
            }
        }
        return sum / width / height;
    }

    private static double getBrightness(int rgb){

        return (0.2126 * ((rgb >> 16) & 0xFF) +
                0.7152 * ((rgb >> 8) & 0xFF) +
                0.0722 * ((rgb >> 0) & 0xFF))/255;
    }
}
