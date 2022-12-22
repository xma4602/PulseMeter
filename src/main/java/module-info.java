module com.pulsemeter {
    requires javafx.controls;
    requires javafx.fxml;

    requires opencv;
    requires java.desktop;

    opens com.pulsemeter to javafx.fxml;
    exports com.pulsemeter;
}