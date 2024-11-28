package fr.insa.bourges.firstapplicationjfx.features.ingredient.view.pages;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractPageView;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.CommandKeys;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.IngredientController;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.view.components.IngredientComponentView;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Ingredient;
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

public class IngredientListPageView extends AbstractPageView<IngredientController> {
    @FXML
    public BorderPane borderPane;
    @FXML
    public TextField searchBox;
    @FXML
    public VBox ingredientListContainer;

    @Override
    public void initializeScene() {
        this.setScene(new Scene(this.borderPane, 600, 400));
        loadIngredientComponentView();
    }

    public void loadIngredientComponentView() {
        List<Ingredient> ingredients = this.getController().getAllIngredientFromInventory();
        ingredientListContainer.getChildren().clear();

        for (Ingredient ingredient : ingredients) {
            HBox ingredientComponent = createIngredientComponent(ingredient);
            ingredientListContainer.getChildren().add(ingredientComponent);
        }
    }

    private HBox createIngredientComponent(Ingredient ingredient) {
        try {
            // Load the UI of component
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fr/insa/bourges/firstapplicationjfx/views/ingredientComponent.fxml")
            );
            HBox ingredientComponentUI = loader.load();

            // Get the representation view for component UI
            IngredientComponentView ingredientComponentView = loader.getController();

            ingredientComponentView.setIngredient(ingredient);

            ingredientComponentView.setParentPageView(this);

            ingredientComponentView.registerCommand(CommandKeys.RELOAD_INGREDIENTS.name(), args -> {
                this.loadIngredientComponentView();
            });
            ingredientComponentView.registerCommand(CommandKeys.DELETE_INGREDIENT.name(), args -> {
                String toBeDeletedIngredientId = (String) args[0];
                this.getController().deleteIngredient(toBeDeletedIngredientId);
                this.loadIngredientComponentView();
            });

            // Return component UI
            return ingredientComponentUI;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load ingredient component", e);
        }
    }

    @FXML
    private void onAddButtonClickHandler(ActionEvent actionEvent) {
        this.getController().navigateToAddIngredientPage();
    }

    @FXML
    private void onSearchHandler(ActionEvent actionEvent) {
    }

    @FXML
    private void onBackToHomePageLinkClickHandler(ActionEvent actionEvent) {
        this.getController().navigateToHomePage();
    }
}
