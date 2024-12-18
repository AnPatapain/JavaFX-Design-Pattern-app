/**
 * HomePage is a concrete page that extends the AbstractPageView, represents the main entry point of
 * the application, serving as a navigation hub to different features such as recipes, ingredients
 * and recipe indications.

 * Responsibilities:
 * - Initializes the main scene with a layout defined in the FXML file.
 * - Handles user interactions to navigate to specific feature pages:
 *   - Navigate to the recipe page.
 *   - Navigate to the ingredient page.
 *   - Navigate to the recipe indication page.

 * Author: Ke An NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.features.home.view.pages;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractPageView;
import fr.insa.bourges.firstapplicationjfx.features.home.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class HomePage extends AbstractPageView<HomeController> {
    @FXML
    BorderPane borderPane;

    @Override
    public void initializeScene() {
        this.setScene(new Scene(borderPane, 800, 600));
    }

    @FXML
    private void navigateToRecipePage(ActionEvent event) {
        this.getController().navigateToRecipePage();
    }

    @FXML
    private void navigateToIngredientPage(ActionEvent event) {
        this.getController().navigateToIngredientPage();
    }

    @FXML
    private void navigateToRecipeIndicationPage(ActionEvent actionEvent) {
        this.getController().navigateToRecipeIndicationPage();
    }
}
