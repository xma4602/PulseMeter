public class Analyzer {
    private double
            maxBrightness,
            minBrightness,
            averageBrightness,
            frequency,
            pulsation;

    public void Ananyze(double[] data, double fpc) {
        maxBrightness = Integer.MIN_VALUE;
        minBrightness = Integer.MAX_VALUE;
        averageBrightness = 0;
        frequency = 0;

        int n1 = 0, n2 = 0;
        boolean flag = true;

        int count = 1;
        for (; count < data.length - 1; count++) {
            averageBrightness += data[count];
            maxBrightness = Math.max(data[count], maxBrightness);
            minBrightness = Math.min(data[count], minBrightness);

            //если пик колебаний
            if (data[count - 1] <= data[count] && data[count] >= data[count + 1]) {
                if (flag) {
                    n1 = count;
                    flag = false;
                } else {
                    n2 = count;
                    flag = true;
                }
            }
            frequency += fpc / Math.abs(n1 - n2);
        }

        averageBrightness /= count;
        frequency /= (count-2);
        pulsation = (maxBrightness - minBrightness) / averageBrightness * 50;
    }
}
