package com.luxoft.shop.dao;

import com.luxoft.shop.entity.User;

public interface UserDao {

    void add(User user);

    User findByName(String user);

    boolean isUserExist(String name);
}
