package com.solvd.taxiservices.utils;

import java.util.List;

public class ListPrinter {
    public static <T> void printList(List<T> list) {
        list.forEach(x -> System.out.println(x.toString()));
    }
}
