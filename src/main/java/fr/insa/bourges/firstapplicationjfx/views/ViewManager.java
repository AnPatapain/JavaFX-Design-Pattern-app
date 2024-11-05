package fr.insa.bourges.firstapplicationjfx.views;

import fr.insa.bourges.firstapplicationjfx.controllers.Controller;
import fr.insa.bourges.firstapplicationjfx.controllers.EventDispatcher;
import fr.insa.bourges.firstapplicationjfx.controllers.EventListener;
import fr.insa.bourges.firstapplicationjfx.controllers.EventType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ViewManager implements EventListener {
    private final Stage stage;
    private Collection<ViewInteractive> viewInteractives;
    private Collection<EventListener> eventListeners;

    Home home;
    Creation creation;
    HorseList horseList;

    public ViewManager(Stage stage) throws IOException {
        this.stage = stage;
        this.viewInteractives = new ArrayList<>();
        this.eventListeners = new ArrayList<>();
        home = Home.create();
        creation = Creation.create();
        horseList = HorseList.create();

        Collections.addAll(this.eventListeners, creation, horseList);
        Collections.addAll(this.viewInteractives, home, creation, horseList);
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void setSubscription(EventDispatcher eventDispatcher) {
        for (EventListener eventListener : eventListeners) {
            eventListener.setSubscription(eventDispatcher);
        }
        eventDispatcher.subscribe(this,
                EventType.SHOW_HOME,
                EventType.SHOW_CREATION,
                EventType.SHOW_HORSE_LIST
        );
    }

    @Override
    public void handleEvent(EventType eventType) {
        switch (eventType) {
            case SHOW_HOME:
                this.getStage().setScene(home.getScene());
                this.getStage().show();
                break;

            case SHOW_CREATION:
                this.getStage().setScene(creation.getScene());
                this.getStage().show();
                break;

            case SHOW_HORSE_LIST:
                this.getStage().setScene(horseList.getScene());
                this.getStage().show();
                break;
        }
    }

    public void setController(Controller controller) {
        this.viewInteractives.forEach(viewInteractive ->
                viewInteractive.setController(controller)
        );
    }
}
