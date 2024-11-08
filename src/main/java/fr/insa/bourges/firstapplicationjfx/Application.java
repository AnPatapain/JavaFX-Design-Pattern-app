package fr.insa.bourges.firstapplicationjfx;

import fr.insa.bourges.firstapplicationjfx.base.controller.ControllerMediator;
import fr.insa.bourges.firstapplicationjfx.base.view.AbstractView;
import fr.insa.bourges.firstapplicationjfx.base.view.RenderViewManager;
import fr.insa.bourges.firstapplicationjfx.base.event.EventDispatcher;
import fr.insa.bourges.firstapplicationjfx.base.event.EventType;
import fr.insa.bourges.firstapplicationjfx.features.creation.CreationController;
import fr.insa.bourges.firstapplicationjfx.features.creation.CreationView;
import fr.insa.bourges.firstapplicationjfx.features.home.HomeController;
import fr.insa.bourges.firstapplicationjfx.features.home.HomeView;
import fr.insa.bourges.firstapplicationjfx.features.horse_list.HorseListController;
import fr.insa.bourges.firstapplicationjfx.features.horse_list.HorseListView;
import fr.insa.bourges.firstapplicationjfx.features.shared.services.HorseServiceImpl;
import fr.insa.bourges.firstapplicationjfx.features.shared.services.IHorseService;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        /**
         * Close part
         * The app features will EXTEND the close part but NEVER MODIFY them. If we need to modify
         * the close part that means our design was wrong at the beginning.
         */
        EventDispatcher controllerMediator = new ControllerMediator();
        RenderViewManager renderViewManager = new RenderViewManager(stage);
        /**
         * Open part
         * These are the feature that will be added over time.
         */
        // HorseService
        IHorseService horseService = new HorseServiceImpl();

        // Home page
        HomeController homeController = new HomeController(controllerMediator, renderViewManager);
        HomeView homeView = AbstractView.createView(HomeView.class, "home.fxml", homeController);
        homeController.setView(homeView);

        // Creation page
        CreationController creationController = new CreationController(controllerMediator, renderViewManager);
        CreationView creationView = AbstractView.createView(CreationView.class, "creation.fxml", creationController);
        creationController.setView(creationView);
        creationController.setHorseService(horseService);

        // HorseList page
        HorseListController horseListController = new HorseListController(controllerMediator, renderViewManager);
        HorseListView horseListView = AbstractView.createView(HorseListView.class, "horselist.fxml", horseListController);
        horseListController.setView(horseListView);
        horseListController.setHorseService(horseService);

        /**
         * This is the event to show the entry page of application
         */
        controllerMediator.dispatchEvent(EventType.SHOW_HOME);
    }

    public static void main(String[] args) {
        launch();
    }
}