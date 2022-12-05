import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class VideoViewer {

    static {
        var a = Core.NATIVE_LIBRARY_NAME;
        System.loadLibrary(a);
    }

    public static BufferedImage[] SplitIntoFrames(String path) {

        //вспомогательные переменные
        Mat frame = new Mat();
        MatOfByte buffer = new MatOfByte();
        ImageIcon icon;
        BufferedImage image;
        Graphics2D graphics;

        //главные переменные
        var cap = new VideoCapture(path); // видеопоток
        var images = new LinkedList<BufferedImage>(); //список кадров

        // считываем каждый кажр из потока
        while (cap.read(frame) && images.size() < 240) {
            Imgcodecs.imencode(".png", frame, buffer);
            icon = new ImageIcon(buffer.toArray());
            image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            graphics = image.createGraphics();
            graphics.drawImage(icon.getImage(), 0, 0, null);
            images.add(image);
            graphics.dispose();
        }

        cap.release();
        return images.toArray(new BufferedImage[0]);
    }
}
