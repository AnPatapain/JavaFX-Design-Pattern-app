package fr.insa.bourges.firstapplicationjfx;

import fr.insa.bourges.firstapplicationjfx.controllers.Controller;
import fr.insa.bourges.firstapplicationjfx.facades.FacadeHorseManager;
import fr.insa.bourges.firstapplicationjfx.facades.FacadeHorseManagerImpl;
import fr.insa.bourges.firstapplicationjfx.views.ViewManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        ViewManager viewManager = new ViewManager(stage);
        FacadeHorseManager facadeHorseManager = new FacadeHorseManagerImpl();
        Controller controller = new Controller(viewManager, facadeHorseManager);
        controller.run();
    }

    public static void main(String[] args) {
        launch();
    }
}