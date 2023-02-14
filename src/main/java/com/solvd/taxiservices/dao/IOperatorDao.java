package com.solvd.taxiservices.dao;

import com.solvd.taxiservices.models.people.Operator;
import com.solvd.taxiservices.models.vehicles.Car;

import java.util.List;

public interface IOperatorDao extends IDao<Operator> {
    public List<Operator> getAll();
    public Operator getById(int id);
    public void update(int id, Operator operator);
    public void delete(int id);
    public void insert(Operator operator);
}
