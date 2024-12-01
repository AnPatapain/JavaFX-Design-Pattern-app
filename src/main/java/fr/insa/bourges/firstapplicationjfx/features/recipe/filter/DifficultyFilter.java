package fr.insa.bourges.firstapplicationjfx.features.recipe.filter;

import fr.insa.bourges.firstapplicationjfx.features.shared.models.DifficultyLevel;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Recipe;

import java.util.List;

public class DifficultyFilter implements FilterStrategy{
    @Override
    public List<Recipe> filter(FilterContext filterContext) {
        List<Recipe> recipes = filterContext.getRecipes();
        DifficultyLevel difficulty = DifficultyLevel.valueOf(filterContext.getArgs());
        return recipes.stream().map(recipe -> {
            if (recipe.getDifficultyLevel() == difficulty) {
                return recipe;
            }
            return null;
        }).toList();
    }
}
