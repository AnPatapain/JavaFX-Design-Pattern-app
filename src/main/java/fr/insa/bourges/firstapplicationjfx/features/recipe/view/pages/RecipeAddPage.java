package fr.insa.bourges.firstapplicationjfx.features.recipe.view.pages;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractPageView;
import fr.insa.bourges.firstapplicationjfx.features.recipe.RecipeController;
import fr.insa.bourges.firstapplicationjfx.features.recipe.view.RecipePageType;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.*;
import fr.insa.bourges.firstapplicationjfx.features.shared.utils.CustomUIAlert;
import fr.insa.bourges.firstapplicationjfx.features.shared.utils.InputFormatter;
import fr.insa.bourges.firstapplicationjfx.features.shared.utils.TimeParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class RecipeAddPage extends AbstractPageView<RecipeController> {
    @FXML
    public VBox root;

    @FXML
    public Label recipeLabel;
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
    public ComboBox<String> categoryIngredientComboBox;

    @FXML
    public Button addIngredientButton;


    @FXML
    public ComboBox<String> preparationTimeComboBox;

    @FXML
    public ComboBox<String> cookingTimeComboBox;

    @FXML
    public TextArea instructionArea;

    @FXML
    public ListView<String> ingredientsListView;

    @FXML
    public Button addRecipeButton;

    @FXML
    public Button removeIngredientButton;

    private Recipe recipe;
    private RecipePageType recipePageType;


    @Override
    public void initializeScene() {
        this.setScene(new Scene(root, 600, 400));
        TextFormatter<String> numericFormatter = InputFormatter.getNumericInputFormatter();
        ingredientQuantityField.setTextFormatter(numericFormatter);
    }


    @FXML
    public void addIngredient(ActionEvent actionEvent) {
        String ingredientName = ingredientNameField.getText();
        String ingredientQuantity= ingredientQuantityField.getText();
        String unit = quantityUnitComboBox.getValue();
        String category = categoryIngredientComboBox.getValue();

        if (ingredientName.isEmpty() || ingredientQuantity.isEmpty() || unit == null|| category == null) {
            CustomUIAlert.showAlert("Error", "Please fill all ingredient fields before adding.");
            return;
        }
        String ingredient = ingredientName + " - " + ingredientQuantity + " " + unit + " - " + category;
        ingredientsListView.getItems().add(ingredient);


        ingredientNameField.clear();
        ingredientQuantityField.clear();
        quantityUnitComboBox.getSelectionModel().clearSelection();
        CustomUIAlert.showAlert("Success", "Ingredient added successfully.");
    }

    public void setPageLabel(String label) {
        recipeLabel.setText(label);
    }

    private void clearFields() {
        // Clear text fields
        recipeNameField.clear();
        instructionArea.clear();
        ingredientNameField.clear();
        ingredientQuantityField.clear();

        // Clear combo boxes
        categoryComboBox.getSelectionModel().clearSelection();
        difficultyComboBox.getSelectionModel().clearSelection();
        preparationTimeComboBox.getSelectionModel().clearSelection();
        cookingTimeComboBox.getSelectionModel().clearSelection();
        quantityUnitComboBox.getSelectionModel().clearSelection();
        categoryIngredientComboBox.getSelectionModel().clearSelection();
        // Clear ListView
        ingredientsListView.getItems().clear();


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
    public void setRecipePageType(RecipePageType recipePageType) {
        this.recipePageType = recipePageType;
    }

    public void setRecipe(Recipe recipe){
        this.recipe = recipe;
        recipeNameField.setText(recipe.getName());
        categoryComboBox.setValue(recipe.getCategory().toString());
        preparationTimeComboBox.setValue(TimeParser.convertDecimalToHHmm(recipe.getPreparationTime()));
        instructionArea.setText(recipe.getInstructions());
        cookingTimeComboBox.setValue(TimeParser.convertDecimalToHHmm(recipe.getCookingTime()));
        difficultyComboBox.setValue(recipe.getDifficultyLevel().toString());
        List<Ingredient> ingredients = recipe.getIngredients();
        for (Ingredient ingredient : ingredients) {
            String ingredientString = ingredient.getName() + " - " + ingredient.getQuantity() + " " + ingredient.getUnit() + " - " + ingredient.getCategoryIngredient();
            ingredientsListView.getItems().add(ingredientString);
        }

    }

    public void saveRecipeHandler(ActionEvent actionEvent) {

        List<Ingredient> ingredientObjects = new ArrayList<>();

        for(String ingredient :ingredientsListView.getItems()){
            String[] parts = ingredient.split(" - ");
            String name = parts[0];
            String[] quantityParts = parts[1].split(" ");
            Double quantity = Double.parseDouble(quantityParts[0]);
            UnitMeasure unit = UnitMeasure.valueOf(quantityParts[1]);
            CategoryIngredient category = CategoryIngredient.valueOf(parts[2]);
            Ingredient ingredientObject = new Ingredient();
            ingredientObject.setName(name);
            ingredientObject.setQuantity(quantity);
            ingredientObject.setUnit(unit);
            ingredientObject.setCategoryIngredient(category);

            ingredientObjects.add(ingredientObject);
        }

        String recipeName = recipeNameField.getText();
        String category = categoryComboBox.getValue();
        String preparationTime = preparationTimeComboBox.getValue();
        String cookingTime = cookingTimeComboBox.getValue();
        String difficulty = difficultyComboBox.getValue();
        String instruction = instructionArea.getText();
        if(recipeName.isEmpty() || category == null || preparationTime == null || cookingTime == null || difficulty == null || ingredientObjects.isEmpty() || instruction.isEmpty()){
            CustomUIAlert.showAlert("Error", "Please fill all recipe fields before adding.");
            return;
        }



        if (this.recipePageType == RecipePageType.EDIT) {

            this.recipe.setName(recipeName);
            this.recipe.setCategory(CategoryRecipe.valueOf(category));
            this.recipe.setPreparationTime(TimeParser.parseTime(preparationTime));
            this.recipe.setInstructions(instructionArea.getText());
            this.recipe.setCookingTime(TimeParser.parseTime(cookingTime));
            this.recipe.setDifficultyLevel(DifficultyLevel.valueOf(difficulty));
            this.recipe.setIngredients(ingredientObjects);

            this.getController().updateRecipe(recipe);
            CustomUIAlert.showAlert("Success", "Recipe updated successfully.");
        }else{
            Recipe recipe = new Recipe(
                    recipeNameField.getText(),
                    CategoryRecipe.valueOf(category),
                    TimeParser.parseTime(preparationTime),
                    TimeParser.parseTime(cookingTime),
                    DifficultyLevel.valueOf(difficulty),
                    ingredientObjects,
                    instruction
            );
            this.getController().addRecipe(recipe);
            CustomUIAlert.showAlert("Success", "Recipe added successfully.");
        }

    }




    public void navigateToRecipeListPage(ActionEvent actionEvent) {
        clearFields();
        this.setPageLabel("Add Recipe");
        this.getController().navigateToRecipeListPage();
    }
}

