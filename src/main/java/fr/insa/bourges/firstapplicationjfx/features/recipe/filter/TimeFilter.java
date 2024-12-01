/**
 * Class TimeFilter
 * Filters recipes based on their total preparation and cooking time.

 * Methods:
 * - **filter(FilterContext filterContext)**:
 *   - Returns recipes where the sum of preparation and cooking time is less than or equal to the specified time.

 * Author: Anh Tuan NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.features.recipe.filter;

import fr.insa.bourges.firstapplicationjfx.features.shared.models.Recipe;

import java.util.List;

public class TimeFilter implements FilterStrategy {


    @Override
    public List<Recipe> filter(FilterContext filterContext) {
        List<Recipe> recipes = filterContext.getRecipes();
        double time = filterContext.getTime();
        return recipes.stream().map(recipe -> {
            if (recipe.getPreparationTime() + recipe.getCookingTime() <= time) {
                return recipe;
            }
            return null;
        }).toList();
    }
}
