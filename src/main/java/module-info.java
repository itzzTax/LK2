module com.example.lk2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.lk2 to javafx.fxml;
    exports com.example.lk2;
}