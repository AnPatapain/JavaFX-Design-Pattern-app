package fr.insa.bourges.firstapplicationjfx.base.view;

import fr.insa.bourges.firstapplicationjfx.base.command.Command;

import java.util.HashMap;
import java.util.Map;

public class ComponentView {
    private AbstractPageView<?> parentPageView;

    private Map<String, Command> commandRegistry = new HashMap<>();

    public AbstractPageView<?> getParentPageView() {
        return parentPageView;
    }

    public void setParentPageView(AbstractPageView<?> parentPageView) {
        this.parentPageView = parentPageView;
    }

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
