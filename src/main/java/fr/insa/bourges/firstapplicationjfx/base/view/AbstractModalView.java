package fr.insa.bourges.firstapplicationjfx.base.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class AbstractModalView extends ComponentView {
    private Stage stage;

    protected AbstractModalView() {
    }

    // Will be called by createModal. This function is for init the logic kind of numeric formatter for input
    public abstract void initialize();

    public static <V extends AbstractModalView> V createModal(
            Class<V> modalView,
            String fxmlFile,
            String componentTitle
    ) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    modalView.getResource("/fr/insa/bourges/firstapplicationjfx/views/" + fxmlFile)
            );
            Parent root = loader.load();

            // Get the modal ingredientEditComponentView
            V loadedModalView = loader.getController();

            // Create a new stage for the modal
            Stage modalStage = new Stage();
            modalStage.setScene(new Scene(root));
            modalStage.initModality(javafx.stage.Modality.APPLICATION_MODAL); // Block interaction with other windows
            modalStage.setTitle(componentTitle);
            modalStage.setResizable(false);

            loadedModalView.setStage(modalStage);
            loadedModalView.initialize();
            return loadedModalView;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load FXML file for " + modalView.getSimpleName());
        }
    }

    public void showModalAndWait() {
        if (stage == null) {
            throw new IllegalStateException("Stage is not initialized");
        }
        stage.showAndWait();
    }

    public void closeModal() {
        if (stage != null) {
            stage.close();
        }
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
