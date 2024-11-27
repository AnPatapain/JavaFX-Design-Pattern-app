package fr.insa.bourges.firstapplicationjfx.base.view;
import fr.insa.bourges.firstapplicationjfx.base.controller.AbstractController;
import javafx.stage.Stage;

public class RenderViewManager {
    private final Stage stage;

    public RenderViewManager(Stage stage) {
        this.stage = stage;
    }

    public void renderView(AbstractView<?> view) {
        this.stage.setScene(view.getScene());
        this.stage.show();
    }
}
