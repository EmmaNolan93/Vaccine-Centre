module com.example.emma_nolan {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;


    opens com.example.emma_nolan to javafx.fxml, xstream;
    exports com.example.emma_nolan;
}