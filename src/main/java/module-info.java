module com.example.javaee8 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.javaee8 to javafx.fxml;
    exports com.example.javaee8;
    exports com.example.javaee8.controller;
    opens com.example.javaee8.controller to javafx.fxml;
}