package fr.insa.bourges.firstapplicationjfx.base.controller;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractView;
import fr.insa.bourges.firstapplicationjfx.base.view.RenderViewManager;
import fr.insa.bourges.firstapplicationjfx.base.event.EventDispatcher;
import fr.insa.bourges.firstapplicationjfx.base.event.EventListener;
import fr.insa.bourges.firstapplicationjfx.base.event.EventType;
import fr.insa.bourges.firstapplicationjfx.base.view.ViewName;

import java.util.EnumMap;

public abstract class AbstractController<V extends AbstractView<?>> implements EventListener {
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
