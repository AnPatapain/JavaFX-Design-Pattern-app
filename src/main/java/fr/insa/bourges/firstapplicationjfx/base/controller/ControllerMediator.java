package fr.insa.bourges.firstapplicationjfx.base.controller;

import fr.insa.bourges.firstapplicationjfx.base.event.EventDispatcher;
import fr.insa.bourges.firstapplicationjfx.base.event.EventListener;
import fr.insa.bourges.firstapplicationjfx.base.event.EventType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

public class ControllerMediator implements EventDispatcher {
    Map<EventType, Collection<EventListener>> eventListeners = new EnumMap<>(EventType.class);

    @Override
    public void subscribe(EventListener eventListener, EventType... eventTypes) {
        for(EventType eventType : eventTypes) {
            eventListeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(eventListener);
        }
    }

    @Override
    public void dispatchEvent(EventType eventType) {
        eventListeners.get(eventType).forEach(listener -> listener.handleEvent(eventType));
    }
}
