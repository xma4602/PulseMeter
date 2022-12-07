import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class Analyzer {

    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private double maxBrightness, minBrightness, averageBrightness, frequency, pulsation, FPS;
    public Analyzer(String path){
        maxBrightness = Integer.MIN_VALUE;
        minBrightness = Integer.MAX_VALUE;
        averageBrightness = 0;
        frequency = 0;
        pulsation = 0;

        int maxIndex = 0;
        int minIndex = 0;
        double brightness;

        VideoCapture cap = new VideoCapture(path); // создание видеопотока

        FPS = cap.get(Videoio.CAP_PROP_FPS);

        Mat frame = new Mat();
        MatOfByte buf = new MatOfByte();

        ImageIcon ic;
        int n = 0;
        while (cap.read(frame)) {// считываем каждый кадр из потока
            Imgcodecs.imencode(".png", frame, buf);
            ic = new ImageIcon(buf.toArray());
            VideoShow.refreshForm(ic); // обновляем изображение в форме(можно убрать)
            BufferedImage bimage = new BufferedImage(ic.getImage().getWidth(null), ic.getImage().getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage(ic.getImage(), 0, 0, null);
            bGr.dispose();

            brightness = ImageParser.getBrightness(bimage);
            averageBrightness += brightness;

            if (brightness > maxBrightness) {
                maxBrightness = brightness;
                maxIndex = n;
            }
            if (brightness < minBrightness) {
                minBrightness = brightness;
                minIndex = n;
            }
            n++;
        }
        cap.release();
        averageBrightness /= n;
        pulsation = (maxBrightness - minBrightness) / averageBrightness * 50;
        frequency = 0.5 * FPS / Math.abs(maxIndex - minIndex);
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
