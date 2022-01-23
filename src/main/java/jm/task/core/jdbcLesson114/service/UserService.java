package jm.task.core.jdbcLesson114.service;

import jm.task.core.jdbcLesson114.dao.UserDaoHibernateImpl;
import jm.task.core.jdbcLesson114.model.User;

import java.util.List;

public interface UserService {
    UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
