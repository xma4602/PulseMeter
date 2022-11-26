import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        var black = ImageIO.read(new File("src\\images\\black.png"));
        var white = ImageIO.read(new File("src\\images\\white.png"));
        var lightgray = ImageIO.read(new File("src\\images\\lightgray.png"));
        var darkgray = ImageIO.read(new File("src\\images\\darkgray.png"));

        var blackBrightness = ImageParser.getBrightness(black);
        var whiteBrightness = ImageParser.getBrightness(white);
        var lightgrayBrightness = ImageParser.getBrightness(lightgray);
        var darkgrayBrightness = ImageParser.getBrightness(darkgray);
    }
}
