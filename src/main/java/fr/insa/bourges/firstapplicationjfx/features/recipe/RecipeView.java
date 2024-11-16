package fr.insa.bourges.firstapplicationjfx.features.recipe;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class RecipeView extends AbstractView<RecipeController> {
    @FXML
    public BorderPane borderPane;

    @Override
    public void initializeScene() {
        this.setScene(new Scene(borderPane, 600, 400));
    }

    public void navigateToHomePage(ActionEvent event) {
        this.getController().navigateToHomePage();
    }
}
