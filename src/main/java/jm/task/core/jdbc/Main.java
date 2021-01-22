package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        User user1 = new User("Ivan", "Ivanov", (byte) 16);
        User user2 = new User("Boris", "Borisov", (byte) 18);
        User user3 = new User("Aleksey", "Alekseev", (byte) 20);
        User user4 = new User("Aleksandr", "Aleksandrov", (byte) 22);

        userService.createUsersTable();
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных\n", user1.getName());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных\n", user2.getName());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных\n", user3.getName());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных\n", user4.getName());

        List<User> usersList = userService.getAllUsers();
        for (User user : usersList) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
