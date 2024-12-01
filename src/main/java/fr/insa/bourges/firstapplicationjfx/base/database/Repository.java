/**
 * Repository defines the base interface for managing entities within the persistence context.
 * It provides CRUD operations and additional methods for persisting and querying entities.
 * This interface is designed to abstract the interaction between the application and the
 * underlying storage mechanism, ensuring a clean separation of concerns.

 * Responsibilities:
 * - **CRUD Operations**:
 *   - `persist`: Adds a new entity to the persistence context, assigning it a unique identifier.
 *   - `update`: Updates an existing tracked entity in the persistence context.
 *   - `deleteById`: Marks an entity for deletion based on its unique identifier.
 * - **Synchronization**:
 *   - `flush`: Synchronizes the persistence context with the database or storage (e.g., a JSON file).
 * - **Querying**:
 *   - `findAll`: Retrieves all entities managed by the repository.
 *   - `findByAttribute`: Finds entities based on a specific field and value dynamically.
 *   - `findById`: Retrieves an entity by its unique identifier.

 * Usage:
 * - Implement this interface for specific storage mechanisms (e.g., JSON, SQL).
 * - Example: `JsonRepository` can persist entities to a JSON file.

 * Example:
 * public class RecipeRepository implements Repository<Recipe> {
 *     // Implementation of CRUD operations
 * }

 * Author: Ke An NGUYEN
 */
package fr.insa.bourges.firstapplicationjfx.base.database;

import java.util.List;

public interface Repository<T extends AbstractEntity> {
    /**
     * Persist an untracked entity to persistence context. Return tracked entity
     * The persisted entity will be only persisted to database if `flush` is called
     * @param entity untracked entity
     * @return tracked entity (with id not null)
     */
    T persist(T entity);

    /**
     * Update a tracked entity on persistence context. Return updated entity
     * The updated entity will be only updated to database if `flush` is called
     * @param entity tracked entity (with id not null)
     * @return updated entity
     */
    T update(T entity);

    /**
     * Delete an entity by id
     * The entity will be only deleted to database if `flush` is called
     */
    void deleteById(String id);

    /**
     * Mirror the persistence context to JSON file
     */
    void flush();

    /**
     * Find all entities and return
     */
    List<T> findAll();

    /**
     * Find all entities having value of field
     */
    List<T> findByAttribute(String field, Object value) throws NoSuchFieldException, IllegalAccessException;
    /**
     * Find all entities by id
     */
    T findById(String id);
}
