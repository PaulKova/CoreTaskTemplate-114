package jm.task.core.jdbcLesson114.service;

import jm.task.core.jdbcLesson114.dao.UserDao;
import jm.task.core.jdbcLesson114.dao.UserDaoHibernateImpl;
import jm.task.core.jdbcLesson114.model.User;

import java.util.List;


public class UserServiceImpl implements UserService {

//    private final UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//    private final UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
    private final UserDao userDao = new UserDaoHibernateImpl();


    public void createUsersTable() {
//        userDaoJDBC.createUsersTable();
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
//        userDaoJDBC.dropUsersTable();
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
//        userDaoJDBC.saveUser(name, lastName, age);
        userDaoHibernate.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
//        userDaoJDBC.removeUserById(id);
        userDaoHibernate.removeUserById(id);
    }


    public List<User> getAllUsers() {
//        return userDaoJDBC.getAllUsers();
        return userDaoHibernate.getAllUsers();
    }

    public void cleanUsersTable() {
//        userDaoJDBC.cleanUsersTable();
        userDaoHibernate.cleanUsersTable();
    }
}
