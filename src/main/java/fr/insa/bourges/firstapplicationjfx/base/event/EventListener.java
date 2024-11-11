/**
 * The EventListener interface represents an observer in the Observer pattern.
 * It allows classes implementing this interface to subscribe to an EventDispatcher
 * and respond to dispatched events based on event type.
 *
 * Author: Ke An NGUYEN
 */
package fr.insa.bourges.firstapplicationjfx.base.event;

public interface EventListener {

    /**
     * Sets up the subscription of this EventListener to an EventDispatcher.
     *
     * This method allows the EventListener to determine which events it should
     * subscribe to by directly interacting with the EventDispatcher.
     * Unlike a direct subscription by the EventDispatcher, this approach gives
     * the EventListener control over which specific events it listens to, making
     * it suitable for cases where the EventDispatcher doesn't know the precise events
     * an EventListener is interested in.
     *
     * @param eventDispatcher The EventDispatcher to which this listener will subscribe.
     */
    void setSubscription(EventDispatcher eventDispatcher);

    /**
     * Handles an event dispatched by the EventDispatcher.
     *
     * This method is called when an event of a type that the EventListener is subscribed
     * to is dispatched by the EventDispatcher. Implementing classes should define how
     * they handle each specific EventType in this method.
     *
     * @param eventType The type of event that was dispatched, which this listener is handling.
     */
    void handleEvent(EventType eventType);
}
