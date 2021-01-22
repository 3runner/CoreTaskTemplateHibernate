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
        String sqlCreationQuery =
                "CREATE TABLE IF NOT EXISTS pp_db.new_table (" +
                "  id INT NOT NULL AUTO_INCREMENT," +
                "  name VARCHAR(45) NOT NULL," +
                "  lastName VARCHAR(45) NOT NULL," +
                "  age INT(3) NOT NULL," +
                "  PRIMARY KEY (id)," +
                "  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE);";

        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sqlCreationQuery);
        } catch (SQLException e) {
            System.out.println("Table creation is failed...\n" + e);
        }
    }

    public void dropUsersTable() {
        String sqlDropQuery = "DROP TABLE IF EXISTS pp_db.new_table;";

        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sqlDropQuery);
        } catch (SQLException e) {
            System.out.println("Table deletion is failed...\n" + e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlQuery = "INSERT INTO pp_db.new_table (name, lastName, age) VALUES (?, ?, ?);";

        try (PreparedStatement preSt = Util.getConnection().prepareStatement(sqlQuery)) {
            preSt.setString(1, name);
            preSt.setString(2, lastName);
            preSt.setInt(3, age);
            preSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("User adding into table is failed...\n" + e);
        }
    }

    public void removeUserById(long id) {
        String sqlQuery = "DELETE FROM pp_db.new_table WHERE id = ?;";

        try (PreparedStatement preSt = Util.getConnection().prepareStatement(sqlQuery)) {
            preSt.setLong(1, id);
            preSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("User adding into table is failed...\n" + e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users= new ArrayList<>();
        String sqlQuery = "SELECT * FROM pp_db.new_table;";

        try (Statement statement = Util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery)) {
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
        String sqlCleanQuery = "TRUNCATE TABLE pp_db.new_table;";

        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sqlCleanQuery);
        } catch (SQLException e) {
            System.out.println("Table cleaning is failed...\n" + e);
        }
    }
}
