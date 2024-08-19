package ru.feolika.Tasks.w2d4.entityDAO;

import java.util.List;

public interface IDAO<T> {

    void save(T entity);

    void update(T entity);

    void delete(Long id);

    List<T> findAll();

    T findById(Long id);
}
