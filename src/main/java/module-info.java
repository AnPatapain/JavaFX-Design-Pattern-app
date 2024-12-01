module fr.insa.bourges.firstapplicationjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires java.desktop;

    exports fr.insa.bourges.firstapplicationjfx;

    ////////////////////////////////////////////////////
    // Opens the base to javafx.fxml & jackson.databind
    opens fr.insa.bourges.firstapplicationjfx.base.controller to javafx.fxml;
    opens fr.insa.bourges.firstapplicationjfx.base.view to javafx.fxml;
    opens fr.insa.bourges.firstapplicationjfx.base.database to com.fasterxml.jackson.databind;

    ////////////////////////////////////////////////////////
    // Opens the features to javafx.fxml & jackson.databind
    opens fr.insa.bourges.firstapplicationjfx.features.shared.models to com.fasterxml.jackson.databind;

    opens fr.insa.bourges.firstapplicationjfx.features.home.view.pages to javafx.fxml;

    opens fr.insa.bourges.firstapplicationjfx.features.ingredient.view.components to javafx.fxml;
    opens fr.insa.bourges.firstapplicationjfx.features.ingredient.view.pages to javafx.fxml;

    opens fr.insa.bourges.firstapplicationjfx.features.recipe.view.pages to javafx.fxml;
    opens fr.insa.bourges.firstapplicationjfx.features.recipe.view.components to javafx.fxml;
}