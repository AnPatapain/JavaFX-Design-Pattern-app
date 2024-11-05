package fr.insa.bourges.firstapplicationjfx.controllers;

import fr.insa.bourges.firstapplicationjfx.exceptions.HorseNameConflictException;
import fr.insa.bourges.firstapplicationjfx.exceptions.HorseNameNotDefinedException;
import fr.insa.bourges.firstapplicationjfx.exceptions.HorseWeightNotANumberException;
import fr.insa.bourges.firstapplicationjfx.facades.FacadeHorseManager;
import fr.insa.bourges.firstapplicationjfx.models.Horse;
import fr.insa.bourges.firstapplicationjfx.views.ViewManager;

import java.util.*;

public class Controller implements EventDispatcher {
    FacadeHorseManager facadeHorseManager;
    // There are maybe multiple EventListeners for a single EventType
    Map<EventType, Collection<EventListener>> eventListeners;

    public Controller(ViewManager viewManager, FacadeHorseManager facadeHorseManager) {
        this.facadeHorseManager = facadeHorseManager;
        eventListeners = new HashMap<>();
        // At the beginning, each eventType doesn't have any eventListener
        for (EventType eventType : EventType.values()) {
            eventListeners.put(eventType, new ArrayList<>());
        }
        viewManager.setSubscription(this);
        viewManager.setController(this);
    }

    public void run() {
        this.dispatchEvent(EventType.SHOW_HOME);
    }

    @Override
    public void subscribe(EventListener eventListener, EventType... eventTypes) {
        for(EventType eventType : eventTypes) {
            eventListeners.get(eventType).add(eventListener);
        }
    }

    @Override
    public void dispatchEvent(EventType eventType) {
        eventListeners.get(eventType).forEach(listener -> listener.handleEvent(eventType));
    }

    public void gotoCreation() {
        this.dispatchEvent(EventType.SHOW_CREATION);
    }

    public void createHorse(String name, int weight) {
        try {
            facadeHorseManager.createHorse(name, weight);
            this.dispatchEvent(EventType.DATA_HORSE_CREATED);
            this.dispatchEvent(EventType.SHOW_HORSE_LIST);
        } catch (HorseNameNotDefinedException e) {
            this.dispatchEvent(EventType.ERROR_NAME_MISSING);
        } catch (HorseNameConflictException e) {
            this.dispatchEvent(EventType.ERROR_HORSE_NAME_CONFLICT);
        } catch (HorseWeightNotANumberException e) {
            this.dispatchEvent(EventType.ERROR_INCONSISTENT_WEIGHT);
        }
    }

    public List<Horse> getHorses() {
        return this.facadeHorseManager.getHorses();
    }

    public void gotoMenu() {
        this.dispatchEvent(EventType.SHOW_HOME);
    }
}
