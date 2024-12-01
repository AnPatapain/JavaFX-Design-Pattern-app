/**
 * IngredientComponent represents a UI component for displaying and managing an individual ingredient.
 * It integrates with the Command pattern to delegate actions such as editing and deleting ingredients.

 * Responsibilities:
 * - Displays ingredient details such as name, quantity, unit, dates, and category.
 * - Handles user actions:
 *   - Editing an ingredient via a modal dialog.
 *   - Deleting an ingredient with command execution.
 * - Refreshes the ingredient list after updates or deletions using the `RELOAD_INGREDIENTS` command.

 * Design:
 * - Extends `ComponentView` for command-based interaction.
 * - Utilizes `IngredientFormModal` for editing, leveraging the modal’s ability to execute commands on save.

 * Example Workflow:
 * - Editing an ingredient:
 *   - Opens an edit modal pre-filled with ingredient details.
 *   - Updates the ingredient in the repository upon modal save.
 *   - Refreshes the ingredient list via the `RELOAD_INGREDIENTS` command.

 * Author: Ke An NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.features.ingredient.view.components;

import fr.insa.bourges.firstapplicationjfx.base.controller.ControllerMediator;
import fr.insa.bourges.firstapplicationjfx.base.view.ComponentView;
import fr.insa.bourges.firstapplicationjfx.base.view.AbstractModalView;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.IngredientCommandKeys;
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
    @FXML
    public Label ingredientCategory;

    private Ingredient ingredient;

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
        ingredientName.setText("Name: " + ingredient.getName());
        ingredientQuantity.setText("Quantity: " + ingredient.getQuantity());
        ingredientUnit.setText("Unit: " + ingredient.getUnit());
        ingredientAddDate.setText("Add Date: " + ingredient.getAddDate());
        ingredientExpirationDate.setText("Expiration Date: " + ingredient.getExpirationDate());
        ingredientCategory.setText("Category: " + ingredient.getCategoryIngredient());
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
        ingredientFormModal.registerCommand(IngredientCommandKeys.UPDATE_INGREDIENT.name(), args -> {
            Ingredient updatedIngredient = (Ingredient) args[0];
            ControllerMediator.getInstance().getControllersByType(IngredientController.class).updateIngredient(updatedIngredient);
        });

        ingredientFormModal.showModalAndWait();

        this.executeCommand(IngredientCommandKeys.RELOAD_INGREDIENTS.name());
    }

    @FXML
    private void onDeleteButtonClick() {
        this.executeCommand(IngredientCommandKeys.DELETE_INGREDIENT.name(), this.ingredient.getId());
    }
}
