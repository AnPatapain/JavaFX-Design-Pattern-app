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
