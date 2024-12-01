/**
 * Represents a command that can be executed with optional arguments.
 * This interface is used to encapsulate an action as a reusable and flexible object,
 * following the Command design pattern.

 * Example usage:
 * Command saveCommand = args -> {
 *     Ingredient ingredient = (Ingredient) args[0];
 *     controller.saveIngredient(ingredient);
 * };
 * saveCommand.execute(someIngredient);

 * Author: Ke An NGUYEN
 */
package fr.insa.bourges.firstapplicationjfx.base.command;

@FunctionalInterface
public interface Command {
    void execute(Object... args);
}
