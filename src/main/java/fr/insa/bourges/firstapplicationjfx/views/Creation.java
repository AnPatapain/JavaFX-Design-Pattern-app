package fr.insa.bourges.firstapplicationjfx.views;

import fr.insa.bourges.firstapplicationjfx.controllers.Controller;
import fr.insa.bourges.firstapplicationjfx.controllers.EventDispatcher;
import fr.insa.bourges.firstapplicationjfx.controllers.EventListener;
import fr.insa.bourges.firstapplicationjfx.controllers.EventType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Creation implements ViewInteractive, EventListener {
    @FXML
    private BorderPane borderPane;
    @FXML
    public TextField name;
    @FXML
    public TextField weight;

    private Controller controller;

    private Scene scene;


    public static Creation create() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Creation.class.getResource("creation.fxml"));
        fxmlLoader.load();
        Creation creationView = fxmlLoader.getController();
        creationView.initializeScene();
        return creationView;
    }

    public Scene getScene() {
        return scene;
    }

    public void initializeScene() {
        this.scene = new Scene(borderPane, 600, 400);
    }

    void printConfirmation(String titre,String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,titre, ButtonType.OK);
        alert.setContentText(message);
        alert.showAndWait();
    }

    void printError(String titre,String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR,titre, ButtonType.OK);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public Controller getController() {
        return controller;
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void createHorse(ActionEvent actionEvent) {
        this.controller.createHorse(
                this.name.getText(),
                Integer.parseInt(this.weight.getText())
        );
    }

    @Override
    public void setSubscription(EventDispatcher eventDispatcher) {
        eventDispatcher.subscribe(this,
                EventType.DATA_HORSE_CREATED,
                EventType.ERROR_NAME_MISSING,
                EventType.ERROR_HORSE_NAME_CONFLICT,
                EventType.ERROR_INCONSISTENT_WEIGHT
        );
    }

    @Override
    public void handleEvent(EventType eventType) {
        switch (eventType) {
            case DATA_HORSE_CREATED:
                this.printConfirmation("Created", "Horse was created");
                break;
        }
    }
}
