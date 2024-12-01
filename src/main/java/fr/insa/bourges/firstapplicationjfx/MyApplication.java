package fr.insa.bourges.firstapplicationjfx;

import fr.insa.bourges.firstapplicationjfx.base.controller.ControllerMediator;
import fr.insa.bourges.firstapplicationjfx.base.view.AbstractPageView;
import fr.insa.bourges.firstapplicationjfx.base.view.RenderViewManager;
import fr.insa.bourges.firstapplicationjfx.base.event.EventDispatcher;
import fr.insa.bourges.firstapplicationjfx.base.event.EventType;
import fr.insa.bourges.firstapplicationjfx.base.view.ViewName;
import fr.insa.bourges.firstapplicationjfx.features.home.HomeController;
import fr.insa.bourges.firstapplicationjfx.features.home.view.pages.HomePage;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.IngredientController;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.view.pages.IngredientListPage;
import fr.insa.bourges.firstapplicationjfx.features.recipe.RecipeController;
import fr.insa.bourges.firstapplicationjfx.features.recipe.view.pages.RecipeAddPage;
import fr.insa.bourges.firstapplicationjfx.features.recipe.view.pages.RecipeIndicationPage;
import fr.insa.bourges.firstapplicationjfx.features.recipe.view.pages.RecipeListPage;
import javafx.stage.Stage;

import java.io.IOException;

public class MyApplication extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        /**
         * Close part
         * The app features will EXTEND the close part but NEVER MODIFY them. If we need to modify
         * the close part that means our design was wrong at the beginning.
         */
        EventDispatcher controllerMediator = ControllerMediator.getInstance();
        RenderViewManager renderViewManager = new RenderViewManager(stage);

        /**
         * Open part
         * These are the feature that will be added over time.
         */
        /////////////
        // Home page
        HomeController homeController = new HomeController(controllerMediator, renderViewManager);
        HomePage homeView = AbstractPageView.createView(HomePage.class, "home.fxml", homeController);
        homeController.addView(ViewName.HOME, homeView);

        ///////////////
        // Recipe page
        RecipeController recipeController = new RecipeController(controllerMediator, renderViewManager);

        RecipeAddPage recipeAddView = AbstractPageView.createView(RecipeAddPage.class, "recipeAdd.fxml", recipeController);
        RecipeListPage recipeListView = AbstractPageView.createView(RecipeListPage.class, "recipeList.fxml", recipeController);
        RecipeIndicationPage recipeIndicationPage = AbstractPageView.createView(RecipeIndicationPage.class, "recipeIndication.fxml", recipeController);

        recipeController.addView(ViewName.RECIPE_ADD, recipeAddView);
        recipeController.addView(ViewName.RECIPE_LIST, recipeListView);
        recipeController.addView(ViewName.RECIPE_INDICATION, recipeIndicationPage);

        ///////////////////
        // Ingredient page
        IngredientController ingredientController = new IngredientController(controllerMediator, renderViewManager);
        IngredientListPage ingredientListView = AbstractPageView.createView(IngredientListPage.class, "ingredientList.fxml", ingredientController);
        ingredientController.addView(ViewName.INGREDIENT_LIST, ingredientListView);

        /**
         * Publish SHOW_HOME_PAGE event to show the entry page of application
         */
        controllerMediator.dispatchEvent(EventType.SHOW_HOME_PAGE);
    }
}