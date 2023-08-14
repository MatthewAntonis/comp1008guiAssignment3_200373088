import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Controller {

    @FXML
    private GridPane gridPane;

    @FXML
    private TextField nameField;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Label errorLabel;

    private Set<String> usedColors = new HashSet<>();
    private Random rand = new Random();

    @FXML
    public void placeStudent() {
        errorLabel.setText(""); // Clear error message

        String name = nameField.getText().trim();
        Color chosenColor = colorPicker.getValue();

        if (name.isEmpty()) {
            errorLabel.setText("Name cannot be empty!");
            return;
        }

        String colorAsString = "#" + chosenColor.toString().substring(2, 8);
        if (usedColors.contains(colorAsString)) {
            errorLabel.setText("Color already used!");
            return;
        }

        int row, col;
        for (int i = 0; i < 9; i++) {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
            if (isCellEmpty(row, col)) {
                Pane cell = new Pane();
                cell.setStyle("-fx-background-color: " + colorAsString);
                cell.setTooltip(new Tooltip(name));
                gridPane.add(cell, col, row);
                usedColors.add(colorAsString);
                return;
            }
        }

        errorLabel.setText("No seats available!");
    }

    private boolean isCellEmpty(int row, int col) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                return false;
            }
        }
        return true;
    }
}
