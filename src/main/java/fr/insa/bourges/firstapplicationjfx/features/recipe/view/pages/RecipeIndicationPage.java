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

public class RecipeIndicationPage extends AbstractPageView<RecipeController> {
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

        System.out.println("inventory: " + inventory);

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
