/**
 * CategoryIngredient classifies ingredients based on their type.

 * Enum Values:
 * - `GRAIN`: Grains and cereals (e.g., rice, wheat).
 * - `VEGETABLE`: Vegetables (e.g., carrots, spinach).
 * - `MEAT`: Meat products (e.g., chicken, beef).
 * - `SPICE`: Spices and seasonings (e.g., pepper, turmeric).
 * - `DAIRY`: Dairy products (e.g., milk, cheese).
 * - `FRUIT`: Fruits (e.g., apples, bananas).
 * - `FISH`: Fish and seafood (e.g., salmon, shrimp).

 * Usage:
 * - Categorize an ingredient:
 *   ingredient.setCategoryIngredient(CategoryIngredient.VEGETABLE);

 * Author: Ke An NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.features.shared.models;

public enum CategoryIngredient {
    GRAIN,
    VEGETABLE,
    MEAT,
    SPICE,
    DAIRY,
    FRUIT,
    FISH,
}

