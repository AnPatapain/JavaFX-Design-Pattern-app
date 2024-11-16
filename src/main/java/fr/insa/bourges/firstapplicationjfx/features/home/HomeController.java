package fr.insa.bourges.firstapplicationjfx.features.home;

import fr.insa.bourges.firstapplicationjfx.base.controller.AbstractController;
import fr.insa.bourges.firstapplicationjfx.base.event.EventDispatcher;
import fr.insa.bourges.firstapplicationjfx.base.event.EventType;
import fr.insa.bourges.firstapplicationjfx.base.view.AbstractView;
import fr.insa.bourges.firstapplicationjfx.base.view.RenderViewManager;
import fr.insa.bourges.firstapplicationjfx.base.view.ViewName;

public class HomeController extends AbstractController<AbstractView<?>> {
    public HomeController(EventDispatcher eventDispatcher, RenderViewManager renderViewManager) {
        super(eventDispatcher, renderViewManager);
    }

    @Override
    public void setSubscription(EventDispatcher eventDispatcher) {
        eventDispatcher.subscribe(this, EventType.SHOW_HOME_PAGE);
    }

    @Override
    public void handleEvent(EventType eventType) {
        if (eventType == EventType.SHOW_HOME_PAGE) {
            this.renderViewManager.renderView(this.getViewAs(ViewName.HOME, HomeView.class));
        }
    }

    public void navigateToRecipePage() { this.eventDispatcher.dispatchEvent(EventType.SHOW_RECIPE_PAGE); }

    public void navigateToIngredientPage() {this.eventDispatcher.dispatchEvent(EventType.SHOW_INGREDIENT_PAGE); }
}
