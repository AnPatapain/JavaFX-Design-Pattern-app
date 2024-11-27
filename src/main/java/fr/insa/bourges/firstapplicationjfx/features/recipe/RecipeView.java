package fr.insa.bourges.firstapplicationjfx.features.recipe;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractView;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class RecipeView extends AbstractView<RecipeController> {
    @FXML
    public VBox root;

    @FXML
    public TextField recipeNameField;

    @FXML
    public ComboBox<String> categoryComboBox;

    @FXML
    public ComboBox<String> difficultyComboBox;

    @FXML
    public TextField ingredientNameField;

    @FXML
    public TextField ingredientQuantityField;

    @FXML
    public ComboBox<String> quantityUnitComboBox;

    @FXML
    public Button addIngredientButton;

    @FXML
    public TextArea ingredientsListArea;

    @FXML
    public ComboBox<String> preparationTimeComboBox;

    @FXML
    public ComboBox<String> cookingTimeComboBox;

    @FXML
    public TextArea instructionArea;

    @FXML
    public Button addRecipeButton;

    @FXML
    public Button modifyRecipeButton;

    @FXML
    public Button deleteRecipeButton;



    @Override
    public void initializeScene() {
        this.setScene(new Scene(root, 600, 400));
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

