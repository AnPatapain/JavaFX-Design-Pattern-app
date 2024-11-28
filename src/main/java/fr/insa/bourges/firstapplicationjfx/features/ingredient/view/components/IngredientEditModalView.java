package fr.insa.bourges.firstapplicationjfx.features.ingredient.view.components;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractModalView;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Ingredient;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.UnitMeasure;
import fr.insa.bourges.firstapplicationjfx.features.shared.utils.InputFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;


public class IngredientEditModalView extends AbstractModalView {
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

    @Override
    public void initialize() {
        TextFormatter<String> numericFormatter = InputFormatter.getNumericInputFormatter();
        quantity.setTextFormatter(numericFormatter);
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
        this.ingredient.setName(this.name.getText());
        this.ingredient.setQuantity(Double.parseDouble(quantity.getText()));
        this.ingredient.setUnit(UnitMeasure.valueOf(this.unit.getValue()));
        this.ingredient.setAddDate(addDate.getValue());
        this.ingredient.setExpirationDate(expirationDate.getValue());

        this.executeCommand("updateIngredient", this.ingredient);

        this.closeModal();
    }

    public void cancelEdit(ActionEvent actionEvent) {
        // Close the modal without saving
        this.closeModal();
    }
}
