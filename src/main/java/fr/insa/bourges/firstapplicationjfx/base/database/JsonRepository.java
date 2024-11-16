package fr.insa.bourges.firstapplicationjfx.base.database;

import java.util.List;

public class JsonRepository<T> implements Repository<T> {
    @Override
    public void persist(T entity) {

    }

    @Override
    public void removeByAttribute(String attribute, Object value) {

    }

    @Override
    public List<T> findAll() {
        return List.of();
    }

    @Override
    public T findById(int id) {
        return null;
    }
}
