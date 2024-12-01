/**
 * ComponentView is a reusable UI component that manages user interactions and delegates
 * logic execution via dynamically registered commands.

 * Responsibilities:
 * - Maintains a reference to the parent `AbstractPageView` for context.
 * - Handles commands: allows dynamic registration (`registerCommand`) and execution (`executeCommand`).

 * Design:
 * - Implements the Command pattern for flexible, decoupled interaction between UI components and logic.
 * - Enables reusable and context-aware behavior.

 * Example (from RecipeComponent):
 * - Register a command to edit a recipe:
 *   recipeComponent.registerCommand(RecipeCommandKeys.UPDATE_RECIPE.name(), args -> {
 *       RecipePageType pageType = (RecipePageType) args[0];
 *       Recipe recipe = (Recipe) args[1];
 *       controller.navigateToEditRecipe(pageType, recipe);
 *   });
 * - Execute the command on user action:
 *   recipeComponent.executeCommand(RecipeCommandKeys.UPDATE_RECIPE.name(), RecipePageType.EDIT, recipe);

 * Author: Ke An NGUYEN
 */


package fr.insa.bourges.firstapplicationjfx.base.view;

import fr.insa.bourges.firstapplicationjfx.base.command.Command;
import fr.insa.bourges.firstapplicationjfx.base.database.AbstractEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;
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
