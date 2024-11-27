package fr.insa.bourges.firstapplicationjfx.features.shared.models;

import fr.insa.bourges.firstapplicationjfx.base.database.AbstractEntity;

import java.util.List;

public class Recipe extends AbstractEntity {
    private String name;
    private CategoryRecipe category;
    private List<Ingredient> ingredients;
    private String instructions;
    private double preparationTime;
    private double cookingTime;
    private DifficultyLevel difficultyLevel;

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

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public CategoryRecipe getCategory() { return category; }
    public void setCategory(CategoryRecipe category) { this.category = category; }

    public double getPreparationTime() { return preparationTime; }
    public void setPreparationTime(double preparationTime) { this.preparationTime = preparationTime; }

    public double getCookingTime() { return cookingTime; }
    public void setCookingTime(double cookingTime) { this.cookingTime = cookingTime; }

    public DifficultyLevel getDifficulty() { return difficultyLevel; }
    public void setDifficultyLevel(DifficultyLevel difficultyLevel) { this.difficultyLevel = difficultyLevel; }

    public List<Ingredient> getIngredients() { return ingredients; }
    public void setIngredients(List<Ingredient> ingredients) { this.ingredients = ingredients; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
}
