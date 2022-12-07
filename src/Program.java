public class Program {
    public static void main(String[] args){

        Analyzer a = new Analyzer("src/movies/60_2.MOV");

        System.out.println("max: " + a.getMaxBrightness());
        System.out.println("min: " + a.getMinBrightness());
        System.out.println("average: " + a.getAverageBrightness());
        System.out.println("Frequency: " + a.getFrequency());
        System.out.println("Pulsation: " + a.getPulsation());
        System.out.println("FPS: " + a.getFPS());
        VideoShow.closeForm();
    }
}
