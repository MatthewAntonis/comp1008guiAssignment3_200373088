// The package where all classes reside.
package seatingapp;

// Importing necessary JavaFX and utility classes.
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.*;

// Controller class for the seating app.
public class Controller {

    // JavaFX elements from the user interface (UI).
    @FXML
    private TextField studentNameText;

    @FXML
    private ColorPicker colorChoice;

    @FXML
    private Text name1, name2, name3, name4, name5, name6, name7, name8, name9;

    @FXML
    private Text feedbackMessage;

    @FXML
    private Rectangle seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8, seat9;

    @FXML
    private Button assignSeatButton;

    // Lists to hold references for seats and names from the UI.
    private List<Text> names;
    private List<Rectangle> seats;

    // List of the students.
    private List<Student> students = new ArrayList<>();

    // Constants to represent message types and colors.
    private final int ERROR_MESSAGE = 1;
    private final int SUCESS_MESSAGE = 2;
    private final String ERROR_MESSAGE_COLOR = "RED";
    private final String SUCCESS_MESSAGE_COLOR = "BLUE";

    // Method that runs when the UI is first loaded.
    @FXML
    public void initialize(){
        constructSeatsList();
        constructNamesList();
    }

    // Constructing the list of seat references from the UI.
    private void constructSeatsList(){
        seats = new ArrayList<>();

        seats.add(seat1);
        seats.add(seat2);
        seats.add(seat3);
        seats.add(seat4);
        seats.add(seat5);
        seats.add(seat6);
        seats.add(seat7);
        seats.add(seat8);
        seats.add(seat9);
    }

    // Constructing the list of name references from the UI.
    private void constructNamesList(){
        names = new ArrayList<>();

        names.add(name1);
        names.add(name2);
        names.add(name3);
        names.add(name4);
        names.add(name5);
        names.add(name6);
        names.add(name7);
        names.add(name8);
        names.add(name9);
    }

    // Event handler for assigning seats.
    @FXML
    private void handleAssignSeatsList(ActionEvent event){
        if(feedbackMessage.isVisible()){
            feedbackMessage.setVisible(false);
        }

        // Check if all seats are occupied.
        if(students.size()>=9){
            displayMessage("Seats Are Now Full!", ERROR_MESSAGE);
            return;
        }

        String studentName = studentNameText.getText();
        Color seatColor = colorChoice.getValue();

        // Validating the student's name and seat color.
        if(!validateStudentName(studentName)){
            displayMessage("Invalid Student Name", ERROR_MESSAGE);
            return;
        }

        if(!checkColors(seatColor)){
            displayMessage("Colour Has Been Taken!", ERROR_MESSAGE);
            return;
        }

        assignSeat(studentName, seatColor);
    }

    // Display feedback messages on the UI.
    public void displayMessage(String message, int caseTag){
        feedbackMessage.setVisible(true);

        // Decide the color of the feedback message based on its type.
        if(caseTag==ERROR_MESSAGE){
            feedbackMessage.setFill(Paint.valueOf(ERROR_MESSAGE_COLOR));
        }else{
            feedbackMessage.setFill(Paint.valueOf(SUCCESS_MESSAGE_COLOR));
        }
        feedbackMessage.setText(message);
    }

    // Assign a seat to a student.
    private void assignSeat(String studentName, Color seatColor){
        Random random = new Random();
        int randomNumber;

        // Get a random seat that is available.
        do{
            randomNumber = random.nextInt(seats.size());
        }while(!checkSeats(randomNumber));

        // Create a student object and add it to the students list.
        Student student = new Student(studentName, randomNumber, seatColor);
        students.add(student);

        // Update the UI to reflect the seating arrangement.
        seats.get(student.getSeatingPosition()).setFill(student.getSeatColor());
        names.get(student.getSeatingPosition()).setVisible(true);
        names.get(student.getSeatingPosition()).setText(student.getName());

        displayMessage(String.format("%s\'s Seat Has Been Assigned!", student.getName()), SUCESS_MESSAGE);
    }

    // Validate if a student's name is unique and not empty.
    private boolean validateStudentName(String studentName){
        boolean availability = true;

        if(studentName.trim().isEmpty()){
            availability = false;
        }else{
            for(int i = 0; i<students.size(); i++){
                if(students.get(i).getName().equals(studentName)){
                    availability = false;
                }
            }
        }
        return availability;
    }

    // Check if a color is already taken by another student.
    private boolean checkColors(Color color){
        boolean availability= true;

        for(int i = 0; i<students.size(); i++){
            if(students.get(i).getSeatColor().equals(color)){
                availability = false;
            }
        }
        return availability;
    }

    // Check if a seat is already occupied.
    private boolean checkSeats(int randomNumber) {
        boolean availability = true;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getSeatingPosition() == randomNumber) {
                availability = false;
            }
        }
        return availability;
    }
}