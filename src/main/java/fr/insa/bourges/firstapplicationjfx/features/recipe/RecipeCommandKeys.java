/**
 * Enum `RecipeCommandKeys` defines the keys used for command pattern integration
 * in the recipe management feature. These keys represent various actions that
 * can be performed on recipes and are used to trigger the corresponding logic
 * through commands.

 * Command Keys:
 * - **RELOAD_RECIPES**: Refreshes the list of recipes displayed in the UI.
 * - **ADD_RECIPE**: Adds a new recipe to the repository and updates the view.
 * - **UPDATE_RECIPE**: Updates an existing recipe's details.
 * - **DELETE_RECIPE**: Deletes a recipe from the repository.
 * - **FAVORITE_RECIPE**: Toggles the favorite status of a recipe.
 * - **UPDATE_NOTE_RECIPE**: Updates personal notes associated with a recipe.

 * Usage:
 * - Used in conjunction with the `Command` interface to decouple user interactions
 *   from the underlying business logic.
 * - Provides a clean and consistent mechanism for managing recipe-related actions.

 * Author: Anh Tuan NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.features.recipe;

public enum RecipeCommandKeys {
    RELOAD_RECIPES,
    ADD_RECIPE,
    UPDATE_RECIPE,
    DELETE_RECIPE,
    FAVORITE_RECIPE,
    UPDATE_NOTE_RECIPE,
}
