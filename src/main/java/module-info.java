module fr.insa.bourges.firstapplicationjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens fr.insa.bourges.firstapplicationjfx.views to javafx.fxml;
    exports fr.insa.bourges.firstapplicationjfx;
    opens fr.insa.bourges.firstapplicationjfx.base.controller to javafx.fxml;
    opens fr.insa.bourges.firstapplicationjfx.base.view to javafx.fxml;
    opens fr.insa.bourges.firstapplicationjfx.features.home to javafx.fxml;
    opens fr.insa.bourges.firstapplicationjfx.features.recipe to javafx.fxml;
    opens fr.insa.bourges.firstapplicationjfx.features.ingredient to javafx.fxml;
}