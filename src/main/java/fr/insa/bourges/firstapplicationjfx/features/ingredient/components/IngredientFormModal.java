package fr.insa.bourges.firstapplicationjfx.features.ingredient.components;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractModalView;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.IngredientCommandKeys;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.exceptions.InvalidNumberFormatException;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.exceptions.InvalidUnitMeasureException;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.CategoryIngredient;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Ingredient;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.UnitMeasure;
import fr.insa.bourges.firstapplicationjfx.features.shared.utils.CustomUIAlert;
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

    @FXML
    public ComboBox<String> category;

    private Ingredient ingredient;

    private IngredientFormType ingredientFormType;

    @Override
    public void initialize() {
        TextFormatter<String> numericFormatter = InputFormatter.getNumericInputFormatter();
        quantity.setTextFormatter(numericFormatter);
        addDate.setValue(LocalDate.now());
    }

    @FXML
    private void onSaveButtonClickHandler(ActionEvent actionEvent) {
        try {
            if (this.ingredient == null) {
                this.ingredient = new Ingredient();
            }

            if (this.name.getText().isBlank() || this.quantity.getText().isBlank() ||
                    this.unit.getValue() == null || this.unit.getValue().isBlank() ||
                    this.category.getValue() == null || this.category.getValue().isBlank()
            ) {
                CustomUIAlert.showAlert("Error", "Please fill all fields!");
                return;
            }

            this.populateIngredientFromForm();

            if (this.ingredientFormType == IngredientFormType.ADD) {
                this.executeCommand(IngredientCommandKeys.ADD_INGREDIENT.name(), this.ingredient);
            } else if (this.ingredientFormType == IngredientFormType.EDIT) {
                this.executeCommand(IngredientCommandKeys.UPDATE_INGREDIENT.name(), this.ingredient);
            }

            this.closeModal();
        } catch (InvalidNumberFormatException | InvalidUnitMeasureException e) {
            showAlert("Input Error", e.getMessage());
        } catch (Exception e) {
            // Log unexpected errors
            e.printStackTrace();
            showAlert("Unexpected Error", "An unexpected error occurred. Please try again.");
        }
    }

    @FXML
    private void onCancelButtonClickHandler(ActionEvent actionEvent) {
        // Close the modal without saving
        this.closeModal();
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
        category.setValue(ingredient.getCategoryIngredient().name());
    }

    public void setIngredientFormType(IngredientFormType ingredientFormType) {
        this.ingredientFormType = ingredientFormType;
    }

    private void populateIngredientFromForm() {
        try {
            this.ingredient.setName(this.name.getText());
            this.ingredient.setQuantity(Double.parseDouble(this.quantity.getText()));
            this.ingredient.setUnit(UnitMeasure.valueOf(this.unit.getValue()));
            this.ingredient.setAddDate(addDate.getValue());
            this.ingredient.setExpirationDate(expirationDate.getValue());
            this.ingredient.setCategoryIngredient(CategoryIngredient.valueOf(this.category.getValue()));
        } catch (NumberFormatException e) {
            throw new InvalidNumberFormatException("Quantity must be a valid number.");
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InvalidUnitMeasureException("Invalid unit of measurement selected.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
