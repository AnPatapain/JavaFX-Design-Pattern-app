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
}
