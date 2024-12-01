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
