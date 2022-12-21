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

    String path = "src/materials/videos/8.mov";
    int fromFrame = 0, toFrame = 300;
    boolean highPrecision = false;

    public void start(Stage stage) {
        VideoHandler videoHandler = new VideoHandler(path, highPrecision);

        stage.setTitle("Chart");
        stage.setResizable(false);

        var xAxis = new NumberAxis();
        var yAxis = new NumberAxis();
        var chart = new LineChart<>(xAxis, yAxis);
        chart.setTitle("√рафик по€влени€ импульсов и тренда €ркости.");

        var seriesMain = new XYChart.Series<Number, Number>();
        var seriesTrend = new XYChart.Series<Number, Number>();
        seriesMain.setName("»мпульсы");
        seriesTrend.setName("“ренд €ркости");

        var frames = videoHandler.getFrames(fromFrame, toFrame);

        var analyzer = new Analyzer(frames, videoHandler.getFPS());
        var trend = analyzer.getTrend();
        var trendK = analyzer.getTrendK();

        for (int x = 0; x < frames.length; x++) {
            seriesMain.getData().add(new XYChart.Data<>(x, frames[x]));
            if (x < trend.length)
                seriesTrend.getData().add(new XYChart.Data<>( x * trendK + trendK / 2 , trend[x]));
        }

        chart.getData().add(seriesMain);
        chart.getData().add(seriesTrend);
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