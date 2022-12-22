package com.example.pulsemeterforms;

import com.example.pulsemeterforms.classes.Analyzer;
import com.example.pulsemeterforms.classes.VideoHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    FileChooser fileChooser = new FileChooser();

    @FXML
    private Label welcomeText, fpsText, minBrightnessText, maxBrightnessText, frequencyText, pulsationText, avgBrightnessText;

    @FXML
    protected void onHelloButtonClick() {
        File file = fileChooser.showOpenDialog(new Stage());
        welcomeText.setText("Path: " + file.getAbsolutePath());
        VideoHandler videoHandler = new VideoHandler(file.getAbsolutePath(), false);

        fpsText.setText(String.valueOf(videoHandler.getFPS()));

        var frames = videoHandler.getFrames(150, 450);
        var analyzer = new Analyzer(frames, videoHandler.getFPS());

        minBrightnessText.setText(String.valueOf(analyzer.getMinBrightness()));
        maxBrightnessText.setText(String.valueOf(analyzer.getMaxBrightness()));
        avgBrightnessText.setText(String.valueOf(analyzer.getAverageBrightness()));
        frequencyText.setText(String.valueOf(analyzer.getFrequency()));
        pulsationText.setText(String.valueOf(analyzer.getPulsation()));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser.setInitialDirectory(new File("C:\\Users\\Sept1m\\IdeaProjects"));
    }
}