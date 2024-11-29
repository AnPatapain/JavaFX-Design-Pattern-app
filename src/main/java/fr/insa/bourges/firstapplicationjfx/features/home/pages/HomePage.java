package fr.insa.bourges.firstapplicationjfx.features.home.pages;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractPageView;
import fr.insa.bourges.firstapplicationjfx.features.home.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class HomePage extends AbstractPageView<HomeController> {
    @FXML
    BorderPane borderPane;

    public void initializeScene() {
        this.setScene(new Scene(borderPane, 600, 400));
    }

    public void navigateToRecipePage(ActionEvent event) {
        this.getController().navigateToRecipePage();
    }

    public void navigateToIngredientPage(ActionEvent event) {
        this.getController().navigateToIngredientPage();
    }
}