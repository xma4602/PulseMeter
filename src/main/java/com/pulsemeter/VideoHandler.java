package com.pulsemeter;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class VideoHandler {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    private final double FPS;
    private final String path;
    private final boolean highPrecision;

    double[] frames;

    public VideoHandler(String path, boolean highPrecision, int fromFrame, int toFrame) {
        this.path = path;
        this.highPrecision = highPrecision;
        FPS = new VideoCapture(path).get(Videoio.CAP_PROP_FPS);
        getFrames(fromFrame, toFrame);
    }

    public void getFrames(int fromFrame, int toFrame) {

        var cap = new VideoCapture(path); // создание видеопотока
        frames = new double[toFrame - fromFrame];
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
                frames[i-fromFrame] = ImageParser.getBrightness(image, highPrecision);
            }
        }
        cap.release();
    }
    public double getFPS() {
        return FPS;
    }

    public double[] getFrames() {
        return frames;
    }
}
