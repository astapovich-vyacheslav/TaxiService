package com.solvd.taxiservices.utils.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {
    private String SQL_VERIFY_CON = "select 1";
    Stack<Connection> freePool = new Stack<>();
    HashSet<Connection> occupiedPool = new HashSet<>();
    private int maxPoolSize;
    private int conNum = 0;
    private String url;
    private String username;
    private String password;

    public ConnectionPool(String url, String username, String password, int maxSize) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.maxPoolSize = maxSize;
    }

    public synchronized Connection getConnection() throws SQLException {
        Connection con = null;
        if (isFull()) {
            throw new SQLException("The connection pool is full");
        }
        con = getConnectionFromPool();
        if (con == null) {
            con = createNewConnectionForPool();
        }
        con = makeAvailable(con);
        return con;
    }

    public synchronized boolean isFull() {
        return ((freePool.size() == 0) && (conNum > maxPoolSize));
    }

    public synchronized void returnConnection(Connection con) throws SQLException {
        if (con == null) {
            throw new NullPointerException();
        }
        if (!occupiedPool.remove(con)) {
            throw new SQLException("The connection is returned already or is in another pool");
        }
        freePool.push(con);
    }

    private Connection createNewConnectionForPool() throws SQLException {
        Connection con = createNewConnection();
        conNum++;
        occupiedPool.add(con);
        return con;
    }

    private Connection createNewConnection() throws SQLException {
        Connection con = null;
        con = DriverManager.getConnection(url, username, password);
        return con;
    }

    private Connection getConnectionFromPool() {
        Connection con = null;
        if (freePool.size() > 0) {
            con = freePool.pop();
            occupiedPool.add(con);
        }
        return con;
    }

    private Connection makeAvailable(Connection con) throws SQLException {
        if (isConnectionAvailable(con)) {
            return con;
        }
        occupiedPool.remove(con);
        conNum--;
        con.close();
        con = createNewConnection();
        occupiedPool.add(con);
        conNum++;
        return con;
    }

    private boolean isConnectionAvailable(Connection con)  {
        try (Statement statement = con.createStatement()){
            statement.executeQuery(SQL_VERIFY_CON);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
