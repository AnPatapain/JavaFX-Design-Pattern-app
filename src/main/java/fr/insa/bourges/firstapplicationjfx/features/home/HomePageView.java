package fr.insa.bourges.firstapplicationjfx.features.home;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractPageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class HomePageView extends AbstractPageView<HomeController> {
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
