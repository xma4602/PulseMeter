package com.pulsemeter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Label path, max, min, aver, f, k, fpc;

    @FXML
    private LineChart<Number, Number> chart;

    VideoHandler handler;
    FileChooser chooser = new FileChooser();

    static {
        var chart = new LineChart<>(new NumberAxis(), new NumberAxis());
        chart.setTitle("График появления импульсов и тренда яркости");

    }

    @FXML
    protected void openButtonClick() {
        File file = new FileChooser().showOpenDialog(new Stage());
        path.setText("Path: " + file.getAbsolutePath());
        var s = file.getAbsolutePath();
        handler = new VideoHandler(s, false, 0, 500);
    }

    @FXML
    protected void startButtonClick() {
        Analyzer analyzer = new Analyzer(handler.getFrames(), handler.getFPS());

        max.setText(String.format("%.2f", 100 * analyzer.getMaxBrightness()) + "%");
        min.setText(String.format("%.2f", 100 * analyzer.getMinBrightness()) + "%");
        aver.setText(String.format("%.2f", 100 * analyzer.getAverageBrightness()) + "%");
        f.setText(String.format("%.2f", analyzer.getFrequency()) + " Гц");
        k.setText(String.format("%.2f", analyzer.getPulsation()) + "%");
        fpc.setText(String.format("%.2f", analyzer.getFPS()) + " кадр/с");

        chart.getData().clear();
        var seriesMain = new XYChart.Series<Number, Number>();
        seriesMain.setName("Импульсы");
        //seriesMain.getNode().lookup("-fx-stroke: #000000;");

        var seriesTrend = new XYChart.Series<Number, Number>();
        seriesTrend.setName("Тренд яркости");
        //seriesTrend.getNode().lookup("-fx-stroke: #FFFFFF;");

        var frames = handler.getFrames();
        var trend = analyzer.getTrend();
        var trendK = analyzer.getTrendK();

        for (int x = 0; x < frames.length; x++) {
            seriesMain.getData().add(new XYChart.Data<>(x, frames[x]));
            if (x < trend.length)
                seriesTrend.getData().add(new XYChart.Data<>(x * trendK + trendK / 2, trend[x]));
        }

        chart.getData().add(seriesMain);
        chart.getData().add(seriesTrend);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooser.setInitialDirectory(new File("src\\main\\resources\\videos\\"));
    }
}