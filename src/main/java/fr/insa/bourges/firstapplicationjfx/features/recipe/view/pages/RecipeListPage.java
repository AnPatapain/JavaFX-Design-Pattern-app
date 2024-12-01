package fr.insa.bourges.firstapplicationjfx.features.recipe.view.pages;

import fr.insa.bourges.firstapplicationjfx.base.view.AbstractPageView;
import fr.insa.bourges.firstapplicationjfx.features.recipe.RecipeCommandKeys;
import fr.insa.bourges.firstapplicationjfx.features.recipe.RecipeController;
import fr.insa.bourges.firstapplicationjfx.features.recipe.view.RecipePageType;
import fr.insa.bourges.firstapplicationjfx.features.recipe.view.components.RecipeComponent;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Recipe;
import fr.insa.bourges.firstapplicationjfx.features.shared.utils.CustomUIAlert;
import fr.insa.bourges.firstapplicationjfx.features.shared.utils.TimeParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;


public class RecipeListPage extends AbstractPageView<RecipeController> {
    @FXML
    public BorderPane borderPane;

    @FXML
    public TextField searchBox;

    @FXML
    public VBox recipeListContainer;

    @FXML
    public ComboBox<String> recipeFilterComboBox;

    @FXML
    public ComboBox<String> timeFilterComboBox;

    @FXML
    public HBox timeFilterSectionDynamic;

    @Override
    public void initializeScene() {
        this.setScene(new Scene(this.borderPane, 800, 600));
        recipeFilterComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            timeFilterSectionDynamic.setVisible(newVal.equals("TIME"));
        });
        loadRecipeComponentView();
    }

    public void loadRecipeComponentView() {
        List<Recipe> recipes = this.getController().getAllRecipe();
        recipeListContainer.getChildren().clear();

        for (Recipe recipe : recipes) {
            HBox recipeComponent = createRecipeComponent(recipe);
            recipeListContainer.getChildren().add(recipeComponent);
        }
    }
    public void loadRecipeComponentView(List<Recipe> recipes) {
        recipeListContainer.getChildren().clear();

        for (Recipe recipe : recipes) {
            if (recipe == null) {
                continue;
            }
            HBox recipeComponent = createRecipeComponent(recipe);
            recipeListContainer.getChildren().add(recipeComponent);
        }
    }
    private HBox createRecipeComponent(Recipe recipe) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fr/insa/bourges/firstapplicationjfx/views/recipeComponent.fxml")
            );
            HBox recipeComponentFXML = loader.load();

            RecipeComponent recipeComponent = loader.getController();
            recipeComponent.setRecipe(recipe);
            recipeComponent.setParentPageView(this);

            recipeComponent.registerCommand(RecipeCommandKeys.RELOAD_RECIPES.name(), args -> {
                this.loadRecipeComponentView();
            });
            recipeComponent.registerCommand(RecipeCommandKeys.UPDATE_RECIPE.name(), args -> {
                this.getController().navigateToEditRecipe((RecipePageType) args[0],(Recipe) args[1]);
            });
            recipeComponent.registerCommand(RecipeCommandKeys.DELETE_RECIPE.name(), args -> {
                String toBeDeletedRecipeId = (String) args[0];
                this.getController().deleteRecipe(toBeDeletedRecipeId);
                this.loadRecipeComponentView();
            });
            recipeComponent.registerCommand(RecipeCommandKeys.FAVORITE_RECIPE.name(), args -> {
                Recipe recipeToBeFavorited = (Recipe) args[0];
                recipeToBeFavorited.setFavorite(!recipeToBeFavorited.getFavorite());
                this.getController().updateRecipe(recipeToBeFavorited);
            });
            return recipeComponentFXML;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load recipe component", e);
        }
    }

    // Event Handlers
    public void onSearchHandler(ActionEvent actionEvent) {
        List<Recipe> allRecipes = this.getController().getAllRecipe();
        Pattern pattern = Pattern.compile(searchBox.getText(), Pattern.CASE_INSENSITIVE);

        List<Recipe> filteredRecipes = allRecipes.stream()
                .filter(recipe -> pattern.matcher(recipe.getName()).find())
                .toList();

        this.loadRecipeComponentView(filteredRecipes);

    }
    public void onCategoryFilterHandler(ActionEvent actionEvent) {
        String filter = recipeFilterComboBox.getValue();

        if (filter.equals("ALL")) {
            this.loadRecipeComponentView();
            return;
        }
        this.getController().setFilter(filter);
        List<Recipe> allRecipes = this.getController().getAllRecipe();
        this.getController().getFilterContext().setRecipes(allRecipes);
        if (filter.equals("TIME")) {
            if (timeFilterComboBox.getValue() == null) {
                CustomUIAlert.showAlert("Error", "Please select time!");
                return;
            }
            double time = TimeParser.parseTime(timeFilterComboBox.getValue());
            this.getController().getFilterContext().setTime(time);
        }
        List<Recipe> filteredRecipes = this.getController().applyFilter();
        this.loadRecipeComponentView(filteredRecipes);
    }

    // Navigation
    public void navigateToAddRecipe(ActionEvent actionEvent) {
        this.getController().navigateToAddRecipe();

    }

    public void onBackToHomePageLinkClickHandler(ActionEvent actionEvent) {
        this.getController().navigateToHomePage();
    }
}
