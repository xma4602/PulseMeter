public class Program {
    public static void main(String[] args){

        var frames = VideoViewer.SplitIntoFrames("movies/240_1.MOV");
        var anal = new Analyzer(frames, 1);

        System.out.println("count:\t\t" + anal.getFrameCount());
        System.out.println("max:\t\t" + anal.getMaxBrightness());
        System.out.println("min:\t\t" + anal.getMinBrightness());
        System.out.println("average:\t" + anal.getAverageBrightness());
        System.out.println("Frequency:\t" + anal.getFrequency());
        System.out.println("Pulsation:\t" + anal.getPulsation());

        //VideoShow.closeForm();
    }
//        var black = ImageIO.read(new File("src\\images\\black.png"));
//        var white = ImageIO.read(new File("src\\images\\white.png"));
//        var lightgray = ImageIO.read(new File("src\\images\\lightgray.png"));
//        var darkgray = ImageIO.read(new File("src\\images\\darkgray.png"));
//
//        var count = 1000;
//        double amplitude = 1.0/2;
//        double frequency = 1000;
//        double offset = 1.0/2;
//        var arr = generate(count, amplitude, frequency, offset);
//
//        Analyzer anal = new Analyzer();
//        var start = System.nanoTime();
//        anal.Analyze(arr, count / frequency);
//        var end = System.nanoTime();
//
//        var time = (end - start) / 1e9;
//
//        System.out.println("time: " + time);
//        System.out.println("max: " + anal.getMaxBrightness());
//        System.out.println("min: " + anal.getMinBrightness());
//        System.out.println("average: " + anal.getAverageBrightness());
//        System.out.println("Frequency: " + anal.getFrequency());
//        System.out.println("Pulsation: " + anal.getPulsation());
//    }
//
//    private static double[] generate(int count, double amplitude, double frequency, double offset) {
//        var br = new double[count];
//
//        for (int i = 0; i < br.length; i++) {
//            br[i] = amplitude * abs(sin(i * 2 * Math.PI/180 * frequency)) + offset;
//        }
//        return br;
//
}
