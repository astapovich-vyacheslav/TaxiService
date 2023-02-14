package com.solvd.taxiservices.services;

import com.solvd.taxiservices.dao.IClientDao;
import com.solvd.taxiservices.models.people.Client;
import com.solvd.taxiservices.utils.JDBC.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientDao implements IClientDao {
    @Override
    public ArrayList<Client> getAll() {
        ArrayList<Client> result = new ArrayList<>();
        try {
            ResultSet qResult = jdbc.statement.executeQuery("select * from taxiservice.client C " +
                    "inner join taxiservice.user_info U on C.user_info_id = U.id");
            while (qResult.next()) {
                Client client = new Client(qResult.getString(3), qResult.getString(5),
                        qResult.getString(6), qResult.getString(7));
                client.setId(qResult.getInt(1));
                result.add(client);
            }
        }
        catch (SQLException ignored) {}
        return result;
    }

    @Override
    public Client getById(int id) {
        try {
            ResultSet qResult = jdbc.statement.executeQuery("select * from taxiservice.client C " +
                    "inner join taxiservice.user_info U on C.user_info_id = U.id AND C.id = " + id);
            qResult.next();
            Client client = new Client(qResult.getString(3), qResult.getString(5),
                    qResult.getString(6), qResult.getString(7));
            client.setId(id);
            return client;
        }
        catch (SQLException ignored) {}
        return null;
    }

    @Override
    public void update(int id, Client client) {
        try {
            String sql = "select user_info_id from taxiservice.client";
            ResultSet resultSet = jdbc.statement.executeQuery(sql);
            resultSet.next();
            int userInfoId = resultSet.getInt(1);

            sql = "update taxiservice.client set name = '" + client.getUsername() + "' where id = " + id;
            int result = jdbc.statement.executeUpdate(sql);

            sql = "update taxiservice.user_info set email = '" + client.getEmail() +
                    "', password = '" + client.getPassword() + "', number = '" + client.getNumber() + "' where id = " + userInfoId;
            result = jdbc.statement.executeUpdate(sql);
            if (result == 0) System.out.println("error");
        }
        catch (SQLException ignored) {}
    }

    @Override
    public void delete(int id) {
        try {
            int result = jdbc.statement.executeUpdate("delete from taxiservice.client where id = " + id);
            if (result == 0) System.out.println("error");
        }
        catch (SQLException ignored) {}
    }

    @Override
    public void insert(Client client) {
        try {
            String sql = "insert into taxiservice.user_info (email, password, number) values" +
                    "('" + client.getEmail() + "', '" + client.getPassword() + "', '" + client.getNumber() + "')";
            int result = jdbc.statement.executeUpdate(sql);
            sql = "select max(id) from taxiservice.user_info";
            ResultSet resultSet = jdbc.statement.executeQuery(sql);
            resultSet.next();
            sql = "insert into taxiservice.client (user_info_id, name) values" +
                    "(" + resultSet.getInt(1) + ", '" + client.getUsername() + "')";
            result = jdbc.statement.executeUpdate(sql);
            if (result == 0) System.out.println("error");
        }
        catch (SQLException ignored) {}
    }
}
