module fr.insa.bourges.firstapplicationjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    exports fr.insa.bourges.firstapplicationjfx;

    opens fr.insa.bourges.firstapplicationjfx.base.controller to javafx.fxml;
    opens fr.insa.bourges.firstapplicationjfx.base.view to javafx.fxml;
    opens fr.insa.bourges.firstapplicationjfx.base.database to com.fasterxml.jackson.databind;
    opens fr.insa.bourges.firstapplicationjfx.features.shared.models to com.fasterxml.jackson.databind;

    opens fr.insa.bourges.firstapplicationjfx.features.home to javafx.fxml;
    opens fr.insa.bourges.firstapplicationjfx.features.recipe to javafx.fxml;
    opens fr.insa.bourges.firstapplicationjfx.features.ingredient to javafx.fxml;
}