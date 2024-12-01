/**
 * IngredientFormType defines the types of operations for the ingredient form modal.
 * It is used to distinguish between adding a new ingredient and editing an existing one.

 * Enum Values:
 * - `ADD`: Represents the operation for creating a new ingredient.
 * - `EDIT`: Represents the operation for modifying an existing ingredient.

 * Usage:
 * - Passed to the ingredient form modal to configure its behavior dynamically.
 * - Example: Set the form type in the modal:
 *   ingredientFormModal.setIngredientFormType(IngredientFormType.ADD);

 * Author: Ke An NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.features.ingredient.view.components;

public enum IngredientFormType {
    ADD,
    EDIT,
}
