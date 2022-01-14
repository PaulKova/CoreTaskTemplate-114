package jm.task.core.jdbcLesson.dao;

import jm.task.core.jdbcLesson.model.User;
import jm.task.core.jdbcLesson.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try {
            PreparedStatement preparedStatement =
                    Util.getConnection().prepareStatement
                            ("CREATE TABLE IF NOT EXISTS users(id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL ," +
                            " name VARCHAR(100) NOT NULL, lastName VARCHAR(100) NOT NULL, " +
                            "age SMALLINT NOT NULL)");
            System.out.println("Таблица создана");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Не удалось создать таблицу");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        try {
            PreparedStatement preparedStatement =
                    Util.getConnection().prepareStatement("DROP TABLE IF EXISTS users");
            System.out.println("Таблица удалена");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Не удалось удалить таблицу");
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try {
            PreparedStatement preparedStatement =
                    Util.getConnection().prepareStatement("INSERT INTO users VALUES (id,?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            System.out.println("User с именем – " + name + " добавлен в базу данных");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Не удалось сохранить пользователя");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        try {
            PreparedStatement preparedStatement =
                    Util.getConnection().prepareStatement("DELETE FROM users WHERE `id`= ?");

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Не удалось удалить пользователя");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        List<User> allUsers = new ArrayList<>();

        try {
            Statement statement = Util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                allUsers.add(user);
            }


        } catch (SQLException e) {
            System.out.println("Не удалось получить всех пользователей");
            e.printStackTrace();
        }
        allUsers.forEach(System.out::println);
        return  allUsers;
    }

    public void cleanUsersTable() {
        try {
            PreparedStatement preparedStatement =
                    Util.getConnection().prepareStatement("DELETE FROM users");

            System.out.println("все пользователи удалены");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Не удалось удалить пользователей");
            e.printStackTrace();
        }
    }
}
