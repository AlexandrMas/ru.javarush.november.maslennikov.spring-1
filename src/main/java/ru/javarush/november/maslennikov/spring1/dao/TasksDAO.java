package ru.javarush.november.maslennikov.spring1.dao;

import java.util.List;

public interface TasksDAO<T> {
    List<T> getAll();

    List<T> getItems(int offset, int count);

    T getById(int id);

    void save(T entity);

    void update(T entity);

    void delete(T entity);

    void deleteById(final int entityId);

    int getTotalCount();
}
