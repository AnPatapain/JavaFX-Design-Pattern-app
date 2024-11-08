package fr.insa.bourges.firstapplicationjfx.base.controller;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractView;
import fr.insa.bourges.firstapplicationjfx.base.view.RenderViewManager;
import fr.insa.bourges.firstapplicationjfx.base.event.EventDispatcher;
import fr.insa.bourges.firstapplicationjfx.base.event.EventListener;
import fr.insa.bourges.firstapplicationjfx.base.event.EventType;

public abstract class AbstractController<V extends AbstractView<?>> implements EventListener {
    protected final EventDispatcher eventDispatcher;
    protected final RenderViewManager renderViewManager;
    protected V view;

    public AbstractController(EventDispatcher eventDispatcher, RenderViewManager renderViewManager) {
        this.eventDispatcher = eventDispatcher;
        this.renderViewManager = renderViewManager;
        this.setSubscription(eventDispatcher);
    }

    @Override
    public abstract void setSubscription(EventDispatcher eventDispatcher);

    @Override
    public abstract void handleEvent(EventType eventType);

    public V getView() {
        return view;
    }

    public void setView(V view) {
        this.view = view;
    }
}
