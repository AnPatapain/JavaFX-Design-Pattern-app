/**
 * Ingredient represents an item used in recipes, with associated details like quantity, unit, and category.

 * Responsibilities:
 * - Encapsulates data related to an ingredient, such as its name, quantity, unit, category, and expiration details.
 * - Provides getter and setter methods for managing ingredient properties.

 * Key Fields:
 * - `name`: The name of the ingredient.
 * - `quantity`: The quantity of the ingredient.
 * - `unit`: The unit of measurement (e.g., GRAMS, LITRES).
 * - `addDate`: The date the ingredient was added to the inventory.
 * - `expirationDate`: The date the ingredient expires.
 * - `categoryIngredient`: The category of the ingredient (e.g., VEGETABLE, DAIRY).

 * Features:
 * - Custom `toString` implementation for clear representation of an ingredient's details.

 * Example:
 * - Create an ingredient:
 *   Ingredient ingredient = new Ingredient("Milk", 2, UnitMeasure.LITRES, LocalDate.now(), LocalDate.now().plusDays(7));
 *
 * - Set and retrieve properties:
 *   ingredient.setName("Butter");
 *   String name = ingredient.getName();

 * Author: Ke An NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.features.shared.models;

import fr.insa.bourges.firstapplicationjfx.base.database.AbstractEntity;

import java.time.LocalDate;
import java.util.List;

public class Ingredient extends AbstractEntity {
    private String name;
    private double quantity;
    private UnitMeasure unit;
    private LocalDate addDate;
    private LocalDate expirationDate;

    private CategoryIngredient categoryIngredient;



    public Ingredient() {
    }

    public Ingredient(String name, double quantity, UnitMeasure unit,
                      LocalDate addDate, LocalDate expirationDate) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.addDate = addDate;
        this.expirationDate = expirationDate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public UnitMeasure getUnit() {
        return unit;
    }

    public void setUnit(UnitMeasure unit) {
        this.unit = unit;
    }

    public LocalDate getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setCategoryIngredient(CategoryIngredient categoryIngredient) {
        this.categoryIngredient = categoryIngredient;
    }
    public CategoryIngredient getCategoryIngredient() {
        return categoryIngredient;
    }



    //!TODO: add category to ingredient toString()
    @Override
    public String toString() {
        return "{ " + "Id: " + this.getId() + ", " +  "Name: " + this.getName() + ", " + "Quantity: " + this.getQuantity()
                + this.getUnit() + ", " + "AddDate: " + this.getAddDate() + ", " + "ExpirationDate: " + this.getExpirationDate() + " }";
    }
}
