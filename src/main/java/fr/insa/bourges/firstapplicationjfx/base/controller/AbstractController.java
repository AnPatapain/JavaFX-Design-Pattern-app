/**
 * AbstractController serves as the base class for all controllers in the application, implementing core functionality
 * common to all controllers, such as managing views and handling events. This class is part of the MVC architecture

 * Responsibilities:
 * - Manages a collection of views (OneToMany relationship) and provides methods to retrieve or add views dynamically.
 * - Subscribes to events via the EventDispatcher and handles them through the implementation of `setSubscription`
 *   and `handleEvent` in concrete controllers.
 * - Delegates the rendering of views to the RenderViewManager.

 * This design follows the Open/Closed Principle:
 * - AbstractController (closed part): Encapsulates common logic for all controllers.
 * - Concrete controllers (open part): Extend the functionality by implementing specific business logic and views.

 * Author: Ke An NGUYEN
 */
package fr.insa.bourges.firstapplicationjfx.base.controller;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractPageView;
import fr.insa.bourges.firstapplicationjfx.base.view.RenderViewManager;
import fr.insa.bourges.firstapplicationjfx.base.event.EventDispatcher;
import fr.insa.bourges.firstapplicationjfx.base.event.EventListener;
import fr.insa.bourges.firstapplicationjfx.base.event.EventType;
import fr.insa.bourges.firstapplicationjfx.base.view.ViewName;

import java.util.EnumMap;

public abstract class AbstractController<V extends AbstractPageView<?>> implements EventListener {
    protected final EventDispatcher eventDispatcher;
    protected final RenderViewManager renderViewManager;
    private final EnumMap<ViewName, V> views = new EnumMap<>(ViewName.class);

    public AbstractController(EventDispatcher eventDispatcher, RenderViewManager renderViewManager) {
        this.eventDispatcher = eventDispatcher;
        this.renderViewManager = renderViewManager;
        this.setSubscription(eventDispatcher);
    }

    @Override
    public abstract void setSubscription(EventDispatcher eventDispatcher);

    @Override
    public abstract void handleEvent(EventType eventType);


    public <T extends V> T getViewAs(ViewName viewName, Class<T> viewType) {
        return viewType.cast(this.views.get(viewName));
    }

    public void addView(ViewName viewName, V view) {
        this.views.put(viewName, view);
    }
}
