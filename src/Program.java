import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Program extends Application {
    public static void main(String[] args) {
//        System.out.println("max: " + a.getMaxBrightness());
//        System.out.println("min: " + a.getMinBrightness());
//        System.out.println("average: " + a.getAverageBrightness());
//        System.out.println("Frequency: " + a.getFrequency());
//        System.out.println("Pulsation: " + a.getPulsation());
//        System.out.println("FPS: " + a.getFPS());
        launch();
    }

    public void start(Stage stage) {
        VideoHandler videoHandler = new VideoHandler("src/materials/videos/4.mp4");

        stage.setTitle("Chart");
        stage.setResizable(false);

        var xAxis = new NumberAxis();
        var yAxis = new NumberAxis();
        var lineChart = new LineChart<>(xAxis, yAxis);
        var series = new XYChart.Series();

        int from = 0, to = 1000;

        for (int i = from; i < to && i < videoHandler.getFrames().length - 1; i++) {
            series.getData().add(new XYChart.Data(i, videoHandler.getFrames()[i]));
        }

        lineChart.getData().add(series);
        Scene scene = new Scene(lineChart, 1920, 1080);
        stage.setScene(scene);

        stage.show();
    }
}