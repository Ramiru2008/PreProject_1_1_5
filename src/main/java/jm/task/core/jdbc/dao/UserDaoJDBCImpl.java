package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() {
        try { Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT,  name VARCHAR(45), lastName VARCHAR(45), age INT)");
            System.out.println("Ты не поверишь, но таблица создана!");
       } catch (SQLException e) {
            e.printStackTrace();
       }
    }
    public void dropUsersTable() {
        try { Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try { Connection connection = Util.getConnection();
            PreparedStatement preparedstatement = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)");
            preparedstatement.setString(1, name);
            preparedstatement.setString(2, lastName);
            preparedstatement.setInt(3, age);
            preparedstatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
       }

        public void removeUserById(long id) {
            try { Connection connection = Util.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE (id = ?)");
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
                    System.out.println(preparedStatement);

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try { Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                userList.add(user);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try { Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
