package fr.insa.bourges.firstapplicationjfx;

import fr.insa.bourges.firstapplicationjfx.base.controller.ControllerMediator;
import fr.insa.bourges.firstapplicationjfx.base.view.AbstractPageView;
import fr.insa.bourges.firstapplicationjfx.base.view.RenderViewManager;
import fr.insa.bourges.firstapplicationjfx.base.event.EventDispatcher;
import fr.insa.bourges.firstapplicationjfx.base.event.EventType;
import fr.insa.bourges.firstapplicationjfx.base.view.ViewName;
import fr.insa.bourges.firstapplicationjfx.features.home.HomeController;
import fr.insa.bourges.firstapplicationjfx.features.home.HomePageView;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.view.IngredientAddPageView;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.IngredientController;
import fr.insa.bourges.firstapplicationjfx.features.ingredient.view.IngredientListPageView;
import fr.insa.bourges.firstapplicationjfx.features.recipe.RecipeController;
import fr.insa.bourges.firstapplicationjfx.features.recipe.view.RecipeAddPageView;
import fr.insa.bourges.firstapplicationjfx.features.recipe.view.RecipeListPageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
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
        // Home page
        HomeController homeController = new HomeController(controllerMediator, renderViewManager);
        HomePageView homeView = AbstractPageView.createView(HomePageView.class, "home.fxml", homeController);
        homeController.addView(ViewName.HOME, homeView);

        // Recipe page
        RecipeController recipeController = new RecipeController(controllerMediator, renderViewManager);
        RecipeAddPageView recipeAddView = AbstractPageView.createView(RecipeAddPageView.class, "recipeAdd.fxml", recipeController);
        RecipeListPageView recipeListView = AbstractPageView.createView(RecipeListPageView.class, "recipeList.fxml", recipeController);
        recipeController.addView(ViewName.RECIPE_ADD, recipeAddView);
        recipeController.addView(ViewName.RECIPE_LIST, recipeListView);

        // Ingredient page
        IngredientController ingredientController = new IngredientController(controllerMediator, renderViewManager);
        IngredientListPageView ingredientListView = AbstractPageView.createView(IngredientListPageView.class, "ingredientList.fxml", ingredientController);
        IngredientAddPageView ingredientAddView = AbstractPageView.createView(IngredientAddPageView.class, "ingredientAdd.fxml", ingredientController);
        ingredientController.addView(ViewName.INGREDIENT_LIST, ingredientListView);
        ingredientController.addView(ViewName.INGREDIENT_ADD, ingredientAddView);

        /**
         * This is the event to show the entry page of application
         */
        controllerMediator.dispatchEvent(EventType.SHOW_HOME_PAGE);
    }

    public static void main(String[] args) {
        EnvConfig.setEnvironment(EnvConfig.Environment.NORMAL);
        launch();
    }
}