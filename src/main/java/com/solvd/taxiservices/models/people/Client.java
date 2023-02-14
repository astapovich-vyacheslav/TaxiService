package com.solvd.taxiservices.models.people;

import java.util.ArrayList;

public class Client extends User{
    private String number;
    private ArrayList<Integer> orderIds;

    public Client(String username, String email, String password, String number) {
        super(username, email, password);
        this.number = number;
        this.orderIds = new ArrayList<>();
    }
    public Client(User user, String number) {
        super(user.getUsername(), user.getEmail(), user.getPassword());
        this.number = number;
        this.orderIds = new ArrayList<>();
    }

    public Client() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void addOrder(int orderId) {
        this.orderIds.add(orderId);
    }

    @Override
    public String toString() {
        return "Client{" +
                "number='" + number + '\'' +
                ", orderIds=" + orderIds +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
