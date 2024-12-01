/**
 * Enum FilterType
 * Defines the different types of filters available for recipes.

 * Values:
 * - **GLUTEN_FREE**: Filters recipes without gluten ingredients.
 * - **VEGETARIAN**: Filters vegetarian recipes.
 * - **TIME**: Filters recipes within a specified preparation and cooking time.
 * - **FAVORITE**: Filters favorite recipes.

 * Author: Anh Tuan NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.features.recipe.filter;

public enum FilterType {
    GLUTEN_FREE,
    VEGETARIAN,
    TIME,
    FAVORITE,
    DIFFICULTY,
    CATEGORY,
}
