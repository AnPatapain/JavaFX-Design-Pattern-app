package fr.insa.bourges.firstapplicationjfx.features.creation;
import fr.insa.bourges.firstapplicationjfx.base.view.AbstractView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;


public class CreationView extends AbstractView<CreationController> {
    @FXML
    private BorderPane borderPane;
    @FXML
    public TextField name;
    @FXML
    public TextField weight;

    public void initializeScene() {
        this.setScene(new Scene(borderPane, 600, 400));
    }

    public void printConfirmation(String titre,String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,titre, ButtonType.OK);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void printError(String titre,String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR,titre, ButtonType.OK);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void createHorse(ActionEvent actionEvent) {
        try {
            this.getController().createHorse(
                    this.name.getText(),
                    Integer.parseInt(this.weight.getText())
            );
        } catch (NumberFormatException e) {
            this.printError("Error", "weight is invalid");
        }
    }

    public void gotomenu(ActionEvent actionEvent) {
        this.getController().navigateToHome();
    }
}
