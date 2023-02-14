package com.solvd.taxiservices.models.orders;

import java.util.Date;
import java.util.List;

public class Order {
    private Integer id;
    private Date orderTime;
    private Integer clientId;
    private Integer driverId;
    private Integer operatorId;
    private Integer rating;
    private String reviewText;
    private Integer carId;
    private String start;
    private String finish;

    public Order(Integer id, Date orderTime, Integer clientId,
                 Integer driverId, Integer operatorId,
                 Integer carId, String start, String finish) {
        this.id = id;
        this.orderTime = orderTime;
        this.clientId = clientId;
        this.driverId = driverId;
        this.operatorId = operatorId;
        this.carId = carId;
        this.start = start;
        this.finish = finish;
    }

    public Order(Integer id, Date orderTime, Integer clientId,
                 Integer driverId, Integer carId,
                 String start, String finish) {
        this.id = id;
        this.orderTime = orderTime;
        this.clientId = clientId;
        this.driverId = driverId;
        this.carId = carId;
        this.start = start;
        this.finish = finish;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderTime=" + orderTime +
                ", clientId=" + clientId +
                ", driverId=" + driverId +
                ", operatorId=" + operatorId +
                ", rating=" + rating +
                ", reviewText='" + reviewText + '\'' +
                ", carId=" + carId +
                ", start='" + start + '\'' +
                ", finish='" + finish + '\'' +
                '}';
    }
}
