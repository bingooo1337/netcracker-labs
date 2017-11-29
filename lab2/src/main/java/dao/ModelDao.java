package dao;

import models.Model;

import java.util.Set;

public interface ModelDao<T extends Model> {
    T getById(long id);
    void deleteById(long id);
    void insert(T model);
    void update(T model);
    void delete(T model);
    Set<T> getAll();
}
