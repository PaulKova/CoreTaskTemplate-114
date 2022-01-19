package jm.task.core.jdbcLesson.dao;

import jm.task.core.jdbcLesson.model.User;
import jm.task.core.jdbcLesson.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users(id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL ," +
                    " name VARCHAR(100) NOT NULL, lastName VARCHAR(100) NOT NULL, " +
                    "age SMALLINT NOT NULL)";

            session.createSQLQuery(CREATE_TABLE).executeUpdate();
            transaction.commit();
            System.out.println("Таблица создана");

        } catch (Exception e) {
            System.out.println("Не удалось создать таблицу");
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception t) {
                t.printStackTrace();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String sqlDeleteTable = "DROP TABLE IF EXISTS users";
            session.createSQLQuery(sqlDeleteTable).executeUpdate();
            transaction.commit();
            System.out.println("Таблица удалена");

        } catch (Exception e) {
            System.out.println("Не удалось удалить таблицу");
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception t) {
                t.printStackTrace();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            transaction.commit();

        } catch (Exception e) {
            System.out.println("Не удалось создать новго пользователя с именем " + name);
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception t) {
                t.printStackTrace();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createQuery("delete User where id = :param")
                    .setParameter("param", id)
                    .executeUpdate();

            //вариант 2
            //User user = (User) session.get(User.class, id);
            //session.delete(user);

            transaction.commit();
            System.out.println("Пользователь с id " + id + " удален");
        } catch (Exception e) {
            System.out.println("Не удальсь удалить пользователя с id " + id);
            e.printStackTrace();
            try {
                if (transaction != null) {
                    transaction.rollback();
                }
            } catch (Exception t) {
                t.printStackTrace();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {

        Transaction transaction;
        Session session = null;
        List<User> allUserList = new ArrayList<>();
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            allUserList = session.createQuery("from User").list();
            transaction.commit();
            System.out.println("Юзеры получены");

        } catch (Exception e) {
            System.out.println("Не удалось получить всех пользователей");
            e.printStackTrace();

        } finally {
            if (session != null) {
                session.close();
            }
        }
        allUserList.forEach(System.out::println);
        return allUserList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction;
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String cleanTable = "DELETE FROM users";
            session.createSQLQuery(cleanTable).executeUpdate();

            transaction.commit();
            System.out.println("Теперь эта таблица пуста");

        } catch (Exception e) {
            System.out.println("Не удалось очистить таблицу");
            e.printStackTrace();

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

