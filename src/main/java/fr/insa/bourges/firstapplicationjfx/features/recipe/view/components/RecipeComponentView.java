package fr.insa.bourges.firstapplicationjfx.features.recipe.view.components;

import fr.insa.bourges.firstapplicationjfx.base.view.ComponentView;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Ingredient;

import fr.insa.bourges.firstapplicationjfx.features.shared.models.Recipe;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.util.List;

public class RecipeComponentView extends ComponentView {

    @FXML
    private Label recipeName;
    @FXML
    private Label recipeCategory;
    @FXML
    private Label recipeInstruction;
    @FXML
    private Label recipePreparationTime;
    @FXML
    private Label recipeCookingTime;
    @FXML
    private Label recipeDifficultyLevel;
    @FXML
    private FlowPane ingredientsFlowPane; // Container for the list of ingredients

    private Recipe recipe;

    // Set the Recipe object
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;

        // Set the recipe information
        recipeName.setText("Recipe Name: " + recipe.getName());
        recipeCategory.setText("Category: " + recipe.getCategory().toString());
        recipeInstruction.setText("Instructions: " + recipe.getInstructions());
        recipePreparationTime.setText("Preparation Time: " + recipe.getPreparationTime() + " minutes");
        recipeCookingTime.setText("Cooking Time: " + recipe.getCookingTime() + " minutes");
        recipeDifficultyLevel.setText("Difficulty Level: " + recipe.getDifficultyLevel());

        // Dynamically add the ingredients to the FlowPane
        List<Ingredient> ingredients = recipe.getIngredients();
        for (Ingredient ingredient : ingredients) {
            Label ingredientLabel = new Label(ingredient.getName() + " - " + ingredient.getQuantity() + " " + ingredient.getUnit());
            ingredientsFlowPane.getChildren().add(ingredientLabel);
        }
    }

    @FXML
    private void showRecipeEditModal() {
        // Handle modal for editing the recipe
    }

    @FXML
    private void deleteRecipe() {
        // Handle recipe deletion
    }
}
