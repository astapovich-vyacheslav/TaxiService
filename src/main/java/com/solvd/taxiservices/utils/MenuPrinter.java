package com.solvd.taxiservices.utils;

public class MenuPrinter {
    public static void PrintMenu() {
        System.out.println("""
                1 - print all objects' info
                2 - create object
                3 - update object
                4 - delete object
                5 - print one object
                6 - make an order
                7 - serialize data
                8 - deserialize data
                0 - exit""");
    }
    public static void PrintSubMenu() {
        System.out.println("""
                1 - client
                2 - operator
                3 - driver
                4 - car""");
    }
    public static void AskForId() {
        System.out.println("enter object's id");
    }
}
