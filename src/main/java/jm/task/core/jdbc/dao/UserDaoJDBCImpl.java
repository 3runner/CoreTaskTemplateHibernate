package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                    "  id BIGINT NOT NULL AUTO_INCREMENT," +
                    "  name VARCHAR(45) NOT NULL," +
                    "  lastName VARCHAR(45) NOT NULL," +
                    "  age INT(3) NOT NULL," +
                    "  PRIMARY KEY (id)," +
                    "  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE);");
        } catch (SQLException e) {
            System.out.println("Table creation is failed...\n" + e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users;");
        } catch (SQLException e) {
            System.out.println("Table deletion is failed...\n" + e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preSt = Util.getConnection()
                .prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?);")) {
            preSt.setString(1, name);
            preSt.setString(2, lastName);
            preSt.setInt(3, age);
            preSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("User adding into table is failed...\n" + e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preSt = Util.getConnection()
                .prepareStatement("DELETE FROM users WHERE id = ?;")) {
            preSt.setLong(1, id);
            preSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("User adding into table is failed...\n" + e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users= new ArrayList<>();

        try (Statement statement = Util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users;")) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                Byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Problem with getting all users from table...\n" + e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users;");
        } catch (SQLException e) {
            System.out.println("Table cleaning is failed...\n" + e);
        }
    }
}
