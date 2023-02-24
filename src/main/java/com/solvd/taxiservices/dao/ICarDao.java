package com.solvd.taxiservices.dao;

import com.solvd.taxiservices.models.vehicles.Car;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ICarDao extends IDao<Car> {
    @Override
    List<Car> getAll();

    @Select("SELECT * FROM car WHERE id = #{id}")
    @Results(value = {
            @Result(property = "model", column = "model"),
            @Result(property = "color", column = "color"),
            @Result(property = "number", column = "number"),        //fix string and int
            @Result(property = "id", column = "id")
    })
    Car getById(@Param("id")int id);

    @Override
    void update(int id, Car car);

    @Override
    void delete(int id);

    @Override
    void insert(Car car);
}
