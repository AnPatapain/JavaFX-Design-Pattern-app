module fr.insa.bourges.firstapplicationjfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.insa.bourges.firstapplicationjfx.views to javafx.fxml;
    exports fr.insa.bourges.firstapplicationjfx;
    opens fr.insa.bourges.firstapplicationjfx.base.controller to javafx.fxml;
    opens fr.insa.bourges.firstapplicationjfx.base.view to javafx.fxml;
    opens fr.insa.bourges.firstapplicationjfx.features.creation to javafx.fxml;
    opens fr.insa.bourges.firstapplicationjfx.features.home to javafx.fxml;
    opens fr.insa.bourges.firstapplicationjfx.features.horse_list to javafx.fxml;
}