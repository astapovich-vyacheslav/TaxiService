package com.solvd.taxiservices.services.carFactory;

public class FordInfo implements ICarInfo{
    @Override
    public void printInfo() {
        System.out.println("Ford Motor Company (commonly known as Ford) is an American multinational automobile" +
                " manufacturer headquartered in Dearborn, Michigan, United States. It was founded by Henry Ford" +
                " and incorporated on June 16, 1903.");
    }
}
