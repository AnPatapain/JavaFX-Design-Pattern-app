package fr.insa.bourges.firstapplicationjfx.features.recipe.filter;

import fr.insa.bourges.firstapplicationjfx.features.shared.models.Recipe;

import java.util.List;

public class RecipeFilterService {

    private FilterStrategy filterStrategy;


    public void setFilter(FilterType filterType) {
        switch (filterType) {
            case GLUTEN_FREE:
                this.filterStrategy = new GlutenFreeFilter();
                break;
            case VEGETARIAN:
                this.filterStrategy = new VegetarianFilter();
                break;
            case FAVORITE:
                this.filterStrategy = new FavortieFilter();
                break;
            case TIME:
                this.filterStrategy = new TimeFilter();
                break;
            case DIFFICULTY:
                this.filterStrategy = new DifficultyFilter();
                break;
            case CATEGORY:
                this.filterStrategy = new CategoryFilter();
                break;

        }
    }

    public List<Recipe> applyFilter(FilterContext filterContext) {

        List<Recipe> f = filterStrategy.filter(filterContext);
        return f;

    }
}
