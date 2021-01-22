package jm.task.core.jdbc.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/pp_db?useSSL=false";
    private static final String user = "admin";
    private static final String password = "admin";
    private static final String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(jdbcDriver).getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Problem with connection creation...\n" + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Statement getStatement() {
        Statement statement = null;

        try {
            statement = Util.getConnection().createStatement();
        } catch (SQLException e) {
            System.out.println("Problem with statement creation...\n" + e);
        }
        return statement;
    }
}
