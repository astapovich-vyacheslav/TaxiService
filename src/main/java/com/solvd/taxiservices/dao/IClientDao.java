package com.solvd.taxiservices.dao;

import com.solvd.taxiservices.models.people.Client;
import com.solvd.taxiservices.models.vehicles.Car;

import java.util.List;

public interface IClientDao extends IDao<Client>{
    public List<Client> getAll();
    public Client getById(int id);
    public void update(int id, Client client);
    public void delete(int id);
    public void insert(Client client);
}
