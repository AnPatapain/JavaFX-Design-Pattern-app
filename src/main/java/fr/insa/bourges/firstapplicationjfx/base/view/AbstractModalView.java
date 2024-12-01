/**
 * AbstractModalView provides a base class for creating reusable modal windows in the application.
 * It handles modal initialization, display, and lifecycle management, ensuring a standardized
 * approach to building and managing modal views.

 * Responsibilities:
 * - Initializes modals using `createModal`, linking the view to its FXML file and title.
 * - Manages modal lifecycle:
 *   - Opens the modal (`showModalAndWait`) with a blocking interaction mode.
 *   - Closes the modal (`closeModal`).
 * - Ensures custom modal behavior via the `initialize` method for additional logic setup.

 * Design:
 * - Enforces standardized modal creation with `createModal` to ensure consistency.
 * - Extends `ComponentView` to enable command-based interaction with the modal.

 * Example:
 * RecipeNoteFormModal modal = AbstractModalView.createModal(
 *     RecipeNoteFormModal.class,
 *     "recipeNoteFormModal.fxml",
 *     "Edit Recipe Notes"
 * );
 * modal.showModalAndWait();

 * Author: Ke An NGUYEN
 */


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
            String modalTitle
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
            modalStage.setTitle(modalTitle);
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
