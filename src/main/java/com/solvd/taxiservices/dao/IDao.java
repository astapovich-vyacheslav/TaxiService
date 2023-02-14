package com.solvd.taxiservices.dao;

import java.util.List;

public interface IDao<T> {
    public List<T> getAll();
    public T getById(int id);
    public void update(int id, T t);
    public void delete(int id);
    public void insert(T t);
}
