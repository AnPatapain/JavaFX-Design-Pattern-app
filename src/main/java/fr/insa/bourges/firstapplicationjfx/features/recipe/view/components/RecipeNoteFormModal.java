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
