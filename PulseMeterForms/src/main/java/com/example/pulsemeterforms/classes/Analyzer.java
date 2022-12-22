package com.example.pulsemeterforms.classes;

import javafx.scene.chart.XYChart;

public class Analyzer {
    private double
            maxBrightness,
            minBrightness,
            averageBrightness,
            frequency,
            pulsation,
            FPS;
    private double[] data, trend;
    private int trendK;

    public Analyzer(double[] frames, double fpc) {
        FPS = fpc;
        data = frames;
        trendK = Long.valueOf(Math.round(frames.length / FPS * 1.25)).intValue();
        trend = trendOf(data, trendK);
        Analyze(frames);
    }

    public void Analyze(double[] data) {
        maxBrightness = Integer.MIN_VALUE;
        minBrightness = Integer.MAX_VALUE;
        averageBrightness = 0;
        frequency = 0;

        int n1 = 0, n2 = 0;
        boolean flag = true;

        int count = 1;
        int n = 0;
        for (; count < data.length - 1; count++) {
            averageBrightness += data[count];
            maxBrightness = Math.max(data[count], maxBrightness);
            minBrightness = Math.min(data[count], minBrightness);

            if (data[count - 1] <= data[count] && data[count] >= data[count + 1]) {
                if (flag) {
                    n1 = count;
                    flag = false;
                } else {
                    n2 = count;
                    flag = true;
                }
                frequency += FPS / Math.abs(n1 - n2);
                n++;
            }

        }

        averageBrightness /= count;
        frequency /= n;
        pulsation = (maxBrightness - minBrightness) / averageBrightness * 50;
    }

    private double[] trendOf(double[] data, int radius) {
        var result = new double[data.length / radius];

        double sum;
        for (int i = 0; i < result.length; i++) {
            sum = 0;
            for (int j = 0; j < radius; j++) {
                sum += data[i * radius + j];
            }
            result[i] = sum / radius;
        }

        return result;
    }

    public double[] getTrend() {
        return trend;
    }

    public double getAverageBrightness() {
        return averageBrightness;
    }

    public double getMaxBrightness() {
        return maxBrightness;
    }

    public double getFrequency() {
        return frequency;
    }

    public double getMinBrightness() {
        return minBrightness;
    }

    public double getPulsation() {
        return pulsation;
    }

    public double getFPS() {
        return FPS;
    }

    public int getTrendK() {
        return trendK;
    }
}