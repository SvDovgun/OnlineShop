package com.luxoft.shop.service;

import com.luxoft.shop.dao.UserDao;
import com.luxoft.shop.entity.User;

public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(User user) {
        userDao.add(user);
    }

    public boolean isUserExist(String name) {
        boolean isExist = userDao.isUserExist(name);
        return isExist;

    }

    public User findUserByName(String name) {
        User user = userDao.findByName(name);
        return user;
    }
}
