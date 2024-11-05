module fr.insa.bourges.firstapplicationjfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.insa.bourges.firstapplicationjfx.views to javafx.fxml;
    exports fr.insa.bourges.firstapplicationjfx;
}