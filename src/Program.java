import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Program extends Application {
    public static void main(String[] args) {

        launch();
    }

    //@SuppressWarnings("unchecked")
    public void start(Stage stage) {
        VideoHandler videoHandler = new VideoHandler("src/materials/videos/7.mp4");

        stage.setTitle("Chart");
        stage.setResizable(false);

        var xAxis = new NumberAxis();
        var yAxis = new NumberAxis();
        var chart = new LineChart<>(xAxis, yAxis);
        chart.setTitle("√рафик по€влени€ импульсов и тренда €ркости.");

        var seriesMain = new XYChart.Series<Number, Number>();
        seriesMain.setName("»мпульсы");
        var seriesMiddle = new XYChart.Series<Number, Number>();
        seriesMiddle.setName("“ренд €ркости");


        var frames = videoHandler.getFrames(0, 500);
        int trendK = 20;
        var analyzer = new Analyzer(frames, videoHandler.getFPS(), trendK);
        var middle = analyzer.getTrend();

        for (int x = 0; x < frames.length; x++) {
            seriesMain.getData().add(new XYChart.Data<>(x, frames[x]));
            if (x < middle.length)
                seriesMiddle.getData().add(new XYChart.Data<>(x * trendK, middle[x]));
        }

        chart.getData().add(seriesMain);
        chart.getData().add(seriesMiddle);
        Scene scene = new Scene(chart, 1280, 720);
        stage.setScene(scene);

        stage.show();

        System.out.println("max:\t\t" + round(analyzer.getMaxBrightness()) + "%");
        System.out.println("min:\t\t" + round(analyzer.getMinBrightness()) + "%");
        System.out.println("average:\t" + round(analyzer.getAverageBrightness()) + "%");
        System.out.println("Frequency:\t" + round(analyzer.getFrequency()) + " √ц");
        System.out.println("Pulsation:\t" + round(analyzer.getPulsation()) + "%");
        System.out.println("FPS:\t\t" + round(analyzer.getFPS()) + " кадр/с");
    }

    private static String round(double n) {
        return String.format("%.3f", n);
    }

}