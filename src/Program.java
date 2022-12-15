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

        final NumberAxis xAxis = new NumberAxis(), yAxis = new NumberAxis();
        final LineChart <Number,Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();
        for (int i = 0; i < videoHandler.getPulsationArray().length - 1; i++) {
            series.getData().add(new XYChart.Data(i, 100 * videoHandler.getPulsationArray()[i]));
        }
        Scene scene = new Scene(lineChart, 1920, 1080);
        lineChart.getData().add(series);
        stage.setScene(scene);

        stage.show();
    }
}