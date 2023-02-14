package com.solvd.taxiservices.services;

import com.solvd.taxiservices.dao.ICarDao;
import com.solvd.taxiservices.models.vehicles.Car;
import com.solvd.taxiservices.utils.JDBC.jdbc;
import com.solvd.taxiservices.utils.MyBatisDaoFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDao implements ICarDao {
    private static final SqlSessionFactory SESSION_FACTORY = MyBatisDaoFactory.getSqlSessionFactory();

    @Override
    public ArrayList<Car> getAll() {
        ArrayList<Car> result = new ArrayList<>();
        try {
            ResultSet qResult = jdbc.statement.executeQuery("select * from taxiservice.car");
            while (qResult.next()) {
                Car car = new Car(qResult.getString(2),
                        qResult.getString(3),
                        qResult.getString(4));
                car.setId(qResult.getInt(1));
                result.add(car);
            }
        } catch (SQLException ignored) {
        }
        return result;
    }

//    @Override
//    public Car getById(int id) {
//        try {
//            ResultSet qResult = jdbc.statement.executeQuery("select * from taxiservice.car where id = " + id);
//            qResult.next();
//            Car car = new Car(qResult.getString(2),
//                    qResult.getString(3),
//                    qResult.getString(4));
//            car.setId(id);
//            return car;
//        } catch (SQLException ignored) {
//        }
//        return null;
//    }
    @Override
    public Car getById(int id) {
    Car car;
    try(SqlSession sqlSession = SESSION_FACTORY.openSession()) {
        ICarDao carDAO = sqlSession.getMapper(ICarDao.class);
        car = carDAO.getById(id);
    }
    return car;
    }

    @Override
    public void update(int id, Car car) {
        try {
            String sql = "update taxiservice.car set model = '" + car.getModel() +
                    "', color = '" + car.getColor() + "', number = '" + car.getNumber() + "' where id = " + id;
            int result = jdbc.statement.executeUpdate(sql);
            if (result == 0) System.out.println("error");
        }
        catch (SQLException ignored) {}
    }

    @Override
    public void delete(int id) {
        try {
            int result = jdbc.statement.executeUpdate("delete from taxiservice.car where id = " + id);
            if (result == 0) System.out.println("error");
        }
        catch (SQLException ignored) {}
    }

    @Override
    public void insert(Car car) {
        try {
            String sql = "insert into taxiservice.car (model, color, number) values" +
                    "('" + car.getModel() + "', '" + car.getColor() + "', '" + car.getNumber() + "')";
            int result = jdbc.statement.executeUpdate(sql);
            if (result == 0) System.out.println("error");
        }
        catch (SQLException ignored) {}
    }
}
