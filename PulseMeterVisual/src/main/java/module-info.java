module com.example.pulsemetervisual {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.pulsemetervisual to javafx.fxml;
    exports com.pulsemetervisual;
}