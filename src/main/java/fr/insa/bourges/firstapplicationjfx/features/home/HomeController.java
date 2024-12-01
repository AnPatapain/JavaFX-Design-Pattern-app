/**
 * HomeController manages the interactions and navigation logic for the HomePage.
 * It listens for specific events and dispatches navigation commands to other parts
 * of the application.

 * Responsibilities:
 * - Listens for `SHOW_HOME_PAGE` events and renders the HomePage when triggered.
 * - Handles navigation logic to feature pages:
 *   - Recipe page (`SHOW_RECIPE_LIST_PAGE`).
 *   - Recipe indication page (`SHOW_RECIPE_INDICATION_PAGE`).
 *   - Ingredient page (`SHOW_INGREDIENT_LIST_PAGE`).

 * Design:
 * - Extends `AbstractController` to utilize the event-driven communication and
 *   dynamic view rendering provided by the base controller class.
 * - Decouples navigation logic from the UI by using `EventDispatcher`.

 * Example:
 * - Navigation from the HomePage:
 *   - User clicks "Recipe Page," triggering `navigateToRecipePage()`.
 *   - `navigateToRecipePage()` dispatches the `SHOW_RECIPE_LIST_PAGE` event to update the view.

 * Author: Ke An NGUYEN
 */


package fr.insa.bourges.firstapplicationjfx.features.home;

import fr.insa.bourges.firstapplicationjfx.base.controller.AbstractController;
import fr.insa.bourges.firstapplicationjfx.base.event.EventDispatcher;
import fr.insa.bourges.firstapplicationjfx.base.event.EventType;
import fr.insa.bourges.firstapplicationjfx.base.view.AbstractPageView;
import fr.insa.bourges.firstapplicationjfx.base.view.RenderViewManager;
import fr.insa.bourges.firstapplicationjfx.base.view.ViewName;
import fr.insa.bourges.firstapplicationjfx.features.home.view.pages.HomePage;

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

    //////////////
    // Navigation
    public void navigateToRecipePage() { this.eventDispatcher.dispatchEvent(EventType.SHOW_RECIPE_LIST_PAGE); }

    public void navigateToRecipeIndicationPage() {
        this.eventDispatcher.dispatchEvent(EventType.SHOW_RECIPE_INDICATION_PAGE);
    }

    public void navigateToIngredientPage() {this.eventDispatcher.dispatchEvent(EventType.SHOW_INGREDIENT_LIST_PAGE); }
}
