module com.example.timer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.timer to javafx.fxml;
    exports com.example.timer;
}