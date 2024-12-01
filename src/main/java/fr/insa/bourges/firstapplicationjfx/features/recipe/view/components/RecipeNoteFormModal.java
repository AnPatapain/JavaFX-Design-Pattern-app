/**
 * Class RecipeNoteFormModal
 * Represents a modal dialog for editing and saving personal notes associated with a recipe.

 * Key Features:
 * - **Personal Notes**:
 *   - Allows users to view, edit, and save personal notes for a recipe.
 * - **Modal Behavior**:
 *   - Blocks interaction with other windows until the modal is closed.

 * Workflow:
 * - The modal displays the current note of a recipe when `setRecipe` is called.
 * - Users can update the note and save it, triggering the `UPDATE_NOTE_RECIPE` command.

 * Methods:
 * - **setRecipe(Recipe recipe)**:
 *   - Sets the recipe and populates the note field with the recipe's current note.
 * - **initialize()**:
 *   - No additional initialization required.
 * - **onSaveButtonClickHandler(ActionEvent actionEvent)**:
 *   - Saves the updated note to the recipe and executes the `UPDATE_NOTE_RECIPE` command.
 * - **onCancelButtonClickHandler(ActionEvent actionEvent)**:
 *   - Closes the modal without saving changes.

 * Dependencies:
 * - **Recipe**: The recipe model containing the personal note.
 * - **RecipeCommandKeys**: Enum for commands, specifically `UPDATE_NOTE_RECIPE`.

 * Author: Anh Tuan NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.features.recipe.view.components;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractModalView;
import fr.insa.bourges.firstapplicationjfx.features.recipe.RecipeCommandKeys;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Recipe;
import fr.insa.bourges.firstapplicationjfx.features.shared.utils.CustomUIAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


public class RecipeNoteFormModal extends AbstractModalView {

    @FXML
    public TextArea noteArea;

    private Recipe recipe;

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        noteArea.setText(recipe.getPersonalNote());
    }

    @Override
    public void initialize() {
        // Nothing to do
    }
    @FXML
    private void onSaveButtonClickHandler(ActionEvent actionEvent) {
        if(this.recipe == null) {
            CustomUIAlert.showAlert("Error", "Recipe is not set");
            return;
        }
        this.recipe.setPersonalNote(noteArea.getText());
        this.executeCommand(RecipeCommandKeys.UPDATE_NOTE_RECIPE.name(), this.recipe);
        this.closeModal();
    }
    @FXML
    private void onCancelButtonClickHandler(ActionEvent actionEvent) {
        this.closeModal();
    }

}
