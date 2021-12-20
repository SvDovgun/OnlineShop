package com.luxoft.shop.dao.jdbc;

import com.luxoft.shop.dao.UserDao;
import com.luxoft.shop.dao.jdbc.mapper.UserRowMapper;
import com.luxoft.shop.entity.User;

import java.sql.*;

public class JdbcUserDao implements UserDao {
    private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();

    private static String url = "jdbc:postgresql://192.168.1.102:5000/camstore";
    private static String user = "postgres";
    private static String password = "postgres";
    private static final String ADD_SQL = "INSERT INTO USERS (name, email, salt,  password) VALUES(?, ?, ?, ?);";
    private static final String FIND_USER_BY_NAME_SQL = "SELECT name, email, salt,  password FROM USERS WHERE name = ?";

    @Override
    public void add(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_SQL))
        {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getSalt());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("User is not created ", e);
        }
    }

    @Override
    public User findByName(String name) {
        User user = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_NAME_SQL)) {

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = USER_ROW_MAPPER.mapRow(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Not able to find user with name: " + name);
        }

        return user;
    }

    public boolean isUserExist(String name) {
        boolean isExist = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_NAME_SQL)) {

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isExist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Not able to find user with name: " + name);
        }

        return isExist;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
