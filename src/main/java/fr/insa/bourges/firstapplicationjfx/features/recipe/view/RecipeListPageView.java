package fr.insa.bourges.firstapplicationjfx.features.recipe.view;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractPageView;
import fr.insa.bourges.firstapplicationjfx.features.recipe.RecipeController;
import fr.insa.bourges.firstapplicationjfx.features.recipe.view.components.RecipeComponentView;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;


public class RecipeListPageView extends AbstractPageView<RecipeController> {
    @FXML
    public BorderPane borderPane;

    @FXML
    public TextField searchBox;

    @FXML
    public VBox recipeListContainer;

    @Override
    public void initializeScene() {
        this.setScene(new Scene(this.borderPane, 600, 400));
        loadRecipeComponentView();
    }

    public void loadRecipeComponentView() {
        List<Recipe> recipes = this.getController().getAllRecipeFromInventory();
        recipeListContainer.getChildren().clear();

        for (Recipe recipe : recipes) {
            HBox recipeComponent = createRecipeComponent(recipe);
            recipeListContainer.getChildren().add(recipeComponent);
        }
    }
    private HBox createRecipeComponent(Recipe recipe) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fr/insa/bourges/firstapplicationjfx/views/recipeComponent.fxml")
            );
            HBox recipeComponent = loader.load();

            RecipeComponentView recipeComponentView = loader.getController();
            recipeComponentView.setRecipe(recipe);
            recipeComponentView.registerCommand("reloadRecipe", args -> {
                this.loadRecipeComponentView();
            });

            return recipeComponent;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load recipe component", e);
        }
    }
    public void navigateToAddRecipe(ActionEvent actionEvent) {

    }
    public void searchRecipe(ActionEvent actionEvent) {

    }

}
