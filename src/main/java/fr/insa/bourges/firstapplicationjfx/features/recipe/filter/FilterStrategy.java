package fr.insa.bourges.firstapplicationjfx.features.recipe.filter;

import fr.insa.bourges.firstapplicationjfx.features.shared.models.Recipe;

import java.util.List;

public interface FilterStrategy {
    List<Recipe> filter(FilterContext filterContext);
}
