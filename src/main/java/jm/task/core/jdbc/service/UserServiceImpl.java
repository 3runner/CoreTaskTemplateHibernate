package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao UserService = new UserDaoHibernateImpl();

    public void createUsersTable() {
        UserService.createUsersTable();
    }

    public void dropUsersTable() {
        UserService.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserService.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        UserService.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return UserService.getAllUsers();
    }

    public void cleanUsersTable() {
        UserService.cleanUsersTable();
    }
}
