/**
 * AbstractPageView provides a base class for all page views in the application.
 * It manages the association between a view and its controller, while also
 * handling the initialization of the JavaFX scene from an FXML file.

 * Responsibilities:
 * - Manages the lifecycle of a page view, including its initialization and scene setup.
 * - Associates each view with a specific controller for interaction and logic handling.
 * - Provides utility to create views dynamically using `createView`.

 * Design:
 * - Enforces the use of `createView` to standardize the initialization process.
 * - Promotes loose coupling between views and controllers by generic association.

 * Example:
 * HomePage homeView = AbstractPageView.createView(HomePage.class, "home.fxml", homeController);
 * renderViewManager.renderView(homeView);

 * Author: Ke An NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.base.view;

import fr.insa.bourges.firstapplicationjfx.base.controller.AbstractController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public abstract class AbstractPageView<C extends AbstractController<?>> {
    private C controller;
    private Scene scene;
    private static boolean isDuringInitialization = false;

    protected AbstractPageView() {
        if (!isDuringInitialization) {
            throw new RuntimeException("Please initialize View using AbstractPageView.createView(Class<T> viewClass, String fxmlFile)");
        }
    }
    public static <V extends AbstractPageView<C>, C extends AbstractController<?>> V createView(Class<V> viewClass, String fxmlFile, C controller) {
        try {
            isDuringInitialization = true;
            String fxmlPath = "/fr/insa/bourges/firstapplicationjfx/views/" + fxmlFile;
            FXMLLoader fxmlLoader = new FXMLLoader(viewClass.getResource(fxmlPath));
            fxmlLoader.load();
            V view = fxmlLoader.getController();
            view.setController(controller);
            view.initializeScene();
            return view;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load FXML file for " + viewClass.getSimpleName());
        } finally {
            isDuringInitialization = false;
        }
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public abstract void initializeScene();

    public C getController() {
        return this.controller;
    }

    public void setController(C controller) {
        this.controller = controller;
    }
}
