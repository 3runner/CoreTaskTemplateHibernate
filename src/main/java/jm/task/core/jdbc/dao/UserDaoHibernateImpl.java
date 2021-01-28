package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS User (" +
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

    @Override
    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS User;");
        } catch (SQLException e) {
            System.out.println("Table deletion is failed...\n" + e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            Transaction tr = session.beginTransaction();
            session.save(user);
            tr.commit();
        } catch (Exception e) {
            System.out.println("Exception in saveUser method...\n" + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            Transaction tr = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User WHERE id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            tr.commit();
        } catch (Exception e) {
            System.out.println("Exception in removeUserById method...\n" + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        List<User> users = null;

        try {
            session = Util.getSessionFactory().openSession();
            Transaction tr = session.beginTransaction();
            Query query = session.createQuery("from User");
            users = query.list();
            tr.commit();
        } catch (Exception e) {
            System.out.println("Exception in removeUserById method...\n" + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User");
        query.executeUpdate();

        tr.commit();
        session.close();
    }
}
