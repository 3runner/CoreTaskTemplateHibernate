package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    public static SessionFactory getSessionFactory() {
        Configuration cfg = new Configuration();
        Properties properties = new Properties();

        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.setProperty(Environment.HBM2DDL_AUTO,"update");
        properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.setProperty(Environment.USER, "admin");
        properties.setProperty(Environment.PASS, "admin");
        properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/pp_db?useSSL=false");
        properties.setProperty(Environment.SHOW_SQL, "true");
        properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.setProperty(Environment.USE_NEW_ID_GENERATOR_MAPPINGS, "jm.task.core.jdbc.model.User");

        cfg.addProperties(properties).addAnnotatedClass(User.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());

        return cfg.buildSessionFactory(builder.build());
    }

    public static Connection getConnection() {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/pp_db?useSSL=false";
        String user = "admin";
        String password = "admin";
        String jdbcDriver = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Problem with connection creation...\n" + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
