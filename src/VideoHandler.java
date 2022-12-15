import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;


public class VideoHandler {

    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private double[] pulsationArray;
    private double maxBrightness, minBrightness, averageBrightness, frequency, pulsation, FPS;
    public VideoHandler(String path){
        pulsationArray = new double[150];
        maxBrightness = Integer.MIN_VALUE;
        minBrightness = Integer.MAX_VALUE;
        averageBrightness = 0;
        frequency = 0;
        pulsation = 0;

        int maxIndex = 0, minIndex = 0;
        double brightness;

        VideoCapture cap = new VideoCapture(path); // создание видеопотока

        FPS = cap.get(Videoio.CAP_PROP_FPS);

        Mat frame = new Mat();
        MatOfByte buf = new MatOfByte();

        ImageIcon ic;
        int frames = 0;
        while (cap.read(frame) && (frames < pulsationArray.length - 1)) {// считываем каждый кадр из потока
            Imgcodecs.imencode(".png", frame, buf);
            ic = new ImageIcon(buf.toArray());
            BufferedImage bimage = new BufferedImage(ic.getIconWidth(), ic.getIconHeight(), BufferedImage.TYPE_INT_RGB);

//            Graphics2D bGr = bimage.createGraphics(); // для вывода видеоизображения
//            bGr.drawImage(ic.getImage(), 0, 0, null);
//            bGr.dispose();

            brightness = ImageParser.getBrightness(bimage);
            pulsationArray[frames] = brightness;

            averageBrightness += brightness;

            if (brightness > maxBrightness) {
                maxBrightness = brightness;
                maxIndex = frames;
            }
            if (brightness < minBrightness) {
                minBrightness = brightness;
                minIndex = frames;
            }
            frames++;
        }
        cap.release();
        averageBrightness /= frames;
        pulsation = (maxBrightness - minBrightness) / averageBrightness * 50;
        frequency = 0.5 * FPS / Math.abs(maxIndex - minIndex);
    }
    public double[] getPulsationArray() {
        return pulsationArray;
    }
    public double getAverageBrightness() {
        return averageBrightness;
    }
    public double getMaxBrightness() {
        return maxBrightness;
    }
    public double getMinBrightness() {
        return minBrightness;
    }
    public double getFrequency() {
        return frequency;
    }
    public double getPulsation() {
        return pulsation;
    }
    public double getFPS() {
        return FPS;
    }
}