/**
 * Recipe represents a cooking recipe with its associated details and methods for feasibility checking.

 * Responsibilities:
 * - Encapsulates data related to a recipe, such as ingredients, category, preparation time, cooking time, difficulty level, instructions, and personal notes.
 * - Provides methods to check the feasibility of making the recipe based on available ingredients.

 * Features:
 * - `checkFeasibility`: Determines if the recipe can be made with the provided ingredients, considering unit compatibility and quantity sufficiency.

 * Key Fields:
 * - `name`: The name of the recipe.
 * - `category`: The recipe's category (e.g., VEGETARIAN, DESSERT).
 * - `ingredients`: The list of ingredients required for the recipe.
 * - `instructions`: Step-by-step preparation instructions.
 * - `preparationTime`: Time required for preparation (in hours).
 * - `cookingTime`: Time required for cooking (in hours).
 * - `difficultyLevel`: Difficulty level of the recipe.
 * - `personalNote`: Custom notes added by the user.
 * - `isFavorite`: Indicates whether the recipe is marked as a favorite.

 * Example:
 * - Create and check feasibility:
 *   Recipe recipe = new Recipe("Pasta", CategoryRecipe.VEGETARIAN, 0.5, 0.75, DifficultyLevel.EASY, ingredients, "Boil and mix.");
 *   Map<String, String> feasibility = recipe.checkFeasibility(availableIngredients);

 * Author: Anh Tuan NGUYEN & Ke An NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.features.shared.models;

import fr.insa.bourges.firstapplicationjfx.base.database.AbstractEntity;
import fr.insa.bourges.firstapplicationjfx.features.shared.utils.UnitMeasureUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recipe extends AbstractEntity {
    private String name;
    private CategoryRecipe category;
    private List<Ingredient> ingredients;
    private String instructions;
    private double preparationTime;
    private double cookingTime;
    private DifficultyLevel difficultyLevel;
    private String personalNote;
    private Boolean isFavorite = false;


    public Recipe() {
    }

    public Recipe(String name, CategoryRecipe category,
                  double preparationTime, double cookingTime,
                  DifficultyLevel difficultyLevel, List<Ingredient> ingredients, String instructions) {
        this.name = name;
        this.category = category;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.preparationTime = preparationTime;
        this.cookingTime = cookingTime;
        this.difficultyLevel = difficultyLevel;
    }

    public Map<String, String> checkFeasibility(List<Ingredient> currentAvailableIngredients) {
        Map<String, String> status = new HashMap<>();

        for (Ingredient required : this.getIngredients()) {
            Ingredient available = currentAvailableIngredients.stream()
                    .filter(ing -> ing.getName().equalsIgnoreCase(required.getName()))
                    .findFirst()
                    .orElse(null);

            if (available == null) {
                status.put(required.getName(), "Missing");
            } else {
                // Check unit compatibility
                if (!UnitMeasureUtils.areUnitsCompatible(available.getUnit(), required.getUnit())) {
                    status.put(
                            required.getName(),
                            "Incompatible unit (Required: " + required.getUnit() +
                                    ", Available: " + available.getUnit() + ")"
                    );
                    continue;
                }

                // Convert available quantity to match required unit
                double convertedAvailableQuantity = UnitMeasureUtils.convertUnits(available.getQuantity(), available.getUnit(), required.getUnit());

                // Check feasibility
                if (convertedAvailableQuantity < required.getQuantity()) {
                    status.put(
                            required.getName(),
                            "Insufficient (Required: " + required.getQuantity() + " " + required.getUnit() +
                                    ", Available: " + convertedAvailableQuantity + " " + required.getUnit() + ")"
                    );
                }
            }
        }

        return status; // Return the feasibility status
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryRecipe getCategory() {
        return category;
    }

    public void setCategory(CategoryRecipe category) {
        this.category = category;
    }

    public double getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(double preparationTime) {
        this.preparationTime = preparationTime;
    }

    public double getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(double cookingTime) {
        this.cookingTime = cookingTime;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPersonalNote() {return personalNote;}

    public void setPersonalNote(String personalNote) {this.personalNote = personalNote;}

    public Boolean getFavorite() {return isFavorite;}

    public void setFavorite(Boolean favorite) {isFavorite = favorite;}
}
