package fr.insa.bourges.firstapplicationjfx.features.ingredient;

import fr.insa.bourges.firstapplicationjfx.base.controller.AbstractController;
import fr.insa.bourges.firstapplicationjfx.base.database.JsonRepository;
import fr.insa.bourges.firstapplicationjfx.base.database.Repository;
import fr.insa.bourges.firstapplicationjfx.base.event.EventDispatcher;
import fr.insa.bourges.firstapplicationjfx.base.event.EventType;
import fr.insa.bourges.firstapplicationjfx.base.view.AbstractView;
import fr.insa.bourges.firstapplicationjfx.base.view.RenderViewManager;
import fr.insa.bourges.firstapplicationjfx.base.view.ViewName;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Ingredient;

public class IngredientController extends AbstractController<AbstractView<?>> {
    private Repository<Ingredient> ingredientRepository = JsonRepository.getRepository(Ingredient.class);

    public IngredientController(EventDispatcher eventDispatcher, RenderViewManager renderViewManager) {
        super(eventDispatcher, renderViewManager);
    }

    @Override
    public void setSubscription(EventDispatcher eventDispatcher) {
        this.eventDispatcher.subscribe(this, EventType.SHOW_INGREDIENT_PAGE);
    }

    @Override
    public void handleEvent(EventType eventType) {
        switch (eventType) {
            case SHOW_INGREDIENT_PAGE: {
                this.renderViewManager.renderView(this.getViewAs(ViewName.INGREDIENT, IngredientView.class));
            }
        }
    }

    public void navigateToHomePage() {
        this.eventDispatcher.dispatchEvent(EventType.SHOW_HOME_PAGE);
    }
}
