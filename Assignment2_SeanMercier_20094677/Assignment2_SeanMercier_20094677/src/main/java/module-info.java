module com.example.assignment2finalversion {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;


    opens com.example.assignment2finalversion to javafx.fxml, xstream;
    exports com.example.assignment2finalversion;
    exports com.example.assignment2finalversion.ADT;
    opens com.example.assignment2finalversion.ADT to javafx.fxml;
}