package com.luxoft.shop.dao.jdbc.mapper;

import com.luxoft.shop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper {

    public User mapRow(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String salt = resultSet.getString("salt");
        String password = resultSet.getString("password");

        User user = User.builder()
                .name(name)
                .email(email)
                .salt(salt)
                .password(password)
                .build();
        return  user;
    }
}
