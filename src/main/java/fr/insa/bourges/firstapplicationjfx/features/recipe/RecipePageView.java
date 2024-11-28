package fr.insa.bourges.firstapplicationjfx.features.recipe;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractView;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.*;
import fr.insa.bourges.firstapplicationjfx.features.shared.utils.CustomUIAlert;
import fr.insa.bourges.firstapplicationjfx.features.shared.utils.InputFormatter;
import fr.insa.bourges.firstapplicationjfx.features.shared.utils.TimeParser;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

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
    public ComboBox<String> preparationTimeComboBox;

    @FXML
    public ComboBox<String> cookingTimeComboBox;

    @FXML
    public TextArea instructionArea;

    @FXML
    public ListView<String> ingredientsListView;;

    @FXML
    public Button addRecipeButton;

    @FXML
    public Button removeIngredientButton;


    @Override
    public void initializeScene() {
        this.setScene(new Scene(root, 600, 400));
        TextFormatter<String> numericFormatter = InputFormatter.getNumericInputFormatter();
        ingredientQuantityField.setTextFormatter(numericFormatter);
    }

    public void navigateToHomePage(ActionEvent event) {
        this.getController().navigateToHomePage();
    }

    public void saveRecipe(ActionEvent actionEvent) {
    }

    @FXML
    public void addIngredient(ActionEvent actionEvent) {
        String ingredientName = ingredientNameField.getText();
        String ingredientQuantity= ingredientQuantityField.getText();
        String unit = quantityUnitComboBox.getValue();

        if (ingredientName.isEmpty() || ingredientQuantity.isEmpty() || unit == null) {
            CustomUIAlert.showAlert("Error", "Please fill all ingredient fields before adding.");
            return;
        }
        String ingredient = ingredientName + " - " + ingredientQuantity + " " + unit;
        ingredientsListView.getItems().add(ingredient);


        ingredientNameField.clear();
        ingredientQuantityField.clear();
        quantityUnitComboBox.getSelectionModel().clearSelection();
        CustomUIAlert.showAlert("Success", "Ingredient added successfully.");
    }


    public void removeSelectedIngredient(ActionEvent event) {
        String selectedIngredient = ingredientsListView.getSelectionModel().getSelectedItem();
        if (selectedIngredient == null) {
            CustomUIAlert.showAlert("Error", "Please select an ingredient to remove.");
            return;
        }

        // Remove the selected ingredient
        ingredientsListView.getItems().remove(selectedIngredient);
    }

    public void addRecipe(ActionEvent actionEvent) {

        List<Ingredient> ingredientObjects = new ArrayList<>();

        for(String ingredient :ingredientsListView.getItems()){
            String[] parts = ingredient.split(" - ");
            String name = parts[0];
            String[] quantityParts = parts[1].split(" ");
            Double quantity = Double.parseDouble(quantityParts[0]);
            UnitMeasure unit = UnitMeasure.valueOf(quantityParts[1]);
            ingredientObjects.add(new Ingredient(name, quantity, unit));
        }

        String recipeName = recipeNameField.getText();
        String category = categoryComboBox.getValue();
        String preparationTime = preparationTimeComboBox.getValue();
        String cookingTime = cookingTimeComboBox.getValue();
        String difficulty = difficultyComboBox.getValue();
        if(recipeName.isEmpty() || category == null || preparationTime == null || cookingTime == null || difficulty == null || ingredientObjects.isEmpty() || instructionArea.getText().isEmpty()){
            CustomUIAlert.showAlert("Error", "Please fill all recipe fields before adding.");
            return;
        }



        Recipe recipe = new Recipe(
                recipeNameField.getText(),
                CategoryRecipe.valueOf(category),
                TimeParser.parseTime(preparationTime),
                TimeParser.parseTime(cookingTime),
                DifficultyLevel.valueOf(difficulty),
                ingredientObjects,
                instructionArea.getText()
        );
        this.getController().addRecipe(recipe);
        CustomUIAlert.showAlert("Success", "Recipe added successfully.");

    }

    public void modifyRecipe(ActionEvent actionEvent) {
    }

    public void deleteRecipe(ActionEvent actionEvent) {
    }




}

