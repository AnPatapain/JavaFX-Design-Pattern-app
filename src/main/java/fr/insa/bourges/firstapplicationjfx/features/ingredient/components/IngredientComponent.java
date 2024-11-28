package fr.insa.bourges.firstapplicationjfx.features.ingredient.components;

import fr.insa.bourges.firstapplicationjfx.base.controller.ControllerMediator;
import fr.insa.bourges.firstapplicationjfx.base.view.ComponentView;
import fr.insa.bourges.firstapplicationjfx.base.view.AbstractModalView;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.CommandKeys;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.IngredientController;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Ingredient;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class IngredientComponent extends ComponentView {
    @FXML
    public Label ingredientName;
    @FXML
    public Label ingredientQuantity;
    @FXML
    public Label ingredientUnit;
    @FXML
    public Label ingredientAddDate;
    @FXML
    public Label ingredientExpirationDate;

    private Ingredient ingredient;

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
        ingredientName.setText("Name: " + ingredient.getName());
        ingredientQuantity.setText("Quantity: " + ingredient.getQuantity());
        ingredientUnit.setText("Unit: " + ingredient.getUnit());
        ingredientAddDate.setText("Add Date: " + ingredient.getAddDate());
        ingredientExpirationDate.setText("Expiration Date: " + ingredient.getExpirationDate());
    }

    @FXML
    private void onEditButtonClick() {
        // Create modal for editing ingredient
        IngredientFormModal ingredientFormModal = AbstractModalView.createModal(
                IngredientFormModal.class,
                "ingredientFormModal.fxml",
                "Edit ingredient"
        );

        ingredientFormModal.setModalLabel("Edit ingredient");
        ingredientFormModal.setIngredientFormType(IngredientFormType.EDIT);
        ingredientFormModal.setIngredient(this.ingredient);
        ingredientFormModal.setParentPageView(this.getParentPageView());

        // Register a callback for modal, modal will call after the "save button" is clicked. Command pattern is used
        ingredientFormModal.registerCommand(CommandKeys.UPDATE_INGREDIENT.name(), args -> {
            Ingredient updatedIngredient = (Ingredient) args[0];
            ControllerMediator.getInstance().getControllersByType(IngredientController.class).updateIngredient(updatedIngredient);
        });

        ingredientFormModal.showModalAndWait();

        this.executeCommand(CommandKeys.RELOAD_INGREDIENTS.name());
    }

    @FXML
    private void onDeleteButtonClick() {
        this.executeCommand(CommandKeys.DELETE_INGREDIENT.name(), this.ingredient.getId());
    }
}
