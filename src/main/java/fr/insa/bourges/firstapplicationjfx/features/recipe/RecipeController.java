package fr.insa.bourges.firstapplicationjfx.features.recipe;

import fr.insa.bourges.firstapplicationjfx.base.controller.AbstractController;
import fr.insa.bourges.firstapplicationjfx.base.database.JsonRepository;
import fr.insa.bourges.firstapplicationjfx.base.database.Repository;
import fr.insa.bourges.firstapplicationjfx.base.event.EventDispatcher;
import fr.insa.bourges.firstapplicationjfx.base.event.EventType;
import fr.insa.bourges.firstapplicationjfx.base.view.AbstractPageView;
import fr.insa.bourges.firstapplicationjfx.base.view.RenderViewManager;
import fr.insa.bourges.firstapplicationjfx.base.view.ViewName;
import fr.insa.bourges.firstapplicationjfx.features.recipe.filter.FilterContext;
import fr.insa.bourges.firstapplicationjfx.features.recipe.filter.FilterType;
import fr.insa.bourges.firstapplicationjfx.features.recipe.filter.RecipeFilterService;
import fr.insa.bourges.firstapplicationjfx.features.recipe.view.RecipePageType;
import fr.insa.bourges.firstapplicationjfx.features.recipe.view.pages.RecipeAddPage;
import fr.insa.bourges.firstapplicationjfx.features.recipe.view.pages.RecipeIndicationPage;
import fr.insa.bourges.firstapplicationjfx.features.recipe.view.pages.RecipeListPage;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Ingredient;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Recipe;

import java.util.List;

public class RecipeController extends AbstractController
        <AbstractPageView<?>> {
    private final Repository<Recipe> recipeRepository = JsonRepository.getRepository(Recipe.class);
    private final Repository<Ingredient> ingredientRepository = JsonRepository.getRepository(Ingredient.class);

    public RecipeController(EventDispatcher eventDispatcher, RenderViewManager renderViewManager) {
        super(eventDispatcher, renderViewManager);
    }

    // Filter
    private final RecipeFilterService recipeFilterService = new RecipeFilterService();
    private final FilterContext filterContext = new FilterContext();

    public void setFilter(String filter) {
        this.recipeFilterService.setFilter(FilterType.valueOf(filter));
    }

    public FilterContext getFilterContext() {
        return this.filterContext;
    }


    public List<Recipe> applyFilter() {
        return this.recipeFilterService.applyFilter(this.filterContext);
    }


    @Override
    public void setSubscription(EventDispatcher eventDispatcher) {
        this.eventDispatcher.subscribe(this, EventType.SHOW_RECIPE_LIST_PAGE);
        this.eventDispatcher.subscribe(this, EventType.SHOW_RECIPE_ADD_PAGE);
        this.eventDispatcher.subscribe(this, EventType.SHOW_RECIPE_INDICATION_PAGE);
    }

    @Override
    public void handleEvent(EventType eventType) {

        switch (eventType) {
            case SHOW_RECIPE_LIST_PAGE: {
                this.getViewAs(ViewName.RECIPE_LIST, RecipeListPage.class).loadRecipeComponentView();
                this.renderViewManager.renderView(this.getViewAs(ViewName.RECIPE_LIST, RecipeListPage.class));
                break;
            }
            case SHOW_RECIPE_ADD_PAGE: {
                this.renderViewManager.renderView(this.getViewAs(ViewName.RECIPE_ADD, RecipeAddPage.class));
                break;
            }
            case SHOW_RECIPE_INDICATION_PAGE: {
                this.getViewAs(ViewName.RECIPE_INDICATION, RecipeIndicationPage.class).loadIndicationView();
                this.renderViewManager.renderView(this.getViewAs(ViewName.RECIPE_INDICATION, RecipeIndicationPage.class));
                break;
            }
        }
    }

    /*
     * CRUD operations
     */
    public List<Recipe> getAllRecipe() {
        return this.recipeRepository.findAll();
    }

    public List<Ingredient> getAllIngredients() { return this.ingredientRepository.findAll(); }

    public void addRecipe(Recipe recipe) {
        this.recipeRepository.persist(recipe);
        this.recipeRepository.flush();
    }

    public void updateRecipe(Recipe recipe) {
        this.recipeRepository.update(recipe);
        this.recipeRepository.flush();
    }

    public void deleteRecipe(String recipeId) {
        this.recipeRepository.deleteById(recipeId);
        this.recipeRepository.flush();
    }


    /*
     * Navigation methods
     */
    public void navigateToAddRecipe() {
        this.eventDispatcher.dispatchEvent(EventType.SHOW_RECIPE_ADD_PAGE);
    }

    public void navigateToRecipeListPage() {
        this.eventDispatcher.dispatchEvent(EventType.SHOW_RECIPE_LIST_PAGE);
    }

    public void navigateToEditRecipe(RecipePageType recipePageType, Recipe recipe) {
        this.getViewAs(ViewName.RECIPE_ADD, RecipeAddPage.class).setPageLabel("Edit Recipe");
        this.getViewAs(ViewName.RECIPE_ADD, RecipeAddPage.class).setRecipePageType(recipePageType);
        this.getViewAs(ViewName.RECIPE_ADD, RecipeAddPage.class).setRecipe(recipe);
        this.eventDispatcher.dispatchEvent(EventType.SHOW_RECIPE_ADD_PAGE);
    }

    public void navigateToHomePage() {
        this.eventDispatcher.dispatchEvent(EventType.SHOW_HOME_PAGE);
    }
}
