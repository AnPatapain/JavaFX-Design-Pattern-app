package fr.insa.bourges.firstapplicationjfx.features.ingredient.view.components;

import fr.insa.bourges.firstapplicationjfx.base.controller.ControllerMediator;
import fr.insa.bourges.firstapplicationjfx.base.view.ComponentView;
import fr.insa.bourges.firstapplicationjfx.base.view.AbstractModalView;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.IngredientController;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Ingredient;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class IngredientComponentView extends ComponentView {
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
    private void showIngredientEditModal() {
        IngredientEditModalView ingredientEditModalView = AbstractModalView.createModal(
                IngredientEditModalView.class,
                "ingredientEditComponent.fxml",
                "Edit ingredient"
        );

        ingredientEditModalView.setIngredient(this.ingredient);
        ingredientEditModalView.setParentPageView(this.getParentPageView());
        ingredientEditModalView.registerCommand("updateIngredient", args -> {
            Ingredient updatedIngredient = (Ingredient) args[0];
            ControllerMediator.getInstance().getControllersByType(IngredientController.class).updateIngredient(updatedIngredient);
        });

        ingredientEditModalView.showModalAndWait();

        this.executeCommand("reloadIngredient");
    }

    @FXML
    private void deleteIngredient() {
//        ingredientListView.getController().deleteIngredient(ingredient);
//        ingredientListView.loadAllIngredients();
    }
}
