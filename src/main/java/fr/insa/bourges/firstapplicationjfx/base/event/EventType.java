/**
 * EventType is an enumeration that defines the types of events used within the application.
 * These events facilitate navigation between pages in application.

 * Purpose:
 * - Serves as a centralized definition of all possible event types in the application.
 * - Used by the event system (e.g., EventDispatcher) to manage and dispatch events.

 * Event Types:
 * - `SHOW_HOME_PAGE`: Triggered to display the home page.
 * - `SHOW_RECIPE_LIST_PAGE`: Triggered to display the recipe list page.
 * - `SHOW_RECIPE_ADD_PAGE`: Triggered to display the recipe addition page.
 * - `SHOW_RECIPE_INDICATION_PAGE`: Triggered to display the recipe indication page.
 * - `SHOW_INGREDIENT_LIST_PAGE`: Triggered to display the ingredient list page.
 * - `SHOW_INGREDIENT_ADD_PAGE`: Triggered to display the ingredient addition page.

 * Usage:
 * - Used in conjunction with EventDispatcher and EventListener to handle UI transitions
 *   and other application-level events.

 * Author: Ke An NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.base.event;

public enum EventType {
    SHOW_HOME_PAGE,
    SHOW_RECIPE_LIST_PAGE,
    SHOW_RECIPE_ADD_PAGE,
    SHOW_RECIPE_INDICATION_PAGE,
    SHOW_INGREDIENT_LIST_PAGE,
    SHOW_INGREDIENT_ADD_PAGE,
}
