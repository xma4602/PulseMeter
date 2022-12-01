import java.awt.image.BufferedImage;

public class Analyzer {

    private double maxBrightness;
    private double minBrightness;
    private double averageBrightness;
    private double frequency;
    private double pulsation;

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
