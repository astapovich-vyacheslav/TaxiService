package com.solvd.taxiservices;

import com.solvd.taxiservices.models.orders.Order;
import com.solvd.taxiservices.models.people.Client;
import com.solvd.taxiservices.models.people.Driver;
import com.solvd.taxiservices.models.people.Operator;
import com.solvd.taxiservices.models.vehicles.Car;
import com.solvd.taxiservices.services.CarDao;
import com.solvd.taxiservices.services.ClientDao;
import com.solvd.taxiservices.utils.JDBC.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.solvd.taxiservices.utils.JDBC.jdbc;
import com.solvd.taxiservices.utils.ListPrinter;
import com.solvd.taxiservices.utils.MenuPrinter;
import com.solvd.taxiservices.utils.Serializer;

public class Main {
    public static void main(String[] args) {
        jdbc.connect();
        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<Client> clients = new ArrayList<>();
        ArrayList<Driver> drivers = new ArrayList<>();
        ArrayList<Operator> operators = new ArrayList<>();
        ArrayList<Car> cars = new ArrayList<>();

        CarDao carDao = new CarDao();
        ClientDao clientDao = new ClientDao();

        Serializer serializer = new Serializer();
        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            MenuPrinter.PrintMenu();
//            1 - print all objects' info
//            2 - create object
//            3 - update object
//            4 - delete object
//            5 - print one object
//            6 - make an order
//            0 - exit

//            1 - client
//            2 - operator
//            3 - driver
//            4 - car
            choice = scanner.nextByte();
            switch (choice) {
                //printing all
                case 1:
                    MenuPrinter.PrintSubMenu();
                    choice = scanner.nextByte();
                    switch (choice) {
                        case 1:
                            clients = clientDao.getAll();
                            ListPrinter.printList(clients);
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            cars = carDao.getAll();
                            ListPrinter.printList(cars);
                            break;
                    }
                    break;
                //inserting
                case 2:
                    MenuPrinter.PrintSubMenu();
                    choice = scanner.nextByte();
                    switch (choice) {
                        case 1:
                            scanner.nextLine();
                            System.out.println("enter username");
                            String username = scanner.nextLine();
                            System.out.println("enter email");
                            String email = scanner.nextLine();
                            System.out.println("enter password");
                            String password = scanner.nextLine();
                            System.out.println("enter number");
                            String number = scanner.nextLine();
                            Client client = new Client(username, email, password, number);
                            clientDao.insert(client);
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            scanner.nextLine();
                            System.out.println("enter car model");
                            String model = scanner.nextLine();
                            System.out.println("enter car color");
                            String color = scanner.nextLine();
                            System.out.println("enter car number");
                            String carNumber = scanner.nextLine();
                            Car car = new Car(model, color, carNumber);
                            carDao.insert(car);
                            break;
                    }
                    break;
                //updating
                case 3:
                    MenuPrinter.PrintSubMenu();
                    choice = scanner.nextByte();
                    switch (choice) {
                        case 1: {
                            System.out.println("enter id");
                            int id = scanner.nextByte();
                            scanner.nextLine();
                            System.out.println("enter username");
                            String username = scanner.nextLine();
                            System.out.println("enter email");
                            String email = scanner.nextLine();
                            System.out.println("enter password");
                            String password = scanner.nextLine();
                            System.out.println("enter number");
                            String number = scanner.nextLine();
                            Client client = new Client(username, email, password, number);
                            clientDao.update(id, client);
                        }
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4: {
                            System.out.println("enter id");
                            int id = scanner.nextByte();
                            scanner.nextLine();
                            System.out.println("enter car model");
                            String model = scanner.nextLine();
                            System.out.println("enter car color");
                            String color = scanner.nextLine();
                            System.out.println("enter car number");
                            String carNumber = scanner.nextLine();
                            Car car = new Car(model, color, carNumber);
                            carDao.update(id, car);
                        }
                            break;
                    }
                    break;
                //deletion
                case 4:
                    MenuPrinter.PrintSubMenu();
                    choice = scanner.nextByte();
                    switch (choice) {
                        case 1: {
                            System.out.println("enter id");
                            int id = scanner.nextByte();
                            clientDao.delete(id);
                        }
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4: {
                            System.out.println("enter id");
                            int id = scanner.nextByte();
                            carDao.delete(id);
                        }
                            break;
                    }
                    break;
                //print one
                case 5:
                    MenuPrinter.PrintSubMenu();
                    choice = scanner.nextByte();
                    switch (choice) {
                        case 1: {
                            System.out.println("enter id");
                            int id = scanner.nextByte();
                            System.out.println(clientDao.getById(id).toString());
                        }
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4: {
                            System.out.println("enter id");
                            int id = scanner.nextByte();
                            System.out.println(carDao.getById(id).toString());
                        }
                            break;
                    }
                    break;
                //new order
                case 6:
                    break;
                //serialize
                case 7:
                    System.out.println("enter car id");
                    int id = scanner.nextByte();
                    Car carToFile = carDao.getById(id);
                    try {
                        Date date = new SimpleDateFormat("dd-MM-yyyy").parse("20-11-2002");
                        carToFile.setDateOfAcquirement(date);
                    } catch (ParseException ignored) {}
                    carToFile.setClientsInCar(clientDao.getAll());
                    serializer.serialize(carToFile, "serialization.xml");
                    break;
                //deserialize
                case 8:
                    Car carFromFile = new Car();
                    carFromFile = Serializer.deserialize(carFromFile, "serialization.xml");
                    System.out.println(carFromFile.fullInfo());
                    break;
            }
        } while (choice != 0);
    }
}