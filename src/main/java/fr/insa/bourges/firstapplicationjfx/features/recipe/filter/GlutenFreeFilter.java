package fr.insa.bourges.firstapplicationjfx.features.recipe.filter;

import fr.insa.bourges.firstapplicationjfx.features.shared.models.CategoryIngredient;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Recipe;

import java.util.List;

public class GlutenFreeFilter implements FilterStrategy {

    @Override
    public List<Recipe> filter(FilterContext filterContext) {
        List<Recipe> recipes = filterContext.getRecipes();
        return recipes.stream()
                .filter(recipe -> recipe.getIngredients().stream()
                        .noneMatch(ingredient -> ingredient.getCategoryIngredient().equals(CategoryIngredient.GRAIN)))
                .toList();
    }
}