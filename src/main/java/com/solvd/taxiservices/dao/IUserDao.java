package com.solvd.taxiservices.dao;

import com.solvd.taxiservices.models.people.User;
import com.solvd.taxiservices.models.vehicles.Car;

import java.util.List;

public interface IUserDao extends IDao<User>{
    public List<User> getAll();
    public User getById(int id);
    public void update(int id, User user);
    public void delete(int id);
    public void insert(User user);
}
