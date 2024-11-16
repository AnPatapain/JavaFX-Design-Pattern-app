package fr.insa.bourges.firstapplicationjfx.features.ingredient;

import fr.insa.bourges.firstapplicationjfx.base.controller.AbstractController;
import fr.insa.bourges.firstapplicationjfx.base.event.EventDispatcher;
import fr.insa.bourges.firstapplicationjfx.base.event.EventType;
import fr.insa.bourges.firstapplicationjfx.base.view.AbstractView;
import fr.insa.bourges.firstapplicationjfx.base.view.RenderViewManager;
import fr.insa.bourges.firstapplicationjfx.base.view.ViewName;

public class IngredientController extends AbstractController<AbstractView<?>> {

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
