/**
 * CustomUIAlert provides a utility method to display simple informational alerts to the user.

 * Responsibilities:
 * - Displays a modal dialog with a title and message.

 * Method:
 * - `showAlert(String title, String message)`: Creates and shows an informational alert.

 * Example:
 * CustomUIAlert.showAlert("Success", "Ingredient added successfully!");

 * Author: Anh Tuan NGUYEN
 */

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
