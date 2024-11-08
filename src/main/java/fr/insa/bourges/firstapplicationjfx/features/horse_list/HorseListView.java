package fr.insa.bourges.firstapplicationjfx.features.horse_list;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractView;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Horse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import java.util.List;

public class HorseListView extends AbstractView<HorseListController> {
    @FXML
    BorderPane borderPane;

    @FXML
    TextArea textArea;

    public void initializeScene() {
        this.setScene(new Scene(borderPane, 600, 400));
    }


    public void updateHorseToView(List<Horse> horses) {
        String horsesToString = horses.stream()
                .map(horse -> horse.getName() + " - " + +horse.getWeight())
                .reduce("", (a, b) -> a + b + "\n");
        this.textArea.setText(horsesToString);
    }

    public void gotomenu(ActionEvent actionEvent) {
        this.getController().navigateToHome();
    }

    public void createHorse(ActionEvent actionEvent) {
        this.getController().navigateToCreation();
    }
}
