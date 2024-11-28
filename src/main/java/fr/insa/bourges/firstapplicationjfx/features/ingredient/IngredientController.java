package fr.insa.bourges.firstapplicationjfx.features.ingredient;

import fr.insa.bourges.firstapplicationjfx.base.controller.AbstractController;
import fr.insa.bourges.firstapplicationjfx.base.database.JsonRepository;
import fr.insa.bourges.firstapplicationjfx.base.database.Repository;
import fr.insa.bourges.firstapplicationjfx.base.event.EventDispatcher;
import fr.insa.bourges.firstapplicationjfx.base.event.EventType;
import fr.insa.bourges.firstapplicationjfx.base.view.AbstractPageView;
import fr.insa.bourges.firstapplicationjfx.base.view.RenderViewManager;
import fr.insa.bourges.firstapplicationjfx.base.view.ViewName;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.view.pages.IngredientListPage;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Ingredient;

import java.util.List;

public class IngredientController extends AbstractController<AbstractPageView<?>> {
    public final Repository<Ingredient> ingredientRepo = JsonRepository.getRepository(Ingredient.class);

    public IngredientController(EventDispatcher eventDispatcher, RenderViewManager renderViewManager) {
        super(eventDispatcher, renderViewManager);
    }

    @Override
    public void setSubscription(EventDispatcher eventDispatcher) {
        this.eventDispatcher.subscribe(this, EventType.SHOW_INGREDIENT_LIST_PAGE);
    }

    @Override
    public void handleEvent(EventType eventType) {
        switch (eventType) {
            case SHOW_INGREDIENT_LIST_PAGE: {
                this.getViewAs(ViewName.INGREDIENT_LIST, IngredientListPage.class).loadIngredientComponentView();
                this.renderViewManager.renderView(this.getViewAs(ViewName.INGREDIENT_LIST, IngredientListPage.class));
                break;
            }
        }
    }

    ////////////////
    // CRUD methods
    public List<Ingredient> getAllIngredientFromInventory() {
        return this.ingredientRepo.findAll();
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredientRepo.persist(ingredient);
        this.ingredientRepo.flush();
    }

    public void updateIngredient(Ingredient ingredient) {
        this.ingredientRepo.update(ingredient);
        this.ingredientRepo.flush();
        System.out.println("Ingredient saved: " + ingredient);
    }

    public void deleteIngredient(String ingredientId) {
        this.ingredientRepo.deleteById(ingredientId);
        this.ingredientRepo.flush();
    }

    ////////////////////
    // Navigate methods
    public void navigateToHomePage() {
        this.eventDispatcher.dispatchEvent(EventType.SHOW_HOME_PAGE);
    }
}
