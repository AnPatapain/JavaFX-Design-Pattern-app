package fr.insa.bourges.firstapplicationjfx.features.recipe.filter;

import fr.insa.bourges.firstapplicationjfx.features.shared.models.Recipe;

import java.util.List;

public class FavortieFilter implements FilterStrategy {

    @Override
    public List<Recipe> filter(FilterContext filterContext) {
        List<Recipe> recipes = filterContext.getRecipes();
        return recipes.stream()
                .filter(Recipe::getFavorite)
                .toList();
    }
}
