package fr.insa.bourges.firstapplicationjfx.features.creation;

import fr.insa.bourges.firstapplicationjfx.base.controller.AbstractController;
import fr.insa.bourges.firstapplicationjfx.base.event.EventDispatcher;
import fr.insa.bourges.firstapplicationjfx.base.event.EventType;
import fr.insa.bourges.firstapplicationjfx.base.view.RenderViewManager;
import fr.insa.bourges.firstapplicationjfx.features.shared.exceptions.HorseNameConflictException;
import fr.insa.bourges.firstapplicationjfx.features.shared.exceptions.HorseNameNotDefinedException;
import fr.insa.bourges.firstapplicationjfx.features.shared.exceptions.HorseWeightNotANumberException;
import fr.insa.bourges.firstapplicationjfx.features.shared.services.IHorseService;

public class CreationController extends AbstractController<CreationView> {
    private IHorseService horseService;

    public CreationController(EventDispatcher eventDispatcher, RenderViewManager renderViewManager) {
        super(eventDispatcher, renderViewManager);
    }

    public void setHorseService(IHorseService horseService) {
        this.horseService = horseService;
    }

    @Override
    public void setSubscription(EventDispatcher eventDispatcher) {
        eventDispatcher.subscribe(this,
                EventType.SHOW_CREATION
        );
    }

    @Override
    public void handleEvent(EventType eventType) {
        if (eventType == EventType.SHOW_CREATION) {
            this.renderViewManager.renderView(this.getView());
        }
    }

    public void createHorse(String horseName, int weight) {
        try {
            horseService.createHorse(horseName, weight);
            this.view.printConfirmation("Created", "Horse Created");
            this.eventDispatcher.dispatchEvent(EventType.DATA_HORSE_CREATED);
            this.eventDispatcher.dispatchEvent(EventType.SHOW_HORSE_LIST);
        } catch (HorseNameNotDefinedException e) {
            this.view.printError("Error", "Horse Name Not Defined");
        } catch (HorseNameConflictException e) {
            this.view.printError("Error", "Horse Name already exists");
        } catch (HorseWeightNotANumberException e) {
            this.view.printError("Error", "Horse Weight Not ANumber");
        }
    }

    public void navigateToHome() {
        this.eventDispatcher.dispatchEvent(EventType.SHOW_HOME);
    }
}
