/**
 * Enum `RecipePageType` represents the types of pages available in the recipe management feature.
 * This is used to distinguish between different actions performed on a recipe.

 * Page Types:
 * - **ADD**: Represents the page used for adding a new recipe.
 * - **EDIT**: Represents the page used for editing an existing recipe.

 * Purpose:
 * - Enables a clear distinction between the "add" and "edit" modes when navigating between pages
 *   or interacting with recipe-related forms.

 * Usage:
 * - Typically used in the navigation logic to determine the appropriate page behavior
 *   (e.g., initializing fields for a new recipe or populating fields for an existing recipe).

 * Author: Anh Tuan NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.features.recipe.view;

public enum RecipePageType {
    ADD,
    EDIT,
}
