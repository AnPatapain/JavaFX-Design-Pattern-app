/**
 * IngredientCommandKeys defines the command keys used for ingredient-related actions.
 * These keys are used in the Command pattern to decouple UI interactions from logic execution.

 * Command Keys:
 * - `RELOAD_INGREDIENTS`: Refreshes the ingredient list view.
 * - `ADD_INGREDIENT`: Adds a new ingredient.
 * - `UPDATE_INGREDIENT`: Updates an existing ingredient.
 * - `DELETE_INGREDIENT`: Deletes an ingredient by its ID.

 * Usage:
 * - Register commands with these keys in the `IngredientListPage` or `IngredientComponent`.
 * - Execute commands dynamically based on user interactions.

 * Example:
 * ingredientComponent.registerCommand(IngredientCommandKeys.ADD_INGREDIENT.name(), args -> {
 *     Ingredient ingredient = (Ingredient) args[0];
 *     controller.addIngredient(ingredient);
 * });

 * Author: Ke An NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.features.ingredient;

public enum IngredientCommandKeys {
    RELOAD_INGREDIENTS,
    ADD_INGREDIENT,
    UPDATE_INGREDIENT,
    DELETE_INGREDIENT,
}
