/**
 * EventDispatcher allows to subscribe different event type with associated event listener
 * Author: Ke An NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.controllers;

public interface EventDispatcher {
    /**
     * One EventListener can listen to different event types
     * @param eventListener
     * @param eventTypes
     */
    void subscribe(EventListener eventListener, EventType... eventTypes);

    /**
     * Dispatch the eventType. The impl of this method may call the handleEvent of the eventListeners.
     * @param eventType
     */
    void dispatchEvent(EventType eventType);
}
