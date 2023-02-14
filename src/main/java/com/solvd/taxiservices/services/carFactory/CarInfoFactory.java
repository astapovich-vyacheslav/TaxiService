package com.solvd.taxiservices.services.carFactory;

public class CarInfoFactory {
    public ICarInfo getCarInfo(String type) {
        if (type.equalsIgnoreCase("Kia"))
            return new KiaInfo();
        if (type.equalsIgnoreCase("Ford"))
            return new FordInfo();
        return null;
    }
}
