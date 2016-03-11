package com.epam.chadov.task3.xml.database;

import java.util.List;

public interface GenericDao<T> {

    List<T> getAll();

    void create(T object);

    void update(T object);

    void delete(Integer id);

    T getById(Integer id);


}
