package com.solvd.taxiservices.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class MyBatisDaoFactory {

    private final static MyBatisDaoFactory myBatisDaoFactory = new MyBatisDaoFactory();
    private static SqlSessionFactory sqlSessionFactory;

    private MyBatisDaoFactory() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            System.out.println("config file not found");
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
