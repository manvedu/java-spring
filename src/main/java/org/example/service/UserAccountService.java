package com.example.service;

import org.example.dao.UserAccountDao;
import org.example.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountDao userAccountDao;

    @Transactional
    public void refillAccount(Long userId, double amount) {
        UserAccount account = userAccountDao.findByUserId(userId);
        account.setBalance(account.getBalance() + amount);
        userAccountDao.save(account);
    }

    public UserAccount getAccount(Long userId) {
        return userAccountDao.findByUserId(userId);
    }
}