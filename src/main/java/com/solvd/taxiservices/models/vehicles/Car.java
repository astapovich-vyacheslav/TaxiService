package com.solvd.taxiservices.models.vehicles;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.solvd.taxiservices.models.people.Client;
import com.solvd.taxiservices.utils.DateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.ArrayList;
import java.util.Date;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlElement(name = "carId")
    private Integer id;
    private String model;
    private String color;
    @XmlElement(name = "carNumber")
    private String number;
    @XmlJavaTypeAdapter(DateAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateOfAcquirement;
    @XmlElementWrapper(name = "clients")
    @XmlElement(name = "client")
    public ArrayList<Client> clientsInCar;
    public Car() {
    }

    public Car(String model, String color, String number) {
        this.model = model;
        this.color = color;
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String fullInfo() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", number='" + number + '\'' +
                ", dateOfAcquirement=" + dateOfAcquirement +
                ", clientsInCar=" + clientsInCar +
                '}';
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public Date getDateOfAcquirement() {
        return dateOfAcquirement;
    }
    public void setDateOfAcquirement(Date dateOfAcquirement) {
        this.dateOfAcquirement = dateOfAcquirement;
    }

    public ArrayList<Client> getClientsInCar() {
        return clientsInCar;
    }

    public void setClientsInCar(ArrayList<Client> clientsInCar) {
        this.clientsInCar = clientsInCar;
    }
}
