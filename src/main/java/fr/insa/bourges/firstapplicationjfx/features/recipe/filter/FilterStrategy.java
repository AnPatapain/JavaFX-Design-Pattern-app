/**
 * Interface FilterStrategy
 * Represents the strategy interface for filtering recipes based on specific criteria.

 * Methods:
 * - **filter(FilterContext filterContext)**:
 *   - Accepts a `FilterContext` containing the data required for filtering.
 *   - Returns a filtered list of recipes based on the implemented strategy.

 * Author: Anh Tuan NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.features.recipe.filter;

import fr.insa.bourges.firstapplicationjfx.features.shared.models.Recipe;

import java.util.List;

public interface FilterStrategy {
    List<Recipe> filter(FilterContext filterContext);
}
