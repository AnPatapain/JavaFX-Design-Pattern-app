package fr.insa.bourges.firstapplicationjfx.features.shared.utils;

import javafx.scene.control.TextFormatter;

public class InputFormatter {
    public static TextFormatter<String> getNumericInputFormatter() {
        return new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*\\.?\\d*")) { // Only allow numeric values
                return change; // Accept the change
            }
            return null; // Reject the change
        });
    }
}
