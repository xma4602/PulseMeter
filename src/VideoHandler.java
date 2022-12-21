import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class VideoHandler {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    private double FPS;
    private String path;

    public VideoHandler(String path) {
        this.path = path;
        FPS = new VideoCapture(path).get(Videoio.CAP_PROP_FPS);
    }

    public double[] getFrames(int fromFrame, int toFrame) {

        var cap = new VideoCapture(path); // создание видеопотока
        var frames = new double[toFrame - fromFrame];

        Mat frame = new Mat();
        MatOfByte buf = new MatOfByte();
        Graphics2D graphics;
        ImageIcon icon;
        BufferedImage image;

        // считываем каждый кадр из потока
        for (int i = 0; cap.read(frame) && i < toFrame; i++) {
            if(i >= fromFrame){
                Imgcodecs.imencode(".png", frame, buf);
                icon = new ImageIcon(buf.toArray());
                image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                graphics = image.createGraphics();
                graphics.drawImage(icon.getImage(), 0, 0, null);
                graphics.dispose();
                frames[i-fromFrame] = ImageParser.getBrightness(image);
            }
        }
        cap.release();

        return frames;
    }
    public double getFPS() {
        return FPS;
    }
}
