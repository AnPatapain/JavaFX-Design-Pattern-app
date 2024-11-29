package fr.insa.bourges.firstapplicationjfx.features.shared.utils;

import javafx.scene.control.Alert;

public class CustomUIAlert {
    public static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
