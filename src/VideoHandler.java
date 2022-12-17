import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class VideoHandler {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    private double[] frames;
    private int FromFrame = 300, ToFrame = 600;
    private double FPS;

    public VideoHandler(String path) {
        VideoCapture cap = new VideoCapture(path); // создание видеопотока
        frames = new double[ToFrame - FromFrame];
        FPS = cap.get(Videoio.CAP_PROP_FPS);

        Mat frame = new Mat();
        MatOfByte buf = new MatOfByte();
        Graphics2D graphics;
        ImageIcon icon;
        BufferedImage image;

        // считываем каждый кадр из потока
        for (int i = 0; cap.read(frame) && i < ToFrame; i++) {
            if(i >= FromFrame){
                Imgcodecs.imencode(".png", frame, buf);
                icon = new ImageIcon(buf.toArray());
                image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                graphics = image.createGraphics();
                graphics.drawImage(icon.getImage(), 0, 0, null);
                graphics.dispose();
                frames[i-FromFrame] = ImageParser.getBrightness(image);
            }
        }
        cap.release();
    }

    public double[] getFrames() {
        return frames;
    }

    public double getFPS() {
        return FPS;
    }
}
