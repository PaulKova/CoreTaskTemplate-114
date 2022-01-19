package jm.task.core.jdbcLesson114;

import jm.task.core.jdbcLesson114.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

//        userService.createUsersTable();
//        userService.saveUser("Dmitriy", "Ivakin", (byte) 30);
//        userService.saveUser("Ivan", "Nikolos", (byte) 21);
//        userService.saveUser("Mike", "Lukas", (byte) 24);
//        userService.saveUser("Nadezda", "Babkina", (byte) 67);
//        userService.getAllUsers();
//        userService.removeUserById(2);
//        userService.getAllUsers();
//        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
