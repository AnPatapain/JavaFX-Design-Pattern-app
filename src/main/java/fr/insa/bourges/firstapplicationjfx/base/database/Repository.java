package fr.insa.bourges.firstapplicationjfx.base.database;

import java.util.List;

public interface Repository<T> {
    void persist(T entity);
    void removeByAttribute(String attribute, Object value);
    List<T> findAll();
    List<T> findByAttribute(String attribute, Object value);
    T findById(int id);
    void flush();
}
