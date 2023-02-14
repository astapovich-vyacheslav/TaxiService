package com.solvd.taxiservices.dao;

import com.solvd.taxiservices.models.orders.Order;
import com.solvd.taxiservices.models.vehicles.Car;

import java.util.List;

public interface IOrderDao extends IDao<Order>{
    public List<Order> getAll();
    public Order getById(int id);
    public void update(int id, Order order);
    public void delete(int id);
    public void insert(Order order);
}
