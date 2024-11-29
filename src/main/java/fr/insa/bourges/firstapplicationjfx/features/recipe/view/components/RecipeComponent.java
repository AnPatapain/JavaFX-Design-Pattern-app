package fr.insa.bourges.firstapplicationjfx.features.recipe.view.components;

import fr.insa.bourges.firstapplicationjfx.base.view.ComponentView;
import fr.insa.bourges.firstapplicationjfx.features.recipe.RecipeCommandKeys;
import fr.insa.bourges.firstapplicationjfx.features.recipe.view.RecipePageType;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Ingredient;

import fr.insa.bourges.firstapplicationjfx.features.shared.models.Recipe;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.util.List;

public class RecipeComponent extends ComponentView {

    @FXML
    public Label recipeName;
    @FXML
    public Label recipeCategory;
    @FXML
    public Label recipeInstruction;
    @FXML
    public Label recipePreparationTime;
    @FXML
    public Label recipeCookingTime;
    @FXML
    public Label recipeDifficultyLevel;
    @FXML
    public FlowPane ingredientsFlowPane; // Container for the list of ingredients

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
    private void editRecipeHandler() {
        // Handle modal for editing the recipe
//        this.getParentPageView().getController().navigateToEditRecipe(this.recipe);
        this.executeCommand(RecipeCommandKeys.UPDATE_RECIPE.name(), RecipePageType.EDIT, this.recipe);

    }

    @FXML
    private void deleteRecipeHandler() {
        this.executeCommand(RecipeCommandKeys.UPDATE_RECIPE.name(), this.recipe.getId());
    }
}
