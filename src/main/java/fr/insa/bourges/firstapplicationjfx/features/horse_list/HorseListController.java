package fr.insa.bourges.firstapplicationjfx.features.horse_list;

import fr.insa.bourges.firstapplicationjfx.base.controller.AbstractController;
import fr.insa.bourges.firstapplicationjfx.base.event.EventDispatcher;
import fr.insa.bourges.firstapplicationjfx.base.event.EventType;
import fr.insa.bourges.firstapplicationjfx.base.view.RenderViewManager;
import fr.insa.bourges.firstapplicationjfx.features.shared.services.IHorseService;

public class HorseListController extends AbstractController<HorseListView> {
    private IHorseService horseService;

    public HorseListController(EventDispatcher eventDispatcher, RenderViewManager renderViewManager) {
        super(eventDispatcher, renderViewManager);
    }

    public void setHorseService(IHorseService horseService) {
        this.horseService = horseService;
    }

    @Override
    public void setSubscription(EventDispatcher eventDispatcher) {
        eventDispatcher.subscribe(this,
                EventType.SHOW_HORSE_LIST,
                EventType.DATA_LOAD,
                EventType.DATA_HORSE_CREATED
        );
    }

    @Override
    public void handleEvent(EventType eventType) {
        switch (eventType) {
            case SHOW_HORSE_LIST: {
                this.view.updateHorseToView(this.horseService.getHorses());
                this.renderViewManager.renderView(this.getView());
                break;
            }

            case DATA_HORSE_CREATED, DATA_LOAD: {
                this.view.updateHorseToView(this.horseService.getHorses());
                break;
            }
        }
    }

    public void navigateToHome() {
        this.eventDispatcher.dispatchEvent(EventType.SHOW_HOME);
    }

    public void navigateToCreation() {
        this.eventDispatcher.dispatchEvent(EventType.SHOW_CREATION);
    }
}
