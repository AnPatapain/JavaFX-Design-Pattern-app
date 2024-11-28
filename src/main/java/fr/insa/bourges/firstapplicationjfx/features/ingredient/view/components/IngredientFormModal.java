package fr.insa.bourges.firstapplicationjfx.features.ingredient.view.components;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractModalView;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.CommandKeys;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Ingredient;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.UnitMeasure;
import fr.insa.bourges.firstapplicationjfx.features.shared.utils.InputFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;


public class IngredientFormModal extends AbstractModalView {
    @FXML
    public Label modalLabel;

    @FXML
    public TextField name;

    @FXML
    public TextField quantity;

    @FXML
    public ComboBox<String> unit;

    @FXML
    public DatePicker addDate;

    @FXML
    public DatePicker expirationDate;


    private Ingredient ingredient;

    private IngredientFormType ingredientFormType;

    @Override
    public void initialize() {
        TextFormatter<String> numericFormatter = InputFormatter.getNumericInputFormatter();
        quantity.setTextFormatter(numericFormatter);
        addDate.setValue(LocalDate.now());
    }

    public void setModalLabel(String modalLabel) {
        this.modalLabel.setText(modalLabel);
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
        name.setText(ingredient.getName());
        quantity.setText(String.valueOf(ingredient.getQuantity()));
        unit.setValue(ingredient.getUnit().name());
        addDate.setValue(ingredient.getAddDate());
        expirationDate.setValue(ingredient.getExpirationDate());
    }

    public void saveIngredient(ActionEvent actionEvent) {
        if (this.ingredient == null) {
            this.ingredient = new Ingredient();
        }

        this.ingredient.setName(this.name.getText());
        this.ingredient.setQuantity(Double.parseDouble(quantity.getText()));
        this.ingredient.setUnit(UnitMeasure.valueOf(this.unit.getValue()));
        this.ingredient.setAddDate(addDate.getValue());
        this.ingredient.setExpirationDate(expirationDate.getValue());

        if (this.ingredientFormType == IngredientFormType.ADD) {
            this.executeCommand(CommandKeys.ADD_INGREDIENT.name(), this.ingredient);
        } else if (this.ingredientFormType == IngredientFormType.EDIT) {
            this.executeCommand(CommandKeys.UPDATE_INGREDIENT.name(), this.ingredient);
        }

        this.closeModal();
    }

    public void cancelEdit(ActionEvent actionEvent) {
        // Close the modal without saving
        this.closeModal();
    }

    public IngredientFormType getIngredientFormType() {
        return ingredientFormType;
    }

    public void setIngredientFormType(IngredientFormType ingredientFormType) {
        this.ingredientFormType = ingredientFormType;
    }
}
