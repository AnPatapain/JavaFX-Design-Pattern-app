/**
 * AbstractEntity serves as the base class for all entities in the application, providing a common structure
 * and functionality for entities that need to be uniquely identified within the system.

 * Responsibilities:
 * - Defines a unique identifier (`id`) for each entity, ensuring consistent identification across the application.
 * - Provides getter and setter methods for the `id` field to allow flexible manipulation and assignment.

 * Design Rationale:
 * - This class promotes code reuse by serving as a parent for all entities in the database layer.
 * - The use of a common `id` field ensures that every entity can be uniquely identified and managed,
 *   particularly when working with persistence mechanisms.

 * Usage:
 * - Extend this class to create specific entity types (e.g., `Recipe`, `Ingredient`).
 * - Ensure the `id` is set during entity creation or database persistence to maintain uniqueness.

 * Example:
 * public class Recipe extends AbstractEntity {
 *     private String name;
 *     private String category;
 *
 *     // Additional fields, constructors, getters, and setters
 * }
 *
 * Author: Ke An NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.base.database;

public class AbstractEntity {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AbstractEntity() {}
}
