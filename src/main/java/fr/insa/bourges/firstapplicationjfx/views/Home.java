package fr.insa.bourges.firstapplicationjfx.views;

import fr.insa.bourges.firstapplicationjfx.controllers.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Home implements ViewInteractive {
    @FXML
    BorderPane borderPane;
    Scene scene;

    private Controller controller;

    public static Home create() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("home.fxml"));
        fxmlLoader.load();
        Home homeView = fxmlLoader.getController();
        homeView.initializeScene();
        return homeView;
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

    public void gotocreation(ActionEvent actionEvent) {
        this.controller.gotoCreation();
    }
}
