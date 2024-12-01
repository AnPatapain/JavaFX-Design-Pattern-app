package fr.insa.bourges.firstapplicationjfx.features.recipe.filter;

import fr.insa.bourges.firstapplicationjfx.features.shared.models.CategoryRecipe;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Recipe;

import java.util.List;

public class CategoryFilter implements FilterStrategy{

    @Override
    public List<Recipe> filter(FilterContext filterContext) {
        List<Recipe> recipes = filterContext.getRecipes();
        CategoryRecipe category = CategoryRecipe.valueOf(filterContext.getArgs());
        return recipes.stream().map(recipe -> {
            if (recipe.getCategory() == category) {
                return recipe;
            }
            return null;
        }).toList();
    }
}
