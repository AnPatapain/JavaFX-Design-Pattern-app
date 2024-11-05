/**
 * Subscribe to the EventDispatcher and handle the dispatched event (from EventDispatcher) based
 * on the event type
 * Author: Ke An NGUYEN
 */
package fr.insa.bourges.firstapplicationjfx.controllers;

public interface EventListener {
    void setSubscription(EventDispatcher eventDispatcher);

    void handleEvent(EventType eventType);
}
