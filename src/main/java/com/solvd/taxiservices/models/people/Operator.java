package com.solvd.taxiservices.models.people;

public class Operator extends User {
    private String number;

    public Operator(String username, String email, String password, String number) {
        super(username, email, password);
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "number='" + number + '\'' +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
