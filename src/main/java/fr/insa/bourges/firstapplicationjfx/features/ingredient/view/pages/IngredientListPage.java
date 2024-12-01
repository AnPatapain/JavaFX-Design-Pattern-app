/**
 * IngredientListPage represents the view for displaying and managing a list of ingredients.
 * It provides functionality for adding, searching, and deleting ingredients through interaction
 * with the UI and the associated controller.

 * Responsibilities:
 * - Displays the list of ingredients dynamically using `loadIngredientComponentView`.
 * - Handles user interactions:
 *   - Adding ingredients via a modal (`onAddButtonClickHandler`).
 *   - Searching ingredients using a search box (`onSearchHandler`).
 *   - Navigating back to the home page (`onBackToHomePageLinkClickHandler`).
 * - Integrates with `IngredientComponent` for individual ingredient management.

 * Design:
 * - Extends `AbstractPageView` to manage the association with the `IngredientController`.
 * - Implements the Command pattern for decoupled interaction between components and logic.
 * - Utilizes dynamic loading of UI components for flexibility and modularity.

 * Example:
 * - Adding a new ingredient:
 *   - Opens a modal for ingredient input.
 *   - Registers a callback command to handle the save action.
 *   - Refreshes the ingredient list after the modal is closed.

 * Author: Ke An NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.features.ingredient.view.pages;

import fr.insa.bourges.firstapplicationjfx.base.controller.ControllerMediator;
import fr.insa.bourges.firstapplicationjfx.base.view.AbstractModalView;
import fr.insa.bourges.firstapplicationjfx.base.view.AbstractPageView;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.IngredientCommandKeys;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.IngredientController;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.view.components.IngredientComponent;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.view.components.IngredientFormModal;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.view.components.IngredientFormType;
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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IngredientListPage extends AbstractPageView<IngredientController> {
    @FXML
    public BorderPane borderPane;
    @FXML
    public TextField searchBox;
    @FXML
    public VBox ingredientListContainer;

    @Override
    public void initializeScene() {
        this.setScene(new Scene(this.borderPane, 800, 600));
        loadIngredientComponentView();
    }

    public void loadIngredientComponentView() {
        List<Ingredient> ingredients = this.getController().getAllIngredients();
        ingredientListContainer.getChildren().clear();

        for (Ingredient ingredient : ingredients) {
            HBox ingredientComponent = createIngredientComponent(ingredient);
            ingredientListContainer.getChildren().add(ingredientComponent);
        }
    }

    public void loadIngredientComponentView(List<Ingredient> ingredients) {
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
            IngredientComponent ingredientComponent = loader.getController();

            ingredientComponent.setIngredient(ingredient);

            ingredientComponent.setParentPageView(this);

            ingredientComponent.registerCommand(IngredientCommandKeys.RELOAD_INGREDIENTS.name(), args -> {
                this.loadIngredientComponentView();
            });
            ingredientComponent.registerCommand(IngredientCommandKeys.DELETE_INGREDIENT.name(), args -> {
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
        // Create modal for adding ingredient
        IngredientFormModal ingredientFormModal = AbstractModalView.createModal(
                IngredientFormModal.class,
                "ingredientFormModal.fxml",
                "Add ingredient"
        );

        ingredientFormModal.setModalLabel("Add ingredient");
        ingredientFormModal.setIngredientFormType(IngredientFormType.ADD);
        ingredientFormModal.setParentPageView(this);

        // Register a callback for modal, modal will call after the "save button" is clicked. Command pattern is used
        ingredientFormModal.registerCommand(IngredientCommandKeys.ADD_INGREDIENT.name(), args -> {
            Ingredient addedIngredient = (Ingredient) args[0];
            ControllerMediator.getInstance().getControllersByType(IngredientController.class).addIngredient(addedIngredient);
        });

        ingredientFormModal.showModalAndWait();

        this.loadIngredientComponentView();
    }

    @FXML
    private void onSearchHandler(ActionEvent actionEvent) {
        List<Ingredient> allIngredients = this.getController().getAllIngredients();
        Pattern pattern = Pattern.compile(this.searchBox.getText(), Pattern.CASE_INSENSITIVE);

        List<Ingredient> ingredientsResult = new ArrayList<>();

        for(Ingredient ingredient : allIngredients) {
            Matcher matcher = pattern.matcher(ingredient.getName());
            if (matcher.find()) {
                ingredientsResult.add(ingredient);
            }
        }

        this.loadIngredientComponentView(ingredientsResult);
    }

    @FXML
    private void onBackToHomePageLinkClickHandler(ActionEvent actionEvent) {
        this.getController().navigateToHomePage();
    }
}
