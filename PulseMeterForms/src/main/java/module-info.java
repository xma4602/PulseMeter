module com.example.pulsemeterforms {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencv;
    requires java.desktop;


    opens com.example.pulsemeterforms to javafx.fxml;
    exports com.example.pulsemeterforms;
}