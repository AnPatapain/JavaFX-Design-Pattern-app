package fr.insa.bourges.firstapplicationjfx.features.home;

import fr.insa.bourges.firstapplicationjfx.base.controller.AbstractController;
import fr.insa.bourges.firstapplicationjfx.base.event.EventDispatcher;
import fr.insa.bourges.firstapplicationjfx.base.event.EventType;
import fr.insa.bourges.firstapplicationjfx.base.view.RenderViewManager;

public class HomeController extends AbstractController<HomeView> {
    public HomeController(EventDispatcher eventDispatcher, RenderViewManager renderViewManager) {
        super(eventDispatcher, renderViewManager);
    }

    @Override
    public void setSubscription(EventDispatcher eventDispatcher) {
        eventDispatcher.subscribe(this, EventType.SHOW_HOME);
    }

    @Override
    public void handleEvent(EventType eventType) {
        if (eventType == EventType.SHOW_HOME) {
            this.renderViewManager.renderView(this.getView());
        }
    }

    public void navigateToCreation() {
        this.eventDispatcher.dispatchEvent(EventType.SHOW_CREATION);
    }

    public void navigateToHorseList() {
        this.eventDispatcher.dispatchEvent(EventType.SHOW_HORSE_LIST);
    }
}
