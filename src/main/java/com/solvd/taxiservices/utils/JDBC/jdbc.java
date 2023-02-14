package com.solvd.taxiservices.utils.JDBC;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
//import com.mysql.cj.jdbc.Driver;

public class jdbc {
    public static Connection con;
    public static Statement statement;
    public static void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://127.0.0.1:3306/?user=root";
        String name = "root";
        String password = "root";

        try {
            //con = DriverManager.getConnection(url, name, password);
            //con = ConnectionPool.getInstance().getConnection();
            con = (new ConnectionPool(url, name, password, 5)).getConnection();
            statement = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
