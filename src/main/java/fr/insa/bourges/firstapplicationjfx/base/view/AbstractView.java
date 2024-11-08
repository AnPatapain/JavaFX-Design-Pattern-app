package fr.insa.bourges.firstapplicationjfx.base.view;

import fr.insa.bourges.firstapplicationjfx.base.controller.AbstractController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public abstract class AbstractView<C extends AbstractController<?>> {
    private C controller;
    private Scene scene;
    private static boolean isDuringInitialization = false;

    protected AbstractView() {
        if (!isDuringInitialization) {
            throw new RuntimeException("Please initialize View using AbstractView.createView(Class<T> viewClass, String fxmlFile)");
        }
    }
    public static <V extends AbstractView<C>, C extends AbstractController<V>> V createView(Class<V> viewClass, String fxmlFile, C controller) {
        try {
            isDuringInitialization = true;
            String fxmlPath = "/fr/insa/bourges/firstapplicationjfx/views/" + fxmlFile;
            FXMLLoader fxmlLoader = new FXMLLoader(viewClass.getResource(fxmlPath));
            fxmlLoader.load();
            V view = fxmlLoader.getController();
            view.initializeScene();
            view.setController(controller);
            return view;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load FXML file for " + viewClass.getName());
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
