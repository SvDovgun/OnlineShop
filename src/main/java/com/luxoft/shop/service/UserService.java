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

    public void checkUserOnValidation(User user) {
        userDao.findByEmail(user.getEmail());

    }
}
