package model.service;

import java.util.List;

public interface IGeneric<T> {
    void add(T t);
    void update(T t);
    void delete(T t);
    T findById(T object, int id);
    List<T> getAll(T object);
}
