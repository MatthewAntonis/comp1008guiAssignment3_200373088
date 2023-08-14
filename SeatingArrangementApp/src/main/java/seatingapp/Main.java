/*
Matthew Antonis
200373088
Assignment3
Due: August 13th, 2023
 */

// The package where all classes reside.
package seatingapp;

// Importing necessary JavaFX classes.
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Main class that launches the JavaFX application.
public class Main extends Application {

    // This method is called when the JavaFX application is started.
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Loading the user interface definition from the 'sample.fxml' file.
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));

        // Setting the title of the primary window.
        primaryStage.setTitle("Seats");

        // Creating a new scene with the loaded UI definition and setting its dimensions.
        primaryStage.setScene(new Scene(root, 400, 600));

        // Displaying the primary window.
        primaryStage.show();
    }

    // The main method that gets called when the Java program is started.
    public static void main(String[] args) {
        // Launching the JavaFX application.
        launch(args);
    }
}
