/**
 * RenderViewManager used by the concrete controller to display the pages based on the event received by
 * this controller

 * Example: In HomeController, to display the HomePage whenever the SHOW_HOME_PAGE arrives:
 * @Override
 * public void handleEvent(EventType eventType) {
 *   if (eventType == EventType.SHOW_HOME_PAGE) {
 *     this.renderViewManager.renderView(this.getViewAs(ViewName.HOME, HomePage.class));
 *   }
 * }

 * Author: Ke An NGUYEN
 */
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
