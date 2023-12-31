package jm.task.core.jdbc.service;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoJDBCImpl();
    UserDao userDaoHibernate = new UserDaoHibernateImpl();
    public void createUsersTable() {
        userDao.createUsersTable();
        userDaoHibernate.createUsersTable();

    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
        userDaoHibernate.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        userDaoHibernate.saveUser(name, lastName, age);
        System.out.println("Новый user - " + name + " " + lastName + " возраст " + age + " добавлен");
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
        userDaoHibernate.removeUserById(id);

    }

    public List<User> getAllUsers() {
        List<User> userList = userDaoHibernate.getAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }
            return userList;

    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
      userDaoHibernate.cleanUsersTable();
    }
}
