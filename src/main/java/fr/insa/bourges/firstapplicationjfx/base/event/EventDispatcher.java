/**
 * The EventDispatcher interface represents the subject in the Observer pattern.
 * It manages the subscription of multiple EventListeners to various EventTypes
 * and is responsible for dispatching events to the appropriate listeners.
 * EventListeners can subscribe to specific EventTypes via this interface, and
 * the EventDispatcher will notify them when events of those types occur.
 *
 * Author: Ke An NGUYEN
 */
package fr.insa.bourges.firstapplicationjfx.base.event;

public interface EventDispatcher {

    /**
     * Subscribes an EventListener to one or more EventTypes.
     *
     * This method allows an EventListener to register itself for specific types
     * of events. When any of these events occur, the EventDispatcher will notify
     * the subscribed EventListener by calling its handleEvent method.
     *
     * @param eventListener The EventListener to subscribe to the specified event types.
     * @param eventTypes    The types of events the listener wants to subscribe to.
     */
    void subscribe(EventListener eventListener, EventType... eventTypes);

    /**
     * Dispatches an event of the specified EventType.
     *
     * This method notifies all EventListeners that have subscribed to the specified
     * EventType by calling their handleEvent method. The implementation of this method
     * may maintain a mapping between event types and their associated listeners to
     * efficiently route the event to the correct listeners.
     *
     * @param eventType The type of event to dispatch.
     */
    void dispatchEvent(EventType eventType);
}
