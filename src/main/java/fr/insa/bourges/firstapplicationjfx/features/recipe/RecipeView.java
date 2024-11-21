package fr.insa.bourges.firstapplicationjfx.features.recipe;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractView;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ListView;

public class RecipeView extends AbstractView<RecipeController> {
    @FXML
    public BorderPane borderPane;
    @FXML
    public TextField name;
    @FXML
    public ComboBox<String> category;
    @FXML
    public TextArea instruction;
    @FXML
    public TextField preparationTime;
    @FXML
    public TextField cookingTime;
    @FXML
    public ComboBox<String> difficultyLevel;


    @Override
    public void initializeScene() {
        this.setScene(new Scene(borderPane, 600, 400));
    }

    public void navigateToHomePage(ActionEvent event) {
        this.getController().navigateToHomePage();
    }

    public void saveRecipe(ActionEvent actionEvent) {
    }

    public void addRecipe(ActionEvent actionEvent) {
    }

    public void modifyRecipe(ActionEvent actionEvent) {
    }

    public void deleteRecipe(ActionEvent actionEvent) {
    }
}

