package fr.insa.bourges.firstapplicationjfx.views;

import fr.insa.bourges.firstapplicationjfx.controllers.Controller;

public interface ViewInteractive {
    Controller getController();
    void setController(Controller controller);
}
