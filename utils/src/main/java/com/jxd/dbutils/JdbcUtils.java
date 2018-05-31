package com.jxd.dbutils;

import java.sql.*;
import java.util.ResourceBundle;

public class JdbcUtils {

    private static String driver ;
    private static String url ;
    private static String user ;
    private static String password ;

    static{
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        driver = bundle.getString("jdbc.driver");
        url = bundle.getString("jdbc.url");
        user = bundle.getString("jdbc.user");
        password = bundle.getString("jdbc.password");
    }

    /**
     * 获得链接
     * @return 链接
     */
    public static Connection getConnection(){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url,user,password);
            return connection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 释放资源
     */
    public static void release(Connection connection , PreparedStatement preparedStatement, ResultSet resultSet){
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement!=null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            if (connection!=null&&(!connection.isClosed())){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 释放资源
     */
    public static void release(Connection connection , PreparedStatement preparedStatement){
        if (preparedStatement!=null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            if (connection!=null&&(!connection.isClosed())){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}