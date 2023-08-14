// The package where all classes reside.
package seatingapp;

// Importing the JavaFX Color class for seat color.
import javafx.scene.paint.Color;

// Class representing a student and their seating information.
public class Student {

    // Attributes of a student: name, seating position, and the color of the seat.
    private String name;
    private int seatingPosition;
    private Color seatColor;

    public Student(String name, int seatingPosition, Color seatColor){
        this.name = name;
        this.seatingPosition = seatingPosition;
        this.seatColor = seatColor;
    }

    public String getName() {
        return name;
    }

    public int getSeatingPosition() {
        return seatingPosition;
    }

    public Color getSeatColor() {
        return seatColor;
    }
}