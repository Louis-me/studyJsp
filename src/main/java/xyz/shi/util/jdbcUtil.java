package xyz.shi.util;

import java.sql.*;
import java.util.ResourceBundle;
import java.sql.*;
import java.util.ResourceBundle;

public class jdbcUtil {
    private static  String url;
    private static  String user;
    private static  String pwd;

    static {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
            url = resourceBundle.getString("url");
            pwd = resourceBundle.getString("password");
            user = resourceBundle.getString("user");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static Connection getConnection() {
        try {
            // 一定要加这个，不然servlet无法识别
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取数据库连接（里面内容依次是：主机名和端口、用户名、密码），返回数据库链接
            return DriverManager.getConnection(url,user, pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // 关闭结果集
    public static void  closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    // 关闭预处理对象
    public static void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    // 关闭数据库连接
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        // 关闭如上三个
    public static void closeResource(ResultSet resultSet, Statement statement, Connection connection) {
        closeResultSet(resultSet);
        closeStatement(statement);
        closeConnection(connection);
    }
}
