package org.example.service;

import org.example.dao.UserDao;
import org.example.model.User;

public class UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User createUser(User user) {
        return userDao.save(user);
    }

    public User getUser(Long id) {
        return userDao.getById(id);
    }
}
