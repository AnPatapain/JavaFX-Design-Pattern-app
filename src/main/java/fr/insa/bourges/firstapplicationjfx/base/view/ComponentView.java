package fr.insa.bourges.firstapplicationjfx.base.view;

import fr.insa.bourges.firstapplicationjfx.base.command.Command;
import fr.insa.bourges.firstapplicationjfx.base.database.AbstractEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ComponentView {
    private Map<String, Command> commandRegistry = new HashMap<>();

    public void registerCommand(String key, Command command) {
        commandRegistry.put(key, command);
    }

    public void executeCommand(String key, Object... args) {
        Command command = commandRegistry.get(key);
        if (command != null) {
            command.execute(args);
        } else {
            throw new RuntimeException("Command " + key + " not found");
        }
    }
}
