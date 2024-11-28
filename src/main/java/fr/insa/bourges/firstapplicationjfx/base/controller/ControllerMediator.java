package fr.insa.bourges.firstapplicationjfx.base.controller;

import fr.insa.bourges.firstapplicationjfx.base.event.EventDispatcher;
import fr.insa.bourges.firstapplicationjfx.base.event.EventListener;
import fr.insa.bourges.firstapplicationjfx.base.event.EventType;

import java.util.*;

public class ControllerMediator implements EventDispatcher {
    private ControllerMediator() {}

    private static ControllerMediator instance;

    public static ControllerMediator getInstance() {
        if (instance == null) {
            instance = new ControllerMediator();
        }
        return instance;
    }

    Map<EventType, Collection<EventListener>> eventListeners = new EnumMap<>(EventType.class);

    @Override
    public void subscribe(EventListener eventListener, EventType... eventTypes) {
        for(EventType eventType : eventTypes) {
            eventListeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(eventListener);
        }
    }

    @Override
    public void dispatchEvent(EventType eventType) {
        if (eventListeners.containsKey(eventType)) {
            eventListeners.get(eventType).forEach(listener -> listener.handleEvent(eventType));
        }
    }

    public <C extends AbstractController<?>> C getControllersByType(Class<C> controllerType) {
        Set<AbstractController<?>> controllers = new HashSet<>();

        for (Collection<EventListener> listeners : eventListeners.values()) {
            for (EventListener listener : listeners) {
                if (listener instanceof AbstractController) {
                    controllers.add((AbstractController<?>) listener);
                }
            }
        }

        for (AbstractController<?> controller : controllers) {
            if (controller.getClass().equals(controllerType)) return (C) controller;
        }

        return null;
    }
}
