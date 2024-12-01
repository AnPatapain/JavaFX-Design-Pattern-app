/**
 * Class RecipeIndicationPage
 * Handles the UI and logic for displaying recipe feasibility based on available ingredients.
 * This page indicates which recipes can be prepared with the current inventory and highlights missing ingredients.

 * Key Features:
 * - **Feasibility Check**:
 *   - Compares the ingredients required for each recipe with the available inventory.
 *   - Displays the status of each recipe as "Complete" (if all ingredients are available) or "Incomplete" (if some ingredients are missing).
 * - **Detailed Missing Ingredients**:
 *   - Lists the missing or insufficient ingredients for recipes marked as "Incomplete."
 * - **Dynamic Recipe Components**:
 *   - Each recipe is represented as a styled VBox containing:
 *     - Recipe name.
 *     - Status (Complete/Incomplete).
 *     - List of missing ingredients (if applicable).

 * Workflow:
 * - On initialization, the scene is set and the `loadIndicationView` method is called.
 * - All recipes are fetched, and their feasibility is calculated against the current inventory.
 * - A visual representation of each recipe's feasibility is added to the container.

 * Event Handlers:
 * - **onBackToHomePageLinkClickHandler**: Navigates back to the home page when the link is clicked.

 * Methods:
 * - **initializeScene**: Sets the scene and loads the indication view.
 * - **loadIndicationView**: Loads all recipes and their feasibility status.
 * - **createIndicationComponent**: Generates a VBox for each recipe, displaying its status and missing ingredients.

 * Dependencies:
 * - **RecipeController**: Provides access to recipes and the ingredient inventory.
 * - **Recipe**: Contains logic to check feasibility using the `checkFeasibility` method.
 * - **Ingredient**: Represents the available inventory for comparison.

 * Design Notes:
 * - The page is dynamically updated based on the current inventory and recipes.
 * - Uses JavaFX components for a responsive and user-friendly UI.

 * Author: Anh Tuan NGUYEN & Ke An NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.features.recipe.view.pages;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractPageView;
import fr.insa.bourges.firstapplicationjfx.features.recipe.RecipeController;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Ingredient;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Map;

public class    RecipeIndicationPage extends AbstractPageView<RecipeController> {
    @FXML
    public BorderPane borderPane;

    @FXML
    public VBox recipeIndicationContainer;

    @FXML
    public void onBackToHomePageLinkClickHandler(ActionEvent actionEvent) {
        this.getController().navigateToHomePage();
    }

    @Override
    public void initializeScene() {
        this.setScene(new Scene(this.borderPane, 800, 600));
        this.loadIndicationView();
    }

    public void loadIndicationView() {
        List<Recipe> recipes = this.getController().getAllRecipe();
        List<Ingredient> inventory = this.getController().getAllIngredients();

        recipeIndicationContainer.getChildren().clear();

        for (Recipe recipe : recipes) {
            VBox recipeComponent = createIndicationComponent(recipe, inventory);
            recipeIndicationContainer.getChildren().add(recipeComponent);
        }
    }

    private VBox createIndicationComponent(Recipe recipe, List<Ingredient> currentAvailableIngredients) {
        VBox recipeBox = new VBox();
        recipeBox.setSpacing(10);
        recipeBox.setStyle("-fx-border-color: lightgray; -fx-padding: 10; -fx-background-color: white;");

        // Create HBox for Recipe Name and Status
        HBox headerBox = new HBox();
        headerBox.setSpacing(10); // Space between the name and status

        Label recipeNameLabel = new Label("Recipe: " + recipe.getName());
        recipeNameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");

        Map<String, String> feasibility = recipe.checkFeasibility(currentAvailableIngredients);

        Label statusLabel;
        if (feasibility.isEmpty()) {
            statusLabel = new Label("Status: Complete");
            statusLabel.setStyle("-fx-text-fill: green;");
        } else {
            statusLabel = new Label("Status: Incomplete");
            statusLabel.setStyle("-fx-text-fill: red;");
        }

        // Add Recipe Name and Status to the HBox
        headerBox.getChildren().addAll(recipeNameLabel, statusLabel);

        VBox detailsBox = new VBox();
        detailsBox.setSpacing(5);
        for (Map.Entry<String, String> entry : feasibility.entrySet()) {
            Label missingLabel = new Label(entry.getKey() + ": " + entry.getValue());
            missingLabel.setStyle("-fx-text-fill: #cd7f00;");
            detailsBox.getChildren().add(missingLabel);
        }

        // Add Header and Details to the Recipe Box
        recipeBox.getChildren().addAll(headerBox, detailsBox);

        return recipeBox;
    }
}
