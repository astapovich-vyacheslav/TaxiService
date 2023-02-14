package com.solvd.taxiservices.services.carFactory;

public class KiaInfo implements ICarInfo{
    @Override
    public void printInfo() {
        System.out.println("Kia Corporation, commonly known as Kia " +
                "(formerly known as Kyungsung Precision Industry and Kia Motors Corporation), " +
                "is a South Korean multinational automobile manufacturer headquartered in Seoul, South Korea.");
    }
}
