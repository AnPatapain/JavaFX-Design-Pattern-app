package fr.insa.bourges.firstapplicationjfx.features.ingredient;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractView;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Ingredient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class IngredientView extends AbstractView<IngredientController> {
    @FXML
    public BorderPane borderPane;
    @FXML
    public TextField name;
    @FXML
    public ComboBox<String> quantity;
    @FXML
    public TextField unit;
    @FXML
    public TextField addDate;
    @FXML
    public TextField expirationDate;


    @Override
    public void initializeScene() {
        this.setScene(new Scene(this.borderPane, 600, 400));
    }

    public void navigateToHomePage(ActionEvent event) {
        this.getController().navigateToHomePage();
    }


    public void deleteIngredient(ActionEvent actionEvent) {
    }
    public void addIngredient(ActionEvent actionEvent) {
    }
    public void modifyIngredient(ActionEvent actionEvent) {
    }
}
