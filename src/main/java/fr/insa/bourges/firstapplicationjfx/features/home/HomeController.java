package fr.insa.bourges.firstapplicationjfx.features.home;

import fr.insa.bourges.firstapplicationjfx.base.controller.AbstractController;
import fr.insa.bourges.firstapplicationjfx.base.event.EventDispatcher;
import fr.insa.bourges.firstapplicationjfx.base.event.EventType;
import fr.insa.bourges.firstapplicationjfx.base.view.AbstractPageView;
import fr.insa.bourges.firstapplicationjfx.base.view.RenderViewManager;
import fr.insa.bourges.firstapplicationjfx.base.view.ViewName;
import fr.insa.bourges.firstapplicationjfx.features.home.pages.HomePage;

public class HomeController extends AbstractController<AbstractPageView<?>> {
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
            this.renderViewManager.renderView(this.getViewAs(ViewName.HOME, HomePage.class));
        }
    }

    public void navigateToRecipePage() { this.eventDispatcher.dispatchEvent(EventType.SHOW_RECIPE_PAGE); }

    public void navigateToIngredientPage() {this.eventDispatcher.dispatchEvent(EventType.SHOW_INGREDIENT_LIST_PAGE); }
}
