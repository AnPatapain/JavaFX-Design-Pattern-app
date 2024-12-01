/**
 * CategoryRecipe categorizes recipes based on the type of meal.

 * Enum Values:
 * - `ENTREE`: Starters or appetizers.
 * - `PRINCIPAL`: Main course dishes.
 * - `DESSERT`: Sweet dishes served after the main meal.

 * Usage:
 * - Assign a category to a recipe:
 *   recipe.setCategory(CategoryRecipe.DESSERT);

 * Author: Ke An NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.features.shared.models;

public enum CategoryRecipe {
    ENTREE,
    PRINCIPAL,
    DESSERT,
}
