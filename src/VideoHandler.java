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
    private LinkedList<Double> frames;
    private int FRAMES_COUNT = 1000;
    double FPS;



    public VideoHandler(String path) {
        VideoCapture cap = new VideoCapture(path); // создание видеопотока
        frames = new LinkedList<>();
        FPS = cap.get(Videoio.CAP_PROP_FPS);

        Mat frame = new Mat();
        MatOfByte buf = new MatOfByte();
        Graphics2D graphics ;
        ImageIcon icon;
        BufferedImage image;
        double brightness;

        // считываем каждый кадр из потока
        for (int i = 0; cap.read(frame) && i < FRAMES_COUNT; i++)  {
            Imgcodecs.imencode(".png", frame, buf);
            icon = new ImageIcon(buf.toArray());
            image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
            graphics = image.createGraphics();
            graphics.drawImage(icon.getImage(), 0, 0, null);
            graphics.dispose();

            brightness = ImageParser.getBrightness(image);
            frames.add(brightness);
        }
        cap.release();
    }

    public Double[] getFrames() {
        return (Double[]) frames.toArray();
    }
}