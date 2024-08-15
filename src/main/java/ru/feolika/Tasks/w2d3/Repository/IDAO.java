package ru.feolika.Tasks.w2d3.Repository;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * CRUD интерфейс для работы с бд
 * @param <T>
 */
public interface IDAO<T> {
    public void addEntity(T entity) throws SQLException;

    public void removeEntity(T entity) throws SQLException;

    public void updateEntity(int ID,T entity) throws SQLException;

    public T getEntity(int ID) throws SQLException;

    public ArrayList<T> getAllEntities() throws SQLException;
    default void close(){

    }
}
