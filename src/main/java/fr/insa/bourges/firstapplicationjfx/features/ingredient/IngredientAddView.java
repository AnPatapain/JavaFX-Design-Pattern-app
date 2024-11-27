package fr.insa.bourges.firstapplicationjfx.features.ingredient;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractView;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Ingredient;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.UnitMeasure;
import fr.insa.bourges.firstapplicationjfx.features.shared.utils.InputFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.time.LocalDate;

public class IngredientAddView extends AbstractView<IngredientController> {
    @FXML
    public BorderPane borderPane;
    @FXML
    public TextField name;
    @FXML
    public ComboBox<String> unit;
    @FXML
    public TextField quantity;
    @FXML
    public DatePicker addDate;
    @FXML
    public DatePicker expirationDate;


    @Override
    public void initializeScene() {
        this.setScene(new Scene(this.borderPane, 600, 400));
        // Set TextFormatter for numeric-only input in the 'unit' TextField
        TextFormatter<String> numericFormatter = InputFormatter.getNumericInputFormatter();
        quantity.setTextFormatter(numericFormatter);

        // addDate by default is today
        addDate.setValue(LocalDate.now());
    }

    public void navigateToHomePage(ActionEvent event) {
        this.getController().navigateToHomePage();
    }


    public void deleteIngredient(ActionEvent actionEvent) {
    }
    public void addIngredient(ActionEvent actionEvent) {
        Ingredient ingredient = new Ingredient(
                this.name.getText(),
                Double.parseDouble(this.quantity.getText()),
                UnitMeasure.valueOf(this.unit.getValue()),
                this.addDate.getValue(),
                this.expirationDate.getValue()
        );
        this.getController().addIngredientToInventory(ingredient);
    }
    public void modifyIngredient(ActionEvent actionEvent) {
    }
}
