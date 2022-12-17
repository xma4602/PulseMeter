public class Analyzer {
    private double
            maxBrightness,
            minBrightness,
            averageBrightness,
            frequency,
            pulsation,
            FPS;
    public Analyzer(double[] data, double fpc) {
        maxBrightness = Integer.MIN_VALUE;
        minBrightness = Integer.MAX_VALUE;
        averageBrightness = 0;
        frequency = 0;
        FPS = fpc;
        int n1 = 0, n2 = 0;
        boolean flag = true;

        int count = 1;
        int n = 0;
        for (; count < data.length - 1; count++) {
            averageBrightness += data[count];
            maxBrightness = Math.max(data[count], maxBrightness);
            minBrightness = Math.min(data[count], minBrightness);

            //���� ��� ���������
            if (data[count - 1] <= data[count] && data[count] >= data[count + 1]) {
                if (flag) {
                    n1 = count;
                    flag = false;
                } else {
                    n2 = count;
                    flag = true;
                }
                frequency += fpc / Math.abs(n1 - n2);
                n++;
            }

        }

        averageBrightness /= count;
        frequency /= n;
        pulsation = (maxBrightness - minBrightness) / averageBrightness * 50;
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
}