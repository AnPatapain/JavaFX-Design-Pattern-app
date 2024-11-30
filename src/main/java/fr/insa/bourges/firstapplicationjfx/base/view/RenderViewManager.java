package fr.insa.bourges.firstapplicationjfx.base.view;
import javafx.stage.Stage;

public class RenderViewManager {
    private final Stage stage;

    public RenderViewManager(Stage stage) {
        this.stage = stage;
    }

    public void renderView(AbstractPageView<?> view) {
        this.stage.setScene(view.getScene());
        this.stage.show();
    }
}
