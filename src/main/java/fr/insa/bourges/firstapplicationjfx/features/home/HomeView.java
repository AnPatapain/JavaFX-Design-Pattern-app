package fr.insa.bourges.firstapplicationjfx.features.home;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class HomeView extends AbstractView<HomeController> {
    @FXML
    BorderPane borderPane;

    public void initializeScene() {
        this.setScene(new Scene(borderPane, 600, 400));
    }


    public void gotocreation(ActionEvent actionEvent) {
        this.getController().navigateToCreation();
    }

    public void gotohorselist(ActionEvent actionEvent) {
        this.getController().navigateToHorseList();
    }
}
