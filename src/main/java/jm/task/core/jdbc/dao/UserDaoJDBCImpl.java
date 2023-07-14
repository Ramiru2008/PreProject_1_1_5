package jm.task.core.jdbc.dao;

import com.mysql.cj.util.Util;
import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class UserDaoJDBCImpl extends Util implements UserDao {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mybase";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Romaneus2015";


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT,  name VARCHAR(45), lastName VARCHAR(45), age INT)");
            System.out.println("Ты не поверишь, но таблица создана!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        public void dropUsersTable() {
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
                Statement statement = connection.createStatement();
                statement.executeUpdate("DROP TABLE IF EXISTS users");
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
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
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
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
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
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
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
