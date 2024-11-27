package fr.insa.bourges.firstapplicationjfx.features.ingredient;

import fr.insa.bourges.firstapplicationjfx.features.shared.models.Ingredient;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class IngredientComponentView {
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
    private IngredientListView ingredientListView;

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
        ingredientName.setText("Name: " + ingredient.getName());
        ingredientQuantity.setText("Quantity: " + ingredient.getQuantity());
        ingredientUnit.setText("Unit: " + ingredient.getUnit());
        ingredientAddDate.setText("Add Date: " + ingredient.getAddDate());
        ingredientExpirationDate.setText("Expiration Date: " + ingredient.getExpirationDate());
    }

    public void setIngredientListView(IngredientListView ingredientListView) {
        this.ingredientListView = ingredientListView;
    }

    @FXML
    private void modifyIngredient() {
//        ingredientListView.getController().navigateToModifyIngredient(ingredient);
    }

    @FXML
    private void deleteIngredient() {
//        ingredientListView.getController().deleteIngredient(ingredient);
//        ingredientListView.loadAllIngredients();
    }
}
