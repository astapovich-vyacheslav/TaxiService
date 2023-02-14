package com.solvd.taxiservices.models.people;

import java.util.ArrayList;

public class Driver extends User{
    private ArrayList<Integer> carIds;
    private ArrayList<Integer> orderIds;

    public Driver(String username, String email, String password) {
        super(username, email, password);
        this.orderIds = new ArrayList<>();
        this.carIds = new ArrayList<>();
    }

    public void addOrder(int oi) {
        this.orderIds.add(oi);
    }

    public void addCar(int carId) {
        this.carIds.add(carId);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "carIds=" + carIds +
                ", orderIds=" + orderIds +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
