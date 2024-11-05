package fr.insa.bourges.firstapplicationjfx.views;

import fr.insa.bourges.firstapplicationjfx.controllers.Controller;
import fr.insa.bourges.firstapplicationjfx.controllers.EventDispatcher;
import fr.insa.bourges.firstapplicationjfx.controllers.EventListener;
import fr.insa.bourges.firstapplicationjfx.controllers.EventType;
import fr.insa.bourges.firstapplicationjfx.models.Horse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.List;

public class HorseList implements ViewInteractive, EventListener {
    @FXML
    BorderPane borderPane;

    @FXML
    TextArea textArea;

    private Controller controller;
    private Scene scene;

    public static HorseList create() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HorseList.class.getResource("horselist.fxml"));
        fxmlLoader.load();
        HorseList horseListView = fxmlLoader.getController();
        horseListView.initializeScene();
        return horseListView;
    }

    public Scene getScene() {
        return scene;
    }

    private void initializeScene() {
        this.scene = new Scene(borderPane, 600, 400);
    }

    @Override
    public Controller getController() {
        return controller;
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void setSubscription(EventDispatcher eventDispatcher) {
        eventDispatcher.subscribe(
                this, EventType.DATA_LOAD, EventType.DATA_HORSE_CREATED
        );
    }

    @Override
    public void handleEvent(EventType eventType) {
        if (eventType == EventType.DATA_LOAD || eventType == EventType.DATA_HORSE_CREATED) {
            List<Horse> horses = this.controller.getHorses();
            String horsesToString = horses.stream()
                    .map(horse -> horse.getName() + " - " + + horse.getWeight())
                    .reduce("", (a, b) -> a + b + "\n");
            this.textArea.setText(horsesToString);
        }
    }

    public void gotomenu(ActionEvent actionEvent) {
        this.controller.gotoMenu();
    }
}
