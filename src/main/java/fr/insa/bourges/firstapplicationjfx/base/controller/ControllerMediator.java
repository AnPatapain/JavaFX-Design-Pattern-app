/**
 * ControllerMediator acts as a central hub for managing communication between controllers and dispatching events
 * across the application. This class implements the Singleton pattern to ensure a single instance is used throughout
 * the application, providing a unified way to handle event subscriptions and dispatching.

 * Responsibilities:
 * - Manages event subscriptions: Allows controllers (or other event listeners) to subscribe to specific event types.
 * - Dispatches events: Notifies all subscribed listeners of a given event type, enabling decoupled communication.
 * - Controller retrieval: Allows retrieval of controllers by their type for advanced use cases where direct
 *   access to specific controllers is required.

 * Key Features:
 * - Implements the Singleton pattern to ensure a single point of access.
 * - Supports OneToMany relationships: An event type can have multiple listeners, and a listener can subscribe to
 *   multiple event types.
 * - Facilitates decoupled communication: Event-driven communication reduces direct dependencies between components.

 * Usage:
 * - `subscribe`: Registers an EventListener for one or more event types.
 * - `dispatchEvent`: Notifies all listeners of a given event type.
 * - `getControllersByType`: Retrieves a controller instance by its class type, enabling dynamic access when needed.

 * Author: Ke An NGUYEN
 */


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
