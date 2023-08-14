module seatingapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens seatingapp to javafx.fxml;
    exports seatingapp;
}