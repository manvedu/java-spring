package org.example.service;

import org.example.dao.UserDao;
import org.example.model.Ticket;
import org.example.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User createUser(Long id, String name, String email) {
        User user = new User(id, name, email);
        logger.info("UserService - createUser: " + user.getName());
        return userDao.save(user);
    }

    public User getUser(Long id) {
        logger.info("UserService - getUser by id: " + id);
        Optional<User> optionalUser = userDao.findById(id);
        return optionalUser.orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    @Autowired
    public void saveUser(User user) {
        userDao.save(user);
    }
}
