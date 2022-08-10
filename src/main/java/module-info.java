module com.example.phonestore2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;
    requires java.desktop;


    opens com.example.phonestore2 to javafx.fxml;
    exports com.example.phonestore2;
    exports DBConnection;
    opens DBConnection to javafx.fxml;
    exports com.example.phonestore2.Homepage;
    opens com.example.phonestore2.Homepage to javafx.fxml;
}