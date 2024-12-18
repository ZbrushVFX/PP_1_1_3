package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {


    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        // Создание таблицы User(ов)
        userService.createUsersTable();

        // Добавление 4 User(ов) в таблицу с данными на свой выбор
        userService.saveUser("Mikhail", "Ivanov", (byte) 35);
        System.out.println("User с именем — Mikhail добавлен в базу данных");

        userService.saveUser("Oksana", "Guli", (byte) 26);
        System.out.println("User с именем — Oksana добавлен в базу данных");

        userService.saveUser("Dmitri", "Galuza", (byte) 35);
        System.out.println("User с именем — Dmitri добавлен в базу данных");

        userService.saveUser("Evgeni", "Prohanov", (byte) 35);
        System.out.println("User с именем — Evgeni добавлен в базу данных");

        // Получение всех User из базы и вывод в консоль
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }

        // Очистка таблицы User(ов)
        userService.cleanUsersTable();

        // Удаление таблицы
        userService.dropUsersTable();
    }


}