module com.csc325.menuproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.csc325.menuproject to javafx.fxml;
    exports com.csc325.menuproject;
}