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

    public void start(Stage stage) {
        VideoHandler videoHandler = new VideoHandler("src/materials/videos/7.mp4");

        stage.setTitle("Chart");
        stage.setResizable(false);

        var xAxis = new NumberAxis();
        var yAxis = new NumberAxis();
        var lineChart = new LineChart<>(xAxis, yAxis);
        var series = new XYChart.Series();

        for (int i = 0; i < videoHandler.getFrames().length; i++) {
            series.getData().add(new XYChart.Data(i, videoHandler.getFrames()[i]));
        }

        lineChart.getData().add(series);
        Scene scene = new Scene(lineChart, 1920, 1080);
        stage.setScene(scene);

        stage.show();

        Analyzer a = new Analyzer(videoHandler.getFrames(), videoHandler.getFPS());
        System.out.println("max: " + a.getMaxBrightness());
        System.out.println("min: " + a.getMinBrightness());
        System.out.println("average: " + a.getAverageBrightness());
        System.out.println("Frequency: " + a.getFrequency());
        System.out.println("Pulsation: " + a.getPulsation());
        System.out.println("FPS: " + a.getFPS());
    }
}