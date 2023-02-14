package com.solvd.taxiservices.dao;

import com.solvd.taxiservices.models.people.Driver;
import com.solvd.taxiservices.models.vehicles.Car;

import java.util.List;

public interface IDriverDao extends IDao<Driver> {
    public List<Driver> getAll();
    public Driver getById(int id);
    public void update(int id, Driver t);
    public void delete(int id);
    public void insert(Driver driver);
}
