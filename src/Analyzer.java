import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

public class Analyzer {

    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private double maxBrightness;
    private double minBrightness;
    private double averageBrightness;
    private double frequency;
    private double pulsation;

    public void Analyze(String src, int fps) {
        maxBrightness = Integer.MIN_VALUE;
        minBrightness = Integer.MAX_VALUE;
        averageBrightness = 0;
        frequency = 0;
        pulsation = 0;

        int maxIndex = 0;
        int minIndex = 0;
        double brightness;

        VideoCapture cap = new VideoCapture(src); // создание видеопотока
        Mat frame = new Mat();
        MatOfByte buf = new MatOfByte();

        ImageIcon ic;
        int n = 0;
        while (cap.read(frame)) {// считываем каждый кажр из потока
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
        frequency = 0.5 * fps / Math.abs(maxIndex - minIndex);
    }

    public void Analyze(BufferedImage[] images, double time) {
        maxBrightness = Integer.MIN_VALUE;
        minBrightness = Integer.MAX_VALUE;
        averageBrightness = 0;
        frequency = 0;
        pulsation = 0;

        int maxIndex = 0;
        int minIndex = 0;
        double brightness;

        int n = 0;
        for (; n < images.length; n++) {
            brightness = ImageParser.getBrightness(images[n]);

            averageBrightness += brightness;

            if (brightness > maxBrightness) {
                maxBrightness = brightness;
                maxIndex = n;
            }
            if (brightness < minBrightness) {
                minBrightness = brightness;
                minIndex = n;
            }
        }
        averageBrightness /= n;
        pulsation = (maxBrightness - minBrightness) / averageBrightness * 50;
        frequency = 0.5 * images.length / time / Math.abs(maxIndex - minIndex);
    }

    public void Analyze(double[] brightnesses, double time) {
        maxBrightness = Integer.MIN_VALUE;
        minBrightness = Integer.MAX_VALUE;
        averageBrightness = 0;
        frequency = 0;
        pulsation = 0;

        int maxIndex = 0;
        int minIndex = 0;
        double brightness;

        int n = 0;
        for (; n < brightnesses.length; n++) {
            brightness = brightnesses[n];
            averageBrightness += brightness;

            if (brightness > maxBrightness) {
                maxBrightness = brightness;
                maxIndex = n;
            }
            if (brightness < minBrightness) {
                minBrightness = brightness;
                minIndex = n;
            }
        }
        averageBrightness /= n;
        pulsation = (maxBrightness - minBrightness) / averageBrightness * 50;
        frequency = brightnesses.length / time * 2 / Math.abs(maxIndex - minIndex);
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
}
